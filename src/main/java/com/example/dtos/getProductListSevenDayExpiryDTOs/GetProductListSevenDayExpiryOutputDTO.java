package com.example.dtos.getProductListSevenDayExpiryDTOs;

import com.example.interfaces.ResponseData;

public class GetProductListSevenDayExpiryOutputDTO implements ResponseData {
    protected String maHang;
    protected String tenHang;
    protected int soLuongTon;
    protected double donGia;
    protected double VAT;

    public GetProductListSevenDayExpiryOutputDTO(String maHang, String tenHang, int soLuongTon, double donGia, double VAT) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.soLuongTon = soLuongTon;
        this.donGia = donGia;
        this.VAT = VAT;
    }

    public String getMaHang() {
        return maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public double getDonGia() {
        return donGia;
    }

    public double getVAT() {
        return VAT;
    }
}
