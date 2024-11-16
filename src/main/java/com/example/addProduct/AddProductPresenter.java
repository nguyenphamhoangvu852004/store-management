package com.example.addProduct;

import com.example.ui.AddProductView;

public class AddProductPresenter implements AddProductOutputBoundary {

    private AddProductOutputDTO addProductOutputDTO;
    private AddProductViewModel addProductViewModel;

    @Override
    public void presenter(AddProductOutputDTO addProductOutputDTO) {
        this.addProductOutputDTO = addProductOutputDTO;

        this.addProductViewModel = new AddProductViewModel(addProductOutputDTO.getMessage());

        AddProductView form = new AddProductView();

        form.displayMessage(addProductViewModel.getMessage());
    }
}
