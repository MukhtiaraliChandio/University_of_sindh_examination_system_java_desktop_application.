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
public class SeatListBean {
    
    private int seatListId;
    private int batchId;
    private int partId;
    private int seatListYear;
    private String seatListType;
    private String remarks;

    @Override
    public String toString(){
        return ""+seatListYear+" "+seatListType;
    }
    
    public int getSeatListId() {
        return seatListId;
    }

    public void setSeatListId(int seatListId) {
        this.seatListId = seatListId;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public int getSeatListYear() {
        return seatListYear;
    }

    public void setSeatListYear(int seatListYear) {
        this.seatListYear = seatListYear;
    }

    public String getSeatListType() {
        return seatListType;
    }

    public void setSeatListType(String seatListType) {
        this.seatListType = seatListType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    
    
}
