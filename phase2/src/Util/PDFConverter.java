package Util;

import Entities.ScheduleEntry;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author parssa
 */
public class PDFConverter {

    /**
     * Converts a user's schedule into a pdf
     * @param filepath the file path
     * @param username username of the user who is making a pdf
     * @param userSchedule that user's schedule
     */
    public void convertToPDF(String filepath, String username, List<ScheduleEntry> userSchedule) {
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
            addScheduleHeader(table);
            addRows(table, userSchedule);
            preface.add(table);
            document.add(preface);
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds rows to the pdf and splits up the events to be read easily
     * @param table table
     * @param userSchedule schedule of the user
     */
    private void addRows(PdfPTable table, List<ScheduleEntry> userSchedule) {
        SimpleDateFormat df = new SimpleDateFormat("h:mm a, yyyy-MM-dd");
        for (ScheduleEntry s : userSchedule) {
            addElementEntry(table, s.getEventName(), s.getRoomID(), df.format(s.getStartTime()));
        }
    }

    /**
     * Adds a header on top of the table
     * @param table table
     */
    private void addScheduleHeader(PdfPTable table) {
        Stream.of("Time", "Event", "Room")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    /**
     * Adds an empty line
     * @param paragraph paragraph
     * @param number an int
     */
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    /**
     * Adds an event element into the pdf
     * @param table table
     * @param eventName name of the event
     * @param roomID id of the room
     * @param startTime start time of the event
     */
    private void addElementEntry(PdfPTable table, String eventName, String roomID, String startTime) {
        table.addCell(eventName);
        table.addCell(roomID);
        table.addCell(startTime);
    }
}
