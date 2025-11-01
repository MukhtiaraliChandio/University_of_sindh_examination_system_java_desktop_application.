/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usindhexaminationsystemproject;

import com.exam.beans.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author Salman Ahmed
 */
public class DatabaseManager {
    
    private static Connection con;
	
    static{
        try{
                init();
        }catch(Exception e){
                e.printStackTrace();
        }
    }

    private static void init()throws Exception{
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    String url = "C:\\Users\\MUKHTIAR ALI CHANDIO\\Documents\\NetBeansProjects\\USindhExaminationSystemProject\\usindhexam.accdb";


        con = DriverManager.getConnection(url);
        System.out.println("Conntion Established");
    }


    public Connection getConnection(){
            return con;
    }
    
    
    
    
    ////**** Faculty Table Attributes ****////
    public static Vector getFaculty()throws Exception{

        String query = "select * from FACULTY";
        System.out.println(query);

        Statement st = null;
        ResultSet result = null;

        try{
            st = con.createStatement();
            result = st.executeQuery(query);

            Vector<FacultyBean> v = new Vector<>();

            while(result.next()){
                FacultyBean bean = new FacultyBean();

                bean.setFacId(result.getInt("fac_id"));
                bean.setFacName(result.getString("fac_name"));
                bean.setRemarks(result.getString("remarks"));

                v.addElement(bean);
            }

            return v;
        }finally{
            if(result != null){
            result.close();
        }
        if(st != null){
                st.close();
            }
        }

    }// End getFaculty Method

    public static int addFaculty(String facName, String remarks)throws Exception{

            String query = "insert into FACULTY(fac_name, remarks) values('"+facName+"', '"+remarks+"')";
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{

                st = (Statement) con.createStatement();
                row = st.executeUpdate(query);

                return row;
            }
            finally{
                if(st != null){
                    st.close();
                }
            }
    }// End addFaculty Method

    public static int updateFaculty(int facId, String facName, String remarks)throws Exception{
            String query = "update FACULTY set fac_name = '"+facName+"', remarks = '"+remarks+"' where fac_id = "+facId;
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                    st = (Statement)con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }
            finally{
                    if(st != null){
                            st.close();
                    }
            }
    }// End updateFaculty Method

    public static int deleteFaculty(int facId)throws Exception{
            String query = "delete from FACULTY where fac_id = "+facId;
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                st = con.createStatement();
                row = st.executeUpdate(query);

                return row;
            }
            finally{
                if(st != null){
                    st.close();
                }
            }
    }// End deleteFaculty Method
	
    ////**** Faculty Table Attributes ****////
    
    
    
    
    ////**** Department Table Attributes ****////

    public static Vector getDepartment()throws Exception{

        String query = "select * remarks from DEPARTMENT";
        System.out.println(query);

        Statement st = null;
        ResultSet result = null;

        try{
            st = con.createStatement();
            result = st.executeQuery(query);
            Vector<DepartmentBean> v = new Vector<>();

            while(result.next()){
                DepartmentBean bean = new DepartmentBean();

                bean.setDeptId(result.getInt("dept_id"));
                bean.setFacId(result.getInt("fac_id"));
                bean.setDeptName(result.getString("dept_name"));
                bean.setRemarks(result.getString("remarks"));

                v.addElement(bean);
            }
            return v;
        }finally{
            if(result != null){
                    result.close();
            }
            if(st != null){
                    st.close();
            }
        }

    }// End getDepartment Method
	
    /////**** OverLoaded Method *****/////
    public static Vector getDepartment(int facId)throws Exception{

        String query = "select * from DEPARTMENT where fac_id = "+facId;
        System.out.println(query);

        Statement st = null;
        ResultSet result = null;

        try{
            st = con.createStatement();
            result = st.executeQuery(query);
            Vector<DepartmentBean> v = new Vector<>();

            while(result.next()){
                DepartmentBean bean = new DepartmentBean();

                bean.setDeptId(result.getInt("dept_id"));
                bean.setFacId(result.getInt("fac_id"));
                bean.setDeptName(result.getString("dept_name"));
                bean.setRemarks(result.getString("remarks"));

                v.addElement(bean);
            }
            return v;
        }finally{
            if(result != null){
                result.close();
            }
            if(st != null){
                st.close();
            }
        }

    }// End getDepartment Method
	
    public static int addDepartment(int facId, String deptName, String remarks)throws Exception{

        String query = "insert into DEPARTMENT(fac_id, dept_name, remarks) values("+facId+", '"+deptName+"', '"+remarks+"')";
        System.out.println(query);

        Statement st = null;
        int row = 0;

        try{

            st = con.createStatement();
            row = st.executeUpdate(query);

            return row;
        }
        finally{
            if(st != null){
                st.close();
            }
        }
    }// End addDepartment Method

    public static int updateDepartment(int deptId, int facId, String deptName, String remarks)throws Exception{
        String query = "update DEPARTMENT set fac_id = "+facId+", dept_name = '"+deptName+"', remarks = '"+remarks+"' where dept_id = "+deptId+"";
        System.out.println(query);

        Statement st = null;
        int row = 0;

        try{
                st = con.createStatement();
                row = st.executeUpdate(query);

                return row;
        }
        finally{
                if(st != null){
                        st.close();
                }
        }
    }// End updateDepartment Method
	
    public static int deleteDepartment(int deptId)throws Exception{
        String query = "delete from DEPARTMENT where dept_id = "+deptId;
        System.out.println(query);

        Statement st = null;
        int row = 0;

        try{
            st = con.createStatement();
            row = st.executeUpdate(query);

            return row;
        }
        finally{
            if(st != null){
                    st.close();
            }
        }
    }// End deleteDepartment Method

    ////**** Department Table Attributes ****////
    
    
    
    
    
    ////**** Program Table Attributes ****////
    public static Vector getProgram(int dept_Id)throws Exception{
        String query = "select * from PROGRAM where dept_id = "+dept_Id;
        System.out.println(query);

        Statement st = null;
        ResultSet result = null;

        try{
            st = con.createStatement();
            result = st.executeQuery(query);
            Vector<ProgramBean> v = new Vector<>();

            while(result.next()){
                ProgramBean bean = new ProgramBean();

                bean.setProgId(result.getInt("prog_id"));
                bean.setDeptId(result.getInt("dept_id"));
                bean.setProgName(result.getString("prog_name"));
                bean.setProgDuration(result.getInt("prog_duration"));
                bean.setRemarks(result.getString("remarks"));

                v.addElement(bean);
            }
            return v;
        }finally{
                if(result != null){
                        result.close();
                }
                if(st != null){
                        st.close();
                }
        }

    }// End getProgram Method

    public static int addProgram(int dept_Id, int prog_Duration, String prog_Name, String remarks)throws Exception{
        String query = "insert into PROGRAM(dept_id, prog_duration, prog_name, remarks) values("+dept_Id+", "+prog_Duration+", '"+prog_Name+"', '"+remarks+"')";
        System.out.println(query);

        Statement st = null;
        int row = 0;

        try{

            st = con.createStatement();
            row = st.executeUpdate(query);

            return row;
        }
        finally{
            if(st != null){
                st.close();
            }
        }
    }// End addProgram Method

    public static int updateProgram(int prog_Id, int dept_Id, int prog_Duration, String prog_Name, String remarks)throws Exception{
        String query = "update PROGRAM set dept_id = "+dept_Id+", prog_duration = "+prog_Duration+", prog_name = '"+prog_Name+"', remarks = '"+remarks+"' where prog_id = "+prog_Id+"";
        System.out.println(query);

        Statement st = null;
        int row = 0;

        try{
            st = con.createStatement();
            row = st.executeUpdate(query);

            return row;
        }
        finally{
            if(st != null){
                st.close();
            }
        }
    }// End updateProgram Method

    public static int deleteProgram(int prog_Id)throws Exception{
        String query = "delete from PROGRAM where prog_id = "+prog_Id;
        System.out.println(query);

        Statement st = null;
        int row = 0;

        try{
            st = con.createStatement();
            row = st.executeUpdate(query);

            return row;
        }
        finally{
            if(st != null){
                st.close();
            }
        }
    }// End deleteProgram Method

    ////**** Program Table Attributes ****////
    
    
    
    
    
    ////**** Batch Table Attributes ****////
    public static Vector getBatch(int prog_Id)throws Exception{

        String query = "select * from BATCH where prog_id = "+prog_Id;
        System.out.println(query);

        Statement st = null;
        ResultSet result = null;

        try{
            st = con.createStatement();
            result = st.executeQuery(query);
            Vector<BatchBean> v = new Vector<>();

            while(result.next()){
                BatchBean bean = new BatchBean();

                bean.setBatchId(result.getInt("batch_id"));
                bean.setProgId(result.getInt("prog_id"));
                bean.setBatchYear(result.getInt("year"));
                bean.setBatchShift(result.getString("shift"));
                bean.setBatchGroup(result.getString("group_desc"));
                bean.setRemarks(result.getString("remarks"));

                v.addElement(bean);
            }
            return v;
        }finally{
            if(result != null){
                result.close();
            }
            if(st != null){
                st.close();
            }
        }

    }// End getBatch Method

    public static int addBatch(int prog_Id, int batch_Year,  String batch_Shift, String batch_Group, String remarks)throws Exception{

        String query = "insert into BATCH(prog_id, year, shift, group_desc, remarks) values("+prog_Id+", "+batch_Year+", '"+batch_Shift+"', '"+batch_Group+"', '"+remarks+"')";
        System.out.println(query);

        Statement st = null;
        int row = 0;

        try{

            st = con.createStatement();
            row = st.executeUpdate(query);

            return row;
        }
        finally{
            if(st != null){
                st.close();
            }
        }
    }// End addBatch Method

    public static int updateBatch(int batch_Id, int prog_Id, int batch_Year, String batch_Shift, String batch_Group, String remarks)throws Exception{
        String query = "update BATCH set prog_id = "+prog_Id+", year = "+batch_Year+", shift = '"+batch_Shift+"', group_desc = '"+batch_Group+"', remarks = '"+remarks+"' where batch_id = "+batch_Id;
        System.out.println(query);

        Statement st = null;
        int row = 0;

        try{
            st = con.createStatement();
            row = st.executeUpdate(query);

            return row;
        }
        finally{
            if(st != null){
                st.close();
            }
        }
    }// End updateBatch Method

    public static int deleteBatch(int batch_Id)throws Exception{
        String query = "delete from BATCH where batch_id = "+batch_Id;
        System.out.println(query);

        Statement st = null;
        int row = 0;

        try{
            st = con.createStatement();
            row = st.executeUpdate(query);

            return row;
        }
        finally{
            if(st != null){
                st.close();
            }
        }
    }// End deleteBatch Method

    ////**** Batch Table Attributes ****////
	
	
    
    
    
    ////**** Scheme Table Attributes ****////

    public static SchemeBean getSchemeBeans(int scheme_Id)throws Exception{

        String query = "select * from SCHEME where scheme_id = "+scheme_Id;
        System.out.println(query);

        Statement st = null;
        ResultSet result = null;

        try{
            st = con.createStatement();
            result = st.executeQuery(query);
            SchemeBean beans = null;

            if(result.next()){
                beans = new SchemeBean();

                beans.setProgId(result.getInt("prog_id"));
                beans.setSchemeId(result.getInt("scheme_id"));
                beans.setSchemeYear(result.getInt("year"));
                beans.setMinMarks(result.getInt("min_marks"));
                beans.setGroupDescription(result.getString("group_desc"));
                beans.setRemarks(result.getString("remarks"));

            }
            return beans;
        }finally{
            if(result != null){
                result.close();
            }
            if(st != null){
                st.close();
            }
        }

    }// End getScheme Method

    public static Vector getScheme(int prog_Id)throws Exception{

        String query = "select * from SCHEME where prog_id = "+prog_Id;
        System.out.println(query);

        Statement st = null;
        ResultSet result = null;

        try{
            st = con.createStatement();
            result = st.executeQuery(query);
            Vector<SchemeBean> v = new Vector<>();

            while(result.next()){
                SchemeBean bean = new SchemeBean();

                bean.setProgId(result.getInt("prog_id"));
                bean.setSchemeId(result.getInt("scheme_id"));
                bean.setSchemeYear(result.getInt("year"));
                bean.setMinMarks(result.getInt("min_marks"));
                bean.setGroupDescription(result.getString("group_desc"));
                bean.setRemarks(result.getString("remarks"));

                v.addElement(bean);
            }
            return v;
        }finally{
            if(result != null){
                result.close();
            }
            if(st != null){
                st.close();
            }
        }

    }// End getScheme Method

    /////**** OverLoaded Method *****/////
    public static SchemeBean getScheme(int prog_Id, int scheme_Year)throws Exception{

        String query = "select * from SCHEME where prog_id = "+prog_Id+" AND year = "+scheme_Year;
        System.out.println(query);

        Statement st = null;
        ResultSet result = null;

        try{
            st = con.createStatement();
            result = st.executeQuery(query);

            SchemeBean beans = null;

            if(result.next()){
                beans = new SchemeBean();

                beans.setProgId(result.getInt("prog_id"));
                beans.setSchemeId(result.getInt("scheme_id"));
                beans.setSchemeYear(result.getInt("year"));
                beans.setMinMarks(result.getInt("min_marks"));
                beans.setGroupDescription(result.getString("group_desc"));
                beans.setRemarks(result.getString("remarks"));
            }
            return beans;
        }finally{
            if(result != null){
                result.close();
            }
            if(st != null){
                st.close();
            }
        }

    }// End getScheme Method

    public static int addScheme(int prog_Id, int scheme_Year, int min_Marks, String group_Description, String remarks)throws Exception{

        String query = "insert into SCHEME(prog_id, year, min_marks, group_desc, remarks) values("+prog_Id+", "+scheme_Year+", "+min_Marks+", '"+group_Description+"', '"+remarks+"')";
        System.out.println(query);

        Statement st = null;
        int row = 0;

        try{

            st = con.createStatement();
            row = st.executeUpdate(query);

            return row;
        }
        finally{
            if(st != null){
                st.close();
            }
        }
    }// End addScheme Method

    public static int updateScheme(int scheme_Id, int prog_Id, int scheme_Year, int min_Marks, String group_Description, String remarks)throws Exception{
        String query = "update SCHEME set prog_id = "+prog_Id+", year = "+scheme_Year+", min_marks = "+min_Marks+", group_desc = '"+group_Description+"', remarks = '"+remarks+"' where scheme_id = "+scheme_Id+"";
        System.out.println(query);

        Statement st = null;
        int row = 0;

        try{
            st = con.createStatement();
            row = st.executeUpdate(query);

            return row;
        }
        finally{
            if(st != null){
                st.close();
            }
        }
    }// End updateScheme Method

    public static int deleteScheme(int scheme_Id)throws Exception{
        String query = "delete from SCHEME where scheme_id = "+scheme_Id;
        System.out.println(query);

        Statement st = null;
        int row = 0;

        try{
            st = con.createStatement();
            row = st.executeUpdate(query);

            return row;
        }
        finally{
            if(st != null){
                st.close();
            }
        }
    }// End deleteProgram Method

    ////**** Scheme Table Attributes ****////

    
    
    
    
    
    ////**** SchemePart Table Attributes ****////

    public static Vector getSchemePart(int scheme_Id)throws Exception{

        String query = "select * from SCHEME_PART where scheme_id = "+scheme_Id;
        System.out.println(query);

        Statement st = null;
        ResultSet result = null;

        try{
            st = con.createStatement();
            result = st.executeQuery(query);
            Vector<SchemePartBean> v = new Vector<>();

            while(result.next()){
                    SchemePartBean bean = new SchemePartBean();

                    bean.setSchemeId(result.getInt("scheme_id"));
                    bean.setSchemePart(result.getInt("scheme_part"));
                    bean.setRemarks(result.getString("remarks"));

                    v.addElement(bean);
            }
            return v;
        }finally{
            if(result != null){
                result.close();
            }
            if(st != null){
                st.close();
            }
        }

    }// End getSchemePart Method

    public static int addSchemePart(int scheme_Part, int scheme_Id, String remarks)throws Exception{

        String query = "insert into SCHEME_PART(scheme_part, scheme_id, remarks) values("+scheme_Part+", "+scheme_Id+", '"+remarks+"')";
        System.out.println(query);

        Statement st = null;
        int row = 0;

        try{

            st = con.createStatement();
            row = st.executeUpdate(query);

            return row;
        }
        finally{
            if(st != null){
                st.close();
            }
        }
    }// End addSchemePart Method

    public static int updateSchemePart(int scheme_Part, int scheme_Id, String remarks)throws Exception{
        String query = "update SCHEME_PART set remarks = '"+remarks+"' where scheme_part = "+scheme_Part+" AND scheme_id = "+scheme_Id+"";
        System.out.println(query);

        Statement st = null;
        int row = 0;

        try{
            st = con.createStatement();
            row = st.executeUpdate(query);

            return row;
        }
        finally{
            if(st != null){
                st.close();
            }
        }
    }// End updateSchemePart Method

    public static int deleteSchemePart(int scheme_Part, int scheme_Id)throws Exception{
        String query = "delete from SCHEME_PART where scheme_part = "+scheme_Part+" AND scheme_id = "+scheme_Id;
        System.out.println(query);

        Statement st = null;
        int row = 0;

        try{
            st = con.createStatement();
            row = st.executeUpdate(query);

            return row;
        }
        finally{
            if(st != null){
                st.close();
            }
        }
    }// End deleteProgram Method

    ////**** SchemePart Table Attributes ****////

    
    
	
    ////**** SchemeSemester Table Attributes ****////


    public static SchemeSemesterBean getSchemeSemester(int scheme_Part, int scheme_Id, boolean bool)throws Exception{

        String query = "select * from SCHEME_SEMESTER where scheme_part = "+scheme_Part+" AND scheme_id = "+scheme_Id;
        System.out.println(query);

        Statement st = null;
        ResultSet result = null;

        try{
            st = con.createStatement();
            result = st.executeQuery(query);
            SchemeSemesterBean bean = null;

            if(result.next()){

                bean = new SchemeSemesterBean();

                bean.setSchemeId(result.getInt("scheme_id"));
                bean.setSchemePart(result.getInt("scheme_part"));
                bean.setSemester(result.getInt("semester"));
                bean.setRemarks(result.getString("remarks"));

            }

            return bean;

        }finally{
            if(result != null){
                result.close();
            }
            if(st != null){
                st.close();
            }
        }

    }// End getSchemeSemester Method

    /////**** OverLoaded Method *****/////
    public static Vector getSchemeSemester(int scheme_Part, int scheme_Id)throws Exception{

        String query = "select * from SCHEME_SEMESTER where scheme_part = "+scheme_Part+" AND scheme_id = "+scheme_Id;
        System.out.println(query);

        Statement st = null;
        ResultSet result = null;

        try{
            st = con.createStatement();
            result = st.executeQuery(query);
            Vector<SchemeSemesterBean> v = new Vector<>();

            while(result.next()){
                SchemeSemesterBean bean = new SchemeSemesterBean();

                bean.setSchemeId(result.getInt("scheme_id"));
                bean.setSchemePart(result.getInt("scheme_part"));
                bean.setSemester(result.getInt("semester"));
                bean.setRemarks(result.getString("remarks"));

                v.addElement(bean);
            }
            return v;
        }finally{
            if(result != null){
                result.close();
            }
            if(st != null){
                st.close();
            }
        }

    }// End getSchemeSemester Method

    public static int addSchemeSemester(int scheme_Part, int scheme_Id, int semester, String remarks)throws Exception{

        String query = "insert into SCHEME_SEMESTER(scheme_part, scheme_id, semester, remarks) values("+scheme_Part+", "+scheme_Id+", "+semester+", '"+remarks+"')";
        System.out.println(query);

        Statement st = null;
        int row = 0;

        try{

            st = con.createStatement();
            row = st.executeUpdate(query);

            return row;
        }
        finally{
            if(st != null){
                st.close();
            }
        }
    }// End addSchemeSemester Method

    public static int updateSchemeSemester(int scheme_Part, int scheme_Id, int semester, String remarks)throws Exception{
        String query = "update SCHEME_SEMESTER set remarks = '"+remarks+"' where scheme_part = "+scheme_Part+" AND scheme_id = "+scheme_Id+" AND semester = "+semester;
        System.out.println(query);

        Statement st = null;
        int row = 0;

        try{
            st = con.createStatement();
            row = st.executeUpdate(query);

            return row;
        }
        finally{
            if(st != null){
                st.close();
            }
        }
    }// End updateSchemeSemester Method

    public static int deleteSchemeSemester(int scheme_Part, int scheme_Id, int semester)throws Exception{
        String query = "delete from SCHEME_SEMESTER where scheme_part = "+scheme_Part+" AND scheme_id = "+scheme_Id+" AND semester = "+semester;
        System.out.println(query);

        Statement st = null;
        int row = 0;

        try{
            st = con.createStatement();
            row = st.executeUpdate(query);

            return row;
        }
        finally{
            if(st != null){
                st.close();
            }
        }
    }// End deleteSchemeSemester Method

    ////**** SchemeSemester Table Attributes ****////

    
    
	
    ////**** SchemeDetails Table Attributes ****////

    public static Vector getSchemeDetails(int scheme_Part, int scheme_Id)throws Exception{

        String query = "select * from SCHEME_DETAIL where scheme_part = "+scheme_Part+" AND scheme_id = "+scheme_Id;
        System.out.println(query);

        Statement st = null;
        ResultSet result = null;

        try{
            st = con.createStatement();
            result = st.executeQuery(query);
            Vector<SchemeDetailBean> v = new Vector<>();

            while(result.next()){
                SchemeDetailBean bean = new SchemeDetailBean();

                bean.setSchemeId(result.getInt("scheme_id"));
                bean.setSchemePart(result.getInt("scheme_part"));
                bean.setSemester(result.getInt("semester"));
                bean.setCourseNo(result.getString("course_no"));
                bean.setCourseTitle(result.getString("course_title"));
                bean.setCreditHours(result.getInt("cr_hrs"));
                bean.setMaxMarks(result.getInt("max_marks"));
                bean.setIsCreditable(result.getString("is_creditable"));



                v.addElement(bean);
            }
            return v;
        }finally{
            if(result != null){
                result.close();
            }
            if(st != null){
                st.close();
            }
        }

    }// End getSchemeDetails Method

    /////**** OverLoaded Method *****/////
    public static Vector getSchemeDetails(int scheme_Part, int scheme_Id, int semester)throws Exception{

        String query = "select * from SCHEME_DETAIL where scheme_part = "+scheme_Part+" AND scheme_Id = "+scheme_Id+" AND semester = "+semester;
        System.out.println(query);

        Statement st = null;
        ResultSet result = null;

        try{
            st = con.createStatement();
            result = st.executeQuery(query);
            Vector<SchemeDetailBean> v = new Vector<>();

            while(result.next()){
                SchemeDetailBean bean = new SchemeDetailBean();

                bean.setSchemeId(result.getInt("scheme_id"));
                bean.setSchemePart(result.getInt("scheme_part"));
                bean.setSemester(result.getInt("semester"));
                bean.setCourseNo(result.getString("course_no"));
                bean.setCourseTitle(result.getString("course_title"));
                bean.setCreditHours(result.getInt("cr_hrs"));
                bean.setMaxMarks(result.getInt("max_marks"));
                bean.setIsCreditable(result.getString("is_creditable"));



                v.addElement(bean);
            }
            return v;
        }finally{
            if(result != null){
                result.close();
            }
            if(st != null){
                st.close();
            }
        }

    }// End getSchemeDetails Method

    /////**** OverLoaded Method *****/////
    public static SchemeDetailBean getSchemeDetails(String course_No)throws Exception{

        String query = "select * from SCHEME_DETAIL where course_no = '"+course_No+"'";
        System.out.println(query);

        Statement st = null;
        ResultSet result = null;

        try{
            st = con.createStatement();
            result = st.executeQuery(query);
            SchemeDetailBean bean = null;

            if(result.next()){
                bean = new SchemeDetailBean();

                bean.setSchemeId(result.getInt("scheme_id"));
                bean.setSchemePart(result.getInt("scheme_part"));
                bean.setSemester(result.getInt("semester"));
                bean.setCourseNo(result.getString("course_no"));
                bean.setCourseTitle(result.getString("course_title"));
                bean.setCreditHours(result.getInt("cr_hrs"));
                bean.setMaxMarks(result.getInt("max_marks"));
                bean.setIsCreditable(result.getString("is_creditable"));

            }
            return bean;
        }finally{
            if(result != null){
                result.close();
            }
            if(st != null){
                st.close();
            }
        }

    }// End getSchemeDetails Method

    /////**** OverLoaded Method *****/////
    public static SchemeDetailBean getSchemeDetails(int scheme_Part, int scheme_Id, int semester, String course_No)throws Exception{

        String query = "select * from SCHEME_DETAIL where scheme_part = "+scheme_Part+" AND scheme_id = "+scheme_Id+" AND semester = "+semester+" AND course_no = '"+course_No+"'";
        System.out.println(query);

        Statement st = null;
        ResultSet result = null;

        try{
            st = con.createStatement();
            result = st.executeQuery(query);
            SchemeDetailBean bean = null;


            if(result.next()){
                bean = new SchemeDetailBean();

                bean.setSchemeId(result.getInt("scheme_id"));
                bean.setSchemePart(result.getInt("scheme_part"));
                bean.setSemester(result.getInt("semester"));
                bean.setCourseNo(result.getString("course_no"));
                bean.setCourseTitle(result.getString("course_title"));
                bean.setCreditHours(result.getInt("cr_hrs"));
                bean.setMaxMarks(result.getInt("max_marks"));
                bean.setIsCreditable(result.getString("is_creditable"));
            }
            return bean;
        }finally{
            if(result != null){
                result.close();
            }
            if(st != null){
                st.close();
            }
        }

    }// End getSchemeDetails Method

    public static int addSchemeDetails(int scheme_Part, int scheme_Id, int semester, String course_No, String course_Title, int credit_Hours, int max_Marks, String is_Creditable)throws Exception{

        String query = "insert into SCHEME_DETAIL(scheme_part, scheme_id, semester, course_no, course_title, cr_hrs, max_marks, is_creditable) values("+scheme_Part+", "+scheme_Id+", "+semester+", '"+course_No+"', '"+course_Title+"', "+credit_Hours+", "+max_Marks+", '"+is_Creditable+"')";
        System.out.println(query);

        Statement st = null;
        int row = 0;

        try{

            st = con.createStatement();
            row = st.executeUpdate(query);

            return row;
        }
        finally{
            if(st != null){
                st.close();
            }
        }
    }// End addSchemeDetails Method

    public static int updateSchemeDetails(int scheme_Part, int scheme_Id, int semester, String course_No, String course_Title, int credit_Hours, int max_Marks, String is_Creditable)throws Exception{
        String query = "update SCHEME_DETAIL set course_no = '"+course_No+"', course_title = '"+course_Title+"', cr_hrs = "+credit_Hours+", max_marks = "+max_Marks+", is_creditable = '"+is_Creditable+"'  where scheme_part = "+scheme_Part+" AND scheme_id = "+scheme_Id+" AND semester = "+semester+" AND course_no = '"+course_No+"'";
        System.out.println(query);

        Statement st = null;
        int row = 0;

        try{
            st = con.createStatement();
            row = st.executeUpdate(query);

            return row;
        }
        finally{
            if(st != null){
                st.close();
            }
        }
    }// End updateSchemeDetails Method

    public static int deleteSchemeDetails(int scheme_Part, int scheme_Id, int semester, String course_No)throws Exception{
        String query = "delete from SCHEME_DETAIL where scheme_part = "+scheme_Part+" AND scheme_id = "+scheme_Id+" AND semester = "+semester+" AND course_no = '"+course_No+"'";
        System.out.println(query);

        Statement st = null;
        int row = 0;

        try{
            st = con.createStatement();
            row = st.executeUpdate(query);

            return row;
        }
        finally{
            if(st != null){
                st.close();
            }
        }
    }// End deleteSchemeSemester Method

    ////**** SchemeDetails Table Attributes ****////


    
    
    
	
    ////**** Student Table Attributes ****////
    public static Vector getStudent()throws Exception{

            String query = "select * from STUDENT";
            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{
                    st = con.createStatement();
                    result = st.executeQuery(query);
                    Vector<StudentBean> v = new Vector<>();

                    while(result.next()){
                            StudentBean bean = new StudentBean();

                            bean.setStuId(result.getInt("stu_id"));
                            bean.setBatchId(result.getInt("batch_id"));
                            bean.setStuName(result.getString("name"));
                            bean.setStuFname(result.getString("fname"));
                            bean.setStuSurname(result.getString("surname"));
                            bean.setStuRollno(result.getString("roll_no"));
                            bean.setRemarks(result.getString("remarks"));

                            v.addElement(bean);
                    }
                    return v;
            }finally{
                    if(result != null){
                            result.close();
                    }
                    if(st != null){
                            st.close();
                    }
            }

    }// End getStudent Method
    
    
    public static Vector getStudent(int batch_Id)throws Exception{

            String query = "select * from STUDENT where batch_id = "+batch_Id;
            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{
                    st = con.createStatement();
                    result = st.executeQuery(query);
                    Vector<StudentBean> v = new Vector<>();

                    while(result.next()){
                            StudentBean bean = new StudentBean();

                            bean.setStuId(result.getInt("stu_id"));
                            bean.setBatchId(result.getInt("batch_id"));
                            bean.setStuName(result.getString("name"));
                            bean.setStuFname(result.getString("fname"));
                            bean.setStuSurname(result.getString("surname"));
                            bean.setStuRollno(result.getString("roll_no"));
                            bean.setRemarks(result.getString("remarks"));

                            v.addElement(bean);
                    }
                    return v;
            }finally{
                    if(result != null){
                            result.close();
                    }
                    if(st != null){
                            st.close();
                    }
            }

    }// End getStudent Method

    /////**** OverLoaded Method *****/////
    public static StudentBean getStudent(String stuRollNo)throws Exception{

            String query = "select * from STUDENT where roll_no = '"+stuRollNo+"'";
            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{
                    st = con.createStatement();
                    result = st.executeQuery(query);
                    StudentBean bean = null;


                    if(result.next()){
                            bean = new StudentBean();

                            bean.setStuId(result.getInt("stu_id"));
                            bean.setBatchId(result.getInt("batch_id"));
                            bean.setStuName(result.getString("name"));
                            bean.setStuFname(result.getString("fname"));
                            bean.setStuSurname(result.getString("surname"));
                            bean.setStuRollno(result.getString("roll_no"));
                            bean.setRemarks(result.getString("remarks"));

                    }
                    return bean;
            }finally{
                    if(result != null){
                            result.close();
                    }
                    if(st != null){
                            st.close();
                    }
            }

    }// End getStudent Method

    public static int addStudent(int batch_Id, String stu_Name, String stu_Fname, String stu_Surname, String stu_Rollno, String remarks)throws Exception{

            String query = "insert into STUDENT(batch_id, name, fname, surname, roll_no, remarks) values("+batch_Id+", '"+stu_Name+"', '"+stu_Fname+"', '"+stu_Surname+"', '"+stu_Rollno+"', '"+remarks+"')";
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{

                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }
            finally{
                    if(st != null){
                            st.close();
                    }
            }
    }// End addStudent Method

    public static int updateStudent(int stu_Id, int batch_Id, String stu_Name, String stu_Fname, String stu_Surname, String stu_Rollno, String remarks)throws Exception{

            String query = "update STUDENT set batch_id = "+batch_Id+", name = '"+stu_Name+"', fname = '"+stu_Fname+"', surname = '"+stu_Surname+"', roll_no = '"+stu_Rollno+"' remarks = '"+remarks+"' where stu_id = "+stu_Id+"";
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }
            finally{
                    if(st != null){
                            st.close();
                    }
            }
    }// End updateStudent Method

    public static int deleteStudent(int stu_Id)throws Exception{
            String query = "delete from STUDENT where stu_id = "+stu_Id;
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }
            finally{
                    if(st != null){
                            st.close();
                    }
            }
    }// End deleteStudent Method

    ////**** Student Table Attributes ****////


    



    ////**** Student Part Table Attributes ****////
    public static Vector getStudentPart(int part_Id, int batch_Id)throws SQLException{
            String query = "select * from STUDENT_PART where part = "+part_Id+" AND batch_id = "+batch_Id;
            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{
                    st = con.createStatement();
                    result = st.executeQuery(query);

                    Vector<StudentPartBean> v = new Vector<>();

                    while(result.next()){
                            StudentPartBean beans = new StudentPartBean();

                            beans.setPartId(result.getInt("part"));
                            beans.setBatchId(result.getInt("batch_id"));
                            beans.setStuRollNo(result.getString("roll_no"));
                            beans.setRemarks(result.getString("remarks"));

                            v.addElement(beans);
                    }
                    return v;
            }finally{
                    if(result != null) result.close();
                    if(st != null) st.close();
            }

    }

    /////**** OverLoaded Method *****/////
    public static Vector getStudentPart(int part_Id, int batch_Id, String stu_Rollno)throws SQLException{
            String query = "select * from STUDENT_PART where part = "+part_Id+" AND batch_id = "+batch_Id+" AND roll_no = '"+stu_Rollno+"'";
            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{
                    st = con.createStatement();
                    result = st.executeQuery(query);

                    Vector<StudentPartBean> v = new Vector<>();

                    while(result.next()){
                            StudentPartBean beans = new StudentPartBean();

                            beans.setPartId(result.getInt("part"));
                            beans.setBatchId(result.getInt("batch_id"));
                            beans.setStuRollNo(result.getString("roll_no"));
                            beans.setRemarks(result.getString("remarks"));

                            v.addElement(beans);
                    }
                    return v;
            }finally{
                    if(result != null) result.close();
                    if(st != null) st.close();
            }

    }

    public static int addStudentPart(int part_Id, int batch_Id, String stu_Rollno, String remarks)throws SQLException{
            String query = "insert into STUDENT_PART(part, batch_id, roll_no, remarks) values("+part_Id+", "+batch_Id+", '"+stu_Rollno+"', '"+remarks+"')";
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }finally{
                    if(st != null) st.close();
            }
    }

    public static int updateStudentPart(int part_Id, int batch_Id, String stu_Rollno, String remarks)throws SQLException{
            String query = "update STUDENT_PART set remarks = '"+remarks+"' where part = "+part_Id+" AND batch_id = "+batch_Id+" AND roll_no = '"+stu_Rollno+"'";
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }finally{
                    if(st != null) st.close();
            }

    }

    public static int deleteStudentPart(int part_Id, int batch_Id, String stu_Rollno)throws SQLException{
            String query = "delete from STUDENT_PART where part = "+part_Id+" AND batch_id = "+batch_Id+" AND roll_no = '"+stu_Rollno+"'";
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }finally{
                    if(st != null) st.close();
            }
    }

    ////**** Student Part Table Attributes ****////



    

    ////**** Part Table Attributes ****////
    public static Vector getPart(int batch_Id)throws Exception{

            String query = "select * from PART where batch_id = "+batch_Id;
            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{
                    st = con.createStatement();
                    result = st.executeQuery(query);
                    Vector<PartBean> v = new Vector<>();

                    while(result.next()){
                            PartBean bean = new PartBean();

                            bean.setPartId(result.getInt("part"));
                            bean.setBatchId(result.getInt("batch_id"));
                            bean.setPartYear(result.getInt("year"));
                            bean.setRemarks(result.getString("remarks"));

                            v.addElement(bean);
                    }
                    return v;
            }finally{
                    if(result != null){
                            result.close();
                    }
                    if(st != null){
                            st.close();
                    }
            }

    }// End getPart Method

    public static int addPart(int part_Id, int batch_Id, int part_Year, String remarks)throws Exception{

            String query = "insert into PART(part, batch_id, year, remarks) values("+part_Id+", "+batch_Id+", "+part_Year+", '"+remarks+"')";
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{

                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }
            finally{
                    if(st != null){
                            st.close();
                    }
            }
    }// End addPart Method

    public static int updatePart(int part_Id, int batch_Id, int part_Year, String remarks)throws Exception{

            String query = "update PART set batch_id = "+batch_Id+", year = '"+part_Year+"', remarks = '"+remarks+"' where part = "+part_Id+"";
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }
            finally{
                    if(st != null){
                            st.close();
                    }
            }
    }// End updatePart Method

    public static int deletePart(int part_Id, int batch_Id)throws Exception{
            String query = "delete from PART where part = "+part_Id+" and batch_id = "+batch_Id;
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }
            finally{
                    if(st != null){
                            st.close();
                    }
            }
    }// End deletePart Method

    ////**** Part Table Attributes ****////



    

    ////**** SeatList Table Attributes ****////
    public static Vector getSeatList(int seatList_Id)throws Exception{

            String query = "select * from SEAT_LIST where sl_id = "+seatList_Id;
            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{
                    st = con.createStatement();
                    result = st.executeQuery(query);
                    Vector<SeatListBean> v = new Vector<>();

                    while(result.next()){
                            SeatListBean bean = new SeatListBean();

                            bean.setSeatListId(result.getInt("sl_id"));
                            bean.setBatchId(result.getInt("batch_id"));
                            bean.setPartId(result.getInt("part"));
                            bean.setSeatListYear(result.getInt("year"));
                            bean.setSeatListType(result.getString("type"));
                            bean.setRemarks(result.getString("remarks"));

                            v.addElement(bean);
                    }
                    return v;
            }finally{
                    if(result != null){
                            result.close();
                    }
                    if(st != null){
                            st.close();
                    }
            }

    }// End getSeatList Method

    /////**** OverLoaded Method *****/////
    public static Vector getSeatList(int part_Id, int batch_Id)throws Exception{

            String query = "select * from SEAT_LIST where part = "+part_Id+" AND batch_id = "+batch_Id;
            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{
                    st = con.createStatement();
                    result = st.executeQuery(query);
                    Vector<SeatListBean> v = new Vector<>();

                    while(result.next()){
                            SeatListBean bean = new SeatListBean();

                            bean.setSeatListId(result.getInt("sl_id"));
                            bean.setBatchId(result.getInt("batch_id"));
                            bean.setPartId(result.getInt("part"));
                            bean.setSeatListYear(result.getInt("year"));
                            bean.setSeatListType(result.getString("type"));
                            bean.setRemarks(result.getString("remarks"));

                            v.addElement(bean);
                    }
                    return v;
            }finally{
                    if(result != null){
                            result.close();
                    }
                    if(st != null){
                            st.close();
                    }
            }

    }// End getSeatList Method

    public static int addSeatList(int part_Id, int batch_Id, int seatList_Year, String seatList_Type, String remarks)throws Exception{

            String query = "insert into SEAT_LIST(part, batch_id, year, type, remarks) values("+part_Id+", "+batch_Id+", "+seatList_Year+", '"+seatList_Type+"','"+remarks+"')";
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{

                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }
            finally{
                    if(st != null){
                            st.close();
                    }
            }
    }// End addSeatList Method

    public static int updateSeatList(int seatList_Id, int part_Id, int batch_Id, int seatList_Year, String seatList_Type, String remarks)throws Exception{

            String query = "update SEAT_LIST set part = "+part_Id+", batch_id = "+batch_Id+", year = "+seatList_Year+", type = '"+seatList_Type+"', remarks = '"+remarks+"' where sl_id = "+seatList_Id+"";
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }
            finally{
                    if(st != null){
                            st.close();
                    }
            }
    }// End updateSeatList Method

    public static int deleteSeatList(int seatList_Id)throws Exception{
            String query = "delete from SEAT_LIST where sl_id = "+seatList_Id;
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }
            finally{
                    if(st != null){
                            st.close();
                    }
            }
    }// End deleteSeatList Method

    ////**** SeatList Table Attributes ****////


    
    

    ////**** SeatListDetails Table Attributes ****////
    public static Vector getSeatListDetails(int seatList_Id, int part_Id, int batch_Id)throws Exception{

            String query = "select * from SEAT_LIST_DETAIL where sl_id = "+seatList_Id+" AND part = "+part_Id+" AND batch_id = "+batch_Id;
            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{
                    st = con.createStatement();
                    result = st.executeQuery(query);
                    Vector<SeatListDetailBean> v = new Vector<>();

                    while(result.next()){
                            SeatListDetailBean bean = new SeatListDetailBean();

                            bean.setSeatListId(result.getInt("sl_id"));
                            bean.setBatchId(result.getInt("batch_id"));
                            bean.setPartId(result.getInt("part"));
                            bean.setStuRollno(result.getString("roll_no"));
                            bean.setStatus(result.getString("status"));
                            bean.setRemarks(result.getString("remarks"));

                            v.addElement(bean);
                    }
                    return v;
            }finally{
                    if(result != null){
                            result.close();
                    }
                    if(st != null){
                            st.close();
                    }
            }

    }// End getSeatListDetails Method

    /////**** OverLoaded Method *****/////
    public static Vector getSeatListDetails(int seatList_Id)throws Exception{
            String query = "select * from SEAT_LIST_DETAIL where sl_id = "+seatList_Id;
            System.out.println(query);


            Statement st = null;
            ResultSet result = null;

            try{
                    Vector<SeatListDetailBean> v = new Vector<>();

                    st = con.createStatement();
                    result = st.executeQuery(query);

                    while(result.next()){
                            SeatListDetailBean bean = new SeatListDetailBean();

                            bean.setSeatListId(result.getInt("sl_id"));
                            bean.setBatchId(result.getInt("batch_id"));
                            bean.setPartId(result.getInt("part"));
                            bean.setStuRollno(result.getString("roll_no"));
                            bean.setStatus(result.getString("status"));
                            bean.setRemarks(result.getString("remarks"));

                            v.addElement(bean);
                    }

                    return v;

            }finally{
                    if(st != null){
                            st.close();
                    }
                    if(result != null){
                            result.close();
                    }

            }
    }

    /////**** OverLoaded Method *****/////
    public static Vector getSeatListDetails(int seatList_Id, int part_Id, int batch_Id, String stu_RollNo)throws Exception{

            String query = "select * from SEAT_LIST_DETAIL where sl_id = "+seatList_Id+" AND part = "+part_Id+" AND batch_id = "+batch_Id+" AND roll_no = '"+stu_RollNo+"'";
            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{
                    st = con.createStatement();
                    result = st.executeQuery(query);
                    Vector<SeatListDetailBean> v = new Vector<>();

                    while(result.next()){
                            SeatListDetailBean bean = new SeatListDetailBean();

                            bean.setSeatListId(result.getInt("sl_id"));
                            bean.setBatchId(result.getInt("batch_id"));
                            bean.setPartId(result.getInt("part"));
                            bean.setStuRollno(result.getString("roll_no"));
                            bean.setStatus(result.getString("status"));
                            bean.setRemarks(result.getString("remarks"));

                            v.addElement(bean);
                    }
                    return v;
            }finally{
                    if(result != null){
                            result.close();
                    }
                    if(st != null){
                            st.close();
                    }
            }

    }// End getSeatListDetails Method

    public static int addSeatListDetails(int seatList_Id, int part_Id, int batch_Id, String stu_RollNo, String status, String remarks)throws Exception{

            String query = "insert into SEAT_LIST_DETAIL(sl_id, part, batch_id, roll_ro, status, remarks) values("+seatList_Id+", "+part_Id+", "+batch_Id+", '"+stu_RollNo+"', '"+status+"','"+remarks+"')";
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{

                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }
            finally{
                    if(st != null){
                            st.close();
                    }
            }
    }// End addSeatListDetails Method

    public static int updateSeatListDetails(int seatList_Id, int part_Id, int batch_Id, String stu_RollNo, String status, String remarks)throws Exception{

            String query = "update SEAT_LIST_DETAIL set status = '"+status+"', remarks = '"+remarks+"' where sl_id = "+seatList_Id+" AND part = "+part_Id+" AND batch_id = "+batch_Id+" AND roll_no = '"+stu_RollNo+"'";
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }
            finally{
                    if(st != null){
                            st.close();
                    }
            }
    }// End updateSeatListDetails Method

    public static int deleteSeatListDetails(int seatList_Id, int part_Id, int batch_Id, String stu_RollNo)throws Exception{
            String query = "delete from SEAT_LIST_DETAIL where sl_id = "+seatList_Id+" AND part = "+part_Id+" AND batch_id = "+batch_Id+" AND roll_no = '"+stu_RollNo+"'";
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }
            finally{
                    if(st != null){
                            st.close();
                    }
            }
    }// End deleteSeatListDetails Method
    ////**** SeatListDetails Table Attributes ****////

    
    


    ////**** Ledger Table Attributes ****////
    
    public static LedgerBean getLedger(int seatList_Id)throws Exception{
            String query = "select * from LEDGER where sl_id = "+seatList_Id;

            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{
                    st = con.createStatement();
                    result = st.executeQuery(query);

                    LedgerBean beans = null;
                    if(result.next()){
                            beans = new LedgerBean();

                            beans.setSeatListId(result.getInt("sl_id"));
                            beans.setSchemeId(result.getInt("scheme_id"));
                            beans.setSchemePart(result.getInt("scheme_part"));
                            beans.setIsAnn(result.getString("is_announced"));
                            beans.setAnnDate(result.getString("ann_date"));
                            beans.setRemarks(result.getString("remarks"));
                    }

                    return beans;
            }finally{
                    if(result != null){
                            result.close();
                    }

                    if(st != null){
                            st.close();
                    }
            }
    }

    public static LedgerBean getLedger(int seatList_Id, int scheme_Id, int scheme_Part)throws Exception{
            String query = "select * from LEDGER where sl_id = "+seatList_Id+" AND scheme_id = "+scheme_Id+" AND scheme_part = "+scheme_Part;

            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{
                    st = con.createStatement();
                    result = st.executeQuery(query);

                    LedgerBean beans = null;
                    if(result.next()){
                            beans = new LedgerBean();

                            beans.setSeatListId(result.getInt("sl_id"));
                            beans.setSchemeId(result.getInt("scheme_id"));
                            beans.setSchemePart(result.getInt("scheme_part"));
                            beans.setIsAnn(result.getString("is_announced"));
                            beans.setAnnDate(result.getString("ann_date"));
                            beans.setRemarks(result.getString("remarks"));
                    }

                    return beans;
            }finally{
                    if(result != null){
                            result.close();
                    }

                    if(st != null){
                            st.close();
                    }
            }
    }

    public static int addLedger(int seatList_Id, int scheme_Id, int scheme_Part, String is_Ann, String ann_Date, String remarks)throws Exception{
            String query = "insert into LEDGER(sl_id, scheme_id, scheme_part, is_announced, ann_date, remarks) values("+seatList_Id+", "+scheme_Id+", "+scheme_Part+", '"+is_Ann+"', '"+ann_Date+"', '"+remarks+"')";

            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }finally{
                    if(st != null){
                            st.close();
                    }
            }
    }

    public static int updateLedger(int seatList_Id, int scheme_Id, int scheme_Part, String is_Ann, String ann_Date, String remarks)throws Exception{
            String query = "update LEDGER set is_announced = '"+is_Ann+"', ann_date = '"+ann_Date+"', remarks = '"+remarks+"' where sl_id = "+seatList_Id+" AND scheme_id = "+scheme_Id+" AND scheme_part = "+scheme_Part;

            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }finally{
                    if(st != null){
                            st.close();
                    }
            }
    }

    ////**** Ledger Table Attributes ****////




    ////**** Ledger List Details Table Attributes ****////

    public static Vector getLedgerListDetails()throws Exception{
            String query = "select * from LEDGER_LIST_DETAIL";

            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{
                    st = con.createStatement();
                    result = st.executeQuery(query);

                    Vector<LedgerListDetailBean> v = new Vector<>();

                    while(result.next()){
                            LedgerListDetailBean beans = new LedgerListDetailBean();

                            beans.setSeatListId(result.getInt("sl_id"));
                            beans.setStuRollNo(result.getString("roll_no"));
                            beans.setRemarks(result.getString("remarks"));

                            v.addElement(beans);
                    }

                    return v;
            }finally{
                    if(result != null){
                            result.close();
                    }

                    if(st != null){
                            st.close();
                    }
            }
    }

    /////**** OverLoaded Method *****/////
    public static Vector getLedgerListDetails(int seatList_Id)throws Exception{
            String query = "select * from LEDGER_LIST_DETAIL where sl_id = "+seatList_Id;

            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{
                    st = con.createStatement();
                    result = st.executeQuery(query);

                    Vector<LedgerListDetailBean> v = new Vector<>();

                    while(result.next()){
                            LedgerListDetailBean beans = new LedgerListDetailBean();

                            beans.setSeatListId(result.getInt("sl_id"));
                            beans.setStuRollNo(result.getString("roll_no"));
                            beans.setRemarks(result.getString("remarks"));

                            v.addElement(beans);
                    }

                    return v;
            }finally{
                    if(result != null){
                            result.close();
                    }

                    if(st != null){
                            st.close();
                    }
            }
    }

    /////**** OverLoaded Method *****/////
    public static Vector getLedgerListDetails(int seatList_Id, String stu_RollNo)throws Exception{
            String query = "select * from LEDGER_LIST_DETAIL where sl_id = "+seatList_Id+" AND roll_no = '"+stu_RollNo+"'";

            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{
                    st = con.createStatement();
                    result = st.executeQuery(query);

                    Vector<LedgerListDetailBean> v = new Vector<>();

                    while(result.next()){
                            LedgerListDetailBean beans = new LedgerListDetailBean();

                            beans.setSeatListId(result.getInt("sl_id"));
                            beans.setStuRollNo(result.getString("roll_no"));
                            beans.setRemarks(result.getString("remarks"));

                            v.addElement(beans);
                    }

                    return v;
            }finally{
                    if(result != null){
                            result.close();
                    }

                    if(st != null){
                            st.close();
                    }
            }
    }

    public static int addLedgerListDetails(int seatList_Id, String stu_RollNo, String remarks)throws Exception{
            String query = "insert into LEDGER_LIST_DETAIL(sl_id, roll_no, remarks) values("+seatList_Id+", '"+stu_RollNo+"', '"+remarks+"')";

            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }finally{
                    if(st != null){
                            st.close();
                    }
            }
    }

    public static int updateLedgerListDetails(int seatList_Id, String stu_RollNo, String remarks)throws Exception{
            String query = "update LEDGER_LIST_DETAIL set remarks = '"+remarks+"' where sl_id = "+seatList_Id+" AND roll_no = '"+stu_RollNo+"'";

            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }finally{
                    if(st != null){
                            st.close();
                    }
            }
    }

    public static int deleteLedgerListDetails(int seatList_Id, String stu_RollNo)throws Exception{
            String query = "delete from LEDGER_LIST_DETAIL where sl_id = "+seatList_Id+" AND roll_no = '"+stu_RollNo+"'";
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }
            finally{
                    if(st != null){
                            st.close();
                    }
            }
    }// End deleteLedgerListDetails Method
    ////**** Ledger List Details Table Attributes ****////



    ////**** LedgerSemester Table Attributes ****////

    public static Vector getLedgerSemester(int seatList_Id, int semester)throws Exception{
            String query = "select * from LEDGER_SEMESTER where sl_id = "+seatList_Id+" AND semester = "+semester;

            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{
                    st = con.createStatement();
                    result = st.executeQuery(query);

                    Vector<LedgerSemesterBean> v = new Vector<>();

                    while(result.next()){
                            LedgerSemesterBean beans = new LedgerSemesterBean();

                            beans.setSeatListId(result.getInt("sl_id"));
                            beans.setStuRollNo(result.getString("roll_no"));
                            beans.setSemester(result.getInt("semester"));
                            beans.setRemarks(result.getString("remarks"));

                            v.addElement(beans);
                    }

                    return v;
            }finally{
                    if(result != null){
                            result.close();
                    }

                    if(st != null){
                            st.close();
                    }
            }
    }

    /////**** OverLoaded Method *****/////
    public static Vector getLedgerSemester(int seatList_Id, String stu_RollNo, int semester)throws Exception{
            String query = "select * from LEDGER_SEMESTER where sl_id = "+seatList_Id+" AND roll_no = '"+stu_RollNo+"' AND semester = "+semester;

            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{
                    st = con.createStatement();
                    result = st.executeQuery(query);

                    Vector<LedgerListDetailBean> v = new Vector<>();

                    while(result.next()){
                            LedgerListDetailBean beans = new LedgerListDetailBean();

                            beans.setSeatListId(result.getInt("sl_id"));
                            beans.setStuRollNo(result.getString("roll_no"));
                            beans.setRemarks(result.getString("remarks"));

                            v.addElement(beans);
                    }

                    return v;
            }finally{
                    if(result != null){
                            result.close();
                    }

                    if(st != null){
                            st.close();
                    }
            }
    }

    public static int addLedgerSemester(int seatList_Id, String stu_RollNo, int semester, String remarks)throws Exception{
            String query = "insert into LEDGER_SEMESTER(sl_id, roll_no, semester, remarks) values("+seatList_Id+", '"+stu_RollNo+"', "+semester+", '"+remarks+"')";

            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }finally{
                    if(st != null){
                            st.close();
                    }
            }
    }

    public static int updateLedgerSemester(int seatList_Id, String stu_RollNo, int semester, String remarks)throws Exception{
            String query = "update LEDGER_SEMESTER set remarks = '"+remarks+"' where sl_id = "+seatList_Id+" AND roll_no = '"+stu_RollNo+"' AND semester = "+semester;

            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }finally{
                    if(st != null){
                            st.close();
                    }
            }
    }

    public static int deleteLedgerSemester(int seatList_Id, String stu_RollNo, int semester)throws Exception{
            String query = "delete from LEDGER_SEMESTER where sl_id = "+seatList_Id+" AND roll_no = '"+stu_RollNo+"' AND semester = "+semester;
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }
            finally{
                    if(st != null){
                            st.close();
                    }
            }
    }// End deleteLedgerSemester Method

    ////**** LedgerSemester Table Attributes ****////


    
    



    ////**** LedgerDetails Table Attributes ****////
    public static Vector getLedgerDetails(int seatList_Id, byte a)throws Exception{
            String query = "select * from LEDGER_DETAILS where sl_id = "+seatList_Id;
            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{

                    st = con.createStatement();
                    result = st.executeQuery(query);
                    Vector<LedgerDetailsBean> v = new Vector<>();

                    while(result.next()){

                            LedgerDetailsBean beans = new LedgerDetailsBean();

                            beans.setSeatListId(result.getInt("sl_id"));
                            beans.setStuRollNo(result.getString("roll_no"));
                            beans.setSchemeId(result.getInt("scheme_id"));
                            beans.setSchemePart(result.getInt("scheme_part"));
                            beans.setSemester(result.getInt("semester"));
                            beans.setCourseNo(result.getString("course_no"));
                            beans.setAcId(result.getString("ac_id"));
                            beans.setMarksObtain(result.getString("marks_obtained"));
                            beans.setGrade(result.getString("grade"));
                            beans.setMinMarks(result.getInt("min_marks"));
                            beans.setQp(result.getInt("qp"));
                            beans.setRemarks(result.getString("remarks"));

                            v.addElement(beans);

                    }

                    return v;

            }finally{
                    if(result != null){
                            result.close();
                    }
                    if(st != null){
                            st.close();
                    }
            }
    }

    /////**** OverLoaded Method *****/////
    public static Vector getLedgerDetails(int seatList_Id)throws Exception{
            String query = "select * from LEDGER_DETAILS where sl_id = "+seatList_Id;
            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{

                    st = con.createStatement();
                    result = st.executeQuery(query);
                    Vector<LedgerDetailsBean> v = null;

                    while(result.next()){
                            v = new Vector<>();
                            LedgerDetailsBean beans = new LedgerDetailsBean();

                            beans.setSeatListId(result.getInt("sl_id"));
                            beans.setStuRollNo(result.getString("roll_no"));
                            beans.setSchemeId(result.getInt("scheme_id"));
                            beans.setSchemePart(result.getInt("scheme_part"));
                            beans.setSemester(result.getInt("semester"));
                            beans.setCourseNo(result.getString("course_no"));
                            beans.setAcId(result.getString("ac_id"));
                            beans.setMarksObtain(result.getString("marks_obtained"));
                            beans.setGrade(result.getString("grade"));
                            beans.setMinMarks(result.getInt("min_marks"));
                            beans.setQp(result.getInt("qp"));
                            beans.setRemarks(result.getString("remarks"));

                            v.addElement(beans);

                    }

                    return v;

            }finally{
                    if(result != null){
                            result.close();
                    }
                    if(st != null){
                            st.close();
                    }
            }
    }

    /////**** OverLoaded Method *****/////
    public static Vector getLedgerDetails(String stu_RollNo)throws Exception{
            String query = "select * from LEDGER_DETAILS where roll_no = '"+stu_RollNo+"'";
            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{

                    st = con.createStatement();
                    result = st.executeQuery(query);
                    Vector<LedgerDetailsBean> v = new Vector<>();

                    while(result.next()){
                            LedgerDetailsBean beans = new LedgerDetailsBean();

                            beans.setSeatListId(result.getInt("sl_id"));
                            beans.setStuRollNo(result.getString("roll_no"));
                            beans.setSchemeId(result.getInt("scheme_id"));
                            beans.setSchemePart(result.getInt("scheme_part"));
                            beans.setSemester(result.getInt("semester"));
                            beans.setCourseNo(result.getString("course_no"));
                            beans.setAcId(result.getString("ac_id"));
                            beans.setMarksObtain(result.getString("marks_obtained"));
                            beans.setGrade(result.getString("grade"));
                            beans.setMinMarks(result.getInt("min_marks"));
                            beans.setQp(result.getInt("qp"));
                            beans.setRemarks(result.getString("remarks"));

                            v.addElement(beans);

                    }

                    return v;

            }finally{
                    if(result != null){
                            result.close();
                    }
                    if(st != null){
                            st.close();
                    }
            }
    }

    /////**** OverLoaded Method *****/////
    public static Vector getLedgerDetails(int seatList_Id, String stu_RollNo)throws Exception{
            String query = "select * from LEDGER_DETAILS where sl_id = "+seatList_Id+" AND roll_no = '"+stu_RollNo+"'";
            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{

                    st = con.createStatement();
                    result = st.executeQuery(query);
                    Vector<LedgerDetailsBean> v = null;

                    while(result.next()){
                            v = new Vector<>();
                            LedgerDetailsBean beans = new LedgerDetailsBean();
                            beans.setSeatListId(result.getInt("sl_id"));
                            beans.setStuRollNo(result.getString("roll_no"));
                            beans.setSchemeId(result.getInt("scheme_id"));
                            beans.setSchemePart(result.getInt("scheme_part"));
                            beans.setSemester(result.getInt("semester"));
                            beans.setCourseNo(result.getString("course_no"));
                            beans.setAcId(result.getString("ac_id"));
                            beans.setMarksObtain(result.getString("marks_obtained"));
                            beans.setGrade(result.getString("grade"));
                            beans.setMinMarks(result.getInt("min_marks"));
                            beans.setQp(result.getInt("qp"));
                            beans.setRemarks(result.getString("remarks"));

                            v.addElement(beans);

                    }

                    return v;

            }finally{
                    if(result != null){
                            result.close();
                    }
                    if(st != null){
                            st.close();
                    }
            }
    }

    /////**** OverLoaded Method *****/////
    public static Vector getLedgerDetails(String course_No, boolean bool)throws Exception{
            String query = "select * from LEDGER_DETAILS where course_no = '"+course_No+"'";
            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{

                    st = con.createStatement();
                    result = st.executeQuery(query);
                    Vector<LedgerDetailsBean> v = new Vector<>();

                    while(result.next()){
                            LedgerDetailsBean beans = new LedgerDetailsBean();

                            beans.setSeatListId(result.getInt("sl_id"));
                            beans.setStuRollNo(result.getString("roll_no"));
                            beans.setSchemeId(result.getInt("scheme_id"));
                            beans.setSchemePart(result.getInt("scheme_part"));
                            beans.setSemester(result.getInt("semester"));
                            beans.setCourseNo(result.getString("course_no"));
                            beans.setAcId(result.getString("ac_id"));
                            beans.setMarksObtain(result.getString("marks_obtained"));
                            beans.setGrade(result.getString("grade"));
                            beans.setMinMarks(result.getInt("min_marks"));
                            beans.setQp(result.getInt("qp"));
                            beans.setRemarks(result.getString("remarks"));

                            v.addElement(beans);

                    }

                    return v;

            }finally{
                    if(result != null){
                            result.close();
                    }
                    if(st != null){
                            st.close();
                    }
            }
    }

    /////**** OverLoaded Method *****/////
    public static Vector getLedgerDetails(int seatList_Id, int scheme_Id, int scheme_Part, String course_No)throws Exception{
            String query = "select * from LEDGER_DETAILS where sl_id = "+seatList_Id+" AND scheme_id = "+scheme_Id+" AND scheme_part = "+scheme_Part+" AND course_no = '"+course_No+"'";
            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{

                    st = con.createStatement();
                    result = st.executeQuery(query);
                    Vector<LedgerDetailsBean> v = null;

                    while(result.next()){
                            v = new Vector<>();
                            LedgerDetailsBean beans = new LedgerDetailsBean();

                            beans.setSeatListId(result.getInt("sl_id"));
                            beans.setStuRollNo(result.getString("roll_no"));
                            beans.setSchemeId(result.getInt("scheme_id"));
                            beans.setSchemePart(result.getInt("scheme_part"));
                            beans.setSemester(result.getInt("semester"));
                            beans.setCourseNo(result.getString("course_no"));
                            beans.setAcId(result.getString("ac_id"));
                            beans.setMarksObtain(result.getString("marks_obtained"));
                            beans.setGrade(result.getString("grade"));
                            beans.setMinMarks(result.getInt("min_marks"));
                            beans.setQp(result.getInt("qp"));
                            beans.setRemarks(result.getString("remarks"));

                            v.addElement(beans);

                    }

                    return v;

            }finally{
                    if(result != null){
                            result.close();
                    }
                    if(st != null){
                            st.close();
                    }
            }
    }

    /////**** OverLoaded Method *****/////
    public static Vector getLedgerDetails(int seatList_Id, int scheme_Id, int scheme_Part, int semester, String course_No)throws Exception{
            String query = "select * from LEDGER_DETAILS where sl_id = "+seatList_Id+" AND scheme_id = "+scheme_Id+" AND scheme_part = "+scheme_Part+" AND semester = "+semester+" AND course_no = '"+course_No+"'";
            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{

                    st = con.createStatement();
                    result = st.executeQuery(query);
                    Vector<LedgerDetailsBean> v = null;

                    while(result.next()){
                            v = new Vector<>();
                            LedgerDetailsBean beans = new LedgerDetailsBean();

                            beans.setSeatListId(result.getInt("sl_id"));
                            beans.setStuRollNo(result.getString("roll_no"));
                            beans.setSchemeId(result.getInt("scheme_id"));
                            beans.setSchemePart(result.getInt("scheme_part"));
                            beans.setSemester(result.getInt("semester"));
                            beans.setCourseNo(result.getString("course_no"));
                            beans.setAcId(result.getString("ac_id"));
                            beans.setMarksObtain(result.getString("marks_obtained"));
                            beans.setGrade(result.getString("grade"));
                            beans.setMinMarks(result.getInt("min_marks"));
                            beans.setQp(result.getInt("qp"));
                            beans.setRemarks(result.getString("remarks"));

                            v.addElement(beans);

                    }

                    return v;

            }finally{
                    if(result != null){
                            result.close();
                    }
                    if(st != null){
                            st.close();
                    }
            }
    }

    public static int addLedgerDetails(int seatList_Id, String stu_RollNo, int scheme_Id, int scheme_Part, int semester, String course_No, String grade, int min_Marks, int qp )throws Exception{
            String query = "insert into LEDGER_DETAILS(sl_id, roll_no, scheme_id, scheme_part, semester, course_no, grade, min_marks, qp) values("+seatList_Id+", '"+stu_RollNo+"', "+scheme_Id+", "+scheme_Part+", "+semester+", '"+course_No+"', '"+grade+"', "+min_Marks+", "+qp+")";
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }finally{
                    if(st != null){
                            st.close();
                    }
            }
    }

    public static int updateLedgerDetails(int seatList_Id, String stu_RollNo, int scheme_Id, int scheme_Part, int semester, String course_No, String grade, String marks_Obtain, int qp )throws Exception{
            String query = "update LEDGER_DETAILS set marks_obtained = '"+marks_Obtain+"', grade = '"+grade+"', qp = "+qp+" where sl_id = "+seatList_Id+" AND roll_no = '"+stu_RollNo+"' AND scheme_id  = "+scheme_Id+" AND scheme_part = "+scheme_Part+" AND semester = "+semester+" AND course_no = '"+course_No+"'";
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }finally{
                    if(st != null){
                            st.close();
                    }
            }
    }

    public static int deleteLedgerDetails(int seatList_Id, String stu_RollNo, int scheme_Id, int scheme_Part, int semester, String course_No)throws Exception{
            String query = "delete from LEDGER_DETAILS where sl_id = "+seatList_Id+" AND roll_no = '"+stu_RollNo+"' AND scheme_id = "+scheme_Id+" AND scheme_part = "+scheme_Part+" AND semester = "+semester+" AND course_no = '"+course_No+"'";
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }
            finally{
                    if(st != null){
                            st.close();
                    }
            }
    }// End deleteLedgerDetails Method

    ////**** LedgerDetails Table Attributes ****////



    
    ////**** LedgerDetailsSummary Table Attributes ****////
    public static Vector getLedgerDetailsSummary()throws Exception{
            String query = "select * from LEDGER_DETAIL_SUMMARY";

            System.out.println(query);


            Statement st = null;
            ResultSet result = null;

            try{
                    st = con.createStatement();
                    result = st.executeQuery(query);
                    Vector<LedgerDetailSummaryBean> v = new Vector<LedgerDetailSummaryBean>();

                    while(result.next()){

                            LedgerDetailSummaryBean beans = new LedgerDetailSummaryBean();
                            beans.setSeatListId(result.getInt("sl_id"));
                            beans.setStuRollNo(result.getString("roll_no"));
                            beans.setTotalMarks(result.getInt("total_marks"));
                            beans.setResultRemarks(result.getString("result_remarks"));
                            beans.setPercentage(result.getFloat("percentage"));
                            beans.setIndvResultAnnDate(result.getString("indv_result_ann_date"));
                            beans.setCgpa(result.getFloat("cgpa"));
                            beans.setObtainMarks(result.getInt("obtain_marks"));
                            beans.setNoDuesRemarks(result.getString("no_dues_remarks"));
                            beans.setAc2No(result.getString("ac_II_no"));
                            beans.setAc2NoDated(result.getString("ac_II_no_dated"));
                            beans.setQp(result.getInt("qp"));
                            beans.setCreditHours(result.getInt("ch"));

                            v.addElement(beans);

                    }

                    return v;
            }
            finally{
                    if(result != null) result.close();
                    if(st != null) st.close();

            }

    }

    public static int addLedgerDetailsSummary(int seatList_Id, String stu_RollNo, int total_Marks, String result_Remarks, float percentage, String indv_Result_Ann_Date, float cgpa, int obtain_Marks, String no_Dues_Remarks, String ac2_No, String ac2_NoDated, int qp, int credit_Hours)throws Exception{

            String query = "insert into LEDGER_DETAIL_SUMMARY(sl_id, roll_no, total_marks, result_remarks, percentage, indv_result_ann_date, cgpa, obtain_marks, no_dues_remarks, ac_II_No, ac_II_no_dated, qp, ch) values("+seatList_Id+", '"+stu_RollNo+"', "+total_Marks+", '"+result_Remarks+"', "+percentage+", '"+indv_Result_Ann_Date+"', "+cgpa+", "+obtain_Marks+", '"+no_Dues_Remarks+"', '"+ac2_No+"', '"+ac2_NoDated+"', "+qp+", "+credit_Hours+")";

            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{
                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }
            finally{
                    if(st != null) st.close();
            }


    }

    ////**** LedgerDetailsSummary Table Attributes ****////


    
    


    ////**** Concentration Area Table Attributes ****////
    public static Vector getConcArea(int scheme_Id, int scheme_Part)throws Exception{

            String query = "select * from CONC_AREA where scheme_id = "+scheme_Id+" AND scheme_part = "+scheme_Part;
            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{
                    st = con.createStatement();
                    result = st.executeQuery(query);
                    Vector<Conc_Area_Bean> v = new Vector<>();

                    while(result.next()){
                            Conc_Area_Bean bean = new Conc_Area_Bean();

                            bean.setAcId(result.getInt("ac_id"));
                            bean.setSchemeId(result.getInt("scheme_id"));
                            bean.setSchemePart(result.getInt("scheme_part"));
                            bean.setTitle(result.getString("title"));
                            bean.setDescription(result.getString("description"));
                            bean.setRemarks(result.getString("remarks"));

                            v.addElement(bean);
                    }
                    return v;
            }finally{
                    if(result != null){
                            result.close();
                    }
                    if(st != null){
                            st.close();
                    }
            }

    }// End getConcArea Method

    public static int addConcArea(int scheme_Id, int scheme_Part, String title, String description, String remarks)throws Exception{

            String query = "insert into CONC_AREA(scheme_id, scheme_part, title, description, remarks) values("+scheme_Id+", "+scheme_Part+", '"+title+"', '"+description+"', '"+remarks+"')";
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{

                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }
            finally{
                    if(st != null){
                            st.close();
                    }
            }
    }// End addConcArea Method

    public static int updateConcArea(int ac_Id, int scheme_Id, int scheme_Part, String title, String description, String remarks)throws Exception{

            String query = "upadte CONC_AREA set title = '"+title+"', description = '"+description+"', remarks = '"+remarks+"' where ac_id = "+ac_Id+" AND scheme_id = "+scheme_Id+" AND scheme_part = "+scheme_Part;
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{

                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }
            finally{
                    if(st != null){
                            st.close();
                    }
            }
    }// End addConcArea Method

    public static int deleteConcArea(int ac_Id, int scheme_Id, int scheme_Part)throws Exception{

            String query = "delete from CONC_AREA where ac_id = "+ac_Id+" AND scheme_id = "+scheme_Id+" AND scheme_part = "+scheme_Part;
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{

                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }
            finally{
                    if(st != null){
                            st.close();
                    }
            }
    }// End addConcArea Method
    ////**** Concentration Area Table Attributes ****////
    


    ////**** AcSchemeDetails Table Attributes ****////    
    public static Vector getAcScheme()throws Exception{

            String query = "select * from AC_SCHEME_DETAIL";
            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{
                    st = con.createStatement();
                    result = st.executeQuery(query);
                    Vector<Ac_Scheme_Detail_Bean> v = new Vector<>();

                    while(result.next()){
                            Ac_Scheme_Detail_Bean beans = new Ac_Scheme_Detail_Bean();
                            
                            beans.setAcId(result.getInt("ac_id"));
                            beans.setSchemeId(result.getInt("scheme_id"));
                            beans.setSchemePart(result.getInt("scheme_part"));
                            beans.setCourseNo(result.getString("course_no"));
                            beans.setCourseTite(result.getString("course_title"));
                            beans.setCreditHours(result.getInt("cr_hrs"));
                            beans.setMaxMarks(result.getInt("max_marks"));
                            beans.setRemarks(result.getString("remarks"));
                            beans.setSemester(result.getInt("semester"));
                            beans.setIsCreditable(result.getString("is_creditable"));
                                
                            v.addElement(beans);
                    }
                    return v;
            }finally{
                    if(result != null){
                            result.close();
                    }
                    if(st != null){
                            st.close();
                    }
            }

    }// End getConcArea Method
    
    ////**** OVERIDE METHOD
    public static Vector getAcScheme(int ac_Id, int scheme_Id, int scheme_Part)throws Exception{

            String query = "select * from AC_SCHEME_DETAIL where ac_id = "+ac_Id+" AND scheme_id = "+scheme_Id+" AND scheme_part = "+scheme_Part;
            System.out.println(query);

            Statement st = null;
            ResultSet result = null;

            try{
                    st = con.createStatement();
                    result = st.executeQuery(query);
                    Vector<Ac_Scheme_Detail_Bean> v = new Vector<>();

                    while(result.next()){
                            Ac_Scheme_Detail_Bean beans = new Ac_Scheme_Detail_Bean();
                            
                            beans.setAcId(result.getInt("ac_id"));
                            beans.setSchemeId(result.getInt("scheme_id"));
                            beans.setSchemePart(result.getInt("scheme_part"));
                            beans.setCourseNo(result.getString("course_no"));
                            beans.setCourseTite(result.getString("course_title"));
                            beans.setCreditHours(result.getInt("cr_hrs"));
                            beans.setMaxMarks(result.getInt("max_marks"));
                            beans.setRemarks(result.getString("remarks"));
                            beans.setSemester(result.getInt("semester"));
                            beans.setIsCreditable(result.getString("is_creditable"));
                                
                            v.addElement(beans);
                    }
                    return v;
            }finally{
                    if(result != null){
                            result.close();
                    }
                    if(st != null){
                            st.close();
                    }
            }

    }// End getConcArea Method

    public static int addAcScheme(int ac_Id, int scheme_Id, int scheme_Part, String course_No, String course_Title, int credit_Hours, int max_Marks, String remarks, int semester, String is_Creditable)throws Exception{

            String query = "insert into AC_SCHEME_DETAIL(ac_id, scheme_id, scheme_part, course_no, course_title, cr_hrs, max_marks, remarks, semester, is_creditable ) values("+ac_Id+", "+scheme_Id+", "+scheme_Part+", '"+course_No+"', '"+course_Title+"', "+credit_Hours+", "+max_Marks+", '"+remarks+"', "+semester+", '"+is_Creditable+"' )";
            
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{

                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }
            finally{
                    if(st != null){
                            st.close();
                    }
            }
    }// End addConcArea Method

    public static int updateAcScheme(int ac_Id, int scheme_Id, int scheme_Part, String course_No, String course_Title, int credit_Hours, int max_Marks, String remarks, int semester, String is_Creditable)throws Exception{

            String query = "update AC_SCHEME_DETAIL set course_no = '"+course_No+"', course_title = '"+course_Title+"', cr_hrs = "+credit_Hours+", max_marks = "+max_Marks+", remarks = '"+remarks+"', semester = "+semester+", is_creditable = '"+is_Creditable+"' where ac_id = "+ac_Id+" AND scheme_id = "+scheme_Id+" AND scheme_part = "+scheme_Part+"";
            
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{

                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }
            finally{
                    if(st != null){
                            st.close();
                    }
            }
    }// End addConcArea Method
    
    public static int deleteAcScheme(int ac_Id, int scheme_Id, int scheme_Part, String course_No)throws Exception{

            String query = "delete From AC_SCHEME_DETAIL where ac_id = "+ac_Id+" AND scheme_id = "+scheme_Id+" AND scheme_part = "+scheme_Part+" AND course_no = '"+course_No+"'";
            
            System.out.println(query);

            Statement st = null;
            int row = 0;

            try{

                    st = con.createStatement();
                    row = st.executeUpdate(query);

                    return row;
            }
            finally{
                    if(st != null){
                            st.close();
                    }
            }
    }// End addConcArea Method

    ////**** AcSchemeDetails Table Attributes ****////


	
    
}
