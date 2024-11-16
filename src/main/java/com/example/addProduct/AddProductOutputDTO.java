package com.example.addProduct;

public class AddProductOutputDTO {
    private String message;

    public AddProductOutputDTO(String message) {
        this.message = message;
    }

    public AddProductOutputDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

