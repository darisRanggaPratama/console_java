/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.tama.crudjavaphp;

/**
 *
 * @author Pongo
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.swing.table.DefaultTableCellRenderer;
import org.json.JSONArray;
import org.json.JSONObject;

public class CrudJavaPHP extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField idField, nameField, emailField, birthField, searchField;
    private JButton addButton, updateButton, deleteButton, searchButton, refreshButton;

    public CrudJavaPHP() {
        setTitle("Customer Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Email");
        tableModel.addColumn("Birth");
        table = new JTable(tableModel);

        // Buat cell renderer untuk alignment tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Terapkan renderer ke kolom ID (indeks 0) dan Birth (indeks 3)
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(table);

        // Create input fields
        idField = new JTextField(10);
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        birthField = new JTextField(20);
        searchField = new JTextField(20);
        searchField.setHorizontalAlignment(JTextField.CENTER);

        // Create buttons
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        searchButton = new JButton("Search");
        refreshButton = new JButton("Refresh");

        // Layout
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);
        inputPanel.add(new JLabel("Birth:"));
        inputPanel.add(birthField);
        inputPanel.add(searchField);
        inputPanel.add(searchButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCustomer();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCustomer();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCustomer();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCustomers();
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });

        setLocationRelativeTo(null);

        refreshTable();
    }

    private void refreshTable() {
        try {
            URL url = new URL("http://localhost/console_php/crud_rest_jtable/view.php");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONArray jsonArray = new JSONArray(response.toString());
            tableModel.setRowCount(0);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject customer = jsonArray.getJSONObject(i);
                tableModel.addRow(new Object[]{
                    customer.getString("id"),
                    customer.getString("name"),
                    customer.getString("email"),
                    customer.getString("birth")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error refreshing data: " + e.getMessage());
        }
    }

    private void addCustomer() {
        try {
            URL url = new URL("http://localhost/console_php/crud_rest_jtable/add.php");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            String params = "id=" + idField.getText() + "&name=" + nameField.getText() + "&email=" + emailField.getText() + "&birth=" + birthField.getText();
            OutputStream os = conn.getOutputStream();
            os.write(params.getBytes());
            os.flush();
            os.close();

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(this, "Customer added successfully");
                System.out.println("check: " + idField.getText() + "\n" + nameField.getText() + "\n" + emailField.getText() + "\n" + birthField.getText());
                clearText();
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Error adding customer");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding customer: " + e.getMessage());
        }
    }

    private void updateCustomer() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a customer to update");
            return;
        }

        String id, name, email, birth;
        id = tableModel.getValueAt(selectedRow, 0).toString();
        name = tableModel.getValueAt(selectedRow, 1).toString();
        email = tableModel.getValueAt(selectedRow, 2).toString();
        birth = tableModel.getValueAt(selectedRow, 3).toString();

        try {
            URL url = new URL("http://localhost/console_php/crud_rest_jtable/edit.php");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String params = "id=" + URLEncoder.encode(id, "UTF-8")
                    + "&name=" + URLEncoder.encode(name, "UTF-8")
                    + "&email=" + URLEncoder.encode(email, "UTF-8")
                    + "&birth=" + URLEncoder.encode(birth, "UTF-8");

            System.out.println("Sending parameters: " + params);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = params.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            System.out.println("HTTP Response Code: " + responseCode);

            BufferedReader br;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
            }

            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println("Server Response: " + response.toString());

            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(this, "Customer updated successfully");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Error updating customer. Response Code: " + responseCode + "\nResponse: " + response.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating customer: " + e.getMessage());
        }
    }

    private void deleteCustomer() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a customer to delete");
            return;
        }

        String id = tableModel.getValueAt(selectedRow, 0).toString();

        try {
            URL url = new URL("http://localhost/console_php/crud_rest_jtable/delete.php");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            String params = "id=" + id;
            OutputStream os = conn.getOutputStream();
            os.write(params.getBytes());
            os.flush();
            os.close();

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(this, "Customer deleted successfully");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Error deleting customer");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting customer: " + e.getMessage());
        }
    }

    private void searchCustomers() {
        try {
            URL url = new URL("http://localhost/console_php/crud_rest_jtable/search.php?query=" + searchField.getText());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONArray jsonArray = new JSONArray(response.toString());
            tableModel.setRowCount(0);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject customer = jsonArray.getJSONObject(i);
                tableModel.addRow(new Object[]{
                    customer.getString("id"),
                    customer.getString("name"),
                    customer.getString("email"),
                    customer.getString("birth")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error searching customers: " + e.getMessage());
        }
    }
    
    private void clearText(){
        idField.setText("");
        nameField.setText("");
        emailField.setText("");
        birthField.setText("");
        idField.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CrudJavaPHP().setVisible(true);
            }
        });
    }
}
