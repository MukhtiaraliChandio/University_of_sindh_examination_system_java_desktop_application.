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
public class StudentBean {
    private int stuId;
    private int batchId;
    private String stuName;
    private String stuFname;
    private String stuSurname;
    private String stuRollno;
    private String remarks;

    @Override
    public String toString(){
        return stuRollno;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuFname() {
        return stuFname;
    }

    public void setStuFname(String stuFname) {
        this.stuFname = stuFname;
    }

    public String getStuSurname() {
        return stuSurname;
    }

    public void setStuSurname(String stuSurname) {
        this.stuSurname = stuSurname;
    }

    public String getStuRollno() {
        return stuRollno;
    }

    public void setStuRollno(String stuRollno) {
        this.stuRollno = stuRollno;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    
    
}
