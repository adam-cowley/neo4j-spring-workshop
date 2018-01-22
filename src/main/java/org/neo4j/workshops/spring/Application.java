package org.neo4j.workshops.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class makes the application executable.
 */
@SpringBootApplication
public class Application
{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
