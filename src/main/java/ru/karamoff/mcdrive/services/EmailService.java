package ru.karamoff.mcdrive.services;

public interface EmailService {
    void sendMail(String email, String subject, String text);
}
