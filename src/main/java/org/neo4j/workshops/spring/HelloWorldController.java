package org.neo4j.workshops.spring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * The @RequestMapping annotation ensures that any GET requests to / will be
 * routed to the helloWorld function.
 *
 * The @RequestParam annotation bings the ?name query string parameter to
 * the name variable, defaulting it to World if no value is provided.
 *
 * The plain old java object returned by the method will be converted to
 * JSON by Spring.
 *
 */
@RestController
public class HelloWorldController
{

    private static final String template = "Hello, %s";

    @RequestMapping("/")
    public Greeting helloWorld(
            @RequestParam(value="name", defaultValue = "World") String name
    ) {
        // Format String
        String response = String.format(template, name);

        // Return a response
        return new Greeting( response );
    }

}
