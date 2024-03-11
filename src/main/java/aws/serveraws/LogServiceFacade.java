package aws.serveraws;

import static spark.Spark.get;
import static spark.Spark.port;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 *
 * @author Juan Cepeda
 */

public class LogServiceFacade {
    private static final Logger logger = LoggerFactory.getLogger(LogServiceFacade.class);

    public static void main(String[] args) {
        port(getPort());
        get("/logServiceFacade", (req, res) -> saveAndGetLogs(req.queryParams("message")));
    }

    /**
     * Save a message and return the latest 10 messages.
     *
     * @param message message to save
     * @return the latest 10 messages
     */
    private static String saveAndGetLogs(String message) {
        try {
            Mongo db = new Mongo();
            db.addLog(message);
            return db.getLogs().toString();
        } catch (Exception e) {
            logger.error("Error saving or retrieving logs ", e);
            return "Error saving or retrieving logs.";
        }

    }

    /**
     *
     * @return the port to use
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4568;
    }

}
