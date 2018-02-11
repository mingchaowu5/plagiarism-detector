package CoreModule;

/**
 * This Interface is the starting point of the Core Plagiarism analysis, between the projects

 *
 * @author      Team 204
 * @version     1.0
 * @since       1.0
 *
 */
public interface Analyser {

    /**
     * This function invokes the comparision of the projects which are saved at the server.
     * Invoked by the UI modules via the Web Server at the server end
     */
    void computeProjects();
}
