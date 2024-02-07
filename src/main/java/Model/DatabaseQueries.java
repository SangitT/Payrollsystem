
package Model;


public class DatabaseQueries {
    
    //DATABASE CREATION QUERIES
    public static final String DROP_IF_EXIST_DATABASE = "DROP DATABASE IF EXISTS payroll_db;";
    public static final String CREATE_NEW_PAYROLL_DATABASE = "CREATE DATABASE payroll_db;";
    
    //TABLES CREATION QUERIES
    public static final String CREATE_NEW_STAFF_TABLE = "CREATE TABLE staff (\n" +
                                                        "    staff_id INTEGER AUTO_INCREMENT,\n" +
                                                        "    firstname VARCHAR(255),\n" +
                                                        "    lastname VARCHAR(255),\n" +
                                                        "    dateofbirth DATE,\n" +
                                                        "    gender VARCHAR(10),\n" +
                                                        "    address VARCHAR(255),\n" +
                                                        "    contactnumber VARCHAR(15),\n"
                                                        + "  employeetype VARCHAR(50)," +
                                                        "    startdate DATE,\n" +
                                                        "    jobtitle VARCHAR(100),"
                                                        +   "email VARCHAR(255),"
                                                        + " PRIMARY KEY (staff_id),\n" +
                                                        "    UNIQUE KEY unique_employee (staff_id, firstname)\n" +
                                                        ");";
    public static final String CREATE_NEW_BANKING_DATA_TABLE = "CREATE TABLE banking_data (\n" +
                                                            "    bank_id INTEGER PRIMARY KEY AUTO_INCREMENT,\n" +
                                                            "    staff_id INTEGER,\n" +
                                                            "    bankname VARCHAR(255),\n"
                                                          + "    bsbnumber VARCHAR(255)," +
                                                            "    accountname VARCHAR(255),\n" +
                                                            "    accountnumber VARCHAR(50),\n" +
                                                            "    FOREIGN KEY (staff_id) REFERENCES staff(staff_id)\n" +
                                                            ");";
    public static final String CREATE_STAFF_LOGIN_TABLE= "CREATE TABLE staff_login (\n" +
                                                    "    user_id INTEGER PRIMARY KEY,\n" +
                                                    "    email VARCHAR(255) UNIQUE NOT NULL,\n" +
                                                    "    password VARCHAR(255) NOT NULL,\n" +
                                                    "    FOREIGN KEY (user_id) REFERENCES staff(staff_id)\n" +
                                                    ");";
        public static final String CREATE_NEW_STAFF_PAYSLIP_TABLE = "CREATE TABLE staff_payslip (\n" +
                                                        "    staff_id INTEGER,\n" +
                                                        "    fromDate DATE,\n" +
                                                        "    toDate DATE,\n" +
                                                        "    hoursWorked DOUBLE,\n" +
                                                        "    totalPay DOUBLE,\n" +
                                                        "    netPay DOUBLE,\n" +
                                                        "    tax DOUBLE,"
                                                        + "  superannuation DOUBLE,\n" +
                                                        "    PRIMARY KEY (staff_id, fromDate),\n" +
                                                        "    FOREIGN KEY (staff_id) REFERENCES staff(staff_id)\n" +
                                                        ");";
    
    //INSERT
    public static final String ADD_NEW_STAFF = "INSERT INTO staff (firstname, lastname, dateofbirth, gender, address, contactnumber, employeetype, startdate, jobtitle, email)\n" +
                                                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    
    public static final String ADD_NEW_BANKING_DATA = "INSERT INTO banking_data (staff_id, bankname, bsbnumber, accountname, accountnumber)"
                                                        + "VALUES (?,?,?,?,?)";
    
    public static final String ADD_STAFF_NEW_LOGIN_DATA = "INSERT INTO staff_login (user_id, email, password) VALUES (?, ?, ?)";
    
    public static final String ADD_NEW_STAFF_PAYSLIP_DATA = "INSERT INTO staff_payslip (staff_id, fromDate, toDate, hoursWorked, totalPay, netPay, tax, superannuation)"
                                                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
    //SELECT
    public static final String SELECT_STAFF_LOGIN_DATA = "SELECT * FROM staff_login where email = ? AND password = ?";
    
    public static final String SELECT_STAFF_LOGIN_DATA_BY_ID = "SELECT * FROM staff_login where user_id = ?";
    
    public static final String SELECT_ALL_BANKING_DATA = "SELECT * FROM banking_data where staff_id = ?";
    
    public static final String SELECT_EMPLOYEE_TYPE = "SELECT employeetype FROM staff where staff_id = ?";
    
    public static final String SELECT_EMPLOYEE_NAME = "SELECT firstname,lastname FROM staff where staff_id = ?";
    
    public static final String SELECT_STAFF_BY_ID = "SELECT * FROM staff where staff_id = ?";
    
    public static final String SELECT_STAFF_PAYSLIP_DATA_BY_DATE = "SELECT * FROM staff_payslip where staff_id = ? AND fromDate = ?";
    
    public static final String SEARCH_STAFF_BY_NAME = "SELECT * FROM staff WHERE firstname = ? AND lastname = ? AND contactnumber = ?";
    
    public static final String SELECT_STAFF_LOGIN__BY_EMAIL = "SELECT * FROM staff_login WHERE email = ?";
    
    public static final String SELECT_ALL_PAYSLIPS_BY_WEEK = "SELECT * FROM staff_payslip WHERE fromDate = ?";
    
    public static final String SELECT_ALL_PAYSLIPS_BETWEEN_DATE = "SELECT * FROM staff_payslip WHERE toDate BETWEEN ? AND ?";
    
    
    //UPDATE
    public static final String UPDATE_SATFF_BY_EMPLOYEE = "UPDATE staff "
                                                + "SET firstname = ?, lastname = ?, address = ?, contactnumber = ?, email = ? "
                                                + "WHERE staff_id = ?";
    
    public static final String UPDATE_STAFF_BY_STAFF = "UPDATE staff "
                                                    + "SET firstname = ?, lastname=?, dateofbirth=?, gender=?, address =?, contactnumber=?,"
                                                    + "employeetype=?, startdate=?, jobtitle=?, email=? WHERE staff_id = ?";
    public static final String UPDATE_BANKING_DATA_BY_STAFF = "UPDATE banking_data "
                                                        + "SET bankname=?, bsbnumber=?, accountname=?, accountnumber=? "
                                                        + "WHERE staff_id=?";
    
    public static final String UPDATE_STAFF_LOGIN_BY_ID = "UPDATE staff_login "
                                                + "SET email = ?, password = ? "
                                                + "WHERE user_id = ?";
    
    public static final String UPDATE_BANKING_DATA_BY_ID = "UPDATE banking_data "
                                                    + "SET bankname = ?, bsbnumber = ?, accountname = ?, accountnumber = ? "
                                                    + "WHERE staff_id = ?";
    
    
}
