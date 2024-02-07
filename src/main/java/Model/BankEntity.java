
package Model;


public class BankEntity {
    private int id;
    private int employeeId;
    private String nameOfBank;
    private String accountHolderName;
    private String bankAccountNumber;
    private String bsbNumber;

    public BankEntity( String bankName,String bsbNumber,String accountName, String accountNumber) {
        this.id = -1;
        this.employeeId = -1;
        this.nameOfBank = bankName;
        this.bsbNumber = bsbNumber;
        this.accountHolderName = accountName;
        this.bankAccountNumber = accountNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getNameOfBank() {
        return nameOfBank;
    }

    public void setNameOfBank(String nameOfBank) {
        this.nameOfBank = nameOfBank;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBsbNumber() {
        return bsbNumber;
    }

    public void setBsbNumber(String bsbNumber) {
        this.bsbNumber = bsbNumber;
    }

    @Override
    public String toString() {
        return "BankEntity{" + "id=" + id + ", employeeId=" + employeeId + ", nameOfBank=" + nameOfBank + ", accountHolderName=" + accountHolderName + ", bankAccountNumber=" + bankAccountNumber + ", bsbNumber=" + bsbNumber + '}';
    }

 

    
    
    
    
}
