package com.example.ui;

import com.example.addProduct.AddProductUseCase;
import com.example.database.MysqlGetAllProductList;
import com.example.database.MysqlGetTypeProductList;
import com.example.interfaces.DatabaseBoundary;
import com.example.interfaces.OutputBoundary;
import com.example.ui.getAllProductListMVVP.GetProductlistViewModel;
import com.example.ui.getAllProductListSevenDaysExpiryMVVP.GetProductListSevenDaysExpiryPresenter;
import com.example.ui.getTypeListMVVP.GetTypeListPresenter;
import com.example.ui.getTypeListMVVP.GetTypeListViewModel;
import com.example.usecase.getListProductExpired.GetProductListSevenDayExpiryUseCase;
import com.example.usecase.getTypeList.GetTypeListUseCase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class MainView {
    private JFrame frame;
    private JPanel controlPanel;
    private JPanel comboBoxPanel;
    private JScrollPane scrollPane;
    private JButton addButton, editButton, deleteButton, expiryButton; // Thêm expiryButton
    private JComboBox<String> typeComboBox;
    private MainController mainController;

    public MainView(MainController mainController) {
        this.mainController = mainController;
        frame = new JFrame("Product Management");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 600);

        // Tạo tiêu đề
        JLabel titleLabel = new JLabel("DANH SÁCH SẢN PHẨM", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Tạo bảng sản phẩm
        scrollPane = new JScrollPane();
        frame.add(scrollPane, BorderLayout.CENTER);

        // Tạo panel điều khiển
        controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        frame.add(controlPanel, BorderLayout.SOUTH);

        // Nút chức năng
        addButton = new JButton("Thêm");
        editButton = new JButton("Sửa");
        deleteButton = new JButton("Xóa");
        expiryButton = new JButton("Sản phẩm gần hết hạn"); // Thêm nút mới

        controlPanel.add(addButton);
        controlPanel.add(editButton);
        controlPanel.add(deleteButton);
        controlPanel.add(expiryButton); // Thêm vào controlPanel

        addButton.addActionListener(e -> {
            try {
                handleAddButtonEvent();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        // Gắn sự kiện cho nút expiryButton
        expiryButton.addActionListener(e -> {
            try {
                handleExpiryButtonEvent();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Hiển thị frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void buildProductTable(List<GetProductlistViewModel> products) {
        String[] columns = {"Mã Hàng", "Tên Hàng", "Số Lượng Tồn", "Đơn giá", "VAT", "Loại hàng"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (GetProductlistViewModel product : products) {
            Object[] row = {
                    product.getMaHang(),
                    product.getTenHang(),
                    product.getSoLuongTon(),
                    product.getDonGia(),
                    product.getVAT(),
                    product.getTenLoai()
            };
            tableModel.addRow(row);
        }

        JTable table = new JTable(tableModel);
        scrollPane.setViewportView(table);
    }

    public void handleAddButtonEvent() throws SQLException {

        AddProductView addProductView = new AddProductView();
        addProductView.setMainController(mainController);


        OutputBoundary outputBoundary = new GetTypeListPresenter(addProductView);
        DatabaseBoundary databaseBoundary = new MysqlGetTypeProductList();
        GetTypeListUseCase getTypeListUseCase  = new GetTypeListUseCase(outputBoundary,databaseBoundary);
        this.mainController.setGetTypeListUseCase(getTypeListUseCase);
        this.mainController.executeGetTypeList(null);
    }
    private void handleExpiryButtonEvent() throws SQLException {
        OutputBoundary outputBoundary = new GetProductListSevenDaysExpiryPresenter();
        DatabaseBoundary databaseBoundary = new MysqlGetAllProductList();
        GetProductListSevenDayExpiryUseCase getProductListSevendayExpiry = new GetProductListSevenDayExpiryUseCase(outputBoundary, databaseBoundary);
        this.mainController.setGetProductListSevenDayExpiryUseCase(getProductListSevendayExpiry);
        mainController.executeGetProductListSevenDayExpiry(null);
    }
}

