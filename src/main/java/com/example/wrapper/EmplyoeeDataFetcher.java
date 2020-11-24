package com.example.wrapper;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import static com.example.wrapper.restAccess.fetchDataFromRestServer;

import java.net.URL;

@Component
public class EmplyoeeDataFetcher implements DataFetcher<emplyoee>{
    private repository repository;
    private static Logger logger = LoggerFactory.getLogger(EmplyoeeDataFetcher.class);

    @Autowired
    public void EmployeeDataFetcher(repository repository) {
        this.repository=repository;
    }

    @Override
    public emplyoee get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = dataFetchingEnvironment.getArgument("id");
        return repository.findById(id).orElse(getFromRest(dataFetchingEnvironment.getArgument("id")));
    }

    private emplyoee getFromRest(String id) {
        emplyoee e = null;
        try {
            String data = fetchDataFromRestServer(new URL("http://dummy.restapiexample.com/api/v1/employee/" + id));
            JSONObject jsonObject = new JSONObject(data);
            JSONObject empJson = jsonObject.getJSONObject("data");
             e = new emplyoee(empJson.getString("id"), empJson.getString("employee_name"),
                    empJson.getString("employee_salary"), empJson.getInt("employee_age"),
                    empJson.getString("profile_image"));
            repository.save(e);
        } catch (Exception ex) {
            logger.info(ex.toString());
        }
        return e;
    }

}
