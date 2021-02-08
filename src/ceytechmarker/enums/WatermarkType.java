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
public enum WatermarkType {
    HORIZONTAL("Horizontal"), VERTICAL("Vertical"), DIAGONAL_LEFT("Diagonal Left"), DIAGONAL_RIGHT("Diagonal Right");
    
    private String typeName;

    private WatermarkType(String typeName) {
        this.typeName = typeName;
    }

}
