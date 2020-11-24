package com.example.wrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionResult;


@RequestMapping("/create")
@RestController
public class controller {
    private static Logger logger = LoggerFactory.getLogger(controller.class);

    private GraphQLService graphQLService;

    @Autowired
    public controller(GraphQLService graphQLService) {
        this.graphQLService=graphQLService;
    }

    @PostMapping
    public ResponseEntity<Object> getAllEmplyoees(@RequestBody String query){
        logger.info("Entering getAllEmplyoee@EmplyoeeController");
        ExecutionResult execute = graphQLService.getGraphQL().execute(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }

}
