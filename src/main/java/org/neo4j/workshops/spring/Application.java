package org.neo4j.workshops.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;

/**
 * This class makes the application executable.
 */
@SpringBootApplication
public class Application
{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public Driver neo4jDriver() {
        return GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic( "neo4j", "neo" ));
    }

}
