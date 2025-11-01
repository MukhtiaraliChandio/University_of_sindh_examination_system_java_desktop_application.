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
public class MainWorkPlace {
    public static void Jpane(String msg){
        javax.swing.JOptionPane.showMessageDialog(null, ""+msg);
    }

    public static void queryResult(int row, String msg){
        switch(row){
            case 0:
                MainWorkPlace.Jpane("This ID not in used");
            break;

            default:
                MainWorkPlace.Jpane(row+" "+msg);
        }
    }
}
