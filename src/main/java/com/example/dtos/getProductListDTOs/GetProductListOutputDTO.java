package com.example.dtos.getProductListDTOs;

public class GetProductListOutputDTO {
    protected String maHang;
    protected String tenHang;
    protected String tenLoai;
    protected int soLuongTon;
    protected double donGia;
    protected double VAT;

    public GetProductListOutputDTO(String maHang, String tenHang, String tenLoai, int soLuongTon, double donGia, double VAT) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.tenLoai = tenLoai;
        this.soLuongTon = soLuongTon;
        this.donGia = donGia;
        this.VAT = VAT;
    }

    public String getTenLoai() {
        return tenLoai;
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
