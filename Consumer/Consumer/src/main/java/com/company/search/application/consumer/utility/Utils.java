package com.company.search.application.consumer.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URL;


@Configuration
public class Utils {

    private static final Logger log = LoggerFactory.getLogger(Utils.class);

    public Response clientConnectionMethod(String apiKey, URL url) throws IOException {
        log.info("Start connecting with TruProxyApi Cloud !!");
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().url(url).addHeader("x-api-key", apiKey).get().build();
        Response response = client.newCall(request).execute();
        log.info("Start returning response from TruProxyApi !!");
        return response;
    }

    public String getPrettifiedResponseData(Response response) {
        log.info("Convert Data to String !!");
        if (response.isSuccessful()) {
            try {
                ResponseBody responseBody = response.body();
                if (responseBody != null) {
                    String contentType = responseBody.contentType() != null ? responseBody.contentType().toString() : "";
                    if (contentType.contains("application/json")) {
                        String json = responseBody.string();
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        return gson.toJson(JsonParser.parseString(json));
                    } else {
                        log.info("Return Converted Data to String !!");
                        return responseBody.string();
                    }
                }
            } catch (Exception e) {
                log.error("Failure in parsing the JSON !!");
                e.printStackTrace();
            }
        }
        return null;
    }
}
