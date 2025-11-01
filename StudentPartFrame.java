/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jframes.forms;

import BeansClasses.*;
import CustomClassess.*;
import java.util.Vector;
import universitysystem.DatabaseManager;

/**
 *
 * @author Salman Ahmed
 */
public class StudentPartFrame extends javax.swing.JFrame {

    /**
     * Creates new form StudentPart
     */
    public StudentPartFrame() {
        initComponents();
        
        getDepartmenrt();
        
    }
    
    
    
    



    /////***** getDepartmenrt Load *****/////
    private void getDepartmenrt(){
            try{
                    Vector v = DatabaseManager.getDepartment();
                    JComboDeptId.removeAllItems();

                    for(int i = 0; i < v.size(); i++){
                            JComboDeptId.addItem(v.elementAt(i));
                    }

            }catch(Exception ee){
                    ee.printStackTrace();
                    MainWorkPlace.Jpane("Error "+ee);
            }

    }
    /////***** getDepartmenrt Load *****/////


    /////***** getProgram Load *****/////
    private void getProgram(){
            DepartmentBean beans = (DepartmentBean)(JComboDeptId.getSelectedItem());
            if(beans == null) return;

            try{
                    Vector v = DatabaseManager.getProgram(beans.getDeptId());
                    JComboProgId.removeAllItems();

                    for(int i = 0; i < v.size(); i++){
                            JComboProgId.addItem(v.elementAt(i));
                    }

            }catch(Exception ee){
                    ee.printStackTrace();
                    MainWorkPlace.Jpane("Error "+ee);
            }

    }
    /////***** getProgram Load *****/////


    /////***** getBatch Load *****/////
    private void getBatch(){
            ProgramBean beans = (ProgramBean)(JComboProgId.getSelectedItem());
            if(beans == null) return;

            try{
                    Vector v = DatabaseManager.getBatch(beans.getProgId());
                    JComboBatchYear.removeAllItems();

                    for(int i = 0; i < v.size(); i++){
                            JComboBatchYear.addItem(v.elementAt(i));
                    }
                    getShiftGroup();
            }catch(Exception ee){
                    ee.printStackTrace();
                    MainWorkPlace.Jpane("Error "+ee);
            }

    }
    /////***** getBatch Load *****/////


    /////***** getShift & Group Load *****/////
    private void getShiftGroup(){
            BatchBean beans = (BatchBean)(JComboBatchYear.getSelectedItem());
            if(beans == null)return;

            TbatchShift.setText(Decode.shiftDecode(beans.getBatchShift()).toString());
            TbatchGroup.setText(Decode.groupDecode(beans.getBatchGroup()).toString());
    }
    /////***** getShift & Group Load *****/////


    /////***** getPart Load *****/////
    private void getPart(){
            BatchBean beans = (BatchBean)(JComboBatchYear.getSelectedItem());
            if(beans == null)return;

            try{
                    Vector v = DatabaseManager.getPart(beans.getBatchId());
                    JComboParts.removeAllItems();

                    for(int i = 0; i < v.size(); i++){
                            JComboParts.addItem(v.elementAt(i));
                    }

            }catch(Exception ee){
                    ee.printStackTrace();
                    MainWorkPlace.Jpane("Error "+ee);
            }

    }
    /////***** getPart Load *****/////


    /////***** getStudent Load *****/////
    private void getStudent(){
            BatchBean beans = (BatchBean)(JComboBatchYear.getSelectedItem());
            if(beans == null)return;

            try{
                    Vector v = DatabaseManager.getStudent(beans.getBatchId());
                    JComboStuRollNo.removeAllItems();

                    for(int i = 0; i < v.size(); i++){
                            JComboStuRollNo.addItem(v.elementAt(i));
                    }

            }catch(Exception ee){
                    ee.printStackTrace();
                    MainWorkPlace.Jpane("Error "+ee);
            }
    }
    /////***** getStudent Load *****/////


    /////***** getStudentPart Load *****/////
    private void getStudentPart(){

            PartBean beans = (PartBean)(JComboParts.getSelectedItem());
            if(beans == null)return;

            try{
                    Vector v = DatabaseManager.getStudentPart(beans.getPartId(), beans.getBatchId());
                    jList.setListData(v);

            }catch(Exception ee){
                    ee.printStackTrace();
                    MainWorkPlace.Jpane("Error "+ee);
            }

            // System.out.println(JComboStuRollNo.getItemCount());

    }
    /////***** getStudentPart Load *****/////


    
    
    
    


    private void paste(){
            PartBean beans = (PartBean)(JComboParts.getSelectedItem());
            if(beans == null)return;

            //// Number of Student Present in Roll Number JCombobox
            int numOfItems = JComboStuRollNo.getItemCount();
            int rows = 0;

            for(int i = 0; i < numOfItems; i++){
                    String rollno = JComboStuRollNo.getItemAt(i).toString();
                    try{
                            rows += DatabaseManager.addStudentPart(beans.getPartId(), beans.getBatchId(), rollno, "");					
                    }catch(Exception ex){
                            MainWorkPlace.Jpane("Data Already add in List "+ex.getMessage());
                    }
            }

            JComboStuRollNo.removeAllItems();
            MainWorkPlace.Jpane(rows+" Student Record Transfered");
            getStudentPart();


    }


    private void update(){
        try{
            StudentPartBean beans = (StudentPartBean)(jList.getSelectedValue());
            if(beans == null)return;

            int rows = 0;
            String remarks = remarksArea.getText();
            
            rows = DatabaseManager.updateStudentPart(beans.getPartId(), beans.getBatchId(), beans.getStuRollNo(), remarks);
            MainWorkPlace.Jpane(rows+" Updatede");
            if(rows >= 1){
                clear();
                getStudentPart();
            }
        }catch(Exception ee){
            ee.printStackTrace();
            MainWorkPlace.Jpane("Data ERROR "+ee);
        }
    }


    private void delete(){

        try{
            Object ob[] = jList.getSelectedValues();
            int rows = 0;


            for(int i = 0; i < ob.length; i++){
                StudentPartBean beans = (StudentPartBean)(ob[i]);
                rows += DatabaseManager.deleteStudentPart(beans.getPartId(), beans.getBatchId(), beans.getStuRollNo());
            }
            MainWorkPlace.Jpane(rows+" Deleted");
            if(rows >= 1){
                clear();
                getStudentPart();
            }
            }catch(Exception ee){
                ee.printStackTrace();
                MainWorkPlace.Jpane("Data has Some Child Member "+ee.getMessage());
            }
            // System.out.println(ob.length);
    }


    private void clear(){
            remarksArea.setText("");
            jList.clearSelection();
    }

    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        JComboDeptId = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        JComboProgId = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        update = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        back = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        remarksArea = new javax.swing.JTextArea();
        hint = new javax.swing.JLabel();
        JComboBatchYear = new javax.swing.JComboBox();
        TbatchShift = new javax.swing.JTextField();
        TbatchGroup = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        JComboParts = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        JComboStuRollNo = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList = new javax.swing.JList();
        paste = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Open Sans", 1, 24)); // NOI18N
        jLabel1.setText("Student Part");

        jLabel3.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel3.setText("Departments");

        JComboDeptId.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        JComboDeptId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboDeptIdActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel4.setText("Programs");

        JComboProgId.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        JComboProgId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboProgIdActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel5.setText("Batch Year");

        jLabel7.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel7.setText("Shift");

        jLabel8.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel8.setText("Group");

        jLabel9.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel9.setText("Remarks");

        update.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        update.setText("UPDATE");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        clear.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        clear.setText("CLEAR");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        delete.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        delete.setText("DELETE");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        back.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        back.setText("EXIT");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        remarksArea.setColumns(20);
        remarksArea.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        remarksArea.setRows(5);
        jScrollPane1.setViewportView(remarksArea);

        hint.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        hint.setForeground(new java.awt.Color(255, 0, 51));

        JComboBatchYear.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        JComboBatchYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBatchYearActionPerformed(evt);
            }
        });

        TbatchShift.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        TbatchGroup.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel11.setText("Part");

        JComboParts.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        JComboParts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboPartsActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel12.setText("Roll Number");

        JComboStuRollNo.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(update, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(clear, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(JComboProgId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JComboBatchYear, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addComponent(hint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TbatchShift, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TbatchGroup)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(JComboDeptId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(18, 18, 18)
                                        .addComponent(JComboParts, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(18, 18, 18)
                                        .addComponent(JComboStuRollNo, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(12, 12, 12))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(JComboDeptId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(JComboProgId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(JComboBatchYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(TbatchShift, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TbatchGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(JComboParts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(JComboStuRollNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(hint, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(update)
                    .addComponent(delete)
                    .addComponent(clear)
                    .addComponent(back))
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        jLabel10.setText("Students");

        jList.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList);

        paste.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        paste.setText("Pasting");
        paste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(paste, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(paste)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JComboDeptIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboDeptIdActionPerformed
        getProgram();
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboDeptIdActionPerformed

    private void JComboProgIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboProgIdActionPerformed
        getBatch();
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboProgIdActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        update();
        // TODO add your handling code here:
    }//GEN-LAST:event_updateActionPerformed

    private void pasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteActionPerformed
        paste();
        // TODO add your handling code here:
    }//GEN-LAST:event_pasteActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        clear();
        // TODO add your handling code here:
    }//GEN-LAST:event_clearActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        delete();
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_backActionPerformed

    private void JComboBatchYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboBatchYearActionPerformed
        getShiftGroup();
        getPart();
        //getStudent();
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboBatchYearActionPerformed

    private void JComboPartsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboPartsActionPerformed
        getStudentPart();
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboPartsActionPerformed

    private void jListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListValueChanged
        StudentPartBean beans = (StudentPartBean)(jList.getSelectedValue());
        if(beans == null)return;

        remarksArea.setText(beans.getRemarks());
		
        // TODO add your handling code here:
    }//GEN-LAST:event_jListValueChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentPartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentPartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentPartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentPartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentPartFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox JComboBatchYear;
    private javax.swing.JComboBox JComboDeptId;
    private javax.swing.JComboBox JComboParts;
    private javax.swing.JComboBox JComboProgId;
    private javax.swing.JComboBox JComboStuRollNo;
    private javax.swing.JTextField TbatchGroup;
    private javax.swing.JTextField TbatchShift;
    private javax.swing.JButton back;
    private javax.swing.JButton clear;
    private javax.swing.JButton delete;
    private javax.swing.JLabel hint;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton paste;
    private javax.swing.JTextArea remarksArea;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
