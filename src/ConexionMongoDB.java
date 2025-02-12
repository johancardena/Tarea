import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class ConexionMongoDB {
    private static final String URI = "mongodb://localhost:27017"; // Conexión local
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static MongoDatabase getDatabase() {
        if (database == null) {
            mongoClient = MongoClients.create(URI);
            database = mongoClient.getDatabase("Alquiler_Vehiculos");
        }
        return database;
    }
}
