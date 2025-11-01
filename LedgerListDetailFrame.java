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
public class LedgerListDetailFrame extends javax.swing.JFrame {

    /**
     * Creates new form LadgerListDetailsFrame
     */
    public LedgerListDetailFrame() {
        initComponents();
        
        
        
          
        TbatchGroup.setEditable(false);
        TbatchShift.setEditable(false);
        TseatListType.setEditable(false);
        TseatListId.setEditable(false);
        Trollno.setEditable(false);
        
        
        
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
            JComboProgId.removeAllItems();

            DepartmentBean beans = (DepartmentBean)(JComboDeptId.getSelectedItem());
            if(beans == null) return;

            try{
                    Vector v = DatabaseManager.getProgram(beans.getDeptId());

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
            JComboBatchYear.removeAllItems();
            TbatchShift.setText("");
            TbatchGroup.setText("");

            ProgramBean beans = (ProgramBean)(JComboProgId.getSelectedItem());
            if(beans == null) return;

            try{
                    Vector v = DatabaseManager.getBatch(beans.getProgId());

                    for(int i = 0; i < v.size(); i++){
                            JComboBatchYear.addItem(v.elementAt(i));
                    }
                    getShiftGroup();
            }catch(Exception ee){
                    ee.printStackTrace();
                    MainWorkPlace.Jpane("Error "+ee);
            }
    }


            /////***** getShift & Group Load *****/////
            private void getShiftGroup(){
                    BatchBean beans = (BatchBean)(JComboBatchYear.getSelectedItem());
                    if(beans == null)return;

                    TbatchShift.setText(Decode.shiftDecode(beans.getBatchShift()).toString());
                    TbatchGroup.setText(Decode.groupDecode(beans.getBatchGroup()).toString());
            }
            /////***** getShift & Group Load *****/////

    /////***** getBatch Load *****/////




    /////***** getPart Load *****/////
    private void getPart(){
            JComboParts.removeAllItems();

            BatchBean beans = (BatchBean)(JComboBatchYear.getSelectedItem());
            if(beans == null)return;

            try{
                    Vector v = DatabaseManager.getPart(beans.getBatchId());

                    for(int i = 0; i < v.size(); i++){
                            JComboParts.addItem(v.elementAt(i));
                    }
            }catch(Exception ee){
                    ee.printStackTrace();
                    MainWorkPlace.Jpane("Error "+ee);
            }


    }
    /////***** getPart Load *****/////

    /////***** getSeatList Load *****/////
    private void getSeatList(){
            JComboSeatListExamYear.removeAllItems();
            TseatListType.setText("");

            PartBean beans = (PartBean)(JComboParts.getSelectedItem());
            if(beans == null) return;

            try{
                    Vector v = DatabaseManager.getSeatList(beans.getPartId(), beans.getBatchId());

                    for(int i = 0; i < v.size(); i++){
                            JComboSeatListExamYear.addItem(v.elementAt(i));
                    }

                    getSeatListExamType();

            }catch(Exception ee){
                    ee.printStackTrace();
                    MainWorkPlace.Jpane("Error "+ee);
            }
    }

            /////***** getSeatListExamType Load *****/////
            private void getSeatListExamType(){
                    SeatListBean beans = (SeatListBean)(JComboSeatListExamYear.getSelectedItem());
                    if(beans == null) return;

                    TseatListType.setText(Decode.typeDecode(beans.getSeatListType()));
            }
            /////***** getSeatListExamType Load *****/////

    /////***** getSeatList Load *****/////



    /////***** getLedgerListDetails Load *****/////
    private void getLedgerListDetails(){

            jList.setListData(new Object[0]);

            SeatListBean beans = (SeatListBean)(JComboSeatListExamYear.getSelectedItem());
            if(beans == null)return;

            try{
                    Vector v = DatabaseManager.getLedgerListDetails(beans.getSeatListId());
                    jList.setListData(v);
            }catch(Exception ee){
                    ee.printStackTrace();
                    MainWorkPlace.Jpane(" getting Ledger List Details Error "+ee);
            }
    }
    /////***** getLedgerListDetails Load *****/////





    private void paste(){
            SeatListBean beans = (SeatListBean)(JComboSeatListExamYear.getSelectedItem());
            if(beans == null) return;

            try{
                    Vector v = DatabaseManager.getSeatListDetails(beans.getSeatListId());
                    int rows = 0;
                    // System.out.println(v.size());
                    for(int i = 0; i < v.size(); i++){
                            SeatListDetailBean slBeans = (SeatListDetailBean)v.elementAt(i);
                            if(slBeans == null) continue;
                            try{
                                    rows += DatabaseManager.addLedgerListDetails(beans.getSeatListId(), slBeans.getStuRollno(), "");
                            }catch(Exception ee){
                                    ee.printStackTrace();
                                    MainWorkPlace.Jpane(" Already Exit Error "+ee);
                            }
                    }
                    MainWorkPlace.queryResult((byte)rows, "Inserted");
                    if(rows >= 1){
                        clear();
                        getLedgerListDetails();
                    }
                    
            }catch(Exception ee){
                    ee.printStackTrace();
                    MainWorkPlace.Jpane(" Pasting Error "+ee);
            }

    }


    private void update(){
            Object ob[] = jList.getSelectedValues();
            int rows = 0;
            String remarks = remarksArea.getText();
            try{
                    for(int i = 0; i < ob.length; i++){
                            LedgerListDetailBean beans = (LedgerListDetailBean)(ob[i]);
                            rows += DatabaseManager.updateLedgerListDetails(beans.getSeatListId(), beans.getStuRollNo(), remarks);
                    }
                    MainWorkPlace.Jpane(rows+" Updated");
                    if(rows >= 1){
                        clear();
                        getLedgerListDetails();
                    }
            }catch(Exception ee){
                    ee.printStackTrace();
                    MainWorkPlace.Jpane("Something Wrong to Update "+ee.getMessage());
            }
    }


    private void delete(){

            Object ob[] = jList.getSelectedValues();
            int rows = 0;

            try{
                    for(int i = 0; i < ob.length; i++){
                            LedgerListDetailBean beans = (LedgerListDetailBean)(ob[i]);
                            rows += DatabaseManager.deleteLedgerListDetails(beans.getSeatListId(), beans.getStuRollNo());
                    }
                    MainWorkPlace.Jpane(rows+" Deleted");
                    if(rows >= 1){
                        clear();
                        getLedgerListDetails();
                    }
            }catch(Exception ee){
                    ee.printStackTrace();
                    MainWorkPlace.Jpane("It has Some Child Data "+ee.getMessage());
            }

    }


    private void clear(){
            jList.clearSelection();
            TseatListId.setText("");
            Trollno.setText("");
            remarksArea.setText("");
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
        JComboSeatListExamYear = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        TseatListType = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        TseatListId = new javax.swing.JTextField();
        Trollno = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList = new javax.swing.JList();
        paste = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Open Sans", 1, 24)); // NOI18N
        jLabel1.setText("Ledger List Details");

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
        jLabel12.setText("Exam Type");

        JComboSeatListExamYear.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        JComboSeatListExamYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboSeatListExamYearActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel16.setText("Exam Year");

        TseatListType.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel13.setText("Seat List ID");

        TseatListId.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        Trollno.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel14.setText("Roll Number");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(hint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(JComboDeptId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JComboBatchYear, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TbatchShift, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TbatchGroup, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JComboProgId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(JComboParts, 0, 196, Short.MAX_VALUE)
                                .addGap(277, 277, 277))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JComboSeatListExamYear, 0, 140, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addGap(13, 13, 13)
                                .addComponent(TseatListType, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(13, 13, 13)
                                .addComponent(TseatListId))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(13, 13, 13)
                                .addComponent(Trollno)))
                        .addGap(230, 230, 230))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(TseatListType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(233, 233, 233)
                        .addComponent(JComboSeatListExamYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(jLabel16)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(TseatListId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(Trollno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addComponent(hint, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delete)
                    .addComponent(clear)
                    .addComponent(back)
                    .addComponent(update))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        jLabel10.setText("Roll Number");

        jList.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList);

        paste.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        paste.setText("PASTING");
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
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(paste, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2)
                .addGap(18, 18, 18)
                .addComponent(paste)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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
        getPart();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboBatchYearActionPerformed

    private void JComboPartsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboPartsActionPerformed
        getSeatList();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboPartsActionPerformed

    private void JComboSeatListExamYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboSeatListExamYearActionPerformed
        getSeatListExamType();
        getLedgerListDetails();
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboSeatListExamYearActionPerformed

    private void jListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListValueChanged
        
        LedgerListDetailBean beans = (LedgerListDetailBean)(jList.getSelectedValue());
        if(beans == null) return;

        TseatListId.setText(""+beans.getSeatListId());
        Trollno.setText(""+beans.getStuRollNo());
        remarksArea.setText(""+beans.getRemarks());

        // TODO add your handling code here:
    }//GEN-LAST:event_jListValueChanged

    private void pasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteActionPerformed
        paste();
        // TODO add your handling code here:
    }//GEN-LAST:event_pasteActionPerformed

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
            java.util.logging.Logger.getLogger(LedgerListDetailFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LedgerListDetailFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LedgerListDetailFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LedgerListDetailFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LedgerListDetailFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox JComboBatchYear;
    private javax.swing.JComboBox JComboDeptId;
    private javax.swing.JComboBox JComboParts;
    private javax.swing.JComboBox JComboProgId;
    private javax.swing.JComboBox JComboSeatListExamYear;
    private javax.swing.JTextField TbatchGroup;
    private javax.swing.JTextField TbatchShift;
    private javax.swing.JTextField Trollno;
    private javax.swing.JTextField TseatListId;
    private javax.swing.JTextField TseatListType;
    private javax.swing.JButton back;
    private javax.swing.JButton clear;
    private javax.swing.JButton delete;
    private javax.swing.JLabel hint;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
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
