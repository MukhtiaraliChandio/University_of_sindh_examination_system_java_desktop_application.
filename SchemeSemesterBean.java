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
public class SchemeSemesterBean {
    private int schemeId;
    private int schemePart;
    private int semester;
    private String remarks;

    @Override
    public String toString(){
            return semester+"";
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
    
	public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getRemarks() {
        return remarks;
    }
}
