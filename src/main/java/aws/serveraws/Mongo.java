package aws.serveraws;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.ConnectionString;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;

/**
 * 
 * @author Daniel Santanilla
 */
public class Mongo {

    private static final String CONNECTION_STRING = "mongodb://mongodb:27017/";
    private static final String DATABASE_NAME = "mongodb";
    private static final Logger LOGGER = LoggerFactory.getLogger(Mongo.class);

    /**
     * Adds a new Log to the database.
     *
     * @param message the Log message
     */
    public void addLog(String message) {
        try (MongoClient client = MongoClients.create(new ConnectionString(CONNECTION_STRING))) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection("logs");
            Document newLog = new Document("message", message).append("createDay", LocalDateTime.now());
            collection.insertOne(newLog);
            LOGGER.info("Added log: {}", newLog);
        } catch (MongoException e) {
            LOGGER.error("Error adding log: {}", e.getMessage());
        }
    }

    /**
     * return the latest 10 log entries from the database.
     *
     * @return a list of log entries as strings
     */
    public List<String> getLogs() {
        List<String> logs = new ArrayList<>();
        try (MongoClient client = MongoClients.create(new ConnectionString(CONNECTION_STRING))) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection("logs");
            collection.find().sort(Sorts.descending("_id")).limit(10)
                    .forEach(log -> logs.add(log.toJson()));
        } catch (MongoException e) {
            LOGGER.error("Error retrieving logs: {}", e.getMessage());
        }
        return logs;
    }
}
