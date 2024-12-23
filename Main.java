import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer; 
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {

    static BankAccountList BAList = new BankAccountList(50);
    static JFrame mainFrame;
    static JButton newButton, updateButton, closeButton;
    static JTable accTable;
    static JTextField textField1, textField2, textField3;
    static BankAccount2 tempAcc;
    static {

        BAList.add(new BankAccount2("1John", "1111"));
    }

    public static void main(String[] args) {

        homePage();
    }

    public static void homePage() {

        mainFrame = new JFrame("Bank Account Profile");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(700, 400);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);

        JLabel label1 = new JLabel("<html>Account Number: <br><br><br>Account Name: <br><br><br>Balance:</html>");
        label1.setLayout(null);
        label1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        label1.setBounds(50, 10, 200, 180);

        textField1 = new JTextField();
        textField1.setForeground(Color.GREEN);
        textField1.setCaretColor(Color.WHITE);
        textField1.setBackground(Color.BLACK);
        textField1.setLayout(null);
        textField1.setBounds(175, 38, 170, 23);

        textField2 = new JTextField();
        textField2.setForeground(Color.GREEN);
        textField2.setCaretColor(Color.WHITE);
        textField2.setBackground(Color.BLACK);
        textField2.setLayout(null);
        textField2.setBounds(175, 90, 170, 23);

        textField3 = new JTextField();
        textField3.setForeground(Color.GREEN);
        textField3.setCaretColor(Color.WHITE);
        textField3.setBackground(Color.BLACK);
        textField3.setLayout(null);
        textField3.setBounds(175, 142, 170, 23);

        String[] columns = { "Account Number", "Account Name", "Balance" };
        String[][] rows = new String[BAList.getList().length][3];

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[0].length; j++) {

                if (j == 0 && BAList.getList()[i] != null) {

                    rows[i][j] = BAList.getList()[i].getAccountNumber();
                }

                else if (j == 1 && BAList.getList()[i] != null) {

                    rows[i][j] = BAList.getList()[i].getAccountName();
                }

                else if (j == 2 && BAList.getList()[i] != null) {

                    rows[i][j] = String.valueOf(BAList.getList()[i].getBalance());
                }
            }
        }

        DefaultTableModel model = new DefaultTableModel(rows, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        accTable = new JTable(model);
        accTable.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        accTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        accTable.setRowHeight(20);
        accTable.setPreferredSize(new Dimension(581, (BAList.getListSize() * 20)));
        accTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int selectedRow = accTable.getSelectedRow();

                if (selectedRow > -1) {

                    if (accTable.getValueAt(selectedRow, 0) != null &&
                            accTable.getValueAt(selectedRow, 1) != null &&
                            accTable.getValueAt(selectedRow, 2) != null) {

                        String accountNumber = accTable.getValueAt(selectedRow, 0).toString();
                        String accountName = accTable.getValueAt(selectedRow, 1).toString();
                        String balance = accTable.getValueAt(selectedRow, 2).toString();

                        tempAcc = BAList.searchByAccountNumber(accountNumber);

                        textField1.setText(accountNumber);
                        textField2.setText(accountName);
                        textField3.setText(balance);
                    }

                    else {

                        textField1.setText("");
                        textField2.setText("");
                        textField3.setText("");
                    }
                }

            }
        });

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < 3; i++) {

            accTable.getColumnModel().getColumn(i).setPreferredWidth(127);
            accTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(accTable);
        scrollPane.setBounds(50, 180, 600, 130);

        newButton = new JButton("New Account");
        newButton.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        newButton.setFocusable(false);
        newButton.setBounds(50, 325, 110, 25);
        newButton.addActionListener(new MyActionListener());

        updateButton = new JButton("Update Account");
        updateButton.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        updateButton.setFocusable(false);
        updateButton.setBounds(170, 325, 110, 25);
        updateButton.addActionListener(new MyActionListener());

        closeButton = new JButton("Close Account");
        closeButton.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        closeButton.setFocusable(false);
        closeButton.setBounds(290, 325, 110, 25);
        closeButton.addActionListener(new MyActionListener());

        mainFrame.add(scrollPane);
        mainFrame.add(textField2);
        mainFrame.add(textField3);
        mainFrame.add(textField1);
        mainFrame.add(label1);
        mainFrame.add(newButton);
        mainFrame.add(updateButton);
        mainFrame.add(closeButton);
        mainFrame.setVisible(true);
    }
}

class MyActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Main.newButton) {

            if (!Main.textField1.getText().trim().matches("\\d+")) {

                JOptionPane.showMessageDialog(null, "Account Number should only consist of numbers!", "ERROR!!!",
                        JOptionPane.ERROR_MESSAGE);
            }

            else if (Main.textField1.getText().trim() == null || Main.textField1.getText().trim().equals(null)) {

                JOptionPane.showMessageDialog(null, "Account Number cannot be empty!", "ERROR!!!",
                        JOptionPane.ERROR_MESSAGE);
            }

            else if (Main.BAList.compareAccountNumber(Main.textField1.getText())) {

                JOptionPane.showMessageDialog(null, "An Account with the same number already exist!", "ERROR!!!",
                        JOptionPane.ERROR_MESSAGE);
            }

            else if (Main.textField2.getText().matches("\\d+")) {

                JOptionPane.showMessageDialog(null, "Account Name should only consist of letters!", "ERROR!!!",
                        JOptionPane.ERROR_MESSAGE);
            }

            else if (Main.textField2.getText() == null || Main.textField2.getText().equals(null)) {

                JOptionPane.showMessageDialog(null, "Account Name cannot be empty!", "ERROR!!!",
                        JOptionPane.ERROR_MESSAGE);
            }

            else if (Main.BAList.compareAccountName(Main.textField2.getText())) {

                JOptionPane.showMessageDialog(null, "An Account with the same name already exist!", "ERROR!!!",
                        JOptionPane.ERROR_MESSAGE);
            }

            else if (!Main.textField3.getText().trim().matches("\\d+")
                    && !Main.textField3.getText().trim().matches("-?\\d+(\\.\\d+)?")) {

                JOptionPane.showMessageDialog(null, "Account Balance should only consist of numbers!", "ERROR!!!",
                        JOptionPane.ERROR_MESSAGE);
            }

            else if (Main.textField3.getText().trim() == null || Main.textField3.getText().trim().equals(null)) {

                JOptionPane.showMessageDialog(null, "Account Balance cannot be empty!", "ERROR!!!",
                        JOptionPane.ERROR_MESSAGE);
            }

            else {

                String number = Main.textField1.getText().trim();
                String name = Main.textField2.getText();
                String balance = Main.textField3.getText().trim();

                BankAccount2 acc = new BankAccount2(name, number);
                acc.setBalance(Double.parseDouble(balance));
                Main.BAList.add(acc);

                Main.mainFrame.dispose();

                Main.homePage();
            }
        }

        if (e.getSource() == Main.updateButton) {

            if (!Main.textField1.getText().trim().matches("\\d+")) {

                JOptionPane.showMessageDialog(null, "Account Number should only consist of numbers!", "ERROR!!!",
                        JOptionPane.ERROR_MESSAGE);
            }

            else if (Main.textField1.getText().trim() == null || Main.textField1.getText().trim().equals(null)) {

                JOptionPane.showMessageDialog(null, "Account Number cannot be empty!", "ERROR!!!",
                        JOptionPane.ERROR_MESSAGE);
            }

            else if (Main.textField2.getText().matches("\\d+")) {

                JOptionPane.showMessageDialog(null, "Account Name should only consist of letters!", "ERROR!!!",
                        JOptionPane.ERROR_MESSAGE);
            }

            else if (Main.textField2.getText() == null || Main.textField2.getText().equals(null)) {

                JOptionPane.showMessageDialog(null, "Account Name cannot be empty!", "ERROR!!!",
                        JOptionPane.ERROR_MESSAGE);
            }

            else if (!Main.textField3.getText().trim().matches("\\d+")
                    && !Main.textField3.getText().trim().matches("-?\\d+(\\.\\d+)?")) {

                JOptionPane.showMessageDialog(null, "Account Balance should only consist of numbers!", "ERROR!!!",
                        JOptionPane.ERROR_MESSAGE);
            }

            else if (Main.textField3.getText().trim() == null || Main.textField3.getText().trim().equals(null)) {

                JOptionPane.showMessageDialog(null, "Account Balance cannot be empty!", "ERROR!!!",
                        JOptionPane.ERROR_MESSAGE);
            }

            else if (Main.tempAcc.getAccountNumber().equals(Main.textField1.getText().trim())) {

                String number = Main.textField1.getText();
                String name = Main.textField2.getText();
                String balance = Main.textField3.getText();

                if (Main.textField3.getText().trim() != null || !Main.textField3.getText().trim().equals("")) {

                    Main.tempAcc.setBalance(Double.parseDouble(balance));
                }

                Main.BAList.update(Main.tempAcc, name, number);

                Main.mainFrame.dispose();

                Main.homePage();
            }

            else if (Main.tempAcc.getAccountName().equals(Main.textField2.getText())) {

                String number = Main.textField1.getText();
                String name = Main.textField2.getText();
                String balance = Main.textField3.getText();

                if (Main.textField3.getText().trim() != null || !Main.textField3.getText().trim().equals("")) {

                    Main.tempAcc.setBalance(Double.parseDouble(balance));
                }

                Main.BAList.update(Main.tempAcc, name, number);

                Main.mainFrame.dispose();

                Main.homePage();
            }

            else if (Main.tempAcc.getAccountNumber().equals(Main.textField1.getText().trim())
                    && Main.tempAcc.getAccountName().equals(Main.textField2.getText())) {

                String number = Main.textField1.getText();
                String name = Main.textField2.getText();
                String balance = Main.textField3.getText();

                if (Main.textField3.getText().trim() != null || !Main.textField3.getText().trim().equals("")) {

                    Main.tempAcc.setBalance(Double.parseDouble(balance));
                }

                Main.BAList.update(Main.tempAcc, name, number);

                Main.mainFrame.dispose();

                Main.homePage();
            }

            else if (Main.BAList.compareAccountName(Main.textField2.getText())) {

                JOptionPane.showMessageDialog(null, "An Account with the same name already exist!", "ERROR!!!",
                        JOptionPane.ERROR_MESSAGE);
            }

            else if (Main.BAList.compareAccountNumber(Main.textField1.getText().trim())) {

                JOptionPane.showMessageDialog(null, "An Account with the same number already exist!", "ERROR!!!",
                        JOptionPane.ERROR_MESSAGE);
            }

            else {

                String number = Main.textField1.getText();
                String name = Main.textField2.getText();
                String balance = Main.textField3.getText();

                if (Main.textField3.getText().trim() != null || !Main.textField3.getText().trim().equals("")) {

                    Main.tempAcc.setBalance(Double.parseDouble(balance));
                }

                Main.BAList.update(Main.tempAcc, name, number);

                Main.mainFrame.dispose();

                Main.homePage();
            }
        }

        if (e.getSource() == Main.closeButton) {

            Main.BAList.delete(Main.tempAcc);

            Main.mainFrame.dispose();

            Main.homePage();
        }
    }

}