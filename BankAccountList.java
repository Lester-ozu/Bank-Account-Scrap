import java.io.*;

public class BankAccountList {

   private BankAccount2[] BAList;
   private int ListSize = 0;

   public BankAccountList(int size) {

      ListSize = 0;
      BAList = new BankAccount2[size];
   }

   public BankAccount2[] getList() {

      return BAList;
   }

   public int getListSize() {

      int i = 0;

      for (int k = 0; k < BAList.length; k++) {

         if (BAList[i] != null) {

            i++;
         }
      }

      return i;
   }

   public void add(BankAccount2 acc) {

      BAList[ListSize] = acc;
      ListSize++;
   }

   public BankAccount2 searchByIndex(int number) {

      if (number >= 0 && number < ListSize && BAList[number] != null) {

         return BAList[number];
      }

      else {

         return null;
      }
   }

   public BankAccount2 searchByAccountNumber(String accNumber) {

      for (BankAccount2 number : BAList) {

         if (number != null && number.getAccountNumber().equals(accNumber)) {

            return number;
         }
      }

      return null;
   }

   public BankAccount2 searchByAccountName(String accName) {

      for (BankAccount2 name : BAList) {

         if (name != null && name.getAccountName().equals(accName)) {

            return name;
         }
      }

      return null;
   }

   public boolean update(BankAccount2 acc, String accountName, String accountNumber) {

      for (int i = 0; i < BAList.length; i++) {

         if (BAList[i] != null & BAList[i] == acc) {

            BAList[i].setAccountName(accountName);
            BAList[i].setAccountNumber(accountNumber);

            return true;
         }
      }

      return false;
   }

   public boolean delete(BankAccount2 acc) {

      for (int i = 0; i < BAList.length; i++) {

         if (BAList[i] != null & BAList[i] == acc) {

            BAList[i] = null;

            reAdjust();

            return true;
         }
      }

      return false;
   }

   public boolean compareAccountName(String accName) {

      for (BankAccount2 acc : BAList) {

         if (acc != null) {

            if (acc.getAccountName().equals(accName)) {

               return true;
            }

            else {
               return false;
            }
         }
      }

      return false;
   }

   public boolean compareAccountNumber(String accNumber) {

      for (BankAccount2 acc : BAList) {

         if (acc != null) {

            if (acc.getAccountNumber().equals(accNumber)) {

               return true;
            }

            else {
               return false;
            }
         }
      }

      return false;
   }

   public int accountIndex(BankAccount2 acc) {

      for (int i = 0; i < BAList.length; i++) {

         if (BAList[i].getAccountName() != null && BAList[i].getAccountName().equals(acc.getAccountName())) {

            return i + 1;
         }
      }

      return -1;
   }

   public void backUpCSV(String fileName) {

      try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

         writer.write("Account Name" + ",");
         writer.write("Account Number" + ",");
         writer.write("Account Balance" + "\n");

         for (BankAccount2 acc : BAList) {

            if (acc != null) {

               writer.write(acc.getAccountName() + ",");
               writer.write(acc.getAccountNumber() + ",");
               writer.write(acc.getBalance() + ",");

               writer.write("\n");
            }
         }
      }

      catch (IOException e) {

         System.err.println("Error: " + e.getMessage());
      }
   }

   public void loadUpCSV(String fileName) {

      try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

         String line;

         while ((line = reader.readLine()) != null) {

            String[] parts = line.split(",");

            if (parts[0].equals("Account Name") ||
                  parts[1].equals("Account Number") ||
                  parts[2].equals("Account Balance")) {

               continue;
            }

            String accName = parts[0];
            String accNumber = parts[1];
            double balance = Double.parseDouble(parts[2]);

            BankAccount2 acc = new BankAccount2(accName, accNumber);
            acc.setBalance(balance);

            this.add(acc);
         }
      }

      catch (IOException e) {

         System.err.println("Error: " + e.getMessage());
      }
   }

   public void reAdjust() {

      BankAccount2[] temp = new BankAccount2[BAList.length];
      int j = 0;

      for (int i = 0; i < BAList.length; i++) {

         if (BAList[i] != null) {

            temp[j] = BAList[i];
            j++;
         }
      }

      BAList = temp.clone();
      ListSize--;

   }

   public void expandArray() {

      if (BAList[BAList.length - 1] != null) {

         BankAccount2[] tempList = new BankAccount2[BAList.length + 10];

         int i = 0;

         for (BankAccount2 acc : BAList) {

            if (acc != null) {

               tempList[i] = acc;
               i++;
            }
         }

         BAList = tempList.clone();
      }
   }
}