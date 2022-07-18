package ru.project.fitstyle;

import ru.project.fitstyle.exception.UnauthorizedException;
import ru.project.fitstyle.panel.CustomJPanel;
import ru.project.fitstyle.panel.tabs.*;
import ru.project.fitstyle.service.AuthInfoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainWindow {
    public void createWindow() {
        JFrame frame = new JFrame("Admin control panel");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createUI(frame);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        frame.setVisible(true);
        frame.setSize((int)dimension.getWidth() / 2, (int)dimension.getHeight() / 2);
        frame.setLocation((int)(dimension.getWidth() - frame.getWidth()) / 2, (int)(dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocationRelativeTo(null);
    }

    private void createUI(final JFrame frame){
        JButton updateButton = new JButton("Обновить");
        JButton exitButton = new JButton("Выйти");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(updateButton, BorderLayout.WEST);
        panel.add(exitButton, BorderLayout.EAST);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon.png"));
        frame.setIconImage(icon.getImage());

        JTabbedPane tabs = new JTabbedPane();


        tabs.addChangeListener(changeEvent -> {
            System.out.println("called");
            try {
                ((CustomJPanel)tabs.getSelectedComponent()).update();
            } catch (UnauthorizedException e) {
                AuthInfoService.setInstance(null);
                frame.dispose();
                new Authorization().createWindow(true);
            }
        });

        tabs.addTab("Назначение ролей", null, new RoleAssigmentTab(),"Назначить роль пользователю");
        tabs.setMnemonicAt(0, KeyEvent.VK_1);

        tabs.addTab("Добавление типов абонементов", null, new SubscriptionTypeTab(),"Добавить новый тип абонемента");
        tabs.setMnemonicAt(1, KeyEvent.VK_2);

        tabs.addTab("Добавление типов тренировок", null, new TrainingTypeTab(),"Добавить новый тип групповой тренировки");
        tabs.setMnemonicAt(2, KeyEvent.VK_3);

        tabs.addTab("Добавление ролей", null, new RoleTab(),"Добавить новую роль");
        tabs.setMnemonicAt(3, KeyEvent.VK_4);

        updateButton.addActionListener(listener -> {
            try {
                ((CustomJPanel)tabs.getSelectedComponent()).update();
            } catch (UnauthorizedException e) {
                AuthInfoService.setInstance(null);
                frame.dispose();
                new Authorization().createWindow(true);
            }
        });

        exitButton.addActionListener(e -> {
            AuthInfoService.setInstance(null);
            frame.dispose();
            new Authorization().createWindow(false);
        });


        frame.getContentPane().add(tabs, BorderLayout.CENTER);
        frame.getContentPane().add(panel, BorderLayout.NORTH);
    }
}
