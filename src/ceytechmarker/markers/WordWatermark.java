package ceytechmarker.markers;

import ceytechmarker.markers.interfaces.WatermarkInterface;
import ceytechmarker.enums.WatermarkType;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.docx4j.TraversalUtil;
import org.docx4j.XmlUtils;
import org.docx4j.finders.SectPrFinder;
import org.docx4j.jaxb.Context;
import org.docx4j.model.structure.SectionWrapper;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.Part;
import org.docx4j.openpackaging.parts.WordprocessingML.HeaderPart;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.openpackaging.parts.relationships.Namespaces;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.CTRel;
import org.docx4j.wml.Hdr;
import org.docx4j.wml.HdrFtrRef;
import org.docx4j.wml.HeaderReference;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.SectPr;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author isuru
 */
public class WordWatermark implements WatermarkInterface {

    private String watermarkText;
    private int watermarkRotationAngle;
    private ObjectFactory factory;

    public WordWatermark() {
        this.factory = Context.getWmlObjectFactory();
    }
    
    private int getRotationAngleForWatermarkType(WatermarkType watermarkType) {
        int rotationAngle = 0;
        switch (watermarkType) {
            case HORIZONTAL:
                rotationAngle = 0;
                break;

            case VERTICAL:
                rotationAngle = 90;
                break;

            case DIAGONAL_LEFT:
                rotationAngle = 45;
                break;

            case DIAGONAL_RIGHT:
                rotationAngle = 315;
                break;

            default:
                rotationAngle = 0; // return HORIZONTAL VALUE BY DEFAULT
        }
        return rotationAngle;
    }

    @Override
    public void addWatermark(File inputFile, File outputFile, WatermarkType watermarkType, String watermarkText) throws Exception {
        WordprocessingMLPackage wmlPackage = WordprocessingMLPackage.load(inputFile);

        this.watermarkRotationAngle = getRotationAngleForWatermarkType(watermarkType);
        this.watermarkText = watermarkText;
        
        Relationship relationship = createHeaderPart(wmlPackage);
        createHeaderReference(wmlPackage, relationship);
        wmlPackage.save(outputFile); // save modified file to the output file path
    }

    public Relationship createHeaderPart(
            WordprocessingMLPackage wordprocessingMLPackage)
            throws Exception {

        HeaderPart headerPart = new HeaderPart();
        Relationship rel = wordprocessingMLPackage.getMainDocumentPart()
                .addTargetPart(headerPart);

        Hdr hdr = getHdr(wordprocessingMLPackage, headerPart, watermarkText, watermarkRotationAngle);

        headerPart.setJaxbElement(hdr);

        return rel;
    }

    public void createHeaderReference(
            WordprocessingMLPackage wordprocessingMLPackage,
            Relationship relationship)
            throws InvalidFormatException {

        List<SectionWrapper> sections = wordprocessingMLPackage.getDocumentModel().getSections();

        SectPr sectPr = sections.get(sections.size() - 1).getSectPr();
        // There is always a section wrapper, but it might not contain a sectPr
        if (sectPr == null) {
            sectPr = factory.createSectPr();
            wordprocessingMLPackage.getMainDocumentPart().addObject(sectPr);
            sections.get(sections.size() - 1).setSectPr(sectPr);
        }

        HeaderReference headerReference = factory.createHeaderReference();
        headerReference.setId(relationship.getId());
        headerReference.setType(HdrFtrRef.DEFAULT);
        sectPr.getEGHdrFtrReferences().add(headerReference);
    }

    public Hdr getHdr(WordprocessingMLPackage wordprocessingMLPackage,
            Part sourcePart, String watermarkText, int rotationAngle) throws Exception {

        Hdr hdr = factory.createHdr();

        String openXML = "<w:p xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:w10=\"urn:schemas-microsoft-com:office:word\">"
                + "<w:pPr>"
                + "<w:pStyle w:val=\"Header\"/>"
                + "</w:pPr>"
                + "<w:sdt>"
                + "<w:sdtPr>"
                + "<w:id w:val=\"-1589924921\"/>"
                + "<w:lock w:val=\"sdtContentLocked\"/>"
                + "<w:docPartObj>"
                + "<w:docPartGallery w:val=\"Watermarks\"/>"
                + "<w:docPartUnique/>"
                + "</w:docPartObj>"
                + "</w:sdtPr>"
                + "<w:sdtEndPr/>"
                + "<w:sdtContent>"
                + "<w:r>"
                + "<w:rPr>"
                + "<w:noProof/>"
                + "<w:lang w:eastAsia=\"zh-TW\"/>"
                + "</w:rPr>"
                + "<w:pict>"
                + "<v:shapetype adj=\"10800\" coordsize=\"21600,21600\" id=\"_x0000_t136\" o:spt=\"136\" path=\"m@7,l@8,m@5,21600l@6,21600e\">"
                + "<v:formulas>"
                + "<v:f eqn=\"sum #0 0 10800\"/>"
                + "<v:f eqn=\"prod #0 2 1\"/>"
                + "<v:f eqn=\"sum 21600 0 @1\"/>"
                + "<v:f eqn=\"sum 0 0 @2\"/>"
                + "<v:f eqn=\"sum 21600 0 @3\"/>"
                + "<v:f eqn=\"if @0 @3 0\"/>"
                + "<v:f eqn=\"if @0 21600 @1\"/>"
                + "<v:f eqn=\"if @0 0 @2\"/>"
                + "<v:f eqn=\"if @0 @4 21600\"/>"
                + "<v:f eqn=\"mid @5 @6\"/>"
                + "<v:f eqn=\"mid @8 @5\"/>"
                + "<v:f eqn=\"mid @7 @8\"/>"
                + "<v:f eqn=\"mid @6 @7\"/>"
                + "<v:f eqn=\"sum @6 0 @5\"/>"
                + "</v:formulas>"
                + "<v:path o:connectangles=\"270,180,90,0\" o:connectlocs=\"@9,0;@10,10800;@11,21600;@12,10800\" o:connecttype=\"custom\" textpathok=\"t\"/>"
                + "<v:textpath fitshape=\"t\" on=\"t\"/>"
                + "<v:handles>"
                + "<v:h position=\"#0,bottomRight\" xrange=\"6629,14971\"/>"
                + "</v:handles>"
                + "<o:lock shapetype=\"t\" text=\"t\" v:ext=\"edit\"/>"
                + "</v:shapetype>"
                + "<v:shape fillcolor=\"silver\" id=\"PowerPlusWaterMarkObject357476642\" o:allowincell=\"f\" o:spid=\"_x0000_s2049\" stroked=\"f\" style=\"position:absolute;margin-left:0;margin-top:0;width:527.85pt;height:131.95pt;rotation:"+rotationAngle+";z-index:-251658752;mso-position-horizontal:center;mso-position-horizontal-relative:margin;mso-position-vertical:center;mso-position-vertical-relative:margin\" type=\"#_x0000_t136\">"
                + "<v:fill opacity=\".5\"/>"
                + "<v:textpath string=\""+watermarkText+"\" style=\"font-family:&quot;Calibri&quot;;font-size:1pt\"/>"
                + "<w10:wrap anchorx=\"margin\" anchory=\"margin\"/>"
                + "</v:shape>"
                + "</w:pict>"
                + "</w:r>"
                + "</w:sdtContent>"
                + "</w:sdt>"
                + "</w:p>";

        P p = (P) XmlUtils.unmarshalString(openXML);

        hdr.getContent().add(p);
        return hdr;
    }

    @Override
    public void removeWatermark(File inputFile, File outputFile) throws Exception {
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
                .load(inputFile);

        MainDocumentPart mdp = wordMLPackage.getMainDocumentPart();

        // Remove from sectPr
        SectPrFinder finder = new SectPrFinder(mdp);
        TraversalUtil traversalUtil = new TraversalUtil(mdp.getContent(), finder);
        
        String targetCtrelId = null;
        
        for (SectPr sectPr : finder.getOrderedSectPrList()) {
            List<CTRel> ctrs = sectPr.getEGHdrFtrReferences();
            CTRel ctrel = ctrs.get(ctrs.size() - 1);
            targetCtrelId = ctrel.getId();
            ctrs.remove(ctrel);
        }

        // Remove rels
        List<Relationship> hfRels = new ArrayList<Relationship>();
        for (Relationship rel : mdp.getRelationshipsPart().getRelationships().getRelationship()) {
            if (rel.getType().equals(Namespaces.HEADER)) {
                hfRels.add(rel);
            }
        }

        for (Relationship rel : hfRels) {
            if (targetCtrelId != null) {
                if (rel.getId() == targetCtrelId) {
                    mdp.getRelationshipsPart().removeRelationship(rel);
                }
            }
        }

        wordMLPackage.save(outputFile);
    }

}
