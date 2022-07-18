package ru.project.fitstyle.panel.tabs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.project.fitstyle.config.Url;
import ru.project.fitstyle.exception.UnauthorizedException;
import ru.project.fitstyle.json.post.AddEditTrainingRequest;
import ru.project.fitstyle.json.response.AllRolesResponse;
import ru.project.fitstyle.panel.CustomJPanel;
import ru.project.fitstyle.service.connection.ConnectionBuilder;
import ru.project.fitstyle.service.connection.ConnectionService;
import ru.project.fitstyle.service.connection.ConnectionType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;

public class RoleTab extends CustomJPanel {

    private final ConnectionService connectionService = ConnectionService.getInstance();
    private final DefaultTableModel model;

    private final JTextField roleName;
    private final JLabel message;

    public RoleTab() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(2, 2));
        JLabel label = new JLabel("Введите название роли");
        message = new JLabel("");
        roleName = new JTextField(20);

        panel.add(label);
        panel.add(roleName);
        panel.add(message);

        model = new DefaultTableModel(new String[][]{}, new String[] {"Идентификатор", "Название"});
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
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane);
        add(panel, BorderLayout.SOUTH);
    }

    @Override
    public void update() throws UnauthorizedException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilder();
        ConnectionService connectionService = ConnectionService.getInstance();
        HttpURLConnection httpURLConnection = connectionBuilder.prepareRequestWithAuthHeader(Url.ALL_ROLES.getUrl());
        httpURLConnection = connectionBuilder.prepareRequest(httpURLConnection, ConnectionType.GET);
        String response = null;
        try {
            response = connectionService.send(httpURLConnection);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            AllRolesResponse allRolesResponse = new ObjectMapper().readValue(response, AllRolesResponse.class);
            updatePanel(allRolesResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void submit() {
        String roleDto = roleName.getText();
        AddEditTrainingRequest addRequest = new AddEditTrainingRequest(roleDto);
        String jsonInputString;
        try {
            //Convert object to json string
            jsonInputString = new ObjectMapper().writeValueAsString(addRequest);
            ConnectionBuilder connectionBuilder = new ConnectionBuilder();
            HttpURLConnection con = connectionBuilder.prepareRequestWithAuthHeader(Url.ROLE_ADD.getUrl());
            con = connectionBuilder.prepareRequest(con, ConnectionType.POST);


            connectionService.send(con, jsonInputString);

            message.setForeground(new Color(0, 107, 14));
            message.setText("Добавлено!");

            Timer timer = new Timer(2000, arg0 -> message.setText(""));
            timer.setRepeats(false);
            timer.start();

            update();


        } catch (IOException ex) {
            message.setForeground(Color.RED);
            message.setText("Не удалось добавить новую роль...");
        }
    }

    private void updatePanel(AllRolesResponse allRolesResponse) {
        //Clear all data
        model.setRowCount(0);

        for(AllRolesResponse.RoleDto roleDto : allRolesResponse.getRoles()) {
            model.addRow(new Object[]{roleDto.getId(), roleDto.getName()});
        }
    }
}
