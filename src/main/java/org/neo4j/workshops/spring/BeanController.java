package org.neo4j.workshops.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.types.Node;

@RestController
public class BeanController
{

    @Autowired
    Driver neo4j;

    @RequestMapping("/beans")
    public List<Map> getIndex() {
        try ( Session session = neo4j.session() ) {
            return session.readTransaction( tx -> {
                StatementResult result = tx.run("MATCH (n) RETURN n LIMIT 20");

                return result.list(record -> {
                    return record.get("n").asMap();
                } );
            } );
        }

    }

}
