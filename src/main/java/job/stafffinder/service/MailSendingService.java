package job.stafffinder.service;

import job.stafffinder.model.User;

import java.util.Locale;

public interface MailSendingService {

    void notifyUserRegistred(User user, Locale locale);
}
