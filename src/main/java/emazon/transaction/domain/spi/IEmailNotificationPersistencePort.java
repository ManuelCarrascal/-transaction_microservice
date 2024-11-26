package emazon.transaction.domain.spi;

public interface IEmailNotificationPersistencePort {
    void sendEmail(String toUser, String subject, String body);

}
