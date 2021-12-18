package ru.project.fitstyle.service.connection;

import ru.project.fitstyle.config.Url;
import ru.project.fitstyle.exception.ConnectionException;
import ru.project.fitstyle.exception.UnauthorizedException;
import ru.project.fitstyle.service.AuthInfoService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class ConnectionBuilder {

    public HttpURLConnection prepareGetRequest(HttpURLConnection con) {
        try {
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            return con;
        } catch (ProtocolException e) {
            e.printStackTrace();
            throw new ConnectionException("Something goes wrong!");
        }
    }

    public HttpURLConnection preparePostRequest(HttpURLConnection con) {
        try {
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            return con;
        } catch (ProtocolException e) {
            e.printStackTrace();
            throw new ConnectionException("Something goes wrong!");
        }
    }

    public HttpURLConnection prepareRequestWithAuthHeader(String url) throws UnauthorizedException{

        HttpURLConnection connection = null;
        try {
            connection = createConnection(Url.SERVER.getUrl() + url);
            connection.setRequestProperty("Authorization", createBasicAuthHeaderValue());

            return connection;
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConnectionException("Something gone wrong!");
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    public HttpURLConnection prepareRequestWithoutAuthHeader(String url) {

        HttpURLConnection connection = null;
        try {
            connection = createConnection(Url.SERVER.getUrl() + url);
            return connection;
        } catch(IOException e) {
            e.printStackTrace();
            throw new ConnectionException("Something gone wrong!");
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }



    private HttpURLConnection createConnection(String urlString) throws IOException {
        URL url = new URL(urlString);
        return (HttpURLConnection) url.openConnection();
    }

    private String createBasicAuthHeaderValue() {
        final AuthInfoService authInfoService = AuthInfoService.getInstance();
        return "Bearer " + authInfoService.getToken();
    }
}