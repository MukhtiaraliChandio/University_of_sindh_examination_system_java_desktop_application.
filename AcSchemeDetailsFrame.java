/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jframes.forms;


import CustomClassess.Decode;
import CustomClassess.MainWorkPlace;
import java.util.Vector;
import  usindhexaminationsystemproject.*;

/**
 *
 * @author Salman Ahmed
 */
public class AcSchemeDetailsFrame extends javax.swing.JFrame {

    /**
     * Creates new form AcSchemeDetailsFrame
     */
    
    
    boolean updateSubmition = false;
    boolean deleteSubmition = false;
    
    
    public AcSchemeDetailsFrame() {
        initComponents();
        
        Tgroup.setEditable(false);
        TminMarks.setEditable(false);
        TacId.setEditable(false);
        
        
        getFaculty();
        getDepartment();
        getProgram();
        getScheme();
        getSchemePart();
        getSchemeSemester();
        getConcentrationArea();
        getAcSchemeDetails();
        
        
        ////**** creditHours ****////
        for(int i =0; i < 10; i++){
            JComboCreditHours.addItem(i+"");
        }
        ////**** creditHours ****////


        ////**** isCreditable ****////
        JComboIsCreditable.addItem("YES");
        JComboIsCreditable.addItem("NO");
        ////**** isCreditable ****////
        
        
    }
    
    
    
    
    
    
    
    

    /////***** getFaculty Load *****/////
    private void getFaculty(){
            try{
                    Vector v = DatabaseManager.getFaculty();
                    JComboFacId.removeAllItems();

                    for(int i = 0; i < v.size(); i++){
                            JComboFacId.addItem(v.elementAt(i));
                    }

            }catch(Exception ee){
                    ee.printStackTrace();
                    MainWorkPlace.Jpane("Error "+ee);
            }
    }
    /////***** getFaculty Load *****/////


    /////***** getDepartment Load *****/////
    private void getDepartment(){
            FacultyBean beans = (FacultyBean)(JComboFacId.getSelectedItem());
            if(beans == null) return;

            try{
                    Vector v = DatabaseManager.getDepartment(beans.getFacId());
                    JComboDeptId.removeAllItems();

                    for(int i = 0; i < v.size(); i++){
                            JComboDeptId.addItem(v.elementAt(i));
                    }

            }catch(Exception ee){
                    ee.printStackTrace();
                    MainWorkPlace.Jpane("Error "+ee);
            }

    }
    /////***** getDepartment Load *****/////


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


    /////***** getScheme Load *****/////
    private void getScheme(){
            TminMarks.setText("");
            Tgroup.setText("");
            jList.setListData(new Object[0]);
            ProgramBean beans = (ProgramBean)(JComboProgId.getSelectedItem());
            if(beans == null)return;

            try{
                    Vector v = DatabaseManager.getScheme(beans.getProgId());
                    JComboSchemeYear.removeAllItems();

                    for(int i = 0; i < v.size(); i++){
                            JComboSchemeYear.addItem(v.elementAt(i));
                    }
                    getMarksGroup();

            }catch(Exception ee){
                    ee.printStackTrace();
                    MainWorkPlace.Jpane("Error "+ee);
            }
    }

    private void getMarksGroup(){
            SchemeBean beans = (SchemeBean)(JComboSchemeYear.getSelectedItem());
            if(beans == null)return;

            TminMarks.setText(""+beans.getMinMarks());
            Tgroup.setText(""+Decode.groupDecode(beans.getGroupDescription()));
    }
    /////***** getScheme Load *****/////


    /////***** getSchemePart Load *****/////
    private void getSchemePart(){
            SchemeBean beans = (SchemeBean)(JComboSchemeYear.getSelectedItem());
            if(beans == null) return;

            try{
                    Vector v = DatabaseManager.getSchemePart(beans.getSchemeId());
                    JComboSchemePart.removeAllItems();

                    for(int i = 0; i < v.size(); i++){
                            JComboSchemePart.addItem(v.elementAt(i));
                    }


            }catch(Exception ee){
                    ee.printStackTrace();
                    MainWorkPlace.Jpane("Error "+ee);
            }
    }
    /////***** getSchemePart Load *****/////

    
    /////***** getSchemeSemester Load *****/////
    private void getSchemeSemester(){
        SchemePartBean beans = (SchemePartBean)(JComboSchemePart.getSelectedItem());
        if(beans == null) return;

        try{
            Vector v = DatabaseManager.getSchemeSemester(beans.getSchemePart(), beans.getSchemeId());
            for(int i = 0; i < v.size(); i++){
                JComboSemester.addItem(v.elementAt(i));
            }
        }catch(Exception ee){
            ee.printStackTrace();
            MainWorkPlace.Jpane("Error "+ee);
        }
    }

    /////***** getSchemeSemester Load *****/////
    

    /////***** getConcentrationArea Load *****/////
    private void getConcentrationArea(){
            SchemePartBean beans = (SchemePartBean)(JComboSchemePart.getSelectedItem());
            if(beans == null) return;

            try{
                    Vector v = DatabaseManager.getConcArea(beans.getSchemeId(), beans.getSchemePart());
                    JComboConcAreaTitle.removeAllItems();

                    for(int i = 0; i < v.size(); i++){
                            JComboConcAreaTitle.addItem(v.elementAt(i));
                    }
                    
            }catch(Exception ee){
                    ee.printStackTrace();
                    MainWorkPlace.Jpane("Error "+ee);
            }
            getAcId();
    } 
    
    
    /////***** getAcId Load *****/////
    private void getAcId(){
        Conc_Area_Bean beans = (Conc_Area_Bean)(JComboConcAreaTitle.getSelectedItem());
        if(beans == null) return;
        
        TacId.setText(beans.getAcId()+"");
    }
    /////***** getAcId Load *****/////
    
    /////***** getConcentrationArea Load *****/////
    
    
    /////***** get AcSchemeDetails Load *****/////
    private void getAcSchemeDetails(){
        try{
            Vector v = DatabaseManager.getAcScheme();
            jList.setListData(v);
        }catch(Exception ee){
            ee.printStackTrace();
            MainWorkPlace.Jpane("Error "+ee);
        }
    }
    
    /////***** get AcSchemeDetails Load *****/////


    
    
    
    
    
    
    
    
    
    
    


    private void add(){
            if(TcourseNo.getText().equals("")){
                    setHint("Course Number Requird!...");
            }
            else if(TcourseTitle.getText().equals("")){
                    setHint("Course Title Requird!...");
            }
            else if(TmaxMarks.getText().equals("")){
                    setHint("Max Marks Requird!...");
            }
            else if(remarksArea.getText().equals("")){
                    setHint("Description Requird!...");
            }
            else{

                    boolean bool = true;
                    int acId = -1;
                    int schemeId = -1;
                    int schemePart = -1;
                    String courseNo = TcourseNo.getText();
                    String courseTitle = TcourseTitle.getText();
                    int maxMarks = 0;
                    int creditHours = 0;
                    int semester = -1;
                    String isCreditable = "";
                    String remarks = remarksArea.getText();

                    /////**** Foreign Keys ****/////
                    Conc_Area_Bean beans = (Conc_Area_Bean)(JComboConcAreaTitle.getSelectedItem());
                    if(beans == null) return;

                    try{
                            acId = (int)(beans.getAcId());
                            schemeId = (int)(beans.getSchemeId());
                            schemePart = (int)(beans.getSchemePart());
                    }catch(Exception ee){
                            bool = false;
                            ee.printStackTrace();
                            setHint("Scheme Part must be Selected!...");
                    }
                    /////**** Foreign Keys ****/////
                    
                    
                    /////**** Get MaxMarks ****/////
                    try{
                        maxMarks = Integer.parseInt(TmaxMarks.getText());
                    }catch(Exception ee){
                            bool = false;
                            ee.printStackTrace();
                            setHint("Max Marks must be in Number...");
                    }
                    /////**** Get MaxMarks ****/////
                    
                    
                    /////**** Get Credit Hours ****/////
                    try{
                        creditHours = (int)(Integer.parseInt((String) JComboCreditHours.getSelectedItem()));
                    }catch(Exception ee){
                            bool = false;
                            ee.printStackTrace();
                            setHint("Credit Hours ERROR...");
                    }
                    /////**** Get Credit Hours ****/////
                    
                    
                    /////**** Get Semester ****/////
                    try{
                        SchemeSemesterBean ssbeans = (SchemeSemesterBean)JComboSemester.getSelectedItem();
                        semester = ssbeans.getSemester();
                    }catch(Exception ee){
                            bool = false;
                            ee.printStackTrace();
                            setHint("Semester ERROR...");
                    }
                    /////**** Get Semester ****/////
                    
                    /////**** Get IsCreditable ****/////
                    try{
                        isCreditable = (String) JComboIsCreditable.getSelectedItem();
                    }catch(Exception ee){
                            bool = false;
                            ee.printStackTrace();
                            setHint("Is Creditable ERROR...");
                    }
                    /////**** Get IsCreditable ****/////

                    
                    
                    
                    if(bool){
                            try{
                                    byte row = (byte)(DatabaseManager.addAcScheme(acId, schemeId, schemePart, courseNo, courseTitle, creditHours, maxMarks, remarks, semester, isCreditable));
                                    MainWorkPlace.queryResult(row, "Inserted");
                            }catch(Exception ee){
                                    ee.printStackTrace();
                                    MainWorkPlace.Jpane("ERROR "+ee.getMessage());
                            }				
                            clear();
                            getConcentrationArea();
                    }

            }
    }


    private void update(){
        
        if(updateSubmition){
        
           if(TcourseNo.getText().equals("")){
                    setHint("Course Number Requird!...");
            }
            else if(TcourseTitle.getText().equals("")){
                    setHint("Course Title Requird!...");
            }
            else if(TmaxMarks.getText().equals("")){
                    setHint("Max Marks Requird!...");
            }
            else if(remarksArea.getText().equals("")){
                    setHint("Description Requird!...");
            }
            else{

                    boolean bool = true;
                    int acId = -1;
                    int schemeId = -1;
                    int schemePart = -1;
                    String courseNo = TcourseNo.getText();
                    String courseTitle = TcourseTitle.getText();
                    int maxMarks = 0;
                    int creditHours = 0;
                    int semester = -1;
                    String isCreditable = "";
                    String remarks = remarksArea.getText();

                    /////**** Foreign Keys ****/////
                    Conc_Area_Bean beans = (Conc_Area_Bean)(JComboSchemePart.getSelectedItem());
                    if(beans == null) return;

                    try{
                            acId = (int)(beans.getAcId());
                            schemeId = (int)(beans.getSchemeId());
                            schemePart = (int)(beans.getSchemePart());
                    }catch(Exception ee){
                            bool = false;
                            ee.printStackTrace();
                            setHint("Scheme Part must be Selected!...");
                    }
                    /////**** Foreign Keys ****/////
                    
                    
                    /////**** Get MaxMarks ****/////
                    try{
                        maxMarks = Integer.parseInt(TmaxMarks.getText());
                    }catch(Exception ee){
                            bool = false;
                            ee.printStackTrace();
                            setHint("Max Marks must be in Number...");
                    }
                    /////**** Get MaxMarks ****/////
                    
                    
                    /////**** Get Credit Hours ****/////
                    try{
                        creditHours = (int)(Integer.parseInt((String) JComboCreditHours.getSelectedItem()));
                    }catch(Exception ee){
                            bool = false;
                            ee.printStackTrace();
                            setHint("Credit Hours ERROR...");
                    }
                    /////**** Get Credit Hours ****/////
                    
                    
                    /////**** Get Semester ****/////
                    try{
                        semester = (int)(Integer.parseInt((String) JComboSemester.getSelectedItem()));
                    }catch(Exception ee){
                            bool = false;
                            ee.printStackTrace();
                            setHint("Semester ERROR...");
                    }
                    /////**** Get Semester ****/////
                    
                    /////**** Get IsCreditable ****/////
                    try{
                        isCreditable = (String) JComboSemester.getSelectedItem();
                    }catch(Exception ee){
                            bool = false;
                            ee.printStackTrace();
                            setHint("Is Creditable ERROR...");
                    }
                    /////**** Get IsCreditable ****/////
                    
                    if(bool){
                            try{
                                    byte row = (byte)(DatabaseManager.updateAcScheme(acId, schemeId, schemePart, courseNo, courseTitle, creditHours, maxMarks, remarks, semester, isCreditable));
                                    MainWorkPlace.queryResult(row, "Inserted");
                            }catch(Exception ee){
                                    ee.printStackTrace();
                                    MainWorkPlace.Jpane("ERROR "+ee.getMessage());
                            }				
                            clear();
                            getConcentrationArea();
                    }

            }
        }
    }


    private void delete(){
        if(deleteSubmition){
            boolean bool = true;
            int acId = -1;
            String courseNo = "";
            int schemeId = -1;
            int schemePart = -1;

           /////**** Foreign Keys ****/////
                    
                Ac_Scheme_Detail_Bean beans = (Ac_Scheme_Detail_Bean)(jList.getSelectedValue());
                if(beans == null) return;

                try{
                        schemeId = (int)(beans.getSchemeId());
                        schemePart = (int)(beans.getSchemePart());
                        acId = (int)(beans.getAcId());
                        courseNo = (String)(beans.getCourseNo());
                }catch(Exception ee){
                        bool = false;
                        ee.printStackTrace();
                        setHint("Scheme Part must be Selected!...");
                }
                
            /////**** Foreign Keys ****/////

            if(bool){
                try{
                    byte row = (byte)(DatabaseManager.deleteAcScheme(acId, schemeId, schemePart, courseNo));
                    MainWorkPlace.queryResult(row, "Deleted");
                }catch(Exception ee){
                    ee.printStackTrace();
                    MainWorkPlace.Jpane(""+ee.getMessage());
                }				
                clear();
                getConcentrationArea();
            }

        }/// End If deleteSubmition
    }


    private void clear(){
        
        jList.clearSelection();
        TcourseNo.setText("");
        TcourseTitle.setText("");
        TmaxMarks.setText("");
        remarksArea.setText("");
        
        JComboCreditHours.setSelectedIndex(0);
        JComboSemester.setSelectedIndex(0);
        JComboIsCreditable.setSelectedIndex(0);
        
        updateSubmition = false;
        deleteSubmition = false;
    }



    private void setHint(String msg){
            hint.setText(msg);
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
        jLabel2 = new javax.swing.JLabel();
        JComboFacId = new javax.swing.JComboBox();
        JComboDeptId = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        JComboProgId = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Tgroup = new javax.swing.JTextField();
        TminMarks = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        JComboSchemePart = new javax.swing.JComboBox();
        add = new javax.swing.JButton();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        back = new javax.swing.JButton();
        hint = new javax.swing.JLabel();
        JComboSchemeYear = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        TacId = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        remarksArea = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        TcourseNo = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        TcourseTitle = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        JComboCreditHours = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        TmaxMarks = new javax.swing.JTextField();
        JComboConcAreaTitle = new javax.swing.JComboBox();
        JComboSemester = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        JComboIsCreditable = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Open Sans", 1, 24)); // NOI18N
        jLabel1.setText("AC Scheme Details");

        jPanel2.setBackground(new java.awt.Color(243, 243, 243));

        jLabel2.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel2.setText("Faculty");

        JComboFacId.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        JComboFacId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboFacIdActionPerformed(evt);
            }
        });

        JComboDeptId.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        JComboDeptId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboDeptIdActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel3.setText("Departments");

        jLabel4.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel4.setText("Programs");

        JComboProgId.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        JComboProgId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboProgIdActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel5.setText("Scheme Year");

        jLabel6.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel6.setText("Group");

        Tgroup.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        TminMarks.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel7.setText("Min Marks");

        jLabel8.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel8.setText("Scheme Part");

        JComboSchemePart.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        JComboSchemePart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboSchemePartActionPerformed(evt);
            }
        });

        add.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        add.setText("ADD");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        update.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        update.setText("UPDATE");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        delete.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        delete.setText("DELETE");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        clear.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        clear.setText("CLEAR");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        back.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        back.setText("EXIT");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        hint.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        hint.setForeground(new java.awt.Color(255, 0, 51));

        JComboSchemeYear.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        JComboSchemeYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboSchemeYearActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel11.setText("AC ID");

        TacId.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel12.setText("Remarks");

        remarksArea.setColumns(20);
        remarksArea.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        remarksArea.setRows(5);
        jScrollPane5.setViewportView(remarksArea);

        jLabel13.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel13.setText("Title");

        TcourseNo.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel14.setText("Cource No");

        TcourseTitle.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel15.setText("Cource Title");

        jLabel16.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel16.setText("Credit Hours");

        JComboCreditHours.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel17.setText("Max Marks");

        TmaxMarks.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        JComboConcAreaTitle.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        JComboConcAreaTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboConcAreaTitleActionPerformed(evt);
            }
        });

        JComboSemester.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel18.setText("Semester");

        jLabel19.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel19.setText("Is Creditable");

        JComboIsCreditable.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(479, 479, 479)
                        .addComponent(hint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(JComboDeptId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(JComboProgId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(JComboFacId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JComboSchemeYear, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(JComboSchemePart, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(Tgroup)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TminMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TcourseNo))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TcourseTitle))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(JComboConcAreaTitle, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TacId, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(0, 6, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel16)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(JComboCreditHours, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel17)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(TmaxMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel18)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(JComboSemester, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel19)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(JComboIsCreditable, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(3, 3, 3)))
                                .addGap(43, 43, 43)))
                        .addGap(12, 12, 12))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(JComboFacId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
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
                    .addComponent(JComboSchemeYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Tgroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(TminMarks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(JComboSchemePart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(TacId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(JComboConcAreaTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(TcourseNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(TcourseTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(JComboCreditHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(TmaxMarks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(JComboSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(JComboIsCreditable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(hint, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add)
                    .addComponent(update)
                    .addComponent(delete)
                    .addComponent(clear)
                    .addComponent(back))
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        jLabel10.setText("Title");

        jList.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(jList);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(jLabel10))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12)
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
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

    private void JComboFacIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboFacIdActionPerformed
        getDepartment();
        getProgram();
        getScheme();
        getSchemePart();
        getSchemeSemester();
        getConcentrationArea();
        getAcSchemeDetails();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboFacIdActionPerformed

    private void JComboDeptIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboDeptIdActionPerformed
        getProgram();
        getScheme();
        getSchemePart();
        getSchemeSemester();
        getConcentrationArea();
        getAcSchemeDetails();
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboDeptIdActionPerformed

    private void JComboProgIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboProgIdActionPerformed
        getScheme();
        getSchemePart();
        getSchemeSemester();
        getConcentrationArea();
        getAcSchemeDetails();
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboProgIdActionPerformed

    private void JComboSchemePartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboSchemePartActionPerformed
        getSchemeSemester();
        getConcentrationArea();
        getAcSchemeDetails();
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboSchemePartActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        add();
        // TODO add your handling code here:
    }//GEN-LAST:event_addActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        update();
        // TODO add your handling code here:
    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        delete();
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        clear();
        // TODO add your handling code here:
    }//GEN-LAST:event_clearActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_backActionPerformed

    private void JComboSchemeYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboSchemeYearActionPerformed
        getConcentrationArea();
        getAcSchemeDetails();
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboSchemeYearActionPerformed

    private void jListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jListValueChanged

    private void JComboConcAreaTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboConcAreaTitleActionPerformed
        getAcSchemeDetails();
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboConcAreaTitleActionPerformed

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
            java.util.logging.Logger.getLogger(AcSchemeDetailsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AcSchemeDetailsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AcSchemeDetailsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AcSchemeDetailsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AcSchemeDetailsFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox JComboConcAreaTitle;
    private javax.swing.JComboBox JComboCreditHours;
    private javax.swing.JComboBox JComboDeptId;
    private javax.swing.JComboBox JComboFacId;
    private javax.swing.JComboBox JComboIsCreditable;
    private javax.swing.JComboBox JComboProgId;
    private javax.swing.JComboBox JComboSchemePart;
    private javax.swing.JComboBox JComboSchemeYear;
    private javax.swing.JComboBox JComboSemester;
    private javax.swing.JTextField TacId;
    private javax.swing.JTextField TcourseNo;
    private javax.swing.JTextField TcourseTitle;
    private javax.swing.JTextField Tgroup;
    private javax.swing.JTextField TmaxMarks;
    private javax.swing.JTextField TminMarks;
    private javax.swing.JButton add;
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
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList jList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea remarksArea;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
