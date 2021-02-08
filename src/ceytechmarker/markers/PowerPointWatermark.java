/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceytechmarker.markers;

import ceytechmarker.enums.WatermarkType;
import ceytechmarker.markers.interfaces.WatermarkInterface;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.docx4j.dml.CTGeomGuideList;
import org.docx4j.dml.CTNoFillProperties;
import org.docx4j.dml.CTNonVisualDrawingProps;
import org.docx4j.dml.CTNonVisualDrawingShapeProps;
import org.docx4j.dml.CTPoint2D;
import org.docx4j.dml.CTPositiveSize2D;
import org.docx4j.dml.CTPresetGeometry2D;
import org.docx4j.dml.CTRegularTextRun;
import org.docx4j.dml.CTSRgbColor;
import org.docx4j.dml.CTShapeProperties;
import org.docx4j.dml.CTSolidColorFillProperties;
import org.docx4j.dml.CTTextBody;
import org.docx4j.dml.CTTextBodyProperties;
import org.docx4j.dml.CTTextCharacterProperties;
import org.docx4j.dml.CTTextListStyle;
import org.docx4j.dml.CTTextParagraph;
import org.docx4j.dml.CTTextShapeAutofit;
import org.docx4j.dml.CTTransform2D;
import org.docx4j.openpackaging.packages.PresentationMLPackage;
import org.docx4j.openpackaging.parts.PresentationML.SlidePart;
import org.pptx4j.pml.NvPr;
import org.pptx4j.pml.Shape;

/**
 *
 * @author isuru
 */
public class PowerPointWatermark implements WatermarkInterface {

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

    private Shape getShapeOOP(int rotation, String watermarkText) throws Exception {

        org.pptx4j.pml.ObjectFactory pmlObjectFactory = new org.pptx4j.pml.ObjectFactory();

        Shape shape = pmlObjectFactory.createShape();
        org.docx4j.dml.ObjectFactory dmlObjectFactory = new org.docx4j.dml.ObjectFactory();
        // Create object for spPr
        CTShapeProperties shapeproperties = dmlObjectFactory.createCTShapeProperties();
        shape.setSpPr(shapeproperties);
        // Create object for noFill
        CTNoFillProperties nofillproperties = dmlObjectFactory.createCTNoFillProperties();
        shapeproperties.setNoFill(nofillproperties);
        // Create object for xfrm
        CTTransform2D transform2d = dmlObjectFactory.createCTTransform2D();
        shapeproperties.setXfrm(transform2d);
        transform2d.setRot(rotation);
        // Create object for off
        CTPoint2D point2d = dmlObjectFactory.createCTPoint2D();
        transform2d.setOff(point2d);
        point2d.setY(3010594);
        point2d.setX(1014543);
        // Create object for ext
        CTPositiveSize2D positivesize2d = dmlObjectFactory.createCTPositiveSize2D();
        transform2d.setExt(positivesize2d);
        positivesize2d.setCx(7566302);
        positivesize2d.setCy(830997);
        // Create object for prstGeom
        CTPresetGeometry2D presetgeometry2d = dmlObjectFactory.createCTPresetGeometry2D();
        shapeproperties.setPrstGeom(presetgeometry2d);
        presetgeometry2d.setPrst(org.docx4j.dml.STShapeType.RECT);
        // Create object for avLst
        CTGeomGuideList geomguidelist = dmlObjectFactory.createCTGeomGuideList();
        presetgeometry2d.setAvLst(geomguidelist);
        // Create object for txBody
        CTTextBody textbody = dmlObjectFactory.createCTTextBody();
        shape.setTxBody(textbody);
        // Create object for bodyPr
        CTTextBodyProperties textbodyproperties = dmlObjectFactory.createCTTextBodyProperties();
        textbody.setBodyPr(textbodyproperties);
        textbodyproperties.setWrap(org.docx4j.dml.STTextWrappingType.NONE);
        // Create object for spAutoFit
        CTTextShapeAutofit textshapeautofit = dmlObjectFactory.createCTTextShapeAutofit();
        textbodyproperties.setSpAutoFit(textshapeautofit);
        // Create object for lstStyle
        CTTextListStyle textliststyle = dmlObjectFactory.createCTTextListStyle();
        textbody.setLstStyle(textliststyle);
        // Create object for p
        CTTextParagraph textparagraph = dmlObjectFactory.createCTTextParagraph();
        textbody.getP().add(textparagraph);
        // Create object for endParaRPr
        CTRegularTextRun regulartextrun = dmlObjectFactory.createCTRegularTextRun();
        textparagraph.getEGTextRun().add(regulartextrun);
        // Create object for rPr
        CTTextCharacterProperties textcharacterproperties = dmlObjectFactory.createCTTextCharacterProperties();
        regulartextrun.setRPr(textcharacterproperties);
        textcharacterproperties.setLang("en-US");
        textcharacterproperties.setSz(4800);
        // Create object for solidFill
        CTSolidColorFillProperties solidcolorfillproperties = dmlObjectFactory.createCTSolidColorFillProperties();
        textcharacterproperties.setSolidFill(solidcolorfillproperties);
        // Create object for srgbClr
        CTSRgbColor srgbcolor = dmlObjectFactory.createCTSRgbColor();
        solidcolorfillproperties.setSrgbClr(srgbcolor);
        srgbcolor.setVal("EAEAEA");
        textcharacterproperties.setSmtId(new Long(0));
        regulartextrun.setT(watermarkText);
        // Create object for endParaRPr
        CTTextCharacterProperties textcharacterproperties2 = dmlObjectFactory.createCTTextCharacterProperties();
        textparagraph.setEndParaRPr(textcharacterproperties2);
        textcharacterproperties2.setLang("en-US");
        textcharacterproperties2.setSz(4800);
        // Create object for solidFill
        CTSolidColorFillProperties solidcolorfillproperties2 = dmlObjectFactory.createCTSolidColorFillProperties();
        textcharacterproperties2.setSolidFill(solidcolorfillproperties2);
        // Create object for srgbClr
        CTSRgbColor srgbcolor2 = dmlObjectFactory.createCTSRgbColor();
        solidcolorfillproperties2.setSrgbClr(srgbcolor2);
        srgbcolor2.setVal("EAEAEA");
        textcharacterproperties2.setSmtId(new Long(0));
        // Create object for nvSpPr
        Shape.NvSpPr shapenvsppr = pmlObjectFactory.createShapeNvSpPr();
        shape.setNvSpPr(shapenvsppr);
        // Create object for cNvPr
        CTNonVisualDrawingProps nonvisualdrawingprops = dmlObjectFactory.createCTNonVisualDrawingProps();
        shapenvsppr.setCNvPr(nonvisualdrawingprops);
        nonvisualdrawingprops.setDescr("");
        nonvisualdrawingprops.setName("watermark");
        nonvisualdrawingprops.setId(4);
        // Create object for cNvSpPr
        CTNonVisualDrawingShapeProps nonvisualdrawingshapeprops = dmlObjectFactory.createCTNonVisualDrawingShapeProps();
        shapenvsppr.setCNvSpPr(nonvisualdrawingshapeprops);
        // Create object for nvPr
        NvPr nvpr = pmlObjectFactory.createNvPr();
        shapenvsppr.setNvPr(nvpr);

        return shape;
    }

    @Override
    public void addWatermark(File inputFile, File outputFile, WatermarkType watermarkType, String watermarkText) throws Exception {
        PresentationMLPackage presentationMLPackage = PresentationMLPackage.load(inputFile);

        int rotationForWatermarkStyle = getRotationForWatermarkStyle(watermarkType);
        
        // Create and add shape
        Shape watermark = getShapeOOP(rotationForWatermarkStyle, watermarkText);

        int slideCount = presentationMLPackage.getMainPresentationPart().getSlideCount();
        for (int i = 0; i < slideCount; i++) {
            System.out.println("\n Adding to Slide " + i);
            SlidePart slide = presentationMLPackage.getMainPresentationPart().getSlide(i);
            slide.getJaxbElement().getCSld().getSpTree().getSpOrGrpSpOrGraphicFrame().add(watermark);
        }

        // All done: save it
        presentationMLPackage.save(outputFile);
    }

    @Override
    public void removeWatermark(File inputFile, File outputFile) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) throws Exception {
        PowerPointWatermark ppw = new PowerPointWatermark();
        File inputFile = null;
        File outputFile = null;

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Microsoft PowerPoint 2007 - 2013 Presentation Files", "pptx");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Select Input File");
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            inputFile = chooser.getSelectedFile();
        }

        JFileChooser chooser2 = new JFileChooser();
        FileNameExtensionFilter filter2 = new FileNameExtensionFilter(
                "Microsoft PowerPoint 2007 - 2013 Presentation Files", "pptx");
        chooser2.setFileFilter(filter2);
        chooser2.setDialogTitle("Select Output File");
        int saveVal = chooser2.showSaveDialog(null);
        if (saveVal == JFileChooser.APPROVE_OPTION) {
            outputFile = chooser2.getSelectedFile();
        }

        ppw.addWatermark(inputFile, outputFile, WatermarkType.HORIZONTAL, "HIGHER MANAGEMENT ONLY");
    }

}
