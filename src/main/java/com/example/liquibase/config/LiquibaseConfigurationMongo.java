package com.example.liquibase.config;

import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.exception.DatabaseException;
import liquibase.ext.mongodb.database.MongoLiquibaseDatabase;
import liquibase.integration.spring.SpringResourceAccessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class LiquibaseConfigurationMongo implements CommandLineRunner,ResourceLoaderAware {

    @Value("${spring.data.mongodb.uri}")
    String dbUrl;

//    public final MongoLiquibaseDatabase database;

    protected ResourceLoader resourceLoader;

//    public LiquibaseConfigurationMongo(MongoLiquibaseDatabase database) {
//        this.database = database;
//    }

    public MongoLiquibaseDatabase database() throws DatabaseException {
        return (MongoLiquibaseDatabase) DatabaseFactory.getInstance().openDatabase(dbUrl,null,null,null,null);
    }
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
    this.resourceLoader=resourceLoader;
    }
    @Override
    public void run(String... args) throws Exception {
        Liquibase liquibase= new Liquibase("changelog/Changelog-master.xml",new SpringResourceAccessor(resourceLoader),database());
        liquibase.update("");
    }




}
