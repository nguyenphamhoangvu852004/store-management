package com.example.database;

import com.example.entity.HangDienMay;
import com.example.entity.HangHoa;
import com.example.entity.HangSanhSu;
import com.example.entity.HangThucPham;
import com.example.interfaces.DatabaseBoundary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MysqlGetAllProductList implements DatabaseBoundary {

    public List<HangHoa> getAllProductList() {
        List<HangHoa> productDB = new ArrayList<>();

        // Các câu truy vấn đã được cập nhật với cột tenLoai
        String sqlQueryHangDienMay = "SELECT h.maHang, h.tenHang, h.soLuongTon, h.donGia, d.thoiGianBaoHanh, d.congSuat, l.tenLoai "
                + "FROM hanghoa h INNER JOIN hangdienmay d ON h.maHang = d.maHang "
                + "INNER JOIN loaihang l ON h.maLoai = l.maLoai";

        String sqlQueryHangSanhsu = "SELECT h.maHang, h.tenHang, h.soLuongTon, h.donGia, s.nhaSanXuat, s.ngayNhapKho, l.tenLoai "
                + "FROM hanghoa h INNER JOIN hangsanhsu s ON h.maHang = s.maHang "
                + "INNER JOIN loaihang l ON h.maLoai = l.maLoai";

        String sqlQueryHangThucPham = "SELECT h.maHang, h.tenHang, h.soLuongTon, h.donGia, t.ngaySanXuat, t.ngayHetHan, t.nhaCungCap, l.tenLoai "
                + "FROM hanghoa h INNER JOIN hangthucpham t ON h.maHang = t.maHang "
                + "INNER JOIN loaihang l ON h.maLoai = l.maLoai";

        // Sử dụng try-with-resources cho Connection để tự động đóng kết nối sau khi hoàn thành
        try (Connection connection = MysqlConnection.getConnection()) {
            // Lấy hàng điện máy
            try (Statement statement1 = connection.createStatement();
                 ResultSet resultSet1 = statement1.executeQuery(sqlQueryHangDienMay)) {
                while (resultSet1.next()) {
                    // Khi tạo đối tượng HangHoa, bạn có thể sử dụng donGiaFormatted nếu cần hiển thị
                    HangHoa hangHoa = new HangDienMay(
                            resultSet1.getString("maHang"),
                            resultSet1.getString("tenHang"),
                            resultSet1.getInt("soLuongTon"),
                            resultSet1.getDouble("donGia"),
                            resultSet1.getString("tenLoai"), // Thêm tenLoai
                            resultSet1.getInt("thoiGianBaoHanh"),
                            resultSet1.getDouble("congSuat")
                    );
                    productDB.add(hangHoa);
                }
            }

            // Lấy hàng sành sứ
            try (Statement statement2 = connection.createStatement();
                 ResultSet resultSet2 = statement2.executeQuery(sqlQueryHangSanhsu)) {
                while (resultSet2.next()) {
                    LocalDate ngayNhapKho = resultSet2.getDate("ngayNhapKho") != null
                            ? resultSet2.getDate("ngayNhapKho").toLocalDate()
                            : null;
                    HangHoa hangHoa = new HangSanhSu(
                            resultSet2.getString("maHang"),
                            resultSet2.getString("tenHang"),
                            resultSet2.getInt("soLuongTon"),
                            resultSet2.getDouble("donGia"),
                            resultSet2.getString("tenLoai"), // Thêm tenLoai
                            resultSet2.getString("nhaSanXuat"),
                            ngayNhapKho
                    );
                    productDB.add(hangHoa);
                }
            }

            // Lấy hàng thực phẩm
            try (Statement statement3 = connection.createStatement();
                 ResultSet resultSet3 = statement3.executeQuery(sqlQueryHangThucPham)) {
                while (resultSet3.next()) {
                    LocalDate ngaySanXuat = resultSet3.getDate("ngaySanXuat") != null
                            ? resultSet3.getDate("ngaySanXuat").toLocalDate()
                            : null;
                    LocalDate ngayHetHan = resultSet3.getDate("ngayHetHan") != null
                            ? resultSet3.getDate("ngayHetHan").toLocalDate()
                            : null;
                    HangHoa hangHoa = new HangThucPham(
                            resultSet3.getString("maHang"),
                            resultSet3.getString("tenHang"),
                            resultSet3.getInt("soLuongTon"),
                            resultSet3.getDouble("donGia"),
                            resultSet3.getString("tenLoai"), // Thêm tenLoai
                            ngaySanXuat,
                            ngayHetHan,
                            resultSet3.getString("nhaCungCap")
                    );
                    productDB.add(hangHoa);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productDB;
    }
}
