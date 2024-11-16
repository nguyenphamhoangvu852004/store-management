package com.example.database;

import com.example.entity.HangHoa;
import com.example.interfaces.DatabaseBoundary;

import java.util.ArrayList;
import java.util.List;

public class MockEmptyDao implements DatabaseBoundary {

    public List<HangHoa> getAllProductList() {
        // Giả lập list rỗng
        return new ArrayList<>();
    }


}
