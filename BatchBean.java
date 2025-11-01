/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exam.beans;

/**
 *
 * @author Salman Ahmed
 */
public class BatchBean {
    
    private int progId;
    private int batchId;
    private int batchYear;
    private String batchShift;
    private String batchGroup;
    private String remarks;

    @Override
    public String toString(){
        return ""+batchYear+" "+batchShift.charAt(0);
    }
    
    public int getProgId() {
        return progId;
    }

    public void setProgId(int progId) {
        this.progId = progId;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public int getBatchYear() {
        return batchYear;
    }

    public void setBatchYear(int batchYear) {
        this.batchYear = batchYear;
    }

    public String getBatchShift() {
        return batchShift;
    }

    public void setBatchShift(String batchShift) {
        this.batchShift = batchShift;
    }

    public String getBatchGroup() {
        return batchGroup;
    }

    public void setBatchGroup(String batchGroup) {
        this.batchGroup = batchGroup;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    
    
    
}
