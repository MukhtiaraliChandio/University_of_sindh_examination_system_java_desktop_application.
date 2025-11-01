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
public class LedgerListDetailBean {
    
    private int seatListId;
    private String stuRollNo;
    private String remarks;

    @Override
    public String toString(){
        return stuRollNo;
    }

    public int getSeatListId() {
        return seatListId;
    }

    public void setSeatListId(int seatListId) {
        this.seatListId = seatListId;
    }

    public String getStuRollNo() {
        return stuRollNo;
    }

    public void setStuRollNo(String stuRollNo) {
        this.stuRollNo = stuRollNo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    
    
    
}
