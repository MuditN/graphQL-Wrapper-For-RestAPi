package com.example.wrapper;

import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {
    private static Logger logger = LoggerFactory.getLogger(GraphQLService.class);
    
    private AllEmployeeDatafetcher allEmployeeDatafetcher;
    private EmplyoeeDataFetcher emplyoeeDataFetcher;

    @Value("classpath:emp.graphql")
    Resource resource;
    repository repo;
    private GraphQL graphql;

    public GraphQLService(repository repo,AllEmployeeDatafetcher allEmployeeDatafetcher, EmplyoeeDataFetcher emplyoeeDataFetcher){
    
        this.allEmployeeDatafetcher = allEmployeeDatafetcher;
        this.emplyoeeDataFetcher = emplyoeeDataFetcher;
        this.repo = repo;
    }

    @PostConstruct
    private void loadSchema() throws IOException{
        logger.info("Entering loadSchema@GraphQLService");
        File file = resource.getFile();

        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(file);
        RuntimeWiring runtimeWiring = buildRuntimeWiring();

        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        graphql = GraphQL.newGraphQL(graphQLSchema).build();
        
    }



    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                .dataFetcher("allEmployee", allEmployeeDatafetcher)
                .dataFetcher("employee", emplyoeeDataFetcher)).build();
                
    }
    public GraphQL getGraphQL(){
        return graphql;
    }
}
