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
     * @param body form: {"key1": "value1", "key2": "value2"}
     * @return the request body for a successful request or null
     */
    public Response execute(HttpMethod method, String targetURL, String body) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method.toString());
            if(body != null) {
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Content-Length", Integer.toString(body.getBytes().length));
            }
            connection.setUseCaches(false);
            if(method == HttpMethod.POST) {
                connection.setDoOutput(true);
                appendBodyToRequest(connection, body);
            }
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

    private void appendBodyToRequest(HttpURLConnection connection, String body) throws IOException {
        DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
        wr.writeBytes(body);
        wr.close();
    }

    private Response receiveRequest(HttpURLConnection connection) {
        Response httpResponse = null;
        try {
            int httpStatus = connection.getResponseCode();
            InputStream is = (httpStatus > 99 && httpStatus < 400) ? connection.getInputStream() : connection.getErrorStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            httpResponse = new Response(httpStatus, response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpResponse;
    }
}
