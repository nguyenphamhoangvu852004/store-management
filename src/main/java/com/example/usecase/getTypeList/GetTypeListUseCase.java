package com.example.usecase.getTypeList;

import com.example.database.MysqlGetTypeProductList;
import com.example.dtos.getTypeListDTOs.GetTypeListResponseData;
import com.example.dtos.getTypeListDTOs.GetTypeOutputDTO;
import com.example.interfaces.DatabaseBoundary;
import com.example.interfaces.InputBoundary;
import com.example.interfaces.OutputBoundary;
import com.example.interfaces.RequestData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetTypeListUseCase implements InputBoundary {
    private OutputBoundary outputBoundary = null;
    private DatabaseBoundary databaseBoundary = null;

    public GetTypeListUseCase(OutputBoundary outputBoundary, DatabaseBoundary databaseBoundary) {
        this.outputBoundary = outputBoundary;
        this.databaseBoundary = databaseBoundary;
    }

    @Override
    public void execute(RequestData requestData) throws SQLException {

        List<String> listType = ((MysqlGetTypeProductList) databaseBoundary).getAllTypeProdcutList();
        List<GetTypeOutputDTO> list = new ArrayList<>();
        for (String type : listType) {
            GetTypeOutputDTO getTypeOutputDTO = new GetTypeOutputDTO(type);
            list.add(getTypeOutputDTO);
        }


        GetTypeListResponseData getTypeListResponseData = new GetTypeListResponseData(list);
        outputBoundary.exportResult(getTypeListResponseData);
    }

}
