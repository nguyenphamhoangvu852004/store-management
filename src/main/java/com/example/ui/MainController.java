package com.example.ui;

import com.example.addProduct.*;
import com.example.interfaces.InputBoundary;
import com.example.interfaces.OutputBoundary;
import com.example.interfaces.RequestData;


import java.sql.SQLException;

public class MainController {

    private InputBoundary getProductListUseCase;
    private InputBoundary getProductListSevenDayExpiryUseCase;
    private InputBoundary getTypeListUseCase;
    private AddProductUseCase addProductUseCase;
    public void setGetProductListUseCase(InputBoundary getProductListUseCase) {
        this.getProductListUseCase = getProductListUseCase;
    }

    public void setGetProductListSevenDayExpiryUseCase(InputBoundary getProductListSevenDayExpiryUseCase) {
        this.getProductListSevenDayExpiryUseCase = getProductListSevenDayExpiryUseCase;
    }

    public void setGetTypeListUseCase(InputBoundary getTypeListUseCase) {
        this.getTypeListUseCase = getTypeListUseCase;
    }
    public void setAddProductUseCase(AddProductUseCase a){
        this.addProductUseCase = a;
    }
    // Use Case 1
    public void executeGetProductList(RequestData requestData) throws SQLException {
        getProductListUseCase.execute(requestData);
    }

    // Use Case 2
    public void executeGetProductListSevenDayExpiry(RequestData requestData) throws SQLException {
        getProductListSevenDayExpiryUseCase.execute(requestData);
    }

    public void executeGetTypeList(RequestData requestData) throws SQLException{
        getTypeListUseCase.execute(requestData);
    }

    public void executeAddProduct(AddProductDTO a){
        AddProductDAO addProductDAO = new AddProductDAO();
        AddProductOutputBoundary addProductOutputBoundary = new AddProductPresenter();
        AddProductUseCase addProductUseCase1 = new AddProductUseCase(addProductOutputBoundary,addProductDAO);

        addProductUseCase1.execute(a);
    }
}


