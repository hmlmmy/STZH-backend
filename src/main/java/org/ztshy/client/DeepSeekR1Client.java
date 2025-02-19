package org.ztshy.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class DeepSeekR1Client {
    public static String getAnswer(String question) throws Exception {
        String apiUrl = "http://localhost:8000/api/generate"; // 替换为实际的模型API地址
        String encodedQuestion = URLEncoder.encode(question, "UTF-8");
        String fullUrl = apiUrl + "?question=" + encodedQuestion;

        URL url = new URL(fullUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        connection.disconnect();

        return response.toString();
    }
}
