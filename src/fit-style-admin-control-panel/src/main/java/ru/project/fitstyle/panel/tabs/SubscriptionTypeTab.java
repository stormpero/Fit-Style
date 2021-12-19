package ru.project.fitstyle.panel.tabs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.project.fitstyle.config.Url;
import ru.project.fitstyle.exception.UnauthorizedException;
import ru.project.fitstyle.json.post.AddSubscriptionTypeRequest;
import ru.project.fitstyle.json.response.AllSubscriptionTypeResponse;
import ru.project.fitstyle.panel.CustomJPanel;
import ru.project.fitstyle.service.connection.ConnectionBuilder;
import ru.project.fitstyle.service.connection.ConnectionService;
import ru.project.fitstyle.service.connection.ConnectionType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;

public class SubscriptionTypeTab extends CustomJPanel {

    private final ConnectionService connectionService = ConnectionService.getInstance();

    private final DefaultTableModel model;

    public SubscriptionTypeTab() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[][]{}, new String[] {"Идентификатор", "Цена", "Наименование", "Время посещения", "Срок(месяцы)"});
        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        // Force the scrollbars to always be displayed
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


        JButton addButton = new JButton("Добавить");

        JTextField costText = new JTextField();
        JTextField nameText = new JTextField();
        JTextField placementTimeText = new JTextField();
        JTextField validityMonthsTimeText = new JTextField();
        JLabel costLabel = new JLabel();
        costLabel.setText("Цена:");
        JLabel nameLabel = new JLabel();
        nameLabel.setText("Наименование:");
        JLabel placementTimeLabel = new JLabel();
        placementTimeLabel.setText("Время посещения:");
        JLabel validityMonthsTimeLabel = new JLabel();
        validityMonthsTimeLabel.setText("Срок(месяцы):");
        JLabel submit = new JLabel();
        add(scrollPane);
        JPanel panel = new JPanel(new GridLayout(10, 1));

        panel.add(costLabel);
        panel.add(costText);
        panel.add(nameLabel);
        panel.add(nameText);
        panel.add(placementTimeLabel);
        panel.add(placementTimeText);
        panel.add(validityMonthsTimeLabel);
        panel.add(validityMonthsTimeText);
        panel.add(submit);
        panel.add(addButton);

        add(panel, BorderLayout.EAST);

        addButton.addActionListener(listener -> {
            String cost = costText.getText();
            String name = nameText.getText();
            String placementTime = placementTimeText.getText();
            int validityMonths = Integer.parseInt(validityMonthsTimeText.getText());
            AddSubscriptionTypeRequest addSubscription = new AddSubscriptionTypeRequest(name,validityMonths,placementTime,cost);
            String jsonInputString;
            try {
                jsonInputString = new ObjectMapper().writeValueAsString(addSubscription);
                ConnectionBuilder connectionBuilder = new ConnectionBuilder();
                HttpURLConnection con = connectionBuilder.prepareRequestWithAuthHeader(Url.SUBSCRIPTION_TYPE_ADD.getUrl());
                con = connectionBuilder.prepareRequest(con, ConnectionType.POST);
                String response = connectionService.send(con, jsonInputString);
                System.out.println(response);

                submit.setForeground(new Color(0, 107, 14));
                submit.setText("Добавлено");

                Timer timer = new Timer(2000, arg0 -> submit.setText(""));
                timer.setRepeats(false);
                timer.start();

                update();
            } catch (IOException ex) {
                submit.setForeground(Color.RED);
                submit.setText("Введите валидные данные");
            }
        });


    }

    @Override
    public void update() throws UnauthorizedException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilder();
        ConnectionService connectionService = ConnectionService.getInstance();
        HttpURLConnection httpURLConnection = connectionBuilder.prepareRequestWithAuthHeader(Url.ALL_SUBSCRIPTION_TYPES.getUrl());
        httpURLConnection = connectionBuilder.prepareRequest(httpURLConnection, ConnectionType.GET);
        String response = null;
        try {
            response = connectionService.send(httpURLConnection);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            AllSubscriptionTypeResponse allSubscriptionTypeResponse = new ObjectMapper().readValue(response, AllSubscriptionTypeResponse.class);
            updatePanel(allSubscriptionTypeResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    private void updatePanel( AllSubscriptionTypeResponse allSubscriptionTypeResponse) {
        //Clear all data
        model.setRowCount(0);

        for(AllSubscriptionTypeResponse.SubscriptionTypeDto dto: allSubscriptionTypeResponse.getSubscriptionTypes()) {
            model.addRow(new Object[]{dto.getId(), dto.getCost(),dto.getName(), dto.getPlacementTime(), dto.getValidityMonths()});
        }
    }


}
