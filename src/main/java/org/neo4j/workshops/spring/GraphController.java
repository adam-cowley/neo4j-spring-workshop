package org.neo4j.workshops.spring;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.types.Node;

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

    /**
     * When the server receives a GET request to /people, create a new session within the driver,
     * run a read transaction and return an ArrayList of properties for each node.
     */
    @RequestMapping("/people")
    public ArrayList< Map<String, Object> > index() {
        ArrayList< Map<String, Object> > output = new ArrayList<>();

        try ( Session session = driver.session() ) {
            session.readTransaction( (tx) -> {
                StatementResult result = tx.run("MATCH (p:Person) RETURN p");

                while (result.hasNext()) {
                    Record record = result.next();

                    output.add( record.get("p").asMap() );
                }

                return tx;
            } );
        }

        return output;
    }

    /**
     * When the server receives a POST request to /people, run a write transaction to
     * create a Person node with the name sent in request body.
     */
    @RequestMapping(method = RequestMethod.POST, path ="/people")
    public Map<String, Object> postPeople(
            @RequestBody Map<String, Object> params
    ) {
        try ( Session session = driver.session() ) {
            Node created = session.writeTransaction( tx -> {
                StatementResult result = tx.run("CREATE (p:Person {name: {name} }) RETURN p", params);

                return result.single().get("p").asNode();
            } );

            return created.asMap();
        }
    }

}