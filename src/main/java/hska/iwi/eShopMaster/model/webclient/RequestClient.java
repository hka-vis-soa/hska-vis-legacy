package hska.iwi.eShopMaster.model.webclient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class RequestClient {

    /**
     * Execute a http request for a given http method against the target url
     * @param method HttpMethod.GET | HttpMethod.POST | HttpMethod.DELETE
     * @param targetURL the url you want to send the request to
     * @param parameters form: ?key1=value1&key2=value2
     * @param body form: {'key1': 'value1', 'key2': 'value2'}
     * @return the request body for a successful request or null
     */
    public String execute(HttpMethod method, String targetURL, String parameters, String body) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method.toString());
            String contentType = "text/plain";
            String contentLength = "0";
            if(parameters != null) {
                contentType = "application/x-www-form-urlencoded";
                contentLength = Integer.toString(parameters.getBytes().length);
            }
            if(body != null) {
                contentType = "application/json";
                contentLength = Integer.toString(body.getBytes().length);
            }
            connection.setRequestProperty("Content-Type", contentType);
            connection.setRequestProperty("Content-Length", contentLength);
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            sendRequest(connection, parameters, body);
            return receiveRequest(connection);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private void sendRequest(HttpURLConnection connection, String parameters, String body) throws IOException {
        DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
        if(parameters != null) {
            wr.writeBytes(parameters);
        }
        if(body != null) {
            wr.writeBytes(body);
        }
        wr.close();
    }

    private String receiveRequest(HttpURLConnection connection) {
        try {
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
