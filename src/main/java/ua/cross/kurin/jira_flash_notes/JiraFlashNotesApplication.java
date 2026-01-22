package ua.cross.kurin.jira_flash_notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@ComponentScan(basePackages = {"ua.cross.kurin.jira_flash_notes", "com.atlassian.connect.spring"})
@Controller
public class JiraFlashNotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(JiraFlashNotesApplication.class, args);
    }

    @GetMapping("/first-deploy-message")
    @ResponseBody
    public String checkMessage() {
        return "<html>" +
                "<head>" +
                "<script src=\"https://connect-cdn.atl-paas.net/all.js\"></script>" +
                "</head>" +
                "<body>" +
                "<h1>It's message for first deploy to check if configurations are good</h1>" +
                "</body>" +
                "</html>";
    }

}