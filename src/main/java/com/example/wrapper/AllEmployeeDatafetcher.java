package com.example.wrapper;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import static com.example.wrapper.restAccess.fetchDataFromRestServer;

import java.net.URL;
import java.util.List;

@Component
public class AllEmployeeDatafetcher implements DataFetcher<List<emplyoee>> {
    private repository repository;
    private static Logger logger = LoggerFactory.getLogger(AllEmployeeDatafetcher.class);

    @Autowired
    public void AllEmployeeDataFetcher(repository repository) {
        this.repository = repository;
    }

    @Override
    public List<emplyoee> get(DataFetchingEnvironment dataFetchingEnvironment) {
        List<emplyoee> ls = repository.findAll();
        if (ls.size() <= 0) {
            try {
                String data = fetchDataFromRestServer(new URL("http://dummy.restapiexample.com/api/v1/employees"));
                JSONObject jsonObject = new JSONObject(data);
                JSONArray arr = jsonObject.getJSONArray("data");
                for (int i = 0; i < arr.length(); i++) {
                    emplyoee e = new emplyoee(arr.getJSONObject(i).getString("id"), arr.getJSONObject(i).getString("employee_name"),
                            arr.getJSONObject(i).getString("employee_salary"), arr.getJSONObject(i).getInt("employee_age"),
                            arr.getJSONObject(i).getString("profile_image"));
                    ls.add(e);
                    repository.save(e);
                }
            } catch (Exception e) {
                logger.info(e.toString());
            }
        }
        return ls;
    }

}
