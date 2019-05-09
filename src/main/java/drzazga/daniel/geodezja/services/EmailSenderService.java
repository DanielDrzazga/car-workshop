package drzazga.daniel.geodezja.services;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String content);
}