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
public class ProgramBean {
    
    private int deptId;
    private int progId;
    private int progDuration;
    private String progName;
    private String remarks;

    public String toString(){
            return progName;
    }

    public void setDeptId(int deptId){
            this.deptId = deptId;
    }

    public void setProgId(int progId){
            this.progId = progId;
    }

    public void setProgDuration(int progDuration){
            this.progDuration = progDuration;
    }

    public void setProgName(String progName){
            this.progName = progName;
    }

    public void setRemarks(String remarks){
            this.remarks = remarks;
    }

    public int getDeptId(){
            return deptId;
    }

    public int getProgId(){
            return progId;
    }

    public int getProgDuration(){
            return progDuration;
    }

    public String getProgName(){
            return progName;
    }

    public String getRemarks(){
            return remarks;
    }
    
}
