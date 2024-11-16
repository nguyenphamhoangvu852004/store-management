package com.example.usecase.getProductList;

import com.example.database.MysqlGetAllProductList;
import com.example.dtos.getProductListDTOs.GetProductListOutputDTO;
import com.example.dtos.getProductListDTOs.GetProductListResponseData;
import com.example.entity.HangHoa;
import com.example.interfaces.DatabaseBoundary;
import com.example.interfaces.InputBoundary;
import com.example.interfaces.OutputBoundary;
import com.example.interfaces.RequestData;
import com.example.ui.getAllProductListMVVP.GetProductListPresenter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetProductListUseCase implements InputBoundary {

    private OutputBoundary outputBoundary = null;
    private DatabaseBoundary databaseBoundary = null;

    public GetProductListUseCase(OutputBoundary outputBoundary, DatabaseBoundary databaseBoundary) {
        this.outputBoundary = outputBoundary;
        this.databaseBoundary = databaseBoundary;
    }

    @Override
    public void execute(RequestData requestData) throws SQLException {

        List<HangHoa> listHangHoa = ((MysqlGetAllProductList) databaseBoundary).getAllProductList();
        List<GetProductListOutputDTO> listOutputDTOS = new ArrayList<>();


        for (HangHoa hangHoa : listHangHoa) {
            GetProductListOutputDTO getProductOutputDTO = new GetProductListOutputDTO(
                    hangHoa.getMaHang(),
                    hangHoa.getTenHang(),
                    hangHoa.getTenLoai(),
                    hangHoa.getSoLuongTon(),
                    hangHoa.getDonGia(),
                    hangHoa.tinhVat());

            // Thêm vào danh sách outputDTO
            listOutputDTOS.add(getProductOutputDTO);
        }

        GetProductListResponseData getProductListResponseData = new GetProductListResponseData(listOutputDTOS);

        outputBoundary.exportResult(getProductListResponseData);

    }
}
