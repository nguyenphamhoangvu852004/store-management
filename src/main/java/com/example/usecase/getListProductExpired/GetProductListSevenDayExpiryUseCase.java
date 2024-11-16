package com.example.usecase.getListProductExpired;

import com.example.database.MysqlGetAllProductList;
import com.example.dtos.getProductListSevenDayExpiryDTOs.GetProductListSevenDayExpiryOutputDTO;
import com.example.dtos.getProductListSevenDayExpiryDTOs.GetProductListSevenDayExpiryResponseData;
import com.example.entity.HangHoa;
import com.example.entity.HangThucPham;
import com.example.interfaces.*;
import com.example.ui.getAllProductListSevenDaysExpiryMVVP.GetProductListSevenDaysExpiryPresenter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GetProductListSevenDayExpiryUseCase implements InputBoundary {
    private OutputBoundary outputBoundary = null;
    private DatabaseBoundary databaseBoundary = null;

    public GetProductListSevenDayExpiryUseCase(OutputBoundary outputBoundary, DatabaseBoundary databaseBoundary) {
        this.outputBoundary = outputBoundary;
        this.databaseBoundary = databaseBoundary;
    }


    @Override
    public void execute(RequestData requestData) throws SQLException {
        // gọi hàm lấy dữ liệu trong database
        List<HangHoa> listHangHoa = ((MysqlGetAllProductList) databaseBoundary).getAllProductList();
        List<GetProductListSevenDayExpiryOutputDTO> listOutputDTOS = new ArrayList<>();

        // Lọc sản phẩm sắp hết hạn trong 7 ngày
        LocalDate dateNow = LocalDate.now();
        for (HangHoa hangHoa : listHangHoa) {
            if (hangHoa instanceof HangThucPham hangThucPham) {
                LocalDate ngayHetHan = hangThucPham.getNgayHetHan();

                // Chỉ tính sản phẩm có ngày hết hạn trong vòng 7 ngày tới và chưa hết hạn
                long daysUntilExpiry = java.time.temporal.ChronoUnit.DAYS.between(dateNow, ngayHetHan);

                // Kiểm tra nếu ngày hết hạn trong vòng 7 ngày tới và chưa hết hạn
                if (daysUntilExpiry >= 0 && daysUntilExpiry <= 7) {
                    GetProductListSevenDayExpiryOutputDTO getProductListOutputDTO = new GetProductListSevenDayExpiryOutputDTO(
                            hangThucPham.getMaHang(),
                            hangThucPham.getTenHang(),
                            hangThucPham.getSoLuongTon(),
                            hangThucPham.getDonGia(),
                            hangThucPham.tinhVat()
                    );
                    listOutputDTOS.add(getProductListOutputDTO);
                }
                // Debug log
                System.out.println("Ngay Het Han: " + ngayHetHan);
                System.out.println("Days Until Expiry: " + daysUntilExpiry);
            }
        }
        ResponseData responseData = new GetProductListSevenDayExpiryResponseData(listOutputDTOS);
        outputBoundary.exportResult(responseData);
    }


}
