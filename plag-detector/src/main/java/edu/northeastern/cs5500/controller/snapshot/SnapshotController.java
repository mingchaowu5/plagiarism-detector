package edu.northeastern.cs5500.controller.snapshot;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5500.models.extras.Graph;
import edu.northeastern.cs5500.models.results.Result;
import edu.northeastern.cs5500.models.snapshot.Snapshot;

/**
 * Semester Controller Class, 
 * handle semester related operations.
 * @author takyon
 *
 */
@RestController
@RequestMapping("/rest/snapshot")
public class SnapshotController {

	@Autowired
	private SnapshotService snapshotService;
	
	/**
	 * Get all the snapshots for an assignment
	 * @return	List: of all the snapshots
	 */
	@GetMapping(value = "/assignment")
	public ResponseEntity<List<Snapshot>> snapshotsByAssignment(@RequestParam(value = "id") int id) {
		List<Snapshot> snapshots = this.snapshotService.getAllSnapshotsForAssignment(id);
		return new ResponseEntity<>(snapshots, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param id1
	 * @param id2
	 * @return
	 */
	@GetMapping(value = "/submission")
	public ResponseEntity<List<Result>> getResult(@RequestParam(value = "id1") int id1, @RequestParam(value = "id2") int id2){
		return new ResponseEntity<>(this.snapshotService.getResults(id1, id2), HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/result")
	public ResponseEntity<Graph> getResultForSnapshot(@RequestParam(value = "id") int id){
		return new ResponseEntity<>(this.snapshotService.getResultsForSnapshot(id), HttpStatus.OK);
	}	
	
	/**
	 * Queue the newly given query
	 * @param assignmentId
	 * @param professorId
	 * @return
	 */
	@GetMapping(value = "/run")
	public ResponseEntity<Boolean> runFreshSnapshotForAssignment(@RequestParam(value = "assignment_id") int assignmentId, @RequestParam(value = "professor_id")int professorId){
		return new ResponseEntity<>(this.snapshotService.runSnapshotForAssignment(assignmentId, professorId), HttpStatus.OK);
	}
	
	/**
	 * Get the file details
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/file")
	public ResponseEntity<Result> file(@RequestParam(value = "id") int id){
		return new ResponseEntity<>(this.snapshotService.getFiles(id), HttpStatus.OK);
	}
	
	/**
	 * Get all the manual snapshots which have been called for multiple submissions.
	 * @return
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<List<Snapshot>> allSnapshots() {
		List<Snapshot> snapshots = this.snapshotService.getAllManualSnapshots();
		return new ResponseEntity<>(snapshots, HttpStatus.OK);
	}
}
