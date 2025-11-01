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
public class SchemePartBean {
    private int schemeId;
    private int schemePart;
    private String remarks;

    @Override
    public String toString(){
            return schemePart+"";
    }

    public void setSchemeId(int schemeId) {
        this.schemeId = schemeId;
    }

    public void setSchemePart(int schemePart) {
        this.schemePart = schemePart;
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

    public String getRemarks() {
        return remarks;
    }
}
