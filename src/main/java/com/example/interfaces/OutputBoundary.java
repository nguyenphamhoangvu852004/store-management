package com.example.interfaces;


import java.sql.SQLException;

public interface OutputBoundary {
    void exportResult(ResponseData responseData) throws SQLException;

}
