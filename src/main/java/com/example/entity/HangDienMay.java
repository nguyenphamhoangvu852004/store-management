package com.example.entity;

public class HangDienMay extends HangHoa {
    private int thoiGianBaoHanh; // Số tháng
    private double congSuat; // KW

    public HangDienMay(String maHang, String tenHang, int soLuongTon, double donGia, String tenLoai, int thoiGianBaoHanh, double congSuat) {
        super(maHang, tenHang, soLuongTon, donGia, tenLoai);
        this.thoiGianBaoHanh = thoiGianBaoHanh;
        this.congSuat = congSuat;
    }

    @Override
    public double tinhVat() {
        return getDonGia() * 0.1;
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
