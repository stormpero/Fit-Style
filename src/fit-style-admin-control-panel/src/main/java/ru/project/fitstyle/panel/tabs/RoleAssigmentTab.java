package ru.project.fitstyle.panel.tabs;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.project.fitstyle.MainWindow;
import ru.project.fitstyle.config.Url;
import ru.project.fitstyle.exception.NotAnAdministratorException;
import ru.project.fitstyle.exception.UnauthorizedException;
import ru.project.fitstyle.json.post.AddEditRoleRequest;
import ru.project.fitstyle.json.post.AssignRoleRequest;
import ru.project.fitstyle.json.post.SignInRequest;
import ru.project.fitstyle.json.response.AllRolesResponse;
import ru.project.fitstyle.json.response.AllUsersResponse;
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
            response = connectionService.sendGet(configConnectionBuild(Url.ALL_ROLES));
            AllRolesResponse allRolesResponse = new ObjectMapper().readValue(response, AllRolesResponse.class);
            response = connectionService.sendGet(configConnectionBuild(Url.ALL_USERS));
            AllUsersResponse allUsersResponse = new ObjectMapper().readValue(response, AllUsersResponse.class);

            updatePanel(allRolesResponse, allUsersResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RoleAssigmentTab() {
        setLayout(new GridLayout(3, 3));

        modelRoles = new DefaultTableModel(new String[][]{}, new String[] {"Идентификатор", "Имя роли"});
        modelUsers = new DefaultTableModel(new String[][]{}, new String[] {"Идентификатор", "ФИО", "Роли"});

        JTable tableRoles = new JTable(modelRoles) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };

        JTable tableUsers = new JTable(modelUsers) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JScrollPane scrollPaneTableUsers = new JScrollPane(tableUsers);
        scrollPaneTableUsers.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(new JScrollPane(tableRoles));
        add(scrollPaneTableUsers);

        JPanel panel = new JPanel(new GridLayout(3, 1));

        JLabel nameLabel = new JLabel();
        nameLabel.setText("Идентификатор пользователя:");
        JTextField userText = new JTextField();

        JLabel roleIdLabel = new JLabel();
        roleIdLabel.setText("Идентификатор роли:");
        JTextField roleText = new JTextField();

        JButton submit = new JButton("Добавить");

        panel.add(nameLabel);
        panel.add(userText);
        panel.add(roleIdLabel);
        panel.add(roleText);

        JLabel message = new JLabel();
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(message);
        panel.add(submit);

        submit.addActionListener(e -> submitButtonPressed(userText, roleText, message));
        add(panel);
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
        String roleName = roleList.get(roleList.size() - 1).getName();
        if (roleName.contains("ROLE_MODERATOR"))
            return "Администратор";
        if (roleName.contains("ROLE_COACH"))
            return "Тренер";
        if (roleName.contains("ROLE_USER"))
            return "Пользователь";
        return "";
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
            String response = connectionService.sendPost(con, jsonInputString);
            System.out.println(response);


            message.setForeground(new Color(0, 107, 14));
            message.setText("Успех");

            update();

        } catch (NotAnAdministratorException ex) {
            message.setForeground(Color.RED);
            message.setText("Вы не администратор!");
        } catch (IOException ex) {
            message.setForeground(Color.RED);
            message.setText("Не удалось войти...");
        }
    }

}
