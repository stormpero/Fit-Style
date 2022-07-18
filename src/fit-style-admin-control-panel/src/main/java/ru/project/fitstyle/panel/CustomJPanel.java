package ru.project.fitstyle.panel;

import ru.project.fitstyle.exception.UnauthorizedException;

import javax.swing.JPanel;

abstract public class CustomJPanel extends JPanel {
    public abstract void update() throws UnauthorizedException;

    public abstract void submit();
}
