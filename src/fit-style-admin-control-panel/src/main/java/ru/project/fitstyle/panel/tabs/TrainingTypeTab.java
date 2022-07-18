package ru.project.fitstyle.panel.tabs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.project.fitstyle.config.Url;
import ru.project.fitstyle.exception.UnauthorizedException;
import ru.project.fitstyle.json.post.AddEditTrainingRequest;
import ru.project.fitstyle.json.response.AllTrainingTypesResponse;
import ru.project.fitstyle.panel.CustomJPanel;
import ru.project.fitstyle.service.connection.ConnectionBuilder;
import ru.project.fitstyle.service.connection.ConnectionService;
import ru.project.fitstyle.service.connection.ConnectionType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import javax.swing.Timer;

public class TrainingTypeTab extends CustomJPanel {
    private final ConnectionService connectionService = ConnectionService.getInstance();
    private final DefaultTableModel model;

    public TrainingTypeTab() {

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(2, 2));
        JLabel label = new JLabel("Введите имя тренировки");
        JLabel message = new JLabel("");
        JTextField trainingName = new JTextField(20);
        JButton addButton = new JButton("Добавить");

        panel.add(label);
        panel.add(trainingName);
        panel.add(message);
        panel.add(addButton);


        addButton.addActionListener(listener -> {
            String training = trainingName.getText();
            AddEditTrainingRequest addRequest = new AddEditTrainingRequest(training);
            String jsonInputString;
            try {
                //Convert object to json string
                jsonInputString = new ObjectMapper().writeValueAsString(addRequest);
                ConnectionBuilder connectionBuilder = new ConnectionBuilder();
                HttpURLConnection con = connectionBuilder.prepareRequestWithAuthHeader(Url.TRAINING_TYPE_ADD.getUrl());
                con = connectionBuilder.prepareRequest(con, ConnectionType.POST);


                connectionService.send(con, jsonInputString);

                message.setForeground(new Color(0, 107, 14));
                message.setText("Добавлено!");
                trainingName.setText("");

                Timer timer = new Timer(2000, arg0 -> message.setText(""));
                timer.setRepeats(false);
                timer.start();

                update();


            } catch (IOException ex) { //| InterruptedException ex
                message.setForeground(Color.RED);
                message.setText("Не удалось добавить тип тренировки...");
            }
        });


        model = new DefaultTableModel(new String[][]{}, new String[] {"Идентификатор", "Тип тренеровки"});
        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        add(scrollPane);
        add(panel, BorderLayout.SOUTH);
    }


    @Override

    public void update() throws UnauthorizedException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilder();
        ConnectionService connectionService = ConnectionService.getInstance();
        HttpURLConnection httpURLConnection = connectionBuilder.prepareRequestWithAuthHeader(Url.ALL_TRAINING_TYPES.getUrl());
        httpURLConnection = connectionBuilder.prepareRequest(httpURLConnection, ConnectionType.GET);
        String response = null;
        try {
            response = connectionService.send(httpURLConnection);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            AllTrainingTypesResponse allTrainingTypesResponse = new ObjectMapper().readValue(response, AllTrainingTypesResponse.class);
            updatePanel(allTrainingTypesResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void updatePanel(AllTrainingTypesResponse allTrainingTypesResponse) {
        //Clear all data
        model.setRowCount(0);

        for(AllTrainingTypesResponse.TrainingTypeDto training : allTrainingTypesResponse.getTrainingNames()) {
            model.addRow(new Object[]{training.getId(), training.getName()});
        }
    }
}
