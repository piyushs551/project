/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author piyush
 */
public class Bean {
    
    private String title;
    
    private double rankSc;
    
    private String path;
    
    private int numFile;

    public int getNumFile() {
        return numFile;
    }

    public void setNumFile(int numFile) {
        this.numFile = numFile;
    }
    
    List<String> content = new ArrayList<>(); 

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRankSc() {
        return rankSc;
    }

    public void setRankSc(double rankSc) {
        this.rankSc = rankSc;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
    
}
