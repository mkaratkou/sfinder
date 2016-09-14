package job.stafffinder.service;

import job.stafffinder.model.User;

import java.util.Locale;

public interface MailService {

    void notifyUserRegistered(User user, Locale locale);
}
