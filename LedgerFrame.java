/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import BeansClasses.*;
import CustomClassess.*;
import java.util.Vector;
import javax.swing.JList;
import universitysystem.DatabaseManager;

/**
 *
 * @author Salman Ahmed
 */
public class LedgerFrame extends javax.swing.JFrame {

    /**
     * Creates new form LedgerFrame
     */
    public LedgerFrame() {
        initComponents();
        
        
        TbatchGroup.setEditable(false);
        TbatchShift.setEditable(false);
        TseatListExamType.setEditable(false);
        TschemeGroup.setEditable(false);
        
        
        JComboIsAnnounced.addItem("YES");
        JComboIsAnnounced.addItem("NO");

        getDepartmenrt(); 
    }
    
    




    /////***** Set SchemeYear from Batch Year/Batch Id *****/////
    private void setSchemeYear(){
        System.out.println("setSchemeYear() calling");
        BatchBean Bbeans = (BatchBean)(JComboBatchYear.getSelectedItem());
        if(Bbeans == null)return;

        try{

            for(int i = 0; i < JComboSchemeYear.getItemCount(); i++){
                SchemeBean Sbeans = null;
                Sbeans = (SchemeBean)(JComboSchemeYear.getItemAt(i));
                if(Sbeans == null) continue;

                System.out.println(Bbeans.getBatchYear()+" "+Sbeans.getSchemeYear());
                if(Bbeans.getBatchYear() == Sbeans.getSchemeYear()){
                        JComboSchemeYear.setSelectedItem(Sbeans);
                }
                else{
                        JComboSchemeYear.setSelectedIndex(0);
                }
            }


        }catch(Exception ee){
                ee.printStackTrace();
                MainWorkPlace.Jpane("Error "+ee);
        }
    }
    /////***** Set SchemeYear from Batch Year/Batch Id *****/////


    /////***** Set SchemePart from Part *****/////
    private void setSchemePart(){
        PartBean Pbeans = (PartBean)(JComboParts.getSelectedItem());
        if(Pbeans == null) return;

        try{

            for(int i = 0; i < JComboSchemePart.getItemCount(); i++){
                SchemePartBean Sbeans = null;
                Sbeans = (SchemePartBean)(JComboSchemePart.getItemAt(i));
                if(Sbeans == null)continue;

                System.out.println(Pbeans.getPartId()+" "+Sbeans.getSchemePart());
                if(Pbeans.getPartId() == Sbeans.getSchemePart()){
                        JComboSchemePart.setSelectedItem(Sbeans);
                }
                else{
                        JComboSchemePart.setSelectedIndex(0);
                }
            }

        }catch(Exception ee){
                ee.printStackTrace();
                MainWorkPlace.Jpane("Error "+ee);
        }
    }
    /////***** Set SchemePart from Part *****/////


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



    /////***** getScheme Load *****/////
    private void getScheme(){
        JComboSchemeYear.removeAllItems();
        TschemeGroup.setText("");

        ProgramBean beans = (ProgramBean)(JComboProgId.getSelectedItem());
        if(beans == null)return;

        try{
            Vector v = DatabaseManager.getScheme(beans.getProgId());

            for(int i = 0; i < v.size(); i++){
                JComboSchemeYear.addItem(v.elementAt(i));
            }
        }catch(Exception ee){
            ee.printStackTrace();
            MainWorkPlace.Jpane("Error "+ee);
        }
    }


    /////***** getSchemeGroup Load *****/////
    private void getSchemeGroup(){
        SchemeBean beans = (SchemeBean)(JComboSchemeYear.getSelectedItem());
        if(beans == null) return;

        TschemeGroup.setText(Decode.groupDecode(beans.getGroupDescription()));
    }
    /////***** getSchemeGroup Load *****/////


    /////***** getScheme Load *****/////




    /////***** getSchemePart Load *****/////
    private void getSchemePart(){
            JComboSchemePart.removeAllItems();

            SchemeBean beans = (SchemeBean)(JComboSchemeYear.getSelectedItem());
            if(beans == null) return;

            try{
                    Vector v = DatabaseManager.getSchemePart(beans.getSchemeId());

                    for(int i = 0; i < v.size(); i++){
                            JComboSchemePart.addItem(v.elementAt(i));
                    }

            }catch(Exception ee){
                    ee.printStackTrace();
                    MainWorkPlace.Jpane("Error "+ee);
            }
    }	
    /////***** getSchemePart Load *****/////




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
            TseatListExamType.setText("");

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

                    TseatListExamType.setText(Decode.typeDecode(beans.getSeatListType()));
            }
            /////***** getSeatListExamType Load *****/////

    /////***** getSeatList Load *****/////



    /////***** getLedger Load *****/////
    private void getLedger(){
            clear();
            SchemePartBean schemebeans = (SchemePartBean)(JComboSchemePart.getSelectedItem());
            SeatListBean seatbeans = (SeatListBean)(JComboSeatListExamYear.getSelectedItem());
            if(schemebeans == null | seatbeans == null) return;

            try{
                    LedgerBean ledgerbeans = DatabaseManager.getLedger(seatbeans.getSeatListId(), schemebeans.getSchemeId(), schemebeans.getSchemePart());
                    if(ledgerbeans == null)return;

                    JComboIsAnnounced.setSelectedItem(Decode.isCreditableDecode(ledgerbeans.getIsAnn()));
                    TannouncementDate.setText(ledgerbeans.getAnnDate());
                    ledgerRemarks.setText(ledgerbeans.getRemarks());

            }catch(Exception ee){
                    ee.printStackTrace();
                    MainWorkPlace.Jpane("Error "+ee);
            }

    }
    /////***** getLedger Load *****/////




    
    
    
    
    
    
    
    
    

    private void add(){
        try{
                   
            String annDate = (String)(TannouncementDate.getText());
            String remarks = (String)(ledgerRemarks.getText());
            String isAnn = (String)(Encode.isCreditableEncode(JComboIsAnnounced.getSelectedItem().toString()));
            
            SeatListBean beans = (SeatListBean)(JComboSeatListExamYear.getSelectedItem());
            if(beans == null) return;
            int seatListId = beans.getSeatListId();

            SchemePartBean spbeans = (SchemePartBean)(JComboSchemePart.getSelectedItem());
            if(spbeans == null) return;
            int schemeId = (int)(spbeans.getSchemeId());
            int schemePart = (int)(spbeans.getSchemePart());
            
            
            int row = DatabaseManager.addLedger(seatListId, schemeId, schemePart, isAnn, annDate, remarks);
            MainWorkPlace.queryResult(row, "Inserted");
            if(row >= 1){
                clear();
                getLedger();
            }
        }catch(Exception ee){
                ee.printStackTrace();
                MainWorkPlace.Jpane("Input Error "+ee.getMessage());
        }				
            
    }


    private void update(){
       
    }


    private void delete(){

    }


    private void clear(){

            JComboIsAnnounced.setSelectedIndex(0);
            TannouncementDate.setText("");
            ledgerRemarks.setText("");

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
        ledgerRemarks = new javax.swing.JTextArea();
        hint = new javax.swing.JLabel();
        JComboBatchYear = new javax.swing.JComboBox();
        TbatchShift = new javax.swing.JTextField();
        TbatchGroup = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        JComboParts = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        JComboSeatListExamYear = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        TseatListExamType = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        JComboSchemeYear = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        TschemeGroup = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        JComboSchemePart = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        TannouncementDate = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        JComboIsAnnounced = new javax.swing.JComboBox();
        add = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Open Sans", 1, 24)); // NOI18N
        jLabel1.setText("Ledger");

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

        ledgerRemarks.setColumns(20);
        ledgerRemarks.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        ledgerRemarks.setRows(5);
        jScrollPane1.setViewportView(ledgerRemarks);

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

        TseatListExamType.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel17.setText("Scheme Year");

        JComboSchemeYear.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        JComboSchemeYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboSchemeYearActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel13.setText("Group");

        TschemeGroup.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel18.setText("Scheme Part");

        JComboSchemePart.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        JComboSchemePart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboSchemePartActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel14.setText("Announcement Date");

        TannouncementDate.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel19.setText("Is Announced");

        JComboIsAnnounced.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        add.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        add.setText("ADD");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(448, 448, 448)
                        .addComponent(hint, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
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
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TbatchShift, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(TbatchGroup))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JComboProgId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(JComboIsAnnounced, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(JComboSchemePart, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(JComboSchemeYear, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(JComboSeatListExamYear, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(18, 18, 18)
                                        .addComponent(JComboParts, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TannouncementDate))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(jLabel13)
                                        .addGap(18, 18, 18)
                                        .addComponent(TschemeGroup))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TseatListExamType))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                    .addComponent(JComboParts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(236, 236, 236)
                                .addComponent(jLabel16)))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JComboSeatListExamYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(TseatListExamType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(TschemeGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(JComboSchemeYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(JComboSchemePart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(JComboIsAnnounced, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TannouncementDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addComponent(hint, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delete)
                    .addComponent(clear)
                    .addComponent(back)
                    .addComponent(update)
                    .addComponent(add))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        getScheme();
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
        getShiftGroup();
        getPart();
      
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboBatchYearActionPerformed

    private void JComboPartsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboPartsActionPerformed
        getSeatList();
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboPartsActionPerformed

    private void JComboSeatListExamYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboSeatListExamYearActionPerformed
        getSeatListExamType();
        getLedger();
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboSeatListExamYearActionPerformed

    private void JComboSchemeYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboSchemeYearActionPerformed
        getSchemeGroup();
        getSchemePart();
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboSchemeYearActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        add();
        // TODO add your handling code here:
    }//GEN-LAST:event_addActionPerformed

    private void JComboSchemePartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboSchemePartActionPerformed
        getLedger();
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboSchemePartActionPerformed

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
            java.util.logging.Logger.getLogger(LedgerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LedgerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LedgerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LedgerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LedgerFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox JComboBatchYear;
    private javax.swing.JComboBox JComboDeptId;
    private javax.swing.JComboBox JComboIsAnnounced;
    private javax.swing.JComboBox JComboParts;
    private javax.swing.JComboBox JComboProgId;
    private javax.swing.JComboBox JComboSchemePart;
    private javax.swing.JComboBox JComboSchemeYear;
    private javax.swing.JComboBox JComboSeatListExamYear;
    private javax.swing.JTextField TannouncementDate;
    private javax.swing.JTextField TbatchGroup;
    private javax.swing.JTextField TbatchShift;
    private javax.swing.JTextField TschemeGroup;
    private javax.swing.JTextField TseatListExamType;
    private javax.swing.JButton add;
    private javax.swing.JButton back;
    private javax.swing.JButton clear;
    private javax.swing.JButton delete;
    private javax.swing.JLabel hint;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea ledgerRemarks;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
