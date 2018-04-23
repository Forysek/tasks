package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.scheduler.EmailScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    private TaskRepository taskRepository;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://forysek.github.io/" );
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_details", companyConfig);

        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String sendScheduledEmail(String message) {

        Context context = new Context();
        context.setVariable("button", "Visit website");
        context.setVariable("show_button", true);
        context.setVariable("tasks_url", "https://forysek.github.io/" );
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_details", companyConfig);
        context.setVariable("tasks_amount", taskRepository.count());
        context.setVariable("message", message);


        return templateEngine.process("mail/scheduled-mail", context);
    }
}
