package ru.project.fitstyle;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.project.fitstyle.config.Url;
import ru.project.fitstyle.exception.NotAnAdministratorException;
import ru.project.fitstyle.json.post.SignInRequest;
import ru.project.fitstyle.json.response.UserAuthInfoResponse;
import ru.project.fitstyle.service.AuthInfoService;
import ru.project.fitstyle.service.connection.ConnectionBuilder;
import ru.project.fitstyle.service.connection.ConnectionService;
import ru.project.fitstyle.service.connection.ConnectionType;

import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Objects;

import javax.swing.*;

public class Authorization {

    private final ConnectionService connectionService = ConnectionService.getInstance();

    //Starting point
    public static void main(String[] args) {
        new Authorization().createWindow(false);
    }

    public void createWindow(boolean isSessionExpired) {
        JFrame frame = new JFrame("Авторизация");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        createUI(frame, isSessionExpired);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        frame.setVisible(true);
        frame.setSize((int) dimension.getWidth() / 5, (int) dimension.getHeight() / 5);
        frame.setLocation((int) (dimension.getWidth() - frame.getWidth()) / 2, (int) (dimension.getHeight() - frame.getHeight()) / 2);
        frame.setResizable(false);
    }

    private void createUI(final JFrame jFrame, boolean isSessionExpired) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(ClassLoader.getSystemResource("icon.png")));
        jFrame.setIconImage(icon.getImage());

        // User Label
        JLabel emailLabel = new JLabel();
        emailLabel.setText("Почта:");
        JTextField emailText = new JTextField();

        // Password
        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Пароль:");
        JPasswordField passwordText = new JPasswordField();

        // Submit
        JButton submit = new JButton("Войти");

        JPanel panel = new JPanel(new GridLayout(3, 1));

        panel.add(emailLabel);
        panel.add(emailText);
        panel.add(passwordLabel);
        panel.add(passwordText);

        JLabel message = new JLabel();
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(message);
        panel.add(submit);

        if(isSessionExpired) {
            message.setForeground(Color.RED);
            message.setText("Сессия истекла!");
        }

        jFrame.add(panel, BorderLayout.CENTER);

        // Adding the listeners to components..

            String email = "AdminProfile@gmail.com";
            String password = "AdminProfile";
            SignInRequest signInRequest = new SignInRequest(email, password);
            String jsonInputString;
            try {
                //Convert object to json string
                jsonInputString = new ObjectMapper().writeValueAsString(signInRequest);

                ConnectionBuilder connectionBuilder = new ConnectionBuilder();
                HttpURLConnection con = connectionBuilder.prepareRequestWithoutAuthHeader(Url.AUTH.getUrl());
                con = connectionBuilder.prepareRequest(con, ConnectionType.POST);
                String response = connectionService.sendPost(con, jsonInputString);

                //Convert json string to object
                UserAuthInfoResponse userInfo = new ObjectMapper().readValue(response, UserAuthInfoResponse.class);

                //Find out if authenticated user is moderator
                userInfo.getRoles().stream().filter(role -> role.equals("ROLE_MODERATOR")).findAny()
                        .orElseThrow(() -> new NotAnAdministratorException("Not an administrator!"));

                //Authenticate
                AuthInfoService.auth(userInfo.getId(), userInfo.getEmail(), userInfo.getToken());
                message.setForeground(new Color(0, 107, 14));
                message.setText("Успех");

                //Close the auth window after success
                jFrame.dispose();

                //Open main window
                new MainWindow().createWindow();
            } catch (NotAnAdministratorException ex) {
                message.setForeground(Color.RED);
                message.setText("Вы не администратор!");
            } catch (IOException ex) {
                message.setForeground(Color.RED);
                message.setText("Не удалось войти...");
            }

    }
}