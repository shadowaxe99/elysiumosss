package utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class NetworkManager {

    private static final String USER_AGENT = "Mozilla/5.0";

    public static String sendGetRequest(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            return readResponse(con);
        } else {
            throw new IOException("GET request not worked, Response Code: " + responseCode);
        }
    }

    public static String sendPostRequest(String url, Map<String, String> parameters) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setDoOutput(true);

        try (java.io.DataOutputStream wr = new java.io.DataOutputStream(con.getOutputStream())) {
            wr.writeBytes(ParameterStringBuilder.getParamsString(parameters));
            wr.flush();
        }

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            return readResponse(con);
        } else {
            throw new IOException("POST request not worked, Response Code: " + responseCode);
        }
    }

    private static String readResponse(HttpURLConnection con) throws IOException {
        try (java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        }
    }

    public static class ParameterStringBuilder {
        public static String getParamsString(Map<String, String> params) {
            StringBuilder result = new StringBuilder();

            for (Map.Entry<String, String> entry : params.entrySet()) {
                result.append(java.net.URLEncoder.encode(entry.getKey(), java.nio.charset.StandardCharsets.UTF_8));
                result.append("=");
                result.append(java.net.URLEncoder.encode(entry.getValue(), java.nio.charset.StandardCharsets.UTF_8));
                result.append("&");
            }

            String resultString = result.toString();
            return resultString.length() > 0
                    ? resultString.substring(0, resultString.length() - 1)
                    : resultString;
        }
    }
}