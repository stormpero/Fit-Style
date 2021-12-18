package ru.project.fitstyle.tabs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.project.fitstyle.exception.UnauthorizedException;
import ru.project.fitstyle.json.response.NewsResponse;
import ru.project.fitstyle.service.connection.ConnectionBuilder;
import ru.project.fitstyle.service.connection.ConnectionService;

import javax.swing.*;
import java.awt.*;
import java.net.HttpURLConnection;

public class TestTab extends JPanel {

    public JPanel createJPanel() throws UnauthorizedException {
        try {
            String response = getResponse();
            NewsResponse newsResponse = new ObjectMapper().readValue(response, NewsResponse.class);
            return createUI(newsResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getResponse() throws UnauthorizedException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilder();
        ConnectionService connectionService = ConnectionService.getInstance();
        HttpURLConnection httpURLConnection = connectionBuilder.prepareRequestWithAuthHeader("/news/1");
        httpURLConnection = connectionBuilder.prepareGetRequest(httpURLConnection);
        return connectionService.sendGet(httpURLConnection);
    }

    private JPanel createUI(NewsResponse newsResponse) {
        GridLayout gridLayout = new GridLayout();
        gridLayout.setRows(1);
        JPanel jPanel = new JPanel(gridLayout);
        for(NewsResponse.News news : newsResponse.getNews()) {
            jPanel.add(new JTextArea(news.getContent()));
        }

        return jPanel;
    }
}
