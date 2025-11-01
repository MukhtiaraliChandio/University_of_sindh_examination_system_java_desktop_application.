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
public class StudentPartBean {
    
    private int partId;
    private int batchId;
    private String stuRollNo;
    private String remarks;

    public String toString(){
        return stuRollNo;
    }

    public void setPartId(int partId){
        this.partId = partId;
    }

    public void setBatchId(int batchId){
        this.batchId = batchId;
    }

    public void setStuRollNo(String stuRollNo){
        this.stuRollNo = stuRollNo;
    }

    public void setRemarks(String remarks){
        this.remarks = remarks;
    }

    public int getPartId(){
        return partId;
    }

    public int getBatchId(){
        return batchId;
    }

    public String getStuRollNo(){
        return stuRollNo;
    }

    public String getRemarks(){
        return remarks;
    }

    
    
}
