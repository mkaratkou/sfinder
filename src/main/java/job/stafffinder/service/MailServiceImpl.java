package job.stafffinder.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import job.stafffinder.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static java.lang.String.format;
import static job.stafffinder.config.EmailConfig.UTF_8;

@Service
public class MailServiceImpl implements MailService {

    private static final String USER_REGISTERED_TEMPLATE = "user-registration.ftl";

    private Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Value("${email.default.from}")
    private String defaultFrom;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Configuration freemarkerConfig;

    @Autowired
    private Environment environment;

    @Override
    @Async("mailThreadPoolTaskExecutor")
    public void notifyUserRegistered(User user, Locale locale) {

        String bodyText = mergeUserRegisteredMessage(user, locale);
        if (bodyText == null) {
            return;
        }
        MimeMessagePreparator preparator = (MimeMessage mimeMessage) -> {
            prepareMimeMessage(user.getEmail(), bodyText, mimeMessage);
        };
        try {
            mailSender.send(preparator);
        } catch (MailException e) {
            logger.error(format("Unable to send email to: %s", user.getEmail()), e);
        }
    }

    private void prepareMimeMessage(String to, String bodyText, MimeMessage mimeMessage) {
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, UTF_8);

        try {
            message.setSubject(environment.getProperty("email.userRegistered.subj"));
            message.setFrom(defaultFrom);
            message.setText(bodyText, true);
            message.setTo(to);
        } catch (MessagingException e) {
            logger.error("Unable to create mail Message", e);
        }
    }

    private String mergeUserRegisteredMessage(User user, Locale locale) {
        final Template fmTemplate;
        try {
            fmTemplate = (locale != null) ? freemarkerConfig.getTemplate(USER_REGISTERED_TEMPLATE, locale)
                                          : freemarkerConfig.getTemplate(USER_REGISTERED_TEMPLATE);
            Map<String, Object> model = new HashMap<>();
            model.put("userFirstName", user.getFirstName());
            model.put("userLastName", user.getLastName());

            return FreeMarkerTemplateUtils.processTemplateIntoString(fmTemplate, model);
        } catch (IOException | TemplateException e) {
            logger.error("Email text body creation failed", e);
        }
        return null;
    }

}
