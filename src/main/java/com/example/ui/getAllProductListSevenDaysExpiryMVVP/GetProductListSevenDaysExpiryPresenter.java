package com.example.ui.getAllProductListSevenDaysExpiryMVVP;

import com.example.dtos.getProductListSevenDayExpiryDTOs.GetProductListSevenDayExpiryOutputDTO;
import com.example.dtos.getProductListSevenDayExpiryDTOs.GetProductListSevenDayExpiryResponseData;
import com.example.interfaces.OutputBoundary;
import com.example.interfaces.ResponseData;

import java.util.ArrayList;
import java.util.List;

public class GetProductListSevenDaysExpiryPresenter implements OutputBoundary {


    private List<GetProductListSevenDayExpiryOutputDTO> listSevenDayExpiryOutputDTOS = null;
    private List<GetProductListSevenDaysExpiryViewModel> listSevenDaysExpiryViewModels = null;


    @Override
    public void exportResult(ResponseData responseData) {
        if (responseData instanceof GetProductListSevenDayExpiryResponseData) {
            listSevenDayExpiryOutputDTOS = ((GetProductListSevenDayExpiryResponseData) responseData).getList();
        }
        listSevenDaysExpiryViewModels = new ArrayList<>();

        for (GetProductListSevenDayExpiryOutputDTO product : listSevenDayExpiryOutputDTOS) {
            GetProductListSevenDaysExpiryViewModel getProductListSevenDaysExpiryViewModel = new GetProductListSevenDaysExpiryViewModel(
                    product.getMaHang(),
                    product.getTenHang(),
                    String.valueOf(product.getSoLuongTon()),
                    String.valueOf(product.getDonGia()),
                    String.valueOf(product.getVAT())
            );
            this.listSevenDaysExpiryViewModels.add(getProductListSevenDaysExpiryViewModel);

        }

        GetProductListSevenDaysExpiryView getProductListSevenDaysExpiryView = new GetProductListSevenDaysExpiryView();
        getProductListSevenDaysExpiryView.buildComponent(listSevenDaysExpiryViewModels);
    }


    public List<GetProductListSevenDaysExpiryViewModel> getListSevenDaysExpiryViewModels() {
        return listSevenDaysExpiryViewModels;
    }
}
