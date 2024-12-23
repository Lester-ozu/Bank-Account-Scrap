public class BankAccount2 {

   private String accountNumber, accountName;
   private double balance;
   private static double interestRate;

   public BankAccount2(String accountName, String accountNumber) {

      this.accountNumber = accountNumber;
      this.accountName = accountName;
      balance = 0.0;
   }

   public String getAccountNumber() {

      return accountNumber;
   }

   public String getAccountName() {

      return accountName;
   }

   public void setAccountNumber(String accountNumber) {

      this.accountNumber = accountNumber;
   }

   public void setAccountName(String accountName) {

      this.accountName = accountName;
   }

   public void setBalance(double balance) {

      this.balance = balance;
   }

   public double getBalance() {

      return balance;
   }

   public void deposit(double amount) {

      balance += amount;
   }

   public boolean withdraw(double amount) {

      if (amount <= balance) {

         balance -= amount;

         return true;
      }

      else {

         return false;
      }
   }

   public static void setInterestRate(double rate) {

      interestRate = rate / 100;
   }

   public static double getInterestRate() {

      return interestRate;
   }

   public void addInterest() {

      double anotherAmount = balance * interestRate;
      balance += anotherAmount;
   }

   @Override
   public String toString() {

      return "Account Number: " + accountNumber +
            "\nAccount Name: " + accountName +
            "\nAccount balance: " + balance;
   }
}