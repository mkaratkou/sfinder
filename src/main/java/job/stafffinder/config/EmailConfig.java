package job.stafffinder.config;


import freemarker.template.TemplateException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import java.io.IOException;
import java.util.Properties;

@Configuration
@PropertySource(value = "classpath:email.properties")
public class EmailConfig {

    public static final String UTF_8 = "UTF-8";

    @Bean
    public FreeMarkerConfigurationFactoryBean emailFreemarkerConfig() throws IOException, TemplateException {
        FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean = new FreeMarkerConfigurationFactoryBean();
        freeMarkerConfigurationFactoryBean.setTemplateLoaderPath("/WEB-INF/templates");
        Properties freemarkerSettings = new Properties();
        freemarkerSettings.put("url_escaping_charset", UTF_8);
        freemarkerSettings.put("default_encoding", UTF_8);
        freeMarkerConfigurationFactoryBean.setFreemarkerSettings(freemarkerSettings);
        freeMarkerConfigurationFactoryBean.afterPropertiesSet();
        return freeMarkerConfigurationFactoryBean;
    }

    @Bean
    public JavaMailSender mailSender(Environment env) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(env.getRequiredProperty("email.smtp.host"));
        sender.setPort(env.getRequiredProperty("email.smtp.port", int.class));
        sender.setProtocol(env.getRequiredProperty("email.smtp.protocol"));
        sender.setUsername(env.getRequiredProperty("email.smtp.username"));
        sender.setPassword(env.getRequiredProperty("email.smtp.password"));

        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtps.auth", env.getRequiredProperty("email.smtp.javamail.smtps.auth"));
        mailProperties.put("mail.smtps.starttls.enable", env.getRequiredProperty("email.smtp.javamail.smtps.starttls.enable"));
        mailProperties.put("mail.smtps.debug", env.getProperty("email.smtp.javamail.smtps.debug"));
        mailProperties.put("mail.mime.charset", UTF_8);

        sender.setJavaMailProperties(mailProperties);
        return sender;
    }

    @Bean
    public ThreadPoolTaskExecutor mailThreadPoolTaskExecutor(Environment env) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(env.getRequiredProperty("email.sender.threadpool.maxsize", int.class));
        return executor;
    }

}

