/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceytechmarker.markers.interfaces;

import ceytechmarker.enums.WatermarkType;
import java.io.File;

/**
 *
 * @author isuru
 */
public interface WatermarkInterface {

    public void addWatermark(File inputFile, File outputFile, WatermarkType watermarkType, String watermarkText) throws Exception;

    public void removeWatermark(File inputFile, File outputFile) throws Exception;
}
