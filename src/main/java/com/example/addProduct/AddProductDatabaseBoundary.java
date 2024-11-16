package com.example.addProduct;

import com.example.entity.HangHoa;

public interface AddProductDatabaseBoundary {
    boolean saveProduct(HangHoa hangHoa);
}
