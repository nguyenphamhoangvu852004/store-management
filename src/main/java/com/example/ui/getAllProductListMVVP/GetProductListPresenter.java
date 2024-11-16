package com.example.ui.getAllProductListMVVP;

import com.example.dtos.getProductListDTOs.GetProductListOutputDTO;
import com.example.dtos.getProductListDTOs.GetProductListResponseData;
import com.example.interfaces.OutputBoundary;
import com.example.interfaces.ResponseData;
import com.example.ui.MainView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GetProductListPresenter implements OutputBoundary {
    private MainView mainView = null;
    private List<GetProductListOutputDTO> listOutDTO = null;
    private List<GetProductlistViewModel> listProductViewModel = null;

    public GetProductListPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void exportResult(ResponseData responseData) {

        if (responseData instanceof GetProductListResponseData) {
            this.listOutDTO = ((GetProductListResponseData) responseData).getList();
            this.listProductViewModel = new ArrayList<>();

            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            for (GetProductListOutputDTO product : listOutDTO) {
                String formattedDonGia = decimalFormat.format(product.getDonGia());
                String formattedVat = decimalFormat.format(product.getVAT());

                GetProductlistViewModel viewModel = new GetProductlistViewModel(
                        product.getMaHang(),
                        product.getTenHang(),
                        String.valueOf(product.getSoLuongTon()),
                        formattedDonGia,
                        formattedVat,
                        product.getTenLoai()
                );
                this.listProductViewModel.add(viewModel);
            }

            mainView.buildProductTable(listProductViewModel); // Gọi riêng
        }

    }


    // Thêm phương thức này để truy cập listProductViewModel từ bên ngoài
    public List<GetProductlistViewModel> getListProductViewModel() {
        return listProductViewModel;
    }

}



