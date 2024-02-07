package Model;

import java.sql.Date;


public class EmployeePayslipEntity {
    
   private int employeeId; 
   private java.sql.Date startingDate;
   private java.sql.Date endingDate;
   private double hoursWorked;
   private static final double PAYRATE = 30.0;
   private double totalPay;
   private static final double SUPERRATE = 0.11;
   private double netPay;
   private double tax;
   private double superAnnuation;

    public EmployeePayslipEntity(int employeeId, java.sql.Date fromDate, java.sql.Date toDate, double hoursWorked) {
        this.employeeId = employeeId;
        this.startingDate = fromDate;
        this.endingDate = toDate;
        this.hoursWorked = hoursWorked;
        
        
        calculateTotalPay();
        calculateTax();
        calculateNetPay();
        calculateSuperAnnuation();
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    
    
    
    public void calculateTotalPay()
    {
        setTotalPay(getHoursWorked() * PAYRATE);
    }
    
    public void calculateNetPay()
    {
        setNetPay(getTotalPay() - getTax());
    }
    
    public void calculateTax()
    {
        setTax(calculateTaxByWeek(getTotalPay()));
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }
    
    

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date fromDate) {
        this.startingDate = fromDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date toDate) {
        this.endingDate = toDate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(double totalPay) {
        this.totalPay = totalPay;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }
    
        public static double calculateTaxByWeek(double weeklySalary) {
        double annualSalary = weeklySalary * 52;

        if (annualSalary <= 18200) {
            return 0; 
        } else if (annualSalary <= 45000) {
            return (annualSalary - 18200) * 0.19 / 52; 
        } else if (annualSalary <= 120000) {
            return (5092 + (annualSalary - 45000) * 0.325) / 52; 
        } else if (annualSalary <= 180000) {
            return (29467 + (annualSalary - 120000) * 0.37) / 52; 
        } else {
            return (51667 + (annualSalary - 180000) * 0.45) / 52; 
        }
    }

    private void calculateSuperAnnuation() {
        setSuperAnnuation(getTotalPay() * SUPERRATE);
    }

    public double getSuperAnnuation() {
        return superAnnuation;
    }

    public void setSuperAnnuation(double superAnnuation) {
        this.superAnnuation = superAnnuation;
    }

    public static double getPAYRATE() {
        return PAYRATE;
    }

    public double getTAXRATE() {
        return (calculateTaxByWeek(getTotalPay())*100)/getTotalPay();
    }

    public static double getSUPERRATE() {
        return SUPERRATE;
    }



   
    
}
