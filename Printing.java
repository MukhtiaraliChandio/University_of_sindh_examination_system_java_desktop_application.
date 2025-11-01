/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usindhexaminationsystemproject;
import com.exam.beans.*;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

/**
 *
 * @author Salman Ahmed
 */
public class Printing {

    
    private int PIXIL_PER_INCH = 72;
    
    public Printing() throws Exception {
        
        Book book = new Book();
        java.util.Vector v = DatabaseManager.getStudent();
        
        int numOfStudent = v.size();
        int studentPage = 10;
        int totalPage = numOfStudent / studentPage;
        int lastPage = numOfStudent % studentPage;
        int pageNo = 0;
        int studentCounter = -1;
       
        /////***** TOTAL PAGE 
        for(int page = 1; page <= totalPage; page++){
            
            System.out.println("works "+page);
            com.exam.beans.StudentBean bean[] = new com.exam.beans.StudentBean[studentPage];
            
            for(int i = 0; i < studentPage; i++){
                bean[i] = (com.exam.beans.StudentBean)v.elementAt(++studentCounter);
                bean[i].setStuId(studentCounter + 1);
            }
            
            Paper paper = new Paper();
            PageFormat pageFormat = getPageFormat(paper);
            Document doc = new Document(bean, ++pageNo, totalPage+ (lastPage>=1 ? 1:0));
            
            book.append(doc, pageFormat);
        }
        
        com.exam.beans.StudentBean bean[] = new com.exam.beans.StudentBean[lastPage];
        for(int i = 0; i < bean.length; i++){
            bean[i] = (com.exam.beans.StudentBean)v.elementAt(++studentCounter);
            bean[i].setStuId(studentCounter + 1);
        }
        
        Paper paper = new Paper();
        PageFormat pageFormat = getPageFormat(paper);
        Document doc = new Document(bean, ++pageNo, totalPage+ (lastPage>=1?1:0));
        book.append(doc, pageFormat);
        
        
        
        /////***** TOTAL PAGE 
        
        
        
        
        
        
        
      
        
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPageable(book);

        if(printerJob.printDialog()){
            printerJob.print();
        }
        
        
    }
    
      private PageFormat getPageFormat(Paper paper){
        PageFormat pageFormat = new PageFormat();
        
        paper.setSize(8.264*PIXIL_PER_INCH, 11.694*PIXIL_PER_INCH);
        
        double bleed = 0.153*PIXIL_PER_INCH;
        
        double width = paper.getWidth() - (bleed + bleed);
        double heigth = paper.getHeight()- (bleed + bleed);
               
        paper.setImageableArea(bleed, bleed, width, heigth);
        
        pageFormat.setPaper(paper);
        pageFormat.setOrientation(PageFormat.PORTRAIT);
        return pageFormat;
    }
    
    
    
}
