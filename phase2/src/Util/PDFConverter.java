package Util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author parssa
 */
public class PDFConverter {
    public void convertToPDF(String filepath, String username) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filepath));
            document.open();
            Font font = FontFactory.getFont(FontFactory.HELVETICA, 25, BaseColor.BLACK);
            Chunk title = new Chunk("My schedule", font);
            document.add(title);
            Chunk name = new Chunk(username, font);
            document.add(name);
            
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
