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
public class SeatListDetailBean {
    
    private int seatListId;
    private int partId;
    private int batchId;
    private String stuRollno;
    private String status;
    private String remarks;

    public String toString(){
        return stuRollno;
    }
    
    public int getSeatListId() {
        return seatListId;
    }

    public void setSeatListId(int seatListId) {
        this.seatListId = seatListId;
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

    public String getStuRollno() {
        return stuRollno;
    }

    public void setStuRollno(String stuRollno) {
        this.stuRollno = stuRollno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    
    
}
