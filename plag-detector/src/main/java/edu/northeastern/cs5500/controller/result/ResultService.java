package edu.northeastern.cs5500.controller.result;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5500.models.results.FileResults;
import edu.northeastern.cs5500.models.results.StudentResults;
import edu.northeastern.cs5500.dao.ResultsDao;
import edu.northeastern.cs5500.dao.StudentDao;
import edu.northeastern.cs5500.models.extras.Edge;
import edu.northeastern.cs5500.models.extras.Graph;
import edu.northeastern.cs5500.models.extras.Node;
import edu.northeastern.cs5500.models.file.FilePath;
import edu.northeastern.cs5500.models.file.FileResult;
import edu.northeastern.cs5500.models.file.FileStructure;
import jplag.ExitException;
import jplag.Program;
import jplag.options.CommandLineOptions;
import edu.northeastern.cs5500.models.person.Student;

@Service
public class ResultService {

	@Autowired
	private ResultsDao resultsDao;	
	
	@Autowired
	private StudentDao studentDao;	
	
	private String path = "/Users/takyon/Documents/homework2/assignment/";
	private String tempPath = "/Users/takyon/Documents/homework2/temp/";
	private String resultPath = "/Users/takyon/Documents/homework2/result/";
	private int assignmentId;
	private Map<String, Integer> fileUser = new HashMap<>();
	private Map<String, Map<String, Double>> map = new HashMap<>();
	private Map<String, Integer> fileIdMap = new HashMap<>();
	private Map<String, String> fileNames = new HashMap<>();

	/**
	 * Connect to the database layer and get the semesters
	 * @return
	 */

	public Graph getResultForStudent(int id) {
		Graph graph = new Graph();
		Set<Integer> tIds = new HashSet<>();
		List<Node> nodes = new ArrayList<>();
		List<Edge> edges = new ArrayList<>();
		List<Student> students = new ArrayList<>();//studentDao.findAllStudentsForAssignment(id);
		for(Student s : students) {
			tIds.add(s.getId());
			Node node = new Node();
			node.setLabel(s.getEmail());
			node.setId(s.getId());
			int studAssignId = 0;//studentDao.findStudentAssignmentId(s.getId(), id);
			List<StudentResults> sResults = resultsDao.findAllStudentResultsForStudentAssignment(studAssignId);
			int i = 0;
			for(StudentResults sr : sResults) {
				i += sr.getResult();
				int tempId = sr.getStudentAssignment2();
				if(sr.getStudentAssignment1() != studAssignId) {
					tempId = sr.getStudentAssignment1();
				}
				tempId = 0;//studentDao.findStudentIdFromStudentAssignment(tempId);
				if(!tIds.contains(tempId)) {
					Edge edge = new Edge();
					edge.setFrom(s.getId());
					edge.setTo(tempId);
					edge.setValue(sr.getResult());
					edges.add(edge);
				}
			}
			node.setValue(i);
			node.setLabel(s.getFirstName());
			nodes.add(node);
		}
		graph.setNodes(nodes);
		graph.setEdges(edges);
		saveFileData(id);
		return graph;
	}
	
	public List<FileResult> getFileResultsForStudents(int sid1, int sid2, int aid) {
		List<FileStructure> files = new ArrayList<>();//studentDao.findAllFileStructuresStudent(sid1, aid);
		List<FileStructure> temp = new ArrayList<>();//studentDao.findAllFileStructuresStudent(sid2, aid);
		Set<Integer> fileS2 = new HashSet<>();
		for(FileStructure fs : temp) {
			fileS2.add(fs.getId());
		}
		List<FileResult> results = new ArrayList<>();
		
		for(FileStructure fr : files) {
			FilePath path = resultsDao.findFileDetailsForId(fr.getId());
			List<FileResults> list = resultsDao.findAllFileResultsForStudentAssignment(fr.getId());
			for(FileResults fs : list) {
				if(fileS2.contains(fs.getStudentAssignmentFile1()) || fileS2.contains(fs.getStudentAssignmentFile2())) {
					FileResult result = new FileResult();
					result.setFile1(this.resultPath + path.getFile());
					result.setId1(fr.getId());
					result.setName1(path.getName());
					int id = fs.getStudentAssignmentFile1();
					if(fr.getId() == fs.getStudentAssignmentFile1())
						id = fs.getStudentAssignmentFile2();
					FilePath tPath = resultsDao.findFileDetailsForId(id);
					result.setId2(id);
					result.setName2(tPath.getName());
					result.setFile2(this.resultPath+tPath.getFile());
					result.setValue(fs.getResult());
					results.add(result);
				}
			}
		}
		return results;
	}
	
	private void saveFileData(int aid) {
		this.fetchFileMatching(aid);
		for(Entry<String, Map<String, Double>> entry : map.entrySet()) {
			String key = entry.getKey();
			Map<String, Double> files = map.get(key);
			for(Entry<String, Double> fEntry : files.entrySet()) {
				String fKey = fEntry.getKey();	
				resultsDao.addFileResults(fileIdMap.get(key), fileIdMap.get(fKey), (int)Math.round(files.get(fKey)));
			}
		}
	}
	
	private void fetchFileMatching(int aid) {
		List<Student> students = new ArrayList<>();//studentDao.findAllStudentsForAssignment(aid);
		for(Student s : students) {
			int sid = s.getId();
			List<FileStructure> files = new ArrayList<>();//studentDao.findAllFileStructuresStudent(sid, aid);		
			for(FileStructure fs : files) {
				String key = sid + "-" + fs.getFile();
				fileIdMap.put(key, fs.getId());
				if(this.fileNames.containsKey(key))
					resultsDao.addFilePaths(fs.getId(), this.fileNames.get(key));
			}
		}
	}
	
	public void run(int id) throws IOException{
		this.assignmentId = id;
		List<Student> students = new ArrayList<>();//studentDao.findAllStudentsForAssignment(id);
		int[] ids = new int[students.size()];
		int i = 0;
		for(Student s : students) {
			ids[i++] = s.getId();
		}
		delete();
		findFiles(ids);
		double[][] results = getPlagResults(ids);
		this.readResults(results, ids);
	}
	
	private void findFiles(int[] ids) throws IOException{
		for(int i = 0;i<ids.length;++i) {
			String newPath = path + this.assignmentId + "/" + ids[i];
			Set<String> files = new HashSet<>();
			File f = new File(newPath);
			List<File> subdirs = this.getSubdirs(f);
			subdirs.add(f);
			for(File file : subdirs) {
				File[] allFiles = file.listFiles();
				if(allFiles == null || allFiles.length == 0)
					continue;
				for(File aF : allFiles) {
					if(!aF.isDirectory()) {
						files.add(ids[i] + "-" + aF.getName());
						//studentDao.addFileForStudentAssignment(ids[i], this.assignmentId, aF.getName());
						fileUser.put(ids[i] + "-" + aF.getName(), i);
						this.copyFile(aF, ids[i]);
					}
				}
			}
		}
	}
	
	private void delete() {
		File f = new File(tempPath);
		if(!f.exists()) {
			return;
		}
		File[] files = f.listFiles();
		for(File temp : files) {
			temp.delete();
		}
	}
	
	private void copyFile(File source, int stu) throws IOException {
		File folder = new File(tempPath);
		if(!folder.exists()) {
    			folder.mkdir();
    		}
		Files.copy(Paths.get(source.getAbsolutePath()), Paths.get(tempPath + stu + "-" +source.getName()),
                StandardCopyOption.REPLACE_EXISTING);
	}
	
	private List<File> getSubdirs(File file) {
		if(!file.exists() || file == null)
			return new ArrayList<>();
	    List<File> subdirs = Arrays.asList(file.listFiles(new FileFilter() {
	        public boolean accept(File f) {
	            return f.isDirectory();
	        }
	    }));
	    
	    subdirs = new ArrayList<File>(subdirs);
	    
	    List<File> deepSubdirs = new ArrayList<File>();
	    for(File subdir : subdirs) {
	        deepSubdirs.addAll(getSubdirs(subdir));  
	    }
	    subdirs.addAll(deepSubdirs);
	    return subdirs;
	}
	
	private double[][] getPlagResults(int ids[]) throws IOException{
		double[][] result = new double[ids.length][ids.length];
		int len = ids.length;
		for(int i = 0;i<len;++i) {
			for(int j = 0;j<len;++j) {
				result[i][j] = 0.0;
			}
		}
		String[] tm = {tempPath, "-l", "python3", "-r", resultPath, "-s"};
		/*CommandLineOptions op;
		try {
			op = new CommandLineOptions(tm);
			Program p = new Program(op);
			p.run();
			this.getResults(result);
		} catch (ExitException e) {
			e.printStackTrace();
		}*/
		return result;
	}
	
	private void readResults(double[][] userResult, int[] ids) {
		for(int i = 0;i<userResult.length;++i) {
			for(int j = 0;j<userResult.length;++j) {
				if(i != j && userResult[i][j] > 0.0) {
					this.resultsDao.addStudentResults(this.getStudentAssignmentId(ids[i]), this.getStudentAssignmentId(ids[j]), (int)Math.round(userResult[i][j]));
				}
			}
		}
	}
	
	private int getStudentAssignmentId(int id) {
		return 0;//studentDao.findStudentAssignmentId(id, assignmentId);
	}
	
	private void getResults(double[][] userResult) throws IOException {
		File input = new File(resultPath + "/index.html");
		Document doc = Jsoup.parse(input, "UTF-8");
		Element table = doc.select("table").get(2); //select the first table.
		Elements rows = table.select("tr");
		for (int i = 0; i < rows.size(); i++) { //first row is the col names so skip it.
		    Element row = rows.get(i);
		    Elements cols = row.select("td");
		    String key = cols.get(0).text();
		    Map<String, Double> tMap = new HashMap<>();
		    fileNames.put(key, "match" + i + "-0" + ".html");
		    for(int j = 2;j<cols.size();++j) {
		    		String filename = cols.get(j).select("a").text();
		    		String per = cols.get(j).select("font").text();
		    		double d = Double.parseDouble(per.substring(1, per.length() - 2));
		    		userResult[fileUser.get(key)][fileUser.get(filename)] += d;
		    		tMap.put(filename, d);
		    		fileNames.put(filename, "match" + i + "-" + (j-1) + ".html");
		    }
		    map.put(key, tMap);
		}
	}
	
	
	
}
