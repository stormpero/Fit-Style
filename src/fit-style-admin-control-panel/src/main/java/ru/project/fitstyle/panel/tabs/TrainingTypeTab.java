package ru.project.fitstyle.panel.tabs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.project.fitstyle.MainWindow;
import ru.project.fitstyle.config.Url;
import ru.project.fitstyle.exception.NotAnAdministratorException;
import ru.project.fitstyle.exception.UnauthorizedException;
import ru.project.fitstyle.json.post.AddEditTrainingRequest;
import ru.project.fitstyle.json.post.SignInRequest;
import ru.project.fitstyle.json.response.AllTrainingTypesResponse;
import ru.project.fitstyle.json.response.TestResponse;
import ru.project.fitstyle.json.response.UserAuthInfoResponse;
import ru.project.fitstyle.panel.CustomJPanel;
import ru.project.fitstyle.service.AuthInfoService;
import ru.project.fitstyle.service.connection.ConnectionBuilder;
import ru.project.fitstyle.service.connection.ConnectionService;
import ru.project.fitstyle.service.connection.ConnectionType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;

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
            String training = trainingName.getText();//emailText.getText();
            AddEditTrainingRequest addRequest = new AddEditTrainingRequest(training);
            String jsonInputString;
            try {
                //Convert object to json string
                jsonInputString = new ObjectMapper().writeValueAsString(addRequest);
                System.out.println(jsonInputString);
                ConnectionBuilder connectionBuilder = new ConnectionBuilder();
                HttpURLConnection con = connectionBuilder.prepareRequestWithAuthHeader(Url.TRAINING_TYPE_ADD.getUrl());
                con = connectionBuilder.prepareRequest(con, ConnectionType.POST);


                String response = connectionService.sendPost(con, jsonInputString);
                System.out.println(response);
                message.setForeground(Color.GREEN);
                message.setText("Добавлено!");
                Thread.sleep(500);
                message.setText("");
                update();
                //Convert json string to object
                //UserAuthInfoResponse userInfo = new ObjectMapper().readValue(response, UserAuthInfoResponse.class);



            } catch (IOException | InterruptedException ex) {
                message.setForeground(Color.RED);
                message.setText("Не удалось добавить тип тренировку...");
            }
        });

        //add(addButton, BorderLayout.SOUTH);
        //add(trainingName, BorderLayout.SOUTH);

        model = new DefaultTableModel(new String[][]{}, new String[] {"Идентификатор", "Тип тренеровки"});
        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JScrollPane scrollPane = new JScrollPane(table);
        // Force the scrollbars to always be displayed
        //scrollPane.setHorizontalScrollBarPolicy(
               // JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        add(scrollPane);
        add(panel, BorderLayout.SOUTH);




        //add(addButton, new GridBagConstraints(0, 6, 1, 1, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
    }




    @Override

    public void update() throws UnauthorizedException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilder();
        ConnectionService connectionService = ConnectionService.getInstance();
        HttpURLConnection httpURLConnection = connectionBuilder.prepareRequestWithAuthHeader(Url.ALL_TRAINING_TYPES.getUrl());
        httpURLConnection = connectionBuilder.prepareRequest(httpURLConnection, ConnectionType.GET);
        String response = null;
        try {
            response = connectionService.sendGet(httpURLConnection);
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


    /*public void addButton() throws UnauthorizedException {

    }*/

}
