/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;


import BeansClasses.*;
import CustomClassess.*;
import java.util.Vector;
import universitysystem.DatabaseManager;

/**
 *
 * @author Salman Ahmed
 */
public class LedgerDetailsSummaryFrame extends javax.swing.JFrame {

    /**
     * Creates new form LedgerDetailsSummaryFrame
     */
  
    
    public LedgerDetailsSummaryFrame() {
        initComponents();
        
        
        getFaculty();
     
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



    /////***** getLedgerDetailsSummary Load *****/////
    private void getLadgerDetailSummary(){
            Vector v = null;
            try{
                    v = DatabaseManager.getLedgerDetailsSummary();
                    if(v == null) return;
                    jList.setListData(v);
            }catch(Exception ee){
                    ee.printStackTrace();
                    MainWorkPlace.Jpane("Error "+ee);
            }
    }
    /////***** getLedgerDetailsSummary Load *****/////



    private void dumping(){


            short rows = 0;

            SeatListBean slBeans = (SeatListBean)(JComboSeatListExamYear.getSelectedItem());
            if(slBeans == null) return;

            //////////////***************** FIND STUDENTS *****************//////////////
            Vector sldVector = null;
            try{
                    sldVector = DatabaseManager.getSeatListDetails(slBeans.getSeatListId());
            }catch(Exception ee){
                    ee.printStackTrace();
                    MainWorkPlace.Jpane("Error "+ee);
            }
            //////////////***************** FIND STUDENTS *****************//////////////


            //System.out.println("Students : "+sldVector.size());

            //////////////***************** FIND COURSES *****************//////////////
            for(int i = 0; i < sldVector.size(); i++){
                    SeatListDetailBean sldBeans = (SeatListDetailBean)(sldVector.elementAt(i));


                    //////////////***************** GETTING NUMBER OF COURSES *****************//////////////
                    Vector ldVector = null;
                    try{
                            ldVector = DatabaseManager.getLedgerDetails(sldBeans.getStuRollno());
                    }catch(Exception ee){
                            ee.printStackTrace();
                            MainWorkPlace.Jpane("Error "+ee);
                    }
                    //////////////***************** GETTING NUMBER OF COURSES *****************//////////////


                    int totalMarks = 0;
                    int totalObtainMarks = 0;
                    int sumOfQp = 0;
                    int totalCreditHours = 0;
                    String resultRemarks = "";
                    boolean permission = true;

                    for(short j = 0; j < ldVector.size(); j++){

                            //////************** GETTING OBTAIN MARKS & QP FROM LEDGER DETAILS **************//////
                            LedgerDetailsBean ldBeans = (LedgerDetailsBean)(ldVector.elementAt(j));

                            if(ldBeans.getMarksObtain() != null){
                                    totalObtainMarks += Integer.parseInt(ldBeans.getMarksObtain());
                            }
                            if(ldBeans.getQp() == 0 & permission){
                                    resultRemarks = "FAIL";
                                    permission = false;
                            }
                            if(ldBeans.getQp() > 0 & permission){
                                    resultRemarks = "PASS";
                            }

                            System.out.println(ldBeans.getQp());
                            sumOfQp += ldBeans.getQp();

                            //////************** GETTING OBTAIN MARKS & QP FROM LEDGER DETAILS **************//////


                            //////************** GETTING MAX MARKS & CREDIT HOURS FROM SCHEME DETAILS **************//////
                            try{
                                    SchemeDetailBean sdBeans = DatabaseManager.getSchemeDetails(ldBeans.getCourseNo());
                                    totalMarks += sdBeans.getMaxMarks();
                                    totalCreditHours += sdBeans.getCreditHours();
                            }catch(Exception ee){
                                    ee.printStackTrace();
                                    MainWorkPlace.Jpane("Error "+ee);
                            }
                            //////************** GETTING MAX MARKS & CREDIT HOURS FROM SCHEME DETAILS **************//////

                    }

                    float percentage = calculatePercentage(totalMarks, totalObtainMarks);
                    float cgpa = (float)((float)sumOfQp / (float)ldVector.size());		//HERE ldVector = number of courses



                    System.out.println("SLID = "+sldBeans.getSeatListId()
                    +"\nRoll Number = "+sldBeans.getStuRollno()
                    +"\nObtain = "+totalObtainMarks
                    +"\nMax Marks = "+totalMarks
                    +"\nPercentage = "+percentage+"%"
                    +"\nCGPA = "+cgpa
                    +"\nsumOfQp = "+sumOfQp
                    +"\nCredit Hours = "+totalCreditHours
                    +"\nResult Remarks = "+resultRemarks+"\n\n");


                    //////////////***************** SAVE DATA IN DATABASE *****************//////////////


                    try{
                            rows += DatabaseManager.addLedgerDetailsSummary(sldBeans.getSeatListId(),
                                                                            sldBeans.getStuRollno(),
                                                                            totalMarks, 
                                                                            resultRemarks,
                                                                            percentage,
                            /*indvResultAnnDate*/                           "",
                                                                            cgpa,
                                                                            totalObtainMarks,
                            /*noDuesRemarks*/                               "",
                            /*ac2No*/                                       "",
                            /*ac2NoDated*/                                  "",
                                                                            sumOfQp,
                                                                            totalCreditHours);

                    }catch(Exception ex){
                            ex.printStackTrace();
                            MainWorkPlace.Jpane("Error "+ex);
                    }

                    //////////////***************** SAVE DATA IN DATABASE *****************//////////////



            }

            //////////////***************** FIND COURSES *****************//////////////
            MainWorkPlace.Jpane(rows+" Dumping");
            getLadgerDetailSummary();
            


    }
    


    private void add(){

    }


    private void update(){

    }


    private void delete(){

    }


    private void clear(){

    }


    private float calculatePercentage(int total, int obtain){
            return (float)((float)(obtain*100)/total);
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
        JComboFacId = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        JComboProgId = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        update = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        back = new javax.swing.JButton();
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
        TslId = new javax.swing.JTextField();
        TrollNo = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        TtotalMarks = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        TobtainMarks = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        Tcgpa = new javax.swing.JTextField();
        Tpercentage = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        TcreditHours = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        Tqp = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        JComboDeptId = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList = new javax.swing.JList();
        dumping = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Open Sans", 1, 24)); // NOI18N
        jLabel1.setText("Ledger Detail Summary");

        jLabel3.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel3.setText("Faculty");

        JComboFacId.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        JComboFacId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboFacIdActionPerformed(evt);
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

        TslId.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        TrollNo.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel14.setText("Roll Number");

        jLabel15.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel15.setText("Total Marks");

        TtotalMarks.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel17.setText("Obtain Marks");

        TobtainMarks.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel18.setText("CGPA");

        Tcgpa.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        Tpercentage.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel19.setText("Percentage");

        jLabel20.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel20.setText("%");

        jLabel21.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel21.setText("Credit Hours");

        TcreditHours.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel22.setText("QP");

        Tqp.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel6.setText("Departments");

        JComboDeptId.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        JComboDeptId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboDeptIdActionPerformed(evt);
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
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JComboBatchYear, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JComboProgId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(update, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(clear, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TbatchShift)
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(18, 18, 18)
                                        .addComponent(JComboParts, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(20, 20, 20)))
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TbatchGroup))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(13, 13, 13)
                                        .addComponent(TslId))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(JComboSeatListExamYear, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel12))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(13, 13, 13)
                                        .addComponent(TrollNo)))
                                .addGap(13, 13, 13)
                                .addComponent(TseatListType))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(JComboDeptId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hint, javax.swing.GroupLayout.DEFAULT_SIZE, 8, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Tpercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20)
                                .addGap(62, 62, 62)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Tcgpa, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TcreditHours))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TtotalMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TobtainMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(Tqp, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(JComboFacId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(JComboFacId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
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
                    .addComponent(jLabel12)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(JComboSeatListExamYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jLabel16))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(TslId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(TrollNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(TtotalMarks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(TobtainMarks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(Tpercentage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(Tcgpa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(TcreditHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(Tqp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82)
                .addComponent(hint, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delete)
                    .addComponent(clear)
                    .addComponent(back)
                    .addComponent(update))
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        jLabel10.setText("Cource Number/Title");

        jList.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList);

        dumping.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        dumping.setText("DUMPING");
        dumping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dumpingActionPerformed(evt);
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
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(dumping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(dumping)
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
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void JComboFacIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboFacIdActionPerformed
        getDepartment();
       
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboFacIdActionPerformed

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
        getLadgerDetailSummary();
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboSeatListExamYearActionPerformed

    private void jListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListValueChanged
        LedgerDetailSummaryBean beans = (LedgerDetailSummaryBean)jList.getSelectedValue();

        TslId.setText(beans.getSeatListId()+"");
        TrollNo.setText(beans.getStuRollNo()+"");
        TtotalMarks.setText(beans.getTotalMarks()+"");
        TobtainMarks.setText(beans.getObtainMarks()+"");
        Tpercentage.setText(beans.getPercentage()+"");
        TcreditHours.setText(beans.getCreditHours()+"");
        Tqp.setText(beans.getQp()+"");
        Tcgpa.setText(beans.getCgpa()+"");
        // TODO add your handling code here:
    }//GEN-LAST:event_jListValueChanged

    private void dumpingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dumpingActionPerformed
        dumping();
        // TODO add your handling code here:
    }//GEN-LAST:event_dumpingActionPerformed

    private void JComboDeptIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboDeptIdActionPerformed
        getProgram();
      
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboDeptIdActionPerformed

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
            java.util.logging.Logger.getLogger(LedgerDetailsSummaryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LedgerDetailsSummaryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LedgerDetailsSummaryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LedgerDetailsSummaryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LedgerDetailsSummaryFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox JComboBatchYear;
    private javax.swing.JComboBox JComboDeptId;
    private javax.swing.JComboBox JComboFacId;
    private javax.swing.JComboBox JComboParts;
    private javax.swing.JComboBox JComboProgId;
    private javax.swing.JComboBox JComboSeatListExamYear;
    private javax.swing.JTextField TbatchGroup;
    private javax.swing.JTextField TbatchShift;
    private javax.swing.JTextField Tcgpa;
    private javax.swing.JTextField TcreditHours;
    private javax.swing.JTextField TobtainMarks;
    private javax.swing.JTextField Tpercentage;
    private javax.swing.JTextField Tqp;
    private javax.swing.JTextField TrollNo;
    private javax.swing.JTextField TseatListType;
    private javax.swing.JTextField TslId;
    private javax.swing.JTextField TtotalMarks;
    private javax.swing.JButton back;
    private javax.swing.JButton clear;
    private javax.swing.JButton delete;
    private javax.swing.JButton dumping;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
