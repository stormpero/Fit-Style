package ru.project.fitstyle.service.connection;

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

    public String send(HttpURLConnection con) throws UnauthorizedException, IOException {
        return send(con, null);
    }

    public String send(HttpURLConnection con, String what) throws UnauthorizedException, IOException {
        String req = con.getRequestMethod();
        if(req.equals("POST")) {
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = what.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
        }

        return getResponse(con);
    }

    private String getResponse(HttpURLConnection con) throws UnauthorizedException, IOException {
        validateAuth(con);
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }


            return response.toString();
        }
    }

    private void validateAuth(HttpURLConnection connection) throws UnauthorizedException, IOException {
        if(connection.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {
            throw new UnauthorizedException("User is not authorized anymore!");
        }
    }
}