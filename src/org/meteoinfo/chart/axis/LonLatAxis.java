/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.meteoinfo.chart.axis;

import java.util.ArrayList;
import java.util.List;
import org.meteoinfo.global.DataConvert;

/**
 *
 * @author wyq
 */
public class LonLatAxis extends Axis implements Cloneable {

    private boolean drawDegreeSymbol;
    private boolean longitude;
    
    /**
     * Constructor
     * @param label Axis label
     * @param isX Is x axis or not
     * @param longitude Is longitude or not
     */
    public LonLatAxis(String label, boolean isX, boolean longitude){
        super(label, isX);
        
        this.drawDegreeSymbol = true;
        this.longitude = longitude;
    }
    
    /**
     * Constructor
     * @param label Axis label
     * @param isX Is x axis or not
     */
    public LonLatAxis(String label, boolean isX){
        this(label, isX, isX);
    }
    
    /**
     * Get if draw degree symbol
     * @return Boolean
     */
    public boolean isDrawDegreeSymbol(){
        return this.drawDegreeSymbol;
    }

    /**
     * Set if draw degree symbol
     * @param value Boolean
     */
    public void setDrawDegreeSymbol(boolean value){
        this.drawDegreeSymbol = value;
    }      
    
    /**
     * Get is longitude or not
     * @return Longitude or not
     */
    public boolean isLongitude(){
        return this.longitude;
    }
    
    /**
     * Set is longitude or not
     * @param value Longitude or not
     */
    public void setLongitude(boolean value){
        this.longitude = value;
    }
    
    /**
     * Get tick labels
     *
     * @return Tick labels
     */
    @Override
    public List<String> updateTickLabels() {
        List<String> tls = new ArrayList<>();
        String lab;
        for (double v : this.getTickValues()) {
            double value = v;
            if (value > 180) {
                value = value - 360;
            }
            lab = String.valueOf(value);
            lab = DataConvert.removeTailingZeros(lab);
            if (this.isXAxis()) {
                if (value == -180) {
                    lab = "180";
                } else if (!(value == 0 || value == 180)) {
                    if (lab.substring(0, 1).equals("-")) {
                        lab = lab.substring(1) + "W";
                    } else {
                        lab = lab + "E";
                    }
                }
            } else {
                if (!(value == 0)) {
                    if (lab.substring(0, 1).equals("-")) {
                        lab = lab.substring(1) + "S";
                    } else {
                        lab = lab + "N";
                    }
                }
            }
            if (drawDegreeSymbol) {
                if (lab.endsWith("E") || lab.endsWith("W") || lab.endsWith("N") || lab.endsWith("S")) {
                    lab = lab.substring(0, lab.length() - 1) + String.valueOf((char) 186) + lab.substring(lab.length() - 1);
                } else {
                    lab = lab + String.valueOf((char) 186);
                }
            }
            tls.add(lab);
        }

        return tls;
    }
    
    @Override
    public Object clone() {
        return (LonLatAxis)super.clone();
    }
}
