package com.jumpydoll.pkmnnerd.javatutorialproject;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDbConnection {
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    public void connect(String uri) {
        ConnectionString connectionString = new ConnectionString(uri);
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();
        this.mongoClient = MongoClients.create(clientSettings);
        //this.mongoClient = MongoClients.create(uri);
        this.mongoDatabase = mongoClient.getDatabase("todoItems");
    }



    public void close() {
        mongoClient.close();
    }
}
