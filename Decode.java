/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomClassess;

/**
 *
 * @author Salman Ahmed
 */
public class Decode {
    public static String shiftDecode(String shift){
        if(shift.equals("M"))return "Morning";
        if(shift.equals("E"))return "Evening";
        if(shift.equals("N"))return "Noon";

        return shift;
    }

    public static String groupDecode(String group){
        if(group.equals("G"))return "General";
        if(group.equals("E"))return "Engineering";
        if(group.equals("M"))return "Medical";
        if(group.equals("C"))return "Commerce";

        return group;
    }

    public static String typeDecode(String type){
        if(type.equals("R"))return "Regular";
        if(type.equals("I"))return "Improver/Failiar";

        return type;
    }

    public static String statusDecode(String status){
        if(status.equals("T"))return "Temporary";
        if(status.equals("L"))return "Legal";

        return status;
    }


    public static String isCreditableDecode(String isCreditable){
        if(isCreditable.equals("Y"))return "YES";
        if(isCreditable.equals("N"))return "NO";

        return isCreditable;
    }
}
