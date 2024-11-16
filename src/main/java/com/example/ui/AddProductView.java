package com.example.ui;

import com.example.addProduct.AddProductDTO;
import com.example.addProduct.AddProductUseCase;
import com.example.interfaces.OutputBoundary;
import com.example.ui.getTypeListMVVP.GetTypeListViewModel;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class AddProductView extends JFrame {
    private JComboBox<String> productTypeComboBox;
    private JTextField productNameField, quantityField, priceField;
    private JTextField warrantyTimeField, powerField;
    private JDateChooser manufactureDateChooser, expiryDateChooser, importDateChooser;
    private JTextField supplierField, producerField;
    private MainController controller;

    public void setMainController(MainController mainController) {
        this.controller = mainController;

    }

    public AddProductView() {
        setTitle("Thêm sản phẩm");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 300);


        JLabel productNameLabel = new JLabel("Tên sản phẩm:");
        productNameField = new JTextField(15);

        JLabel quantityLabel = new JLabel("Số lượng:");
        quantityField = new JTextField(10);

        JLabel priceLabel = new JLabel("Đơn giá:");
        priceField = new JTextField(10);

        JLabel productTypeLabel = new JLabel("Loại hàng:");
        productTypeComboBox = new JComboBox<>();

        JLabel warrantyTimeLabel = new JLabel("Thời gian bảo hành:");
        warrantyTimeField = new JTextField(10);

        JLabel powerLabel = new JLabel("Công suất:");
        powerField = new JTextField(10);

        JLabel manufactureDateLabel = new JLabel("Ngày sản xuất:");
        manufactureDateChooser = new JDateChooser();

        JLabel expiryDateLabel = new JLabel("Ngày hết hạn:");
        expiryDateChooser = new JDateChooser();

        JLabel supplierLabel = new JLabel("Nhà cung cấp:");
        supplierField = new JTextField(15);

        JLabel producerLabel = new JLabel("Nhà sản xuất:");
        producerField = new JTextField(15);

        JLabel importDateLabel = new JLabel("Ngày nhập kho:");
        importDateChooser = new JDateChooser();

        JButton addButton = new JButton("Thêm");
        JButton cancelButton = new JButton("Cancel");
        JButton resetButton = new JButton("Reset");

        // Ẩn các trường đặc thù khi chưa chọn loại sản phẩm
        warrantyTimeLabel.setVisible(false);
        warrantyTimeField.setVisible(false);
        powerLabel.setVisible(false);
        powerField.setVisible(false);

        manufactureDateLabel.setVisible(false);
        manufactureDateChooser.setVisible(false);
        expiryDateLabel.setVisible(false);
        expiryDateChooser.setVisible(false);
        supplierLabel.setVisible(false);
        supplierField.setVisible(false);

        producerLabel.setVisible(false);
        producerField.setVisible(false);
        importDateLabel.setVisible(false);
        importDateChooser.setVisible(false);

        productTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) productTypeComboBox.getSelectedItem();

                // Reset visibility
                warrantyTimeLabel.setVisible(false);
                warrantyTimeField.setVisible(false);
                powerLabel.setVisible(false);
                powerField.setVisible(false);

                manufactureDateLabel.setVisible(false);
                manufactureDateChooser.setVisible(false);
                expiryDateLabel.setVisible(false);
                expiryDateChooser.setVisible(false);
                supplierLabel.setVisible(false);
                supplierField.setVisible(false);

                producerLabel.setVisible(false);
                producerField.setVisible(false);
                importDateLabel.setVisible(false);
                importDateChooser.setVisible(false);

                // Show fields based on product type
                if ("Hàng Điện Máy".equals(selectedType)) {
                    warrantyTimeLabel.setVisible(true);
                    warrantyTimeField.setVisible(true);
                    powerLabel.setVisible(true);
                    powerField.setVisible(true);
                } else if ("Hàng Thực Phẩm".equals(selectedType)) {
                    manufactureDateLabel.setVisible(true);
                    manufactureDateChooser.setVisible(true);
                    expiryDateLabel.setVisible(true);
                    expiryDateChooser.setVisible(true);
                    supplierLabel.setVisible(true);
                    supplierField.setVisible(true);
                } else if ("Hàng Sành Sứ".equals(selectedType)) {
                    producerLabel.setVisible(true);
                    producerField.setVisible(true);
                    importDateLabel.setVisible(true);
                    importDateChooser.setVisible(true);
                }
                revalidate();
                repaint();
            }
        });

        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String productName = productNameField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                double price = Double.parseDouble(priceField.getText());
                String selectedType = (String) productTypeComboBox.getSelectedItem();

                if ("Hàng Thực Phẩm".equals(selectedType)) {
                    LocalDate manufactureDate = convertToLocalDate(manufactureDateChooser.getDate());
                    LocalDate expiryDate = convertToLocalDate( expiryDateChooser.getDate());
                    AddProductDTO addProductDTO = new AddProductDTO(
                            productName, quantity, price, selectedType, manufactureDate, expiryDate, supplierField.getText()
                    );

                    controller.executeAddProduct(addProductDTO);
                } else if ("Hàng Điện Máy".equals(selectedType)) {
                    AddProductDTO addProductDTO = new AddProductDTO(
                            productName, quantity, price, selectedType,
                            Integer.parseInt(warrantyTimeField.getText()), Double.parseDouble(powerField.getText())
                    );
                    controller.executeAddProduct(addProductDTO);
                } else if ("Hàng Sành Sứ".equals(selectedType)) {
                    LocalDate importDate = convertToLocalDate( importDateChooser.getDate());
                    AddProductDTO addProductDTO = new AddProductDTO(
                            productName, quantity, price, selectedType, producerField.getText(), importDate
                    );
                    controller.executeAddProduct(addProductDTO);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productNameField.setText("");
                quantityField.setText("");
                priceField.setText("");
                warrantyTimeField.setText("");
                powerField.setText("");
                manufactureDateChooser.setDate(null);
                expiryDateChooser.setDate(null);
                supplierField.setText("");
                producerField.setText("");
                importDateChooser.setDate(null);
            }
        });

        // Sử dụng GroupLayout để căn chỉnh
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

// Cập nhật layout.setHorizontalGroup
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(productNameLabel)
                                .addComponent(quantityLabel)
                                .addComponent(priceLabel)
                                .addComponent(productTypeLabel)
                                .addComponent(warrantyTimeLabel)
                                .addComponent(powerLabel)
                                .addComponent(manufactureDateLabel)
                                .addComponent(expiryDateLabel)
                                .addComponent(supplierLabel)
                                .addComponent(producerLabel)
                                .addComponent(importDateLabel)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(productNameField)
                                .addComponent(quantityField)
                                .addComponent(priceField)
                                .addComponent(productTypeComboBox)
                                .addComponent(warrantyTimeField)
                                .addComponent(powerField)
                                .addComponent(manufactureDateChooser)
                                .addComponent(expiryDateChooser)
                                .addComponent(supplierField)
                                .addComponent(producerField)
                                .addComponent(importDateChooser)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(30) // Thêm khoảng cách bên trái
                                        .addComponent(cancelButton)
                                        .addGap(10) // Thêm khoảng cách giữa các nút
                                        .addComponent(resetButton)
                                        .addGap(10) // Thêm khoảng cách giữa các nút
                                        .addComponent(addButton)
                                        .addGap(30) // Thêm khoảng cách bên phải
                                )
                        )
        );

// Cập nhật layout.setVerticalGroup
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(productNameLabel)
                                .addComponent(productNameField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(quantityLabel)
                                .addComponent(quantityField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(priceLabel)
                                .addComponent(priceField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(productTypeLabel)
                                .addComponent(productTypeComboBox))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(warrantyTimeLabel)
                                .addComponent(warrantyTimeField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(powerLabel)
                                .addComponent(powerField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(manufactureDateLabel)
                                .addComponent(manufactureDateChooser))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(expiryDateLabel)
                                .addComponent(expiryDateChooser))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(supplierLabel)
                                .addComponent(supplierField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(producerLabel)
                                .addComponent(producerField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(importDateLabel)
                                .addComponent(importDateChooser))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(cancelButton)
                                .addComponent(resetButton)
                                .addComponent(addButton)
                        )
        );

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void buildComboBoxType(List<GetTypeListViewModel> typeList) {
        productTypeComboBox.removeAllItems(); // Xóa các item cũ
        // Thêm tùy chọn "Tất cả"
        productTypeComboBox.addItem("Tất Cả");

        for (GetTypeListViewModel type : typeList) {
            productTypeComboBox.addItem(type.getType());
        }

        // Gắn sự kiện khi thay đổi lựa chọn trong ComboBox
        productTypeComboBox.addActionListener(e -> {
//            String selectedType = (String) productTypeComboBox.getSelectedItem();
//            if ("Hàng Thực Phẩm".equals(selectedType)) {
//                // Tạo và hiển thị dialog
//                JDialog dialog = new JDialog(frame, "Thông báo", true);
//                dialog.setSize(300, 200);
//                dialog.setLayout(new BorderLayout());
//
//                JLabel messageLabel = new JLabel("Bạn đã chọn Hàng Thực Phẩm!", SwingConstants.CENTER);
//                dialog.add(messageLabel, BorderLayout.CENTER);
//
//                dialog.setLocationRelativeTo(frame);
//                dialog.setVisible(true);
//            }
//            if ("Hàng Sành Sứ".equals(selectedType)) {
//                // Tạo và hiển thị dialog
//                JDialog dialog = new JDialog(frame, "Thông báo", true);
//                dialog.setSize(300, 200);
//                dialog.setLayout(new BorderLayout());
//
//                JLabel messageLabel = new JLabel("Bạn đã chọn Hàng Sành Sứ!", SwingConstants.CENTER);
//                dialog.add(messageLabel, BorderLayout.CENTER);
//
//                dialog.setLocationRelativeTo(frame);
//                dialog.setVisible(true);
//            }
//            if ("Hàng Điện Máy".equals(selectedType)) {
//                // Tạo và hiển thị dialog
//                JDialog dialog = new JDialog(frame, "Thông báo", true);
//                dialog.setSize(300, 200);
//                dialog.setLayout(new BorderLayout());
//
//                JLabel messageLabel = new JLabel("Bạn đã chọn Hàng Điện Máy!", SwingConstants.CENTER);
//                dialog.add(messageLabel, BorderLayout.CENTER);
//
//                dialog.setLocationRelativeTo(frame);
//                dialog.setVisible(true);
//            }
//
//            if ("Tất Cả".equals(selectedType)) {
//                // Tạo và hiển thị dialog
//                JDialog dialog = new JDialog(frame, "Thông báo", true);
//                dialog.setSize(500, 200);
//                dialog.setLayout(new BorderLayout());
//
//                JLabel messageLabel = new JLabel("Thực hiện gọi controller thực hiện usecase Get tất cả sản phẩm", SwingConstants.CENTER);
//                dialog.add(messageLabel, BorderLayout.CENTER);
//
//                dialog.setLocationRelativeTo(frame);
//                dialog.setVisible(true);
//
//                try {
//                    mainController.executeGetProductList(null);
//                    System.out.println("Thực hiện usecase khi nhấn chọn lựa chọn");
//                } catch (SQLException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
        });
    }
    private LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
