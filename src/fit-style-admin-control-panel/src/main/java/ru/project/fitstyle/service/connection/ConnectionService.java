package ru.project.fitstyle.service.connection;

import ru.project.fitstyle.exception.ConnectionException;
import ru.project.fitstyle.exception.UnauthorizedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

public class ConnectionService {

    private static ConnectionService instance;

    private ConnectionService() {
    }

    public static ConnectionService getInstance () {
        if (instance == null) {
            instance = new ConnectionService();
        }
        return instance;
    }

    public String sendGet(HttpURLConnection con) {
        try {
            return getResponse(con);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConnectionException("Something gone wrong!");
        }
    }

    public String sendPost(HttpURLConnection con, String what) {
        try {
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = what.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            return getResponse(con);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConnectionException("Something gone wrong!");
        }
    }

    private String getResponse(HttpURLConnection con) throws IOException {
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            validateAuth(con);

            return response.toString();
        }
    }

    private void validateAuth(HttpURLConnection connection) throws IOException {
        if(connection.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {
            throw new UnauthorizedException("User is not authorized anymore!");
        }
    }
}