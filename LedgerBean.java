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
public class LedgerBean {
    
    private int seatListId;
    private int schemeId;
    private int schemePart;
    private String isAnn;
    private String AnnDate;
    private String remarks;

    public int getSeatListId() {
        return seatListId;
    }

    public void setSeatListId(int seatListId) {
        this.seatListId = seatListId;
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

    public String getIsAnn() {
        return isAnn;
    }

    public void setIsAnn(String isAnn) {
        this.isAnn = isAnn;
    }

    public String getAnnDate() {
        return AnnDate;
    }

    public void setAnnDate(String AnnDate) {
        this.AnnDate = AnnDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    
    
    
}
