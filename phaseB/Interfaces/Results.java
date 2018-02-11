package CoreModule;

/**
 * This Interface is used for fetching the final results by the UI modules and also Core program services

 *
 * @author      Team 204
 * @version     1.0
 * @since       1.0
 *
 */
public interface Results {

    /**
     * The function returns the final result after aggregating all the sub ASTtrees scores
     * @return The final score which is aggregated and normalized to percentage.
     */
    public double getResult();
}
