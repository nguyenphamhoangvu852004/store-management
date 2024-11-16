package com.example.database;

import com.example.entity.HangDienMay;
import com.example.entity.HangHoa;
import com.example.interfaces.DatabaseBoundary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlGetTypeProductList implements DatabaseBoundary {

    public List<String> getAllTypeProdcutList(){
        List<String> arrayList = new ArrayList<>();
        String query = "Select maLoai, tenLoai From loaihang";

        // Sử dụng try-with-resources cho Connection để tự động đóng kết nối sau khi hoàn thành
        try (Connection connection = MysqlConnection.getConnection()) {
            // Lấy hàng điện máy
            try (Statement statement1 = connection.createStatement();
                 ResultSet resultSet1 = statement1.executeQuery(query)) {
                while (resultSet1.next()) {
                    arrayList.add(resultSet1.getString("tenLoai"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
