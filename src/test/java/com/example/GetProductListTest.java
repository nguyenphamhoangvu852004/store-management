package com.example;

import com.example.database.MysqlGetAllProductList;
import com.example.interfaces.DatabaseBoundary;
import com.example.interfaces.InputBoundary;
import com.example.interfaces.OutputBoundary;
import com.example.interfaces.RequestData;
import com.example.ui.MainController;
import com.example.ui.MainView;
import com.example.ui.getAllProductListMVVP.GetProductListPresenter;
import com.example.ui.getAllProductListMVVP.GetProductlistViewModel;
import com.example.usecase.getProductList.GetProductListUseCase;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GetProductListTest {

    @Test
    public void testGetAllProductListOK() throws SQLException {

        MainController mainController = new MainController();
        MainView view = new MainView(mainController);
        DatabaseBoundary databaseBoundary = new MysqlGetAllProductList();
        OutputBoundary p = new GetProductListPresenter(view);
        InputBoundary i = new GetProductListUseCase(p, databaseBoundary);
        i.execute(null);
        List<GetProductlistViewModel> productList = ((GetProductListPresenter) p).getListProductViewModel();

        // Kiểm tra số lượng sản phẩm (ví dụ giả định số lượng mong đợi là 10)
        assertNotNull(productList); // Kiểm tra danh sách không null
        assertEquals(33, productList.size()); // Kiểm tra số lượng sản phẩm là 10
    }

    @Test
    public void testGetAllProductListFAIL() throws SQLException {
//        DatabaseBoundary databaseBoundary = new MockEmptyDao();
//        GetProductListPresenter p = new GetProductListPresenter();
//        InputBoundary i = new GetProductListUseCase(p, (MockEmptyDao) databaseBoundary);
//        i.execute(null);
//        GetProducListOutputDTO outputDTO = (GetProducListOutputDTO) p.getResponse();
//        outputDTO.getMessage();
//        assertEquals(0, outputDTO.getList().size());
//        System.out.println(outputDTO.getMessage());
//        // outputDTO.getList().forEach(System.out::println);
    }

}
