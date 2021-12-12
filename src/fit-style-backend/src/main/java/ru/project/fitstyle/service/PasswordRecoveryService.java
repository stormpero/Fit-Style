package ru.project.fitstyle.service;

public interface PasswordRecoveryService {

    void sendEmail(final String toAddress);

    void confirmRecovery(final String code, final String password);
}
