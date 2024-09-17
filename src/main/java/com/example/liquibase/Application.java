package com.example.liquibase;

import com.example.liquibase.config.LiquibaseConfigurationMongo;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(LiquibaseConfigurationMongo.class)
                .web(WebApplicationType.NONE)
                        .run(args);
        System.out.println("hello world");
    }
}
