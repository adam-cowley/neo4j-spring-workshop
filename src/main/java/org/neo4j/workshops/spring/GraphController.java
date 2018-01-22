package org.neo4j.workshops.spring;

import org.springframework.web.bind.annotation.RestController;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;


@RestController
public class GraphController
{

    private final Driver driver;

    /**
     * On instantiation, create a driver connection to Neo4j.
     *
     * Note: There are more sophisticated ways of getting database credentials.
     */
    public GraphController() {
        this.driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic( "neo4j", "neo" ));
    }



}
