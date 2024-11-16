package com.example.ui.getAllProductListSevenDaysExpiryMVVP;

public class GetProductListSevenDaysExpiryViewModel {
    public String maHang;
    public String tenHang;
    public String soLuongTon;
    public String donGia;
    public String VAT;

    public GetProductListSevenDaysExpiryViewModel(String maHang, String tenHang, String soLuongTon, String donGia, String VAT) {
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

    public String getSoLuongTon() {
        return soLuongTon;
    }

    public String getDonGia() {
        return donGia;
    }

    public String getVAT() {
        return VAT;
    }
}
