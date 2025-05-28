/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tama.crudphprestapi;

/**
 *
 * @author Pongo
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;
import org.json.JSONArray;
import org.json.JSONObject;

public class ViewEdit extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton refreshButton, editButton, deleteButton;
    private JTextField searchField;
    private TableRowSorter<DefaultTableModel> sorter;

    public ViewEdit() {
        setTitle("Data Viewer");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Inisialisasi komponen
        String[] columnNames = {"ID", "Name", "Email", "Birth"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // ID column tidak bisa diedit
            }
        };
        table = new JTable(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        refreshButton = new JButton("Refresh");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        searchField = new JTextField(20);

        // Layout
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Search: "));
        topPanel.add(searchField);
        topPanel.add(refreshButton);
        topPanel.add(editButton);
        topPanel.add(deleteButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Event Listeners
        refreshButton.addActionListener(e -> refreshData());

        searchField.addActionListener(e -> {
            String text = searchField.getText();
            if (text.trim().length() == 0) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            }
        });

        editButton.addActionListener(e -> editSelectedRow());
        deleteButton.addActionListener(e -> deleteSelectedRow());
        
        setLocationRelativeTo(null);

        // Initial data load
        refreshData();
    }
    
    private void refreshData() {
        new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost/console_php/crud_rest_java/view.php"))
                    .GET()
                    .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                return response.body();
            }

            @Override
            protected void done() {
                try {
                    String result = get();
                    updateTable(result);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(ViewEdit.this, "Error fetching data: " + e.getMessage());
                }
            }
        }.execute();
    }

    private void updateTable(String jsonData) {
        tableModel.setRowCount(0);  // Clear existing data
        JSONArray jsonArray = new JSONArray(jsonData);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Object[] row = {
                jsonObject.getString("id"),
                jsonObject.getString("name"),
                jsonObject.getString("email"),
                jsonObject.getString("birth")
            };
            tableModel.addRow(row);
        }
    }

    private void editSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String id = table.getValueAt(selectedRow, 0).toString();
            String name = table.getValueAt(selectedRow, 1).toString();
            String email = table.getValueAt(selectedRow, 2).toString();
            String birth = table.getValueAt(selectedRow, 3).toString();

            new SwingWorker<String, Void>() {
                @Override
                protected String doInBackground() throws Exception {
                    HttpClient client = HttpClient.newHttpClient();
                    String requestBody = String.format("id=%s&name=%s&email=%s&birth=%s", id, name, email, birth);
                    HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost/console_php/crud_rest_java/edit.php"))
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                        .build();

                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    return response.body();
                }

                @Override
                protected void done() {
                    try {
                        String result = get();
                        JOptionPane.showMessageDialog(ViewEdit.this, result);
                        refreshData();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(ViewEdit.this, "Error updating data: " + e.getMessage());
                    }
                }
            }.execute();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to edit.");
        }
    }

    private void deleteSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String id = table.getValueAt(selectedRow, 0).toString();

            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this record?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                new SwingWorker<String, Void>() {
                    @Override
                    protected String doInBackground() throws Exception {
                        HttpClient client = HttpClient.newHttpClient();
                        String requestBody = "id=" + id;
                        HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create("http://localhost/console_php/crud_rest_java/delete.php"))
                            .header("Content-Type", "application/x-www-form-urlencoded")
                            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                            .build();

                        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                        return response.body();
                    }

                    @Override
                    protected void done() {
                        try {
                            String result = get();
                            JOptionPane.showMessageDialog(ViewEdit.this, result);
                            refreshData();
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(ViewEdit.this, "Error deleting data: " + e.getMessage());
                        }
                    }
                }.execute();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewData().setVisible(true));
    }
}