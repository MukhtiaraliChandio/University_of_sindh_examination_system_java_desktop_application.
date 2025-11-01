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
public class Ac_Scheme_Detail_Bean {
    
    private int acId;
    private int schemeId;
    private int schemePart;
    private String courseNo;
    private String courseTite;
    private int creditHours;
    private int maxMarks;
    private String remarks;
    private int semester;
    private String isCreditable;

    public String toString(){
        return courseNo;
    }
    
    public int getAcId() {
        return acId;
    }

    public void setAcId(int acId) {
        this.acId = acId;
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

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getCourseTite() {
        return courseTite;
    }

    public void setCourseTite(String courseTite) {
        this.courseTite = courseTite;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(int maxMarks) {
        this.maxMarks = maxMarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getIsCreditable() {
        return isCreditable;
    }

    public void setIsCreditable(String isCreditable) {
        this.isCreditable = isCreditable;
    }
    
    
    
    
}
