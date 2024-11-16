package com.example.addProduct;

import com.example.database.MysqlConnection;
import com.example.entity.HangDienMay;
import com.example.entity.HangHoa;
import com.example.entity.HangSanhSu;
import com.example.entity.HangThucPham;

import java.sql.*;

public class AddProductDAO implements AddProductDatabaseBoundary {

    private String generateMaHang(Connection conn, String prefix) throws SQLException {
        String sql = "SELECT COUNT(maHang) FROM hanghoa WHERE maHang LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prefix + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1) + 1; // Đếm và thêm 1 để tăng dần
                    return prefix + String.format("%04d", count); // Định dạng mã với 4 chữ số
                }
            }
        }
        throw new SQLException("Không thể tạo mã hàng");
    }

    public boolean saveProduct(HangHoa hangHoa) {
        try (Connection conn = MysqlConnection.getConnection()) {
            conn.setAutoCommit(false); // Bắt đầu giao dịch

            // Bước 1: Tạo mã hàng tự động
            String prefix = "";
            if (hangHoa instanceof HangThucPham) {
                prefix = "TP";
            } else if (hangHoa instanceof HangDienMay) {
                prefix = "DM";
            } else if (hangHoa instanceof HangSanhSu) {
                prefix = "SS";
            }

            String maHang = generateMaHang(conn, prefix);
            hangHoa.setMaHang(maHang); // Gán mã hàng tự động vào đối tượng

            // Bước 2: Lưu vào bảng hanghoa
            String sqlHangHoa = "INSERT INTO hanghoa (maHang, tenHang, soLuongTon, donGia, maLoai) " +
                    "VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement stmtHangHoa = conn.prepareStatement(sqlHangHoa)) {
                String maLoai;
                if (hangHoa.getTenLoai().equals("Hàng Thực Phẩm")) {
                    maLoai = "HTP";
                } else if (hangHoa.getTenLoai().equals("Hàng Điện Máy")){
                    maLoai = "HDM";
                }else {
                    maLoai = "HSS";
                }
                    stmtHangHoa.setString(1, hangHoa.getMaHang());
                stmtHangHoa.setString(2, hangHoa.getTenHang());
                stmtHangHoa.setInt(3, hangHoa.getSoLuongTon());
                stmtHangHoa.setDouble(4, hangHoa.getDonGia());
                stmtHangHoa.setString(5, maLoai);
                stmtHangHoa.executeUpdate();
            }

            // Bước 3: Lưu vào bảng con tùy loại hàng hóa
            if (hangHoa instanceof HangThucPham thucPham) {
                String sqlThucPham = "INSERT INTO hangthucpham (maHang, ngaySanXuat, ngayHetHan, nhaCungCap) " +
                        "VALUES (?, ?, ?, ?)";
                try (PreparedStatement stmtThucPham = conn.prepareStatement(sqlThucPham)) {
                    stmtThucPham.setString(1, thucPham.getMaHang());
                    stmtThucPham.setDate(2, Date.valueOf(thucPham.getNgaySanXuat()));
                    stmtThucPham.setDate(3, Date.valueOf(thucPham.getNgayHetHan()));
                    stmtThucPham.setString(4, thucPham.getNhaCungCap());
                    stmtThucPham.executeUpdate();
                }

            } else if (hangHoa instanceof HangDienMay dienMay) {
                String sqlDienMay = "INSERT INTO hangdienmay (maHang, thoiGianBaoHanh, congSuat) " +
                        "VALUES (?, ?, ?)";
                try (PreparedStatement stmtDienMay = conn.prepareStatement(sqlDienMay)) {
                    stmtDienMay.setString(1, dienMay.getMaHang());
                    stmtDienMay.setInt(2, dienMay.getThoiGianBaoHanh());
                    stmtDienMay.setDouble(3, dienMay.getCongSuat());
                    stmtDienMay.executeUpdate();
                }

            } else if (hangHoa instanceof HangSanhSu sanhSu) {
                String sqlSanhSu = "INSERT INTO hangsanhsu (maHang, nhaSanXuat, ngayNhapKho) " +
                        "VALUES (?, ?, ?)";
                try (PreparedStatement stmtSanhSu = conn.prepareStatement(sqlSanhSu)) {
                    stmtSanhSu.setString(1, sanhSu.getMaHang());
                    stmtSanhSu.setString(2, sanhSu.getNhaSanXuat());
                    stmtSanhSu.setDate(3, Date.valueOf(sanhSu.getNgayNhapKho()));
                    stmtSanhSu.executeUpdate();
                }
            }

            conn.commit(); // Hoàn thành giao dịch
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

}
