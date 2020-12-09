package Util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * @author parssa
 */
public class PDFConverter {
    public void convertToPDF(String filepath, String username, ArrayList<String> userSchedule) {
        Document document = new Document();
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25, BaseColor.BLACK);
        Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 15, BaseColor.BLACK);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filepath));
            document.open();
            Paragraph preface = new Paragraph();
            Chunk title = new Chunk("My schedule", titleFont);
            preface.add(title);
            addEmptyLine(preface, 1);
            Chunk name = new Chunk(username, bodyFont);
            preface.add(name);
            addEmptyLine(preface, 2);
            PdfPTable table = new PdfPTable(3);
            addTableHeader(table);
            addRows(table);
            preface.add(table);
            document.add(preface);
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Time", "Event", "Room")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table) {
        for (int i=0; i <= 10; i++) {
            addElementEntry(table);
        }
    }

    private void addElementEntry(PdfPTable table) {
        table.addCell("12:00pm");
        table.addCell("Event name");
        table.addCell("Room name");
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
