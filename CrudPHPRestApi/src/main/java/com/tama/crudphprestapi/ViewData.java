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

public class ViewData extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton refreshButton;
    private JTextField searchField;
    private TableRowSorter<DefaultTableModel> sorter;

    public ViewData() {
        setTitle("Data Viewer");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Inisialisasi komponen
        String[] columnNames = {"ID", "Name", "Email", "Birth"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        refreshButton = new JButton("Refresh");
        searchField = new JTextField(20);

        // Layout
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Search: "));
        topPanel.add(searchField);
        topPanel.add(refreshButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Event Listeners
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshData();
            }
        });

        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = searchField.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });
        
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
                    JOptionPane.showMessageDialog(ViewData.this, "Error fetching data: " + e.getMessage());
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ViewData().setVisible(true);
            }
        });
    }
}
