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
public class SchemeDetailBean {
    private int schemeId;
    private int schemePart;
    private int semester;
    private String courseNo;
    private String courseTitle;
    private int creditHours;
    private int maxMarks;
    private String isCreditable;

    @Override
    public String toString(){
            return courseTitle+" "+courseNo;
    }

    public void setSchemeId(int schemeId) {
        this.schemeId = schemeId;
    }

    public void setSchemePart(int schemePart) {
        this.schemePart = schemePart;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public void setMaxMarks(int maxMarks) {
        this.maxMarks = maxMarks;
    }

    public void setIsCreditable(String isCreditable) {
        this.isCreditable = isCreditable;
    }

	public int getSchemeId() {
        return schemeId;
    }

    public int getSchemePart() {
        return schemePart;
    }

    public int getSemester() {
        return semester;
    }

    public String getCourseNo() {
        return courseNo;
    }


    public String getCourseTitle() {
        return courseTitle;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public String getIsCreditable() {
        return isCreditable;
    }
    
    
    
}
