package com.example.dtos.getProductListDTOs;

import com.example.interfaces.ResponseData;

import java.util.List;

public class GetProductListResponseData implements ResponseData {
    private List<GetProductListOutputDTO> list;

    public GetProductListResponseData(List<GetProductListOutputDTO> list) {
        this.list = list;
    }

    public List<GetProductListOutputDTO> getList() {
        return list;
    }
}
