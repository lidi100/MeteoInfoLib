/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.meteoinfo.global.colors;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.meteoinfo.global.MIMath;
import org.meteoinfo.legend.LegendManage;

/**
 *
 * @author wyq
 */
public class ColorMap {
    // <editor-fold desc="Variables">
    private Color[] colors;
    private String name = "";
    
    final static int GRADS_RAINBOW = 0;
    // </editor-fold>
    // <editor-fold desc="Constructor">
    /**
     * Constructor
     */
    public ColorMap(){
        colors = new Color[1];
        colors[0] = Color.red;
    }
    
    /**
     * Constructor
     * @param c Color
     */
    public ColorMap(Color c) {
        colors = new Color[1];
        colors[0] = c;
    }
    
    /**
     * Construct
     * @param n Color number
     */
    public ColorMap(int n){
        colors = new Color[n];
        int i;
        Random randomColor = new Random();
        
        for (i = 0; i < n; i++) {
            colors[i] = new Color(randomColor.nextInt(256),
                    randomColor.nextInt(256), randomColor.nextInt(256));
        }
    }
    
    /**
     * Constructor
     * @param cs Colors
     */
    public ColorMap(List<Color> cs){
        colors = new Color[cs.size()];
        for (int i = 0; i < cs.size(); i++)
            colors[i] = cs.get(i);
    }
    // </editor-fold>
    // <editor-fold desc="Get Set Methods">
    /**
     * Get name
     * @return Name
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Set name
     * @param value Name
     */
    public void setName(String value){
        this.name = value;
    }
    
    /**
     * Get colors
     * @return Colors
     */
    public Color[] getColors(){
        return this.colors;
    }
    
    /**
     * Set colors
     * @param value Colors
     */
    public void setColors(Color[] value){
        this.colors = value;
    }
    // </editor-fold>
    // <editor-fold desc="Methods">
    
    /**
     * Get color
     * @param idx Color index
     * @return Color
     */
    public Color getColor(int idx){
        return colors[idx];
    }
    
    /**
     * Set color
     * @param idx Color index
     * @param color The color
     */
    public void setColor(int idx, Color color){
        colors[idx] = color;
    }
    
    /**
     * Get color count
     * @return Color count
     */
    public int getColorCount(){
        return this.colors.length;
    }    
    
    /**
     * Get colors
     * @param n Color number
     * @return Colors
     */
    public Color[] getColors(int n){
        if (this.name.equalsIgnoreCase("grads_rainbow")){
            return this.gradsRainBowColors(n);
        } 
        
        if (this.colors.length < n){
            Color[] ncs = new Color[n];
            for (int i = 0; i < n; i++)
                ncs[i] = colors[0];
            
            return ncs;
        }
        
        Color[] ncs = new Color[n];
        int cn = this.colors.length;
        int gap = cn / n;
        if (gap == 0)
            gap = 1;
        
        int idx = 0;
        if (cn > n)
            idx = (cn % n) / 2;
        
        for (int i = 0; i < n; i++){
            ncs[i] = this.colors[idx];
            idx += gap;
            if (idx >= cn)
                idx = 0;
        }
        
//        if (cn > n){
//            ncs[0] = this.colors[0];
//            ncs[n - 1] = this.colors[cn - 1];
//        }

        return ncs;
    }
    
    /**
     * Create rainbow colors
     *
     * @param cNum Color number
     * @return Rainbow color array
     */
    private Color[] gradsRainBowColors(int cNum) {
        if (cNum > 13) {
            //return getRainBowColors_HSL(cNum);
            return LegendManage.getRainBowColors_HSV(cNum);
        }
        
        List<Color> colorList = new ArrayList<>();
        
        colorList.add(new Color(160, 0, 200));
        colorList.add(new Color(110, 0, 220));
        colorList.add(new Color(30, 60, 255));
        colorList.add(new Color(0, 160, 255));
        colorList.add(new Color(0, 200, 200));
        colorList.add(new Color(0, 210, 140));
        colorList.add(new Color(0, 220, 0));
        colorList.add(new Color(160, 230, 50));
        colorList.add(new Color(230, 220, 50));
        colorList.add(new Color(230, 175, 45));
        colorList.add(new Color(240, 130, 40));
        colorList.add(new Color(250, 60, 60));
        colorList.add(new Color(240, 0, 130));
        
        switch (cNum) {
            case 12:
                colorList.remove(new Color(0, 210, 140));
                break;
            case 11:
                colorList.remove(new Color(0, 210, 140));
                colorList.remove(new Color(30, 60, 255));
                break;
            case 10:
                colorList.remove(new Color(0, 210, 140));
                colorList.remove(new Color(30, 60, 255));
                colorList.remove(new Color(230, 175, 45));
                break;
            case 9:
                colorList.remove(new Color(0, 210, 140));
                colorList.remove(new Color(30, 60, 255));
                colorList.remove(new Color(230, 175, 45));
                colorList.remove(new Color(160, 230, 50));
                break;
            case 8:
                colorList.remove(new Color(0, 210, 140));
                colorList.remove(new Color(30, 60, 255));
                colorList.remove(new Color(230, 175, 45));
                colorList.remove(new Color(160, 230, 50));
                colorList.remove(new Color(110, 0, 220));
                break;
            case 7:
                colorList.remove(new Color(0, 210, 140));
                colorList.remove(new Color(30, 60, 255));
                colorList.remove(new Color(230, 175, 45));
                colorList.remove(new Color(160, 230, 50));
                colorList.remove(new Color(110, 0, 220));
                colorList.remove(new Color(0, 200, 200));
                break;
            case 6:
                colorList.remove(new Color(0, 210, 140));
                colorList.remove(new Color(30, 60, 255));
                colorList.remove(new Color(230, 175, 45));
                colorList.remove(new Color(160, 230, 50));
                colorList.remove(new Color(110, 0, 220));
                colorList.remove(new Color(0, 200, 200));
                colorList.remove(new Color(240, 130, 40));
                break;
            case 5:
                colorList.remove(new Color(0, 210, 140));
                colorList.remove(new Color(30, 60, 255));
                colorList.remove(new Color(230, 175, 45));
                colorList.remove(new Color(160, 230, 50));
                colorList.remove(new Color(110, 0, 220));
                colorList.remove(new Color(0, 200, 200));
                colorList.remove(new Color(240, 130, 40));
                colorList.remove(new Color(160, 0, 200));
                break;
        }
        
        Color[] cs = new Color[cNum];
        for (int i = 0; i < cNum; i++) {
            cs[i] = colorList.get(i);
        }
        
        return cs;
    }
    
    private void readFromFile(BufferedReader sr) throws IOException{
        String line = sr.readLine();
        String[] strs;
        Color color;
        List<Color> clist = new ArrayList<>();
        int r, g, b;
        int n = 0;
        boolean isdouble = false;
        while (line != null){
            line = line.trim();
            strs = line.split("\\s+");
            if (strs.length == 3){
                if (MIMath.isNumeric(strs[0])){
                    if (n == 0){
                        if (strs[0].contains("."))
                            isdouble = true;
                    }
                    if (isdouble){
                        r = (int)(Double.parseDouble(strs[0]) * 255);
                        g = (int)(Double.parseDouble(strs[1]) * 255);
                        b = (int)(Double.parseDouble(strs[2]) * 255);
                    } else {
                        r = Integer.parseInt(strs[0]);
                        g = Integer.parseInt(strs[1]);
                        b = Integer.parseInt(strs[2]);
                    }
                    color = new Color(r, g, b);
                    clist.add(color);
                    n += 1;
                }                
            }
                        
            line = sr.readLine();
        }
        sr.close();
        
        this.colors = new Color[clist.size()];
        for (int i = 0; i < clist.size(); i++)
            this.colors[i] = clist.get(i);
    }
    
    /**
     * Read from input stream
     * @param is Input stram
     * @throws IOException 
     */
    public void readFromFile(InputStream is) throws IOException{
        BufferedReader sr = new BufferedReader(new InputStreamReader(is));
        this.readFromFile(sr);
    }
    
    /**
     * Read colors from file
     * @param fileName The file name
     * @throws java.io.FileNotFoundException
     */
    public void readFromFile(String fileName) throws FileNotFoundException, IOException{
        BufferedReader sr = new BufferedReader(new FileReader(new File(fileName)));
        this.readFromFile(sr);
    }
    
    /**
     * Read colors from file
     * @param file The file
     * @throws java.io.FileNotFoundException
     */
    public void readFromFile(File file) throws FileNotFoundException, IOException{
        BufferedReader sr = new BufferedReader(new FileReader(file));
        this.readFromFile(sr);
    }
    // </editor-fold>
}
