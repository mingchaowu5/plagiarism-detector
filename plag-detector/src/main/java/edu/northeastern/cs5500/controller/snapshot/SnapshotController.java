package edu.northeastern.cs5500.controller.snapshot;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import edu.northeastern.cs5500.models.semester.Semester;
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
	 * Get all the semester when an user logs in.
	 * @return	List: of all the semester in the database which have not been deleted
	 */
	@GetMapping(value = "/assignment")
	public ResponseEntity<List<Snapshot>> snapshotsByAssignment(@RequestParam(value = "id") int id) {
		List<Snapshot> snapshots = this.snapshotService.getAllSnapshotsForAssignment(id);
		return new ResponseEntity<>(snapshots, HttpStatus.OK);
	}
	
}
