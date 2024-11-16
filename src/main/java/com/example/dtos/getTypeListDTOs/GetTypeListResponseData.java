package com.example.dtos.getTypeListDTOs;

import com.example.interfaces.ResponseData;

import java.util.List;

public class GetTypeListResponseData implements ResponseData {
    private List<GetTypeOutputDTO> typeList;

    public GetTypeListResponseData(List<GetTypeOutputDTO> typeList) {
        this.typeList = typeList;
    }

    public List<GetTypeOutputDTO> getTypeList() {
        return typeList;
    }

}
