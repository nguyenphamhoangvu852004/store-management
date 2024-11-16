package com.example;

import com.example.database.MysqlGetAllProductList;
import com.example.ui.getAllProductListSevenDaysExpiryMVVP.GetProductListSevenDaysExpiryPresenter;
import com.example.ui.getAllProductListSevenDaysExpiryMVVP.GetProductListSevenDaysExpiryViewModel;
import com.example.interfaces.DatabaseBoundary;
import com.example.interfaces.InputBoundary;
import com.example.usecase.getListProductExpired.GetProductListSevenDayExpiryUseCase;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GetProductListSevenDayExpiryTest {
    @Test
    public void getProductListSevenDayExpiryOK() throws SQLException {
        DatabaseBoundary mdao = new MysqlGetAllProductList();
        GetProductListSevenDaysExpiryPresenter p = new GetProductListSevenDaysExpiryPresenter();
        InputBoundary i = new GetProductListSevenDayExpiryUseCase(p, mdao);
        i.execute(null);

        List<GetProductListSevenDaysExpiryViewModel> list =  p.getListSevenDaysExpiryViewModels();
        assertNotNull(list); // Kiểm tra danh sách không null

        assertEquals(4, list.size()); // Kiểm tra số lượng sản phẩm là 10
    }

    @Test
    public void getProductListSevenDayExpiryFail() throws SQLException {
//        DatabaseBoundary mdao = new MysqlGetAllProductList();
//        GetProductListPresenter p = new GetProductListPresenter();
//        InputBoundary i = new GetProductListSevenDayExpiryUseCase(p, mdao);
//        i.execute(null);
//        GetProductListSevenDayExpiryOutputDTO outputDTO = (GetProductListSevenDayExpiryOutputDTO) p.getResponse();
//
//        assertEquals(2, outputDTO.getList().size());
//        outputDTO.getList().forEach(System.out::println);
//        System.out.printf("", outputDTO.getMessage());
//        System.out.printf("", outputDTO.isSuccess());
    }
}
