package plaCompare;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Checking similarity of documents
 *
 */
public class PlaCompare {
	//initial path of compared files
	private static final String FILE_PATH = "/Users/mc/Desktop/text1.txt";

	private static final String COMPARED_FILE_PATH = "/Users/mc/Desktop/text2.txt";

	private static final String RESULT_FILE_PATH = "/Users/mc/Desktop/result.txt";

	public static void main(String args[]){
		startComp();
	}
	public static void startComp() {
		System.out.println("======Start Search!=======");
		long startTime = System.currentTimeMillis();
		// Read first file
		File file = new File(FILE_PATH);
		File comparedFile = new File(COMPARED_FILE_PATH);
		BufferedReader br = null;
		BufferedReader cbr = null;
		BufferedWriter rbw = null;
		int simN = 0;
		int lineNum = 1;
		try {
			br = new BufferedReader(new FileReader(file));
			cbr = new BufferedReader(new FileReader(comparedFile));
			cbr.mark(90000000);
			rbw = new BufferedWriter(new FileWriter(RESULT_FILE_PATH));
			String lineText = null;
			while ((lineText = br.readLine()) != null) {
				String searchText = lineText.trim();
				simN = searchAndSignProcess(searchText, cbr, rbw, simN, lineNum);
				lineNum++;

			}
			String lineStr = 100 * (float) simN / (float) lineNum + "%" + " are similar" + "\n";
			rbw.write(lineStr);
			long endTime = System.currentTimeMillis();
			System.out.println("======Process Over!=======");
			System.out.println("Time Cost:" + ((endTime - startTime) / 1000D) + "s");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (cbr != null && rbw != null) {
						try {
							cbr.close();
							rbw.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	// just template ,line for here can regard as expression(which includes variables, statements and nodes )
	// compare more logic words and can use tf idf or consine similarity
	/**
	 * Finding the similarity for each line
	 * @param searchText
	 * @param comparedReader
	 * @param rbw
	 * @param simNum
	 * @param lineNum
	 * @return
	 * @throws IOException
	 */
	public static int searchAndSignProcess(String searchText, BufferedReader comparedReader, BufferedWriter rbw,
			int simNum, int lineNum) throws IOException {
		String lineStr = "-\n";
		if (searchText == null) {
			return simNum;
		}
		if ("".equals(searchText)) {
			rbw.write(lineStr);
			return simNum;
		}
		String lineText = null;
		int linenum = 1;

		String[] st = searchText.split(" ");
		while ((lineText = comparedReader.readLine()) != null) {

			String comparedLine = lineText.trim();
			String[] cl = comparedLine.split(" ");
			float lineWordNum = st.length;
			float simWordNum = 0;
			for (int i = 0; i < st.length; i++) {
				//System.out.println(cl.length);
				for (int j = 0; j < cl.length; j++) {
					if (st[i].equals(cl[j])) {
						simWordNum++;
						break;
					}
				}
			}
			if (simWordNum / lineWordNum > 0.9) {
				lineStr = "Line" + lineNum + "in file1 Equals to line:" + linenum + " in file2 =###\n";
				simNum++;
				rbw.write(lineStr);
				break;
			}
			

			linenum++;
		}

		comparedReader.reset();
		return simNum;
	}

}
