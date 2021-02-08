/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceytechmarker.markers;

import ceytechmarker.enums.WatermarkType;
import ceytechmarker.markers.interfaces.WatermarkInterface;
import java.io.File;
import java.util.List;
import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.packages.SpreadsheetMLPackage;
import org.docx4j.openpackaging.parts.DrawingML.Drawing;
import org.docx4j.openpackaging.parts.SpreadsheetML.WorksheetPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.relationships.Relationships;
import org.xlsx4j.sml.Sheet;
import org.xlsx4j.sml.Sheets;

/**
 *
 * @author isuru
 */
public class ExcelWatermark implements WatermarkInterface {

    private int getRotationForWatermarkStyle(WatermarkType type) {
        int rotation = 0;

        switch (type) {
            case HORIZONTAL:
                rotation = 0;
                break;
            case VERTICAL:
                rotation = 90 * 60000;
                break;
            case DIAGONAL_LEFT:
                rotation = 45 * 60000;
                break;
            case DIAGONAL_RIGHT:
                rotation = 315 * 60000;
                break;
        }

        return rotation;
    }

    @Override
    public void addWatermark(File inputFile, File outputFile, WatermarkType watermarkType, String watermarkText) throws Exception {
        SpreadsheetMLPackage pkg = SpreadsheetMLPackage.load(inputFile);
        Sheets sheets = pkg.getWorkbookPart().getContents().getSheets();
        List<Sheet> sheetList = sheets.getSheet();
        for (int i = 0; i < sheetList.size(); i++) {
            WorksheetPart worksheet = pkg.getWorkbookPart().getWorksheet(i);

            // Create Drawing part and add to sheet 
            Drawing drawingPart = new Drawing();
            Relationship drawingRel = worksheet.addTargetPart(drawingPart);

            // Add anchor XML to worksheet
            org.xlsx4j.sml.CTDrawing drawing = org.xlsx4j.jaxb.Context.getsmlObjectFactory().createCTDrawing();
            worksheet.getJaxbElement().setDrawing(drawing);
            drawing.setId(drawingRel.getId());

            // Create and set drawing part content
            drawingPart.setJaxbElement(
                    buildDrawingPartContentFromXmlString(watermarkText, watermarkType));
        }

        // Save the xlsx
        pkg.save(outputFile);
    }

    public org.docx4j.dml.spreadsheetdrawing.CTDrawing buildDrawingPartContentFromXmlString(String watermarkText, WatermarkType watermarkType) throws Exception {

        int rotation = getRotationForWatermarkStyle(watermarkType);

        String openXML = "<xdr:wsDr xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\" xmlns:xdr=\"http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing\">"
            + "<xdr:oneCellAnchor>"
                + "<xdr:from>"
                    + "<xdr:col>1</xdr:col>"
                    + "<xdr:colOff>115489</xdr:colOff>"
                    + "<xdr:row>16</xdr:row>"
                    + "<xdr:rowOff>101346</xdr:rowOff>"
                +"</xdr:from>"
                + "<xdr:ext cx=\"6317977\" cy=\"1219436\"/>"
                + "<xdr:sp macro=\"\" textlink=\"\">"
                    + "<xdr:nvSpPr>"
                        + "<xdr:cNvPr id=\"2\" name=\"Rectangle 1\"/>"
                        + "<xdr:cNvSpPr/>"
                    +"</xdr:nvSpPr>"
                    + "<xdr:spPr>"
                        + "<a:xfrm rot=\""+rotation+"\">"
                            + "<a:off x=\"725089\" y=\"3149346\"/>"
                            + "<a:ext cx=\"6317977\" cy=\"1219436\"/>"
                        +"</a:xfrm>"
                        + "<a:prstGeom prst=\"rect\">"
                            + "<a:avLst/>"
                        +"</a:prstGeom>"
                        + "<a:noFill/>"
                        + "<a:ln>"
                            + "<a:noFill/>"
                        +"</a:ln>"
                    +"</xdr:spPr>"
                    + "<xdr:txBody>"
                        + "<a:bodyPr bIns=\"45720\" lIns=\"91440\" rIns=\"91440\" tIns=\"45720\" wrap=\"square\">"
                            + "<a:spAutoFit/>"
                        +"</a:bodyPr>"
                        + "<a:lstStyle/>"
                        + "<a:p>"
                            + "<a:pPr algn=\"ctr\"/>"
                            + "<a:r>"
                                + "<a:rPr b=\"true\" cap=\"none\" lang=\"en-US\" spc=\"0\" sz=\"7200\">"
                                    + "<a:ln w=\"12700\">"
                                        + "<a:noFill/>"
                                        + "<a:prstDash val=\"solid\"/>"
                                    +"</a:ln>"
                                    + "<a:solidFill>"
                                        + "<a:schemeClr val=\"bg1\">"
                                            + "<a:lumMod val=\"85000\"/>"
                                        +"</a:schemeClr>"
                                    +"</a:solidFill>"
                                    + "<a:effectLst/>"
                                +"</a:rPr>"
                                + "<a:t>"+watermarkText+"</a:t>"
                            +"</a:r>"
                        +"</a:p>"
                    +"</xdr:txBody>"
                +"</xdr:sp>"
                + "<xdr:clientData/>"
            +"</xdr:oneCellAnchor>"
        +"</xdr:wsDr>";

        return (org.docx4j.dml.spreadsheetdrawing.CTDrawing) XmlUtils.unwrap(
                XmlUtils.unmarshalString(openXML));
    }

    @Override
    public void removeWatermark(File inputFile, File outputFile) throws Exception {
        SpreadsheetMLPackage pkg = SpreadsheetMLPackage.load(inputFile);
        Sheets sheets = pkg.getWorkbookPart().getContents().getSheets();
        List<Sheet> sheetList = sheets.getSheet();
        for (int i = 0; i < sheetList.size(); i++) {
            WorksheetPart worksheet = pkg.getWorkbookPart().getWorksheet(i);

            String id = worksheet.getContents().getDrawing().getId();
            if (id != null) {
                Relationship relationship = worksheet.getRelationshipsPart().getRelationshipByID(id);
                Relationships contents = worksheet.getRelationshipsPart().getContents();
                contents.getRelationship().remove(relationship);
            }

            worksheet.getContents().setDrawing(null);
        }

        // Save the xlsx
        pkg.save(outputFile);
    }

}
