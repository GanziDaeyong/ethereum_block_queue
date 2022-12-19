package org.example;


import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CrawlJob implements Job {

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        System.out.println("JobExecuted");
        crawl();

    }

    private void crawl() {
        String blockno = "F6B1B3";
        String apitoken = "password";
        String etherscanUrl = String.format("https://api.etherscan.io/api?module=proxy&action=eth_getBlockByNumber&tag=%s&boolean=true&apikey=%s", blockno, apitoken);

        String res = "";
        try {
            HttpGet httpGet = new HttpGet(etherscanUrl);
            httpGet.setHeader("Accept", "application/json");

            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            CloseableHttpResponse response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {

                ResponseHandler<String> handler = new BasicResponseHandler();
                res = handler.handleResponse(response);
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(res);
                DataFormatter(jsonObject);
                System.out.println("Saved to jsonobj");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void DataFormatter(JSONObject jsonObject) {
        try{
            FileWriter file = new FileWriter("./j.json");
            file.write(jsonObject.toJSONString());
            file.flush();
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void QueueInserter() {

    }
}
