package com.example.ui.getTypeListMVVP;

import com.example.dtos.getTypeListDTOs.GetTypeListResponseData;
import com.example.dtos.getTypeListDTOs.GetTypeOutputDTO;
import com.example.interfaces.OutputBoundary;
import com.example.interfaces.ResponseData;
import com.example.ui.AddProductView;

import java.util.ArrayList;
import java.util.List;

public class GetTypeListPresenter implements OutputBoundary {

    private AddProductView addProductView = null;
    private List<GetTypeOutputDTO> listGetTypeOutputDTO = null;
    private List<GetTypeListViewModel> listGetTypeViewModel = null;

    public GetTypeListPresenter(AddProductView addProductView) {
        this.addProductView = addProductView;
    }

    @Override
    public void exportResult(ResponseData responseData) {
        if (responseData instanceof GetTypeListResponseData) {
            this.listGetTypeOutputDTO = ((GetTypeListResponseData) responseData).getTypeList();
            this.listGetTypeViewModel = new ArrayList<>();

            for (GetTypeOutputDTO getTypeOutputDTO : listGetTypeOutputDTO) {
                GetTypeListViewModel getTypeListViewModel = new GetTypeListViewModel(getTypeOutputDTO.getType());
                this.listGetTypeViewModel.add(getTypeListViewModel);
            }

            addProductView.buildComboBoxType(listGetTypeViewModel);
        }
    }

    public List<GetTypeListViewModel> getListGetTypeViewModel() {
        return listGetTypeViewModel;
    }
}
