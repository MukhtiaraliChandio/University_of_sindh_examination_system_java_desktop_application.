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
public class LedgerDetailsBean {
    
    private int seatListId;
    private String stuRollNo;
    private int schemeId;
    private int schemePart;
    private int semester;
    private String courseNo;
    private String acId;
    private String marksObtain;
    private String grade;
    private int minMarks;
    private int qp;
    private String remarks;

    @Override
    public String toString(){
        return stuRollNo+" "+courseNo;
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

    public int getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(int schemeId) {
        this.schemeId = schemeId;
    }

    public int getSchemePart() {
        return schemePart;
    }

    public void setSchemePart(int schemePart) {
        this.schemePart = schemePart;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getAcId() {
        return acId;
    }

    public void setAcId(String acId) {
        this.acId = acId;
    }

    public String getMarksObtain() {
        return marksObtain;
    }

    public void setMarksObtain(String marksObtain) {
        this.marksObtain = marksObtain;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getMinMarks() {
        return minMarks;
    }

    public void setMinMarks(int minMarks) {
        this.minMarks = minMarks;
    }

    public int getQp() {
        return qp;
    }

    public void setQp(int qp) {
        this.qp = qp;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    

    
}
