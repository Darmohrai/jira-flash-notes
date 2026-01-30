package ua.cross.kurin.jira_flash_notes;

import org.openpdf.text.Document;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.PdfWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;

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

    @GetMapping("/download-pdf")
    public ResponseEntity<byte[]> downloadPdf(@RequestParam String issueKey) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph("Jira Flash Notes PDF Generator"));
            document.add(new Paragraph("Exporting issue: " + issueKey));
            document.add(new Paragraph("Status: Configuration is successful!"));
            document.close();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        byte[] pdfBytes = out.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", issueKey + ".pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/export-config-dialog")
    public String showDialog(@RequestParam String issueKey, org.springframework.ui.Model model) {
        model.addAttribute("issueKey", issueKey);
        return "export-dialog";
    }
}