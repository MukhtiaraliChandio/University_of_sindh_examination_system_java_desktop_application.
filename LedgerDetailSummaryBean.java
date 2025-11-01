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
public class LedgerDetailSummaryBean {
    
    
    private int seatListId;
    private String stuRollNo;
    private int totalMarks;
    private String resultRemarks;
    private float percentage;
    private String indvResultAnnDate;
    private float cgpa;
    private int obtainMarks;
    private String noDuesRemarks;
    private String ac2No;
    private String ac2NoDated;
    private int qp;
    private int creditHours;

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

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public String getResultRemarks() {
        return resultRemarks;
    }

    public void setResultRemarks(String resultRemarks) {
        this.resultRemarks = resultRemarks;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public String getIndvResultAnnDate() {
        return indvResultAnnDate;
    }

    public void setIndvResultAnnDate(String indvResultAnnDate) {
        this.indvResultAnnDate = indvResultAnnDate;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    public int getObtainMarks() {
        return obtainMarks;
    }

    public void setObtainMarks(int obtainMarks) {
        this.obtainMarks = obtainMarks;
    }

    public String getNoDuesRemarks() {
        return noDuesRemarks;
    }

    public void setNoDuesRemarks(String noDuesRemarks) {
        this.noDuesRemarks = noDuesRemarks;
    }

    public String getAc2No() {
        return ac2No;
    }

    public void setAc2No(String ac2No) {
        this.ac2No = ac2No;
    }

    public String getAc2NoDated() {
        return ac2NoDated;
    }

    public void setAc2NoDated(String ac2NoDated) {
        this.ac2NoDated = ac2NoDated;
    }

    public int getQp() {
        return qp;
    }

    public void setQp(int qp) {
        this.qp = qp;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }
    
    
    
    
    
    
}
