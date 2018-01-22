package org.neo4j.workshops.spring;

/**
 * Spring uses the Jackson JSON library to convert this generic class
 * into a JSON response.
 */
public class Greeting
{

    private final String response;

    public Greeting(String response) {
        this.response = response;
    }

    public String getContent() {
        return response;
    }
}
