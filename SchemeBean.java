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
public class SchemeBean {
    
    private int progId;
    private int schemeId;
    private int schemeYear;
    private int minMarks;
    private String groupDescription;
    private String remarks;

    @Override
    public String toString(){
            return schemeYear+" "+groupDescription; 
    }

    public void setProgId(int progId) {
        this.progId = progId;
    }
	
    public void setSchemeId(int schemeId) {
        this.schemeId = schemeId;
    }
	
    public void setSchemeYear(int schemeYear) {
        this.schemeYear = schemeYear;
    }

    public void setMinMarks(int minMarks) {
        this.minMarks = minMarks;
    }
	
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
	
	public int getProgId() {
        return progId;
    }

    public int getSchemeId() {
        return schemeId;
    }


    public int getSchemeYear() {
        return schemeYear;
    }

    public int getMinMarks() {
        return minMarks;
    }


    public String getGroupDescription() {
        return groupDescription;
    }

    public String getRemarks() {
        return remarks;
    }
    
}
