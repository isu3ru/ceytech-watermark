/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceytechmarker.enums;

/**
 *
 * @author isuru
 */
public enum DocumentType {

    /**
     * DOCX Type
     */
    DOCX("DOCX", ".docx"),

    /**
     * XLSX Type
     */
    XLSX("XLSX", ".xlsx"),

    /**
     * PPTX Type
     */
    PPTX("PPTX", ".pptx"),

    /**
     * PDF Type
     */
    PDF("PDF", ".pdf");
    
    private String typeName;
    private String extension;

    DocumentType(String typeName, String extension) {
        this.typeName = typeName;
        this.extension = extension;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getExtension() {
        return extension;
    }

    
    
}
