package com.example.addProduct;


import com.example.interfaces.RequestData;

import java.time.LocalDate;

public class AddProductDTO {

    // Chung
    private String tenHang;
    private int soLuong;
    private double donGia;
    private String loaiHang;

    // Thuc Pham
    private LocalDate ngaySanXuat; // Ngày sản xuất (yyyy-MM-dd)
    private LocalDate ngayHetHan;      // Ngày hết hạn (yyyy-MM-dd)
    private String nhaCungCap;

    // Sanh Su
    private String nhaSanXuat;    // Nhà sản xuất
    private LocalDate ngayNhapKho;

    // Dien may
    private int thoiGianBaoHanh;     // Số tháng bảo hành (>= 0)
    private double congSuat;



    public AddProductDTO(String tenHang, int soLuong, double donGia, String loaiHang) {
        this.tenHang = tenHang;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.loaiHang = loaiHang;
    }

    public AddProductDTO(String tenHang, int soLuong, double donGia, String loaiHang, int thoiGianBaoHanh, double congSuat) {
        this(tenHang, soLuong, donGia, loaiHang);
        this.thoiGianBaoHanh = thoiGianBaoHanh;
        this.congSuat = congSuat;
    }

    public AddProductDTO(String tenHang, int soLuong, double donGia, String loaiHang, String nhaSanXuat, LocalDate ngayNhapKho) {
        this(tenHang, soLuong, donGia, loaiHang);
        this.nhaSanXuat = nhaSanXuat;
        this.ngayNhapKho = ngayNhapKho;
    }

    public AddProductDTO(String tenHang, int soLuong, double donGia, String loaiHang, LocalDate ngaySanXuat, LocalDate ngayHetHan, String nhaCungCap) {
        this(tenHang, soLuong, donGia, loaiHang);
        this.ngaySanXuat = ngaySanXuat;
        this.ngayHetHan = ngayHetHan;
        this.nhaCungCap = nhaCungCap;
    }


    public AddProductDTO(){}

    public String getTenHang() {
        return tenHang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public String getLoaiHang() {
        return loaiHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public void setLoaiHang(String loaiHang) {
        this.loaiHang = loaiHang;
    }

    public LocalDate getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(LocalDate ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public LocalDate getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(LocalDate ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(String nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    public LocalDate getNgayNhapKho() {
        return ngayNhapKho;
    }

    public void setNgayNhapKho(LocalDate ngayNhapKho) {
        this.ngayNhapKho = ngayNhapKho;
    }

    public int getThoiGianBaoHanh() {
        return thoiGianBaoHanh;
    }

    public void setThoiGianBaoHanh(int thoiGianBaoHanh) {
        this.thoiGianBaoHanh = thoiGianBaoHanh;
    }

    public double getCongSuat() {
        return congSuat;
    }

    public void setCongSuat(double congSuat) {
        this.congSuat = congSuat;
    }
}
