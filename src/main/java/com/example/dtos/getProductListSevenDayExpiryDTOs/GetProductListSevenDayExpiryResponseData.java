package com.example.dtos.getProductListSevenDayExpiryDTOs;

import com.example.interfaces.ResponseData;

import java.util.List;

public class GetProductListSevenDayExpiryResponseData implements ResponseData {
    private List<GetProductListSevenDayExpiryOutputDTO> list;

    public GetProductListSevenDayExpiryResponseData(List<GetProductListSevenDayExpiryOutputDTO> list) {
        this.list = list;
    }

    public List<GetProductListSevenDayExpiryOutputDTO> getList() {
        return list;
    }
}
