package com.example.interfaces;

import java.sql.SQLException;

public interface InputBoundary {
    void execute(RequestData requestData) throws SQLException, SQLException;

}
