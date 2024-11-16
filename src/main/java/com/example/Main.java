package com.example;

import com.example.database.MysqlGetAllProductList;
import com.example.database.MysqlGetTypeProductList;
import com.example.interfaces.DatabaseBoundary;
import com.example.interfaces.InputBoundary;
import com.example.interfaces.OutputBoundary;
import com.example.ui.MainController;
import com.example.ui.getAllProductListMVVP.GetProductListPresenter;
import com.example.ui.MainView;
import com.example.ui.getTypeListMVVP.GetTypeListPresenter;
import com.example.usecase.getProductList.GetProductListUseCase;
import com.example.usecase.getTypeList.GetTypeListUseCase;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        MainController controller = new MainController();
        MainView mainView = new MainView(controller);

        DatabaseBoundary getAllProductListDatabase = new MysqlGetAllProductList();

        OutputBoundary productListPresenter = new GetProductListPresenter(mainView);
        InputBoundary getProductListUseCase = new GetProductListUseCase(productListPresenter, getAllProductListDatabase);

        controller.setGetProductListUseCase(getProductListUseCase);

        controller.executeGetProductList(null);
        System.out.println("Thực hiện usecase get ProductList khi vừa chạy chương trình");

    }
}
