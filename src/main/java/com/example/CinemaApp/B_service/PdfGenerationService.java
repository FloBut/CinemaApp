package com.example.CinemaApp.B_service;

import org.springframework.stereotype.Service;

import javax.swing.text.Document;
@Service
public class PdfGenerationService {
    public void generatePdf(String text, String filePath) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(filePath)));
            document.open();

            Paragraph paragraph = new Paragraph(text);
            document.add(paragraph);

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } catch (Exception e) {
            e.printStackTrace(); // Handle other exceptions
        }
    }
}
