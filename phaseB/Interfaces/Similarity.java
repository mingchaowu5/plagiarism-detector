package CoreModule;

/**
 * This Interface is used for initiating the main comparision for the AST trees.
 * Called by Core module functions.
 *
 * @author      Team 204
 * @version     1.0
 * @since       1.0
 *
 */
public interface Similarity {

    /**
     * This function starts the comparision of the AST Tree, which in turn invokes the sub comparision
     * This also invokes the Result  functions
     */
    void compute();

}
