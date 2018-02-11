package CoreModule;

/**
 * This Interface is used for forming the ASTtrees for the given program sequences

 *
 * @author      Team 204
 * @version     1.0
 * @since       1.0
 *
 */
public interface ASTCore {

    /**
     * This function forms the AST tree for the given code sequence.
     * @return The ASTTree structure given out by the module.
     */
    ASTTree getTree();
}
