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
public class Encode {
    public static String shiftEncode(String shift){
        if(shift.equals("Morning"))return "M";
        if(shift.equals("Evening"))return "E";
        if(shift.equals("Noon"))return "N";

        return shift;
    }
	
    public static String groupEncode(String group){
        if(group.equals("General"))return "G";
        if(group.equals("Engineering"))return "E";
        if(group.equals("Medical"))return "M";
        if(group.equals("Commerce"))return "C";

        return group;
    }
	
    public static String typeEncode(String type){
        if(type.equals("Regular"))return "R";
        if(type.equals("Improver/Failiar"))return "I";

        return type;
    }


    public static String statusEncode(String status){
        if(status.equals("Temporary"))return "T";
        if(status.equals("Legal"))return "L";

        return status;
    }

    public static String isCreditableEncode(String isCreditable){
        if(isCreditable.equals("YES"))return "Y";
        if(isCreditable.equals("NO"))return "N";

        return isCreditable;
    }
}
