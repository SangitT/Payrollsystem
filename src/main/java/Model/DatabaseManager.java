package Model;

import Controller.UpdateEmployeeSceneController;
import Controller.ViewEmployeeSceneController;
import Controller.ViewPayslipsDetailsSceneController;
import View.App;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private String URL;
    private final String USERNAME;
    private final String PASSWORD;
    private String database;
    private EmployeePayslipEntity cp;

    public DatabaseManager(String username, String password) {
        this.URL = "jdbc:mysql://localhost:3306/";
        this.USERNAME = username;
        this.PASSWORD = password;

    }
    
    public void createDatabaseAndTables()
    {
        createDatabaseIfNotExist();
        createNewTableEmployee();
        createNewTableBank();
        createNewTableLogin();
        createNewTablePayslip();
    }

    public int createDatabaseIfNotExist() {
        Connection c = null;
        try {
            // make the connection
            c = this.connectToTheDatabase();

            // make the statement
            Statement s = c.createStatement();

            // get the database creation query
            String drop = DatabaseQueries.DROP_IF_EXIST_DATABASE;
            String create = DatabaseQueries.CREATE_NEW_PAYROLL_DATABASE;

            // execute the database creation query
            s.execute(drop);
            s.execute(create);

            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        this.database = "payroll_db";
        URL = URL + database;
        return 1;
    }

    public void createNewTableEmployee() {
        Connection c = null;
        try {
            // make the connection
            c = this.connectToTheDatabase();

            // make the statement
            Statement s = c.createStatement();

            // get the database creation query
            String create = DatabaseQueries.CREATE_NEW_STAFF_TABLE;

            // execute the database creation query
            s.execute(create);

            c.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void createNewTableBank() {
        Connection c = null;
        try {
            // make the connection
            c = this.connectToTheDatabase();

            // make the statement
            Statement s = c.createStatement();

            // get the database creation query
            String create = DatabaseQueries.CREATE_NEW_BANKING_DATA_TABLE;

            // execute the database creation query
            s.execute(create);

            c.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void createNewTableLogin() {
        Connection c = null;
        try {
            // make the connection
            c = this.connectToTheDatabase();

            // make the statement
            Statement s = c.createStatement();

            // get the database creation query
            String create = DatabaseQueries.CREATE_STAFF_LOGIN_TABLE;

            // execute the database creation query
            s.execute(create);

            inserDataIntoTables();
            

            c.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
    


    public void createNewTablePayslip() {
        Connection c = null;
        try {
            // make the connection
            c = this.connectToTheDatabase();

            // make the statement
            Statement s = c.createStatement();

            // get the database creation query
            String create = DatabaseQueries.CREATE_NEW_STAFF_PAYSLIP_TABLE;

            // execute the database creation query
            s.execute(create);

            c.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public int addNewEmployeeData(EmployeeEntity emp, BankEntity ba) {

        Connection c = null;
        try {
            // make the connection
            c = this.connectToTheDatabase();
            String add = DatabaseQueries.ADD_NEW_STAFF;
            // make a preparedststement
            PreparedStatement ps = c.prepareStatement(add);
            ps.setString(1, emp.getFirstName());
            ps.setString(2, emp.getLastName());
            ps.setDate(3, emp.getDateOfBirth());
            ps.setString(4, emp.getGenderType());
            ps.setString(5, emp.getAddress());
            ps.setString(6, emp.getContactNumber());
            ps.setString(7, emp.getEmployeeType());
            ps.setDate(8, emp.getJoiningDate());
            ps.setString(9, emp.getJobTitle());
            ps.setString(10, emp.getEmailAddress());
            

            Statement s = c.createStatement();
            int ra = ps.executeUpdate();

            if (ra > 0) {
                ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
                if (rs.next()) {
                    int id = rs.getInt(1);
                    emp.setEmployeeid(id);
                }
            }
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public void addNewBankData(BankEntity ba, int empID) {
        Connection c = null;
        try {
            // make the connection
            c = this.connectToTheDatabase();
            String add = DatabaseQueries.ADD_NEW_BANKING_DATA;
            // make a preparedststement
            PreparedStatement ps = c.prepareStatement(add);

            ps.setInt(1, empID);
            ps.setString(2, ba.getNameOfBank());
            ps.setString(3, ba.getBsbNumber());
            ps.setString(4, ba.getAccountHolderName());
            ps.setString(5, ba.getBankAccountNumber());

            ps.executeUpdate();

            c.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void addNewLoginData(LoginEntity data) {
        Connection c = null;
        try {
            // make the connection
            c = this.connectToTheDatabase();
            String add = DatabaseQueries.ADD_STAFF_NEW_LOGIN_DATA;
            // make a preparedststement
            PreparedStatement ps = c.prepareStatement(add);

            ps.setInt(1, data.getUserId());
            ps.setString(2, data.getEmail());
            ps.setString(3, data.getPassword());

            ps.executeUpdate();

            c.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public boolean addNewPayslipData(EmployeePayslipEntity p) {
        EmployeePayslipEntity pslip = fetchPayslipData(p.getEmployeeId(), p.getStartingDate());
        if (pslip == null) {
            Connection c = null;
            try {
                // make the connection
                c = this.connectToTheDatabase();

                // checking if the week already exist
                String add = DatabaseQueries.ADD_NEW_STAFF_PAYSLIP_DATA;
                // make a preparedststement
                PreparedStatement ps = c.prepareStatement(add);

                ps.setInt(1, p.getEmployeeId());
                ps.setDate(2, p.getStartingDate());
                ps.setDate(3, p.getEndingDate());
                ps.setDouble(4, p.getHoursWorked());
                ps.setDouble(5, p.getTotalPay());
                ps.setDouble(6, p.getNetPay());
                ps.setDouble(7, p.getTax());
                ps.setDouble(8, p.getSuperAnnuation());

                ps.executeUpdate();

                c.close();
                return true;

            } catch (Exception e) {
                e.printStackTrace();

            }

        } 
        return false;
    }

    public EmployeePayslipEntity fetchPayslipData(int empId, java.sql.Date date) {
        Connection c = null;
        EmployeePayslipEntity p = null;
        try {
            // make the connection
            c = this.connectToTheDatabase();
            String get = DatabaseQueries.SELECT_STAFF_PAYSLIP_DATA_BY_DATE;
            // make a preparedststement
            PreparedStatement ps = c.prepareStatement(get);

            ps.setInt(1, empId);
            ps.setDate(2, date);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = new EmployeePayslipEntity(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getDouble(4));

            } else {
                return p;
            }
            c.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
        this.cp = p;
        return p;
    }

    public int verifyIfUserExist(String email, String pass) {
        Connection c = null;
        try {
            c = this.connectToTheDatabase();
            String get = DatabaseQueries.SELECT_STAFF_LOGIN_DATA;

            PreparedStatement ps = c.prepareStatement(get);

            ps.setString(1, email);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1);
            } else {
               
                return -1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String getTypeForEmployee(int empID) {
        String et = "";
        Connection c = null;
        try {
            c = this.connectToTheDatabase();
            String get = DatabaseQueries.SELECT_EMPLOYEE_TYPE;

            PreparedStatement ps = c.prepareStatement(get);

            ps.setInt(1, empID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                et = rs.getString(1);
            }
            setEmployeeName(empID);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return et;
    }
    public EmployeeEntity fetchEmployeeData(int empID) {
        
        Connection c = null;
        EmployeeEntity em = null;
        try {
            c = this.connectToTheDatabase();
            String get = DatabaseQueries.SELECT_STAFF_BY_ID;

            PreparedStatement ps = c.prepareStatement(get);

            ps.setInt(1, empID);

            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {

                em = new EmployeeEntity(rs.getString(2), rs.getString(3), rs.getDate(4),rs.getString(5), rs.getString(7), rs.getString(6), rs.getDate(9), rs.getString(10), rs.getString(8),rs.getString(11));
                em.setEmployeeid(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return em;
    }
    
    public EmployeeEntity searchForEmployeeByName(String firstName, String lastName, String contactNumber) {
        
        Connection c = null;
        EmployeeEntity em = null;
        try {
            c = this.connectToTheDatabase();
            String search = DatabaseQueries.SEARCH_STAFF_BY_NAME;

            PreparedStatement ps = c.prepareStatement(search);

            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, contactNumber);

            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {

                em = new EmployeeEntity(rs.getString(2), rs.getString(3), rs.getDate(4),rs.getString(5), rs.getString(7), rs.getString(6), rs.getDate(9), rs.getString(10), rs.getString(8),rs.getString(11));
                em.setEmployeeid(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return em;
    }
    
      public BankEntity fetchBankDetails(int empID) {
        
        Connection c = null;
        BankEntity ba = null;
        try {
            c = this.connectToTheDatabase();
            String get = DatabaseQueries.SELECT_ALL_BANKING_DATA;

            PreparedStatement ps = c.prepareStatement(get);

            ps.setInt(1, empID);

            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                ba = new BankEntity(rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ba;
    }
      
    public LoginEntity fetchLoginDetails(int empID) {
        
        Connection c = null;
        LoginEntity ls = null;
        try {
            c = this.connectToTheDatabase();
            String get = DatabaseQueries.SELECT_STAFF_LOGIN_DATA_BY_ID;

            PreparedStatement ps = c.prepareStatement(get);

            ps.setInt(1, empID);

            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                ls = new LoginEntity(empID, rs.getString(2), rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public void setEmployeeName(int empID) {
        String ename = "";
        Connection c = null;
        try {
            c = this.connectToTheDatabase();
            String get = DatabaseQueries.SELECT_EMPLOYEE_NAME;

            PreparedStatement ps = c.prepareStatement(get);

            ps.setInt(1, empID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                ename += rs.getString(1);
                ename += " ";
                ename += rs.getString(2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        App.setEmployeeName(ename);

    }
    
    public void setFieldForViewEmployee(ViewEmployeeSceneController vec, int empID)
    {
        EmployeeEntity emp = fetchEmployeeData(empID);
        BankEntity ba = fetchBankDetails(empID);
        LoginEntity ld = fetchLoginDetails(empID);
        vec.setFirstNameField(emp.getFirstName());
        vec.setLastNameField(emp.getLastName());
        vec.setDateOfBirthField(emp.getDateOfBirth().toLocalDate());
        vec.setGenderField(emp.getGenderType());
        vec.setAddressField(emp.getAddress());
        vec.setContactNumberField(emp.getContactNumber());
        vec.setEmailField(emp.getEmailAddress());
        vec.setPasswordField(ld.getPassword());
        vec.setStartDateField(emp.getJoiningDate().toString());
        vec.setJobTitleField(emp.getJobTitle());
        vec.setEmployeeType(emp.getEmployeeType());
        vec.setBankNameField(ba.getNameOfBank());
        vec.setBsbField(ba.getBsbNumber());
        vec.setAccountNumberField(ba.getBankAccountNumber());
        vec.setAccountNameField(ba.getAccountHolderName());
    }
    
    public void setFieldsForUpdateEmployee(UpdateEmployeeSceneController uec, int empID)
    {
        EmployeeEntity emp = fetchEmployeeData(empID);
        BankEntity ba = fetchBankDetails(empID);
        LoginEntity ld = fetchLoginDetails(empID);
        uec.setFirstNameField(emp.getFirstName());
        uec.setLastNameField(emp.getLastName());
        uec.setDateOfBirthField(emp.getDateOfBirth().toLocalDate());
        uec.setGenderField(emp.getGenderType());
        uec.setAddressField(emp.getAddress());
        uec.setContactNumberField(emp.getContactNumber());
        uec.setEmailField(emp.getEmailAddress());
        uec.setPasswordField(ld.getPassword());
        uec.setStartDateField(emp.getJoiningDate().toString());
        uec.setJobTitleField(emp.getJobTitle());
        uec.setEmployeeType(emp.getEmployeeType());
        uec.setBankNameField(ba.getNameOfBank());
        uec.setBsbField(ba.getBsbNumber());
        uec.setAccountNumberField(ba.getBankAccountNumber());
        uec.setAccountNameField(ba.getAccountHolderName());
        if(App.getEmployeeType().equalsIgnoreCase("Staff"))
        {
            uec.makeEditable();
        }
    }
    
    public void setFieldsForViewPayslip(ViewPayslipsDetailsSceneController vpdc)    
    {
        EmployeeEntity emp = fetchEmployeeData(App.getEmployeeId());
        vpdc.setFullNameField(emp.getFirstName()+" "+emp.getLastName());
        vpdc.setDateFromField(cp.getStartingDate().toString());
        vpdc.setDateToField(cp.getEndingDate().toString());
        vpdc.setHoursWorkedField(cp.getHoursWorked()+"");
        vpdc.setHourlyRateField("$ "+EmployeePayslipEntity.getPAYRATE()+"");
        vpdc.setTotalField(String.format("$ %.2f",cp.getTotalPay()));
        vpdc.setTaxField(String.format("$ %.2f",cp.getTax()));
        vpdc.setSuperField(String.format("$ %.2f",cp.getSuperAnnuation()));
        vpdc.setNetPayField(String.format("$ %.2f",cp.getNetPay()));
        
    }
    
    public void updateEmployeeDetails(String fname, String lname, String address, String contactNum, String email, String pass)
    {
        Connection c = null;
        try{
            c = connectToTheDatabase();
            
            String update = DatabaseQueries.UPDATE_SATFF_BY_EMPLOYEE;
            
            PreparedStatement ps = c.prepareStatement(update);
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, address);
            ps.setString(4, contactNum);
            ps.setString(5, email);
            ps.setInt(6, App.getEmployeeId());
            
            ps.executeUpdate();
            updateLoginData(email, pass);
            c.close();

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void updateEmployeeDetails(EmployeeEntity emp, BankEntity ba, LoginEntity ld)
    {
        Connection c = null;
        try{
            c = connectToTheDatabase();
            
            String update = DatabaseQueries.UPDATE_STAFF_BY_STAFF;
            
            PreparedStatement ps = c.prepareStatement(update);
            ps.setString(1, emp.getFirstName());
            ps.setString(2, emp.getLastName());
            ps.setDate(3, emp.getDateOfBirth());
            ps.setString(4, emp.getGenderType());
            ps.setString(5, emp.getAddress());
            ps.setString(6, emp.getContactNumber());
            ps.setString(7, emp.getEmployeeType());
            ps.setDate(8, emp.getJoiningDate());
            ps.setString(9, emp.getJobTitle());
            ps.setString(10, emp.getEmailAddress());
            ps.setInt(11, emp.getEmployeeid());
            
            ps.executeUpdate();
            updateLoginData(ld.getEmail(), ld.getPassword());
            updateBankData(ba.getNameOfBank(), ba.getBsbNumber(), ba.getAccountHolderName(), ba.getBankAccountNumber());
            c.close();

        }catch(Exception e)
        {
            e.printStackTrace();
        }        
    }
    
    public void updateLoginData(String email, String pass)
    {
        Connection c = null;
        try{
            c = connectToTheDatabase();
            
            String update = DatabaseQueries.UPDATE_STAFF_LOGIN_BY_ID;
            
            PreparedStatement ps = c.prepareStatement(update);
            ps.setString(1, email);
            ps.setString(2, pass);
            if(App.getEmployeeType().equalsIgnoreCase("Employee"))
            {
                
                ps.setInt(3, App.getEmployeeId());
            }
            else{
                ps.setInt(3, App.getSearchEmployeeID()); 
            }
            
            ps.executeUpdate();
            
            c.close();

        }catch(Exception e)
        {
            e.printStackTrace();
        }        
    }
    
public void updateBankData(String bname, String bsb, String acName, String acNum)
    {
        Connection c = null;
        try{
            c = connectToTheDatabase();
            
            String update = DatabaseQueries.UPDATE_BANKING_DATA_BY_ID;
            
            PreparedStatement ps = c.prepareStatement(update);
            ps.setString(1, bname);
            ps.setString(2, bsb);
            ps.setString(3, acName);
            ps.setString(4, acNum);
            
            if(App.getEmployeeType().equalsIgnoreCase("Employee"))
            {
                
                ps.setInt(5, App.getEmployeeId());
            }
            else{
                ps.setInt(5, App.getSearchEmployeeID()); 
            }
            
            ps.executeUpdate();
            
            c.close();

        }catch(Exception e)
        {
            e.printStackTrace();
        }        
    }

    public boolean isEmailRegistered(String email){
        boolean result= false;
        Connection c = null;
        
        try{
            c = connectToTheDatabase();
            
            String select = DatabaseQueries.SELECT_STAFF_LOGIN__BY_EMAIL;
            PreparedStatement ps = c.prepareStatement(select);
            ps.setString(1, email);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
               result = true;
            }
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public List<EmployeePayslipEntity> fetchPayslip(java.sql.Date date)
    {
        List<EmployeePayslipEntity> li = null;
        Connection c = null;
        try{
            c = connectToTheDatabase();
            
            String get = DatabaseQueries.SELECT_ALL_PAYSLIPS_BY_WEEK;
            
            PreparedStatement ps = c.prepareStatement(get);
            ps.setDate(1, date);
            
            ResultSet rs = ps.executeQuery();
            li = new ArrayList<>();
            while(rs.next())
            {
                li.add(new EmployeePayslipEntity(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getDouble(4)));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return li;
    }
    
    public List<EmployeePayslipEntity> fetchPayslip(java.sql.Date firstDate, java.sql.Date lastDate)
    {
        List<EmployeePayslipEntity> li = null;
        Connection c = null;
        try{
            c = connectToTheDatabase();
            
            String get = DatabaseQueries.SELECT_ALL_PAYSLIPS_BETWEEN_DATE;
            
            PreparedStatement ps = c.prepareStatement(get);
            ps.setDate(1, firstDate);
            ps.setDate(2, lastDate);
            ResultSet rs = ps.executeQuery();
            li = new ArrayList<>();
            while(rs.next())
            {
                li.add(new EmployeePayslipEntity(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getDouble(4)));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return li;
    }

    public Connection connectToTheDatabase() {
        Connection c = null;
        try {
            c = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
    
        public void inserDataIntoTables()
    {
            LocalDate localDate = LocalDate.now();
            // add the Administrator
            EmployeeEntity administrator = new EmployeeEntity("firstname", "lastname", java.sql.Date.valueOf(localDate), "Male", "123456789", "400 Kent street, Sydney, AUSTRALIA", java.sql.Date.valueOf(localDate), "Satff Admin", "Staff","admin@gmail.com");
            BankEntity ba = new BankEntity("Bank Of America", "222 333", "Mr Admin Admin", "5555 5555");
            addNewEmployeeData(administrator, ba);
            addNewBankData(ba, administrator.getEmployeeid());
            LoginEntity ld = new LoginEntity(administrator.getEmployeeid(), "admin@gmail.com", "admin");
            addNewLoginData(ld);
            
            // add employee one
            EmployeeEntity employee1 = new EmployeeEntity("john", "wick", java.sql.Date.valueOf(localDate), "Male", "222222222", "Ontario, Canada", java.sql.Date.valueOf(localDate), "Data Analyst", "Employee","johnwick@gmail.com");
            BankEntity ba1 = new BankEntity("Central Bank Of Canada", "062 225", "Mr John Wick", "6666 6666");
            addNewEmployeeData(employee1, ba1);
            addNewBankData(ba1, employee1.getEmployeeid());
            LoginEntity ld1 = new LoginEntity(employee1.getEmployeeid(), "johnwick@gmail.com", "john1234");
            addNewLoginData(ld1);
            
            // add employee 2
            EmployeeEntity employee2 = new EmployeeEntity("dom", "torreto", java.sql.Date.valueOf(localDate), "Male", "333333333", "Kyoto, Japan", java.sql.Date.valueOf(localDate), "Network Engineer", "Employee","domtorreto@gmail.com");
            BankEntity ba2 = new BankEntity("Bank Of Japan", "444 444", "Mr Dominic Torreto", "7777 7777");
            addNewEmployeeData(employee2, ba2);
            addNewBankData(ba2, employee2.getEmployeeid());
            LoginEntity ld2 = new LoginEntity(employee2.getEmployeeid(), "domtorreto@gmail.com", "dom1234");
            addNewLoginData(ld2);
    }
}
