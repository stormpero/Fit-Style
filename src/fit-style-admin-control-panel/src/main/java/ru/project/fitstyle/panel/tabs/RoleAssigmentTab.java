package ru.project.fitstyle.panel.tabs;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.project.fitstyle.config.Url;
import ru.project.fitstyle.exception.UnauthorizedException;
import ru.project.fitstyle.json.post.AssignRoleRequest;
import ru.project.fitstyle.json.response.AllRolesResponse;
import ru.project.fitstyle.json.response.AllUsersResponse;
import ru.project.fitstyle.panel.CustomJPanel;
import ru.project.fitstyle.panel.MultiLineTableCellRenderer;
import ru.project.fitstyle.service.connection.ConnectionBuilder;
import ru.project.fitstyle.service.connection.ConnectionService;
import ru.project.fitstyle.service.connection.ConnectionType;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

public class RoleAssigmentTab extends CustomJPanel {

    private final DefaultTableModel modelRoles;
    private final DefaultTableModel modelUsers;

    private final ConnectionService connectionService = ConnectionService.getInstance();
    private final ConnectionBuilder connectionBuilder = new ConnectionBuilder();
    @Override
    public void update() throws UnauthorizedException {
        String response = null;
        try {
            response = connectionService.send(configConnectionBuild(Url.ALL_ROLES));
            AllRolesResponse allRolesResponse = new ObjectMapper().readValue(response, AllRolesResponse.class);
            response = connectionService.send(configConnectionBuild(Url.ALL_USERS));
            AllUsersResponse allUsersResponse = new ObjectMapper().readValue(response, AllUsersResponse.class);

            updatePanel(allRolesResponse, allUsersResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RoleAssigmentTab() {
        setLayout(new BorderLayout());

        modelRoles = new DefaultTableModel(new String[][]{}, new String[] {"Идентификатор", "Имя роли"});
        modelUsers = new DefaultTableModel(new String[][]{}, new String[] {"Идентификатор", "ФИО", "Роли"}){
            @Override
            public Class<String> getColumnClass(int columnIndex) {
                return String.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };;

        JTable tableRoles = new JTable(modelRoles) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tableUsers = new JTable(modelUsers) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableUsers.setDefaultRenderer(String.class, new MultiLineTableCellRenderer());


        JPanel panel = new JPanel(new GridLayout(2, 1));

        JLabel nameLabel = new JLabel();
        nameLabel.setText("Идентификатор пользователя:");
        JTextField userText = new JTextField();

        JLabel roleIdLabel = new JLabel();
        roleIdLabel.setText("Идентификатор роли:");
        JTextField roleText = new JTextField();

        JButton submit = new JButton("Добавить");

        JLabel message = new JLabel();
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setVerticalAlignment(SwingConstants.CENTER);


//        panel.add(nameLabel);
//        panel.add(userText);
//        panel.add(roleIdLabel);
//        panel.add(roleText);
        panel.add(message);
        message.setText("");
        panel.add(submit);

        JScrollPane scrollPaneTableUsers = new JScrollPane(tableUsers);
        scrollPaneTableUsers.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel tablePane = new JPanel(new GridLayout(1,2));

        tablePane.add(scrollPaneTableUsers);
        tablePane.add(new JScrollPane(tableRoles));

        add(tablePane);


        submit.addActionListener(e -> submitButtonPressed(userText, roleText, message));
        add(panel, BorderLayout.SOUTH);
    }

    private void updatePanel(AllRolesResponse allRolesResponse, AllUsersResponse allUsersResponse) {
        //Clear all data
        modelRoles.setRowCount(0);
        modelUsers.setRowCount(0);

        for(AllRolesResponse.RoleDto roleDto : allRolesResponse.getRoles()) {
            modelRoles.addRow(new Object[]{roleDto.getId(), roleDto.getName()});
        }

        for(AllUsersResponse.FitUserFullInfoDto fitUserFullInfoDto : allUsersResponse.getFitUsers()) {
            AllUsersResponse.FitUserFullInfoDto.FitUserDto fitUserInfo = fitUserFullInfoDto.getFitUserInfo();
            modelUsers.addRow(new Object[]{fitUserInfo.getId(), addSpaces(fitUserInfo.getSurname(),fitUserInfo.getName(), fitUserInfo.getPatronymic()),  getMainRole(fitUserFullInfoDto.getRoles())});
        }
    }

    private HttpURLConnection configConnectionBuild(Url url) {
        HttpURLConnection httpURLConnection = connectionBuilder.prepareRequestWithAuthHeader(url.getUrl());
        return connectionBuilder.prepareRequest(httpURLConnection, ConnectionType.GET);
    }

    private String addSpaces(String ...strs) {
        StringBuilder res = new StringBuilder();
        for (String str : strs) {
            res.append(str).append(" ");
        }
        return res.toString();
    }

    private String getMainRole(List<AllUsersResponse.FitUserFullInfoDto.RoleDto> roleList) {
        StringBuilder result = new StringBuilder();
        for(AllUsersResponse.FitUserFullInfoDto.RoleDto roleDto : roleList) {
            String name;
            switch(roleDto.getName()) {
                case "ROLE_USER" :
                    name = "Пользователь";
                    break;
                case "ROLE_COACH" :
                    name = "Тренер";
                    break;
                case "ROLE_MODERATOR":
                    name = "Администратор";
                    break;
                default:
                    name = roleDto.getName();
                    break;
            }
            result.append(name).append('\n');
        }
        return result.toString();
    }

    private void submitButtonPressed(JTextField userText, JTextField roleText, JLabel message) {
        Long userId = Long.parseLong(userText.getText());
        Long roleId = Long.parseLong(roleText.getText());
        AssignRoleRequest assignRoleRequest = new AssignRoleRequest(userId, roleId);
        try {
            String jsonInputString = new ObjectMapper().writeValueAsString(assignRoleRequest);
            ConnectionBuilder connectionBuilder = new ConnectionBuilder();
            HttpURLConnection con = connectionBuilder.prepareRequestWithAuthHeader(Url.ROLE_ASSIGN.getUrl());
            con = connectionBuilder.prepareRequest(con, ConnectionType.POST);
            String response = connectionService.send(con, jsonInputString);

            message.setForeground(new Color(0, 107, 14));
            message.setText("Успех");

            update();
        } catch (IOException ex) {
            message.setForeground(Color.RED);
            message.setText("Ошибка...");
        }
    }
}
