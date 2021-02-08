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
public enum WatermarkText {
    CONFIDENTIAL("CONFIDENTIAL"), COPY("COPY"), DRAFT("DRAFT"), 
    HIGHER_MANAGEMENT("HIGHER MANAGEMENT"), INTERNAL("INTERNAL"), 
    CANCELLED("CANCELLED"), REJECTED("REJECTED"), OPEN("OPEN"), 
    CUSTOM("CUSTOM");
    
    private String text;

    WatermarkText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
