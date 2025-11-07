package com.smartlogi.smds.service;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);

    void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);

    /**
     * Sends an email with HTML content.
     *
     * @param to       the recipient's email address.
     * @param subject  the subject of the email.
     * @param htmlBody the HTML body of the email.
     */
    void sendHtmlMessage(String to, String subject, String htmlBody);
}
