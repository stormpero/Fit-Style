package ru.project.fitstyle.panel.tabs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.project.fitstyle.exception.UnauthorizedException;
import ru.project.fitstyle.json.response.NewsResponse;
import ru.project.fitstyle.panel.CustomJPanel;
import ru.project.fitstyle.service.connection.ConnectionBuilder;
import ru.project.fitstyle.service.connection.ConnectionService;
import ru.project.fitstyle.service.connection.ConnectionType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.HttpURLConnection;

public class TestTab extends CustomJPanel {

    private final DefaultTableModel model;

    public TestTab() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[][]{}, new String[] {"Идентификатор", "Заголовок", "Контент", "Дата"});
        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JScrollPane scrollPane = new JScrollPane(table);
        // Force the scrollbars to always be displayed
        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        add(scrollPane);
    }

    @Override
    public void update() throws UnauthorizedException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilder();
        ConnectionService connectionService = ConnectionService.getInstance();
        HttpURLConnection httpURLConnection = connectionBuilder.prepareRequestWithAuthHeader("/news/1");
        httpURLConnection = connectionBuilder.prepareRequest(httpURLConnection, ConnectionType.GET);
        String response = connectionService.sendGet(httpURLConnection);
        try {
            NewsResponse newsResponse = new ObjectMapper().readValue(response, NewsResponse.class);
            updatePanel(newsResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void updatePanel(NewsResponse newsResponse) {
        //Clear all data
        model.setRowCount(0);
        for(NewsResponse.News news : newsResponse.getNews()) {
            model.addRow(new Object[]{news.getId(), news.getHeader(), news.getContent(), news.getDateTime()});
        }
    }
}
