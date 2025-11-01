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
public class PartBean {
    private int partId;
    private int batchId;
    private int partYear;
    private String remarks;
    
    @Override
    public String toString(){
        return "part "+partId+" "+partYear;
    }
	

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public int getPartYear() {
        return partYear;
    }

    public void setPartYear(int partYear) {
        this.partYear = partYear;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    
    
}
