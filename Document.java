/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usindhexaminationsystemproject;

import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import javax.swing.ImageIcon;

/**
 *
 * @author Salman Ahmed
 */
public class Document extends Component implements Printable{

    private final com.exam.beans.StudentBean bean[];
    private final int pageNo;
    private final int totalPages;
    private final int PIXIL_PER_INCH = 72;
    
    
    public Document(com.exam.beans.StudentBean bean[], int pageNo, int totalPages) {
        this.bean = bean;
        this.pageNo = pageNo;
        this.totalPages = totalPages;
    }
    
    
    
    
    @Override
    public int print(Graphics grphcs, PageFormat pf, int i) throws PrinterException {
        
        Graphics2D graphics2D = (Graphics2D)grphcs;
        graphics2D.translate(pf.getImageableX(), pf.getImageableY());
        
        int x = 0;
        int y = 0;
        
        y += pf.getImageableY();
        x += 3.00 * PIXIL_PER_INCH;
        
        /////***** LOGO IMAGE
        ImageIcon icon = new ImageIcon("D://logo.jpg");
        java.awt.Image image = icon.getImage();
        graphics2D.drawImage(image, x, y, this);
        
        graphics2D.setFont(new java.awt.Font("Open Sans", java.awt.Font.PLAIN, 33));
        graphics2D.setColor(Color.BLACK);
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        
        y += 2.444 * PIXIL_PER_INCH;
        x = (int) (1.444 * PIXIL_PER_INCH);
        graphics2D.drawString("UNIVERSITY OF SINDH", x, y);
        
        graphics2D.setFont(new java.awt.Font("Open Sans", java.awt.Font.PLAIN, 16));
        y += fontMetrics.getHeight();
        x += 0.833 * PIXIL_PER_INCH;
        graphics2D.drawString("ADMISSION LIST OF IICT 2017", x, y);
        
        
        graphics2D.setFont(new java.awt.Font("Open Sans", java.awt.Font.BOLD, 16));
        y += 0.844 * PIXIL_PER_INCH;
        
        int startX = x, startY = y;
        
                                                    x = (int) (0.211 * PIXIL_PER_INCH);
        graphics2D.drawString("SNo", x, y);         x += 0.627 * PIXIL_PER_INCH;
        graphics2D.drawString("Name", x, y);        x += 1.798 * PIXIL_PER_INCH;
        graphics2D.drawString("Father Name", x, y); x += 2.339 * PIXIL_PER_INCH;
        graphics2D.drawString("Surname", x, y);     x += 1.278 * PIXIL_PER_INCH;
        graphics2D.drawString("Roll Number", x, y);
        
        y += fontMetrics.getHeight();
        
        graphics2D.setFont(new java.awt.Font("Open Sans", java.awt.Font.PLAIN, 16));
        
        for(int z = 0; z < bean.length; z++){
            com.exam.beans.StudentBean stdBean = bean[z];
            
            
                                                                        x = (int) (0.211 * PIXIL_PER_INCH);
            graphics2D.drawLine(x, y - 25, (int)(7.744 * PIXIL_PER_INCH), y - 25);                                                            
            graphics2D.drawString(""+stdBean.getStuId(), x, y);         x += 0.627 * PIXIL_PER_INCH;
            graphics2D.drawString(""+stdBean.getStuName(), x, y);       x += 1.798 * PIXIL_PER_INCH;
            graphics2D.drawString(""+stdBean.getStuFname(), x, y);      x += 2.339 * PIXIL_PER_INCH;
            graphics2D.drawString(""+stdBean.getStuSurname(), x, y);    x += 1.278 * PIXIL_PER_INCH;
            graphics2D.drawString(""+stdBean.getStuRollno(), x, y);
            
            y += fontMetrics.getHeight() - 15;
            
        }
        
        graphics2D.drawRect(startX - 155, startY - 20, x + 110, y - 285);
        
        y += 10;
        
        graphics2D.drawString("Page No "+pageNo+" Of "+totalPages, x, y);
        
        
        return this.PAGE_EXISTS;
    }
    
}
