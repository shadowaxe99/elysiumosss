package elysiumos.src.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class DatabaseManager {
    private MongoClient mongoClient;
    private MongoDatabase database;

    public DatabaseManager(String uri, String dbName) {
        MongoClientURI clientURI = new MongoClientURI(uri);
        this.mongoClient = new MongoClient(clientURI);
        this.database = mongoClient.getDatabase(dbName);
    }

    public MongoCollection<Document> getCollection(String collectionName) {
        return database.getCollection(collectionName);
    }

    public void insertDocument(String collectionName, Document document) {
        MongoCollection<Document> collection = getCollection(collectionName);
        collection.insertOne(document);
    }

    public Document findDocument(String collectionName, Document query) {
        MongoCollection<Document> collection = getCollection(collectionName);
        return collection.find(query).first();
    }

    public void updateDocument(String collectionName, Document query, Document update) {
        MongoCollection<Document> collection = getCollection(collectionName);
        collection.updateOne(query, new Document("$set", update));
    }

    public void deleteDocument(String collectionName, Document query) {
        MongoCollection<Document> collection = getCollection(collectionName);
        collection.deleteOne(query);
    }

    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}