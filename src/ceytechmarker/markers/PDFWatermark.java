/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceytechmarker.markers;

import ceytechmarker.enums.WatermarkType;
import ceytechmarker.markers.interfaces.WatermarkInterface;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import java.io.File;

/**
 *
 * @author isuru
 */
public class PDFWatermark implements WatermarkInterface {

    private float getRads(int angle) {
        return angle * 3.14159F / 180;
    }

    private float getRotationAngleForWatermarkType(WatermarkType watermarkType) {
        float rotationAngle = 0;
        switch (watermarkType) {
            case HORIZONTAL:
                rotationAngle = getRads(0);
                break;

            case VERTICAL:
                rotationAngle = getRads(90);
                break;

            case DIAGONAL_LEFT:
                rotationAngle = getRads(45);
                break;

            case DIAGONAL_RIGHT:
                rotationAngle = getRads(315);
                break;
        }

        return rotationAngle;
    }

    @Override
    public void addWatermark(File inputFile, File outputFile, WatermarkType watermarkType, String watermarkText) throws Exception {
        Paragraph paragraph = new Paragraph(watermarkText);

        PdfFont font = PdfFontFactory.createFont(FontProgramFactory.createFont(StandardFonts.HELVETICA));

        PdfDocument pdfDoc = new PdfDocument(new PdfReader(inputFile), new PdfWriter(outputFile));

        int numberOfPages = pdfDoc.getNumberOfPages();

        for (int i = 1; i <= numberOfPages; i++) {
            PdfPage page = pdfDoc.getPage(i);

            PdfCanvas over = new PdfCanvas(page);
            over.setFillColor(ColorConstants.LIGHT_GRAY);
            paragraph.setFont(font)
                    .setFontSize(45);
            over.saveState();

            // Creating a dictionary that maps resource names to graphics state parameter dictionaries
            PdfExtGState gs1 = new PdfExtGState();
            gs1.setFillOpacity(0.3f);
            over.setExtGState(gs1);

            float rotationAngleForWatermarkType = getRotationAngleForWatermarkType(watermarkType);

            Canvas canvasWatermark3 = new Canvas(over, pdfDoc.getDefaultPageSize())
                    .showTextAligned(paragraph, 297, 450, 1, TextAlignment.CENTER, VerticalAlignment.TOP, rotationAngleForWatermarkType);
            canvasWatermark3.close();
            over.restoreState();
            over.release();
        }

        pdfDoc.close();
    }

    @Override
    public void removeWatermark(File inputFile, File outputFile) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
