package com.example.addProduct;
import com.example.entity.HangDienMay;
import com.example.entity.HangHoa;
import com.example.entity.HangSanhSu;
import com.example.entity.HangThucPham;

import java.time.LocalDate;

public class AddProductUseCase implements com.example.addProduct.AddProductInputBoundary {

    private AddProductOutputBoundary outputBoundary;
    private AddProductDatabaseBoundary databaseBoundary;
    AddProductOutputDTO addProductOutputDTO = new AddProductOutputDTO();

    public AddProductUseCase(AddProductOutputBoundary outputBoundary,
                             AddProductDatabaseBoundary databaseBoundary) {
        this.outputBoundary = outputBoundary;
        this.databaseBoundary = databaseBoundary;
    }

    @Override
    public void execute(AddProductDTO addProductDTO) {
        try {

            AddProductDAO database = (AddProductDAO) databaseBoundary;

//            if(database.findProductById(addProductDTO.getMaHang()) != null){
//                addProductOutputDTO.setMessage("Mã hàng đã tồn tại.");
//                outputBoundary.presenter(addProductOutputDTO);
//                return;
//            }

            // Kiểm tra input
            String validationError = validate(addProductDTO);
            if (validationError != null) {
                // Nếu có lỗi, trả về ngay thông báo lỗi đó
                addProductOutputDTO.setMessage(validationError);
                outputBoundary.presenter(addProductOutputDTO);
                return;
            }

            HangHoa hangHoa;

            // Nếu không có lỗi, tiếp tục xử lý tạo đối tượng HangHoa và lưu vào DB
            if (addProductDTO.getLoaiHang().equals("Hàng Thực Phẩm")) {
                hangHoa = new HangThucPham(
                        null,
                        addProductDTO.getTenHang(),
                        addProductDTO.getSoLuong(),
                        addProductDTO.getDonGia(),
                        addProductDTO.getLoaiHang(),
                        addProductDTO.getNgaySanXuat(),
                        addProductDTO.getNgayHetHan(),
                        addProductDTO.getNhaCungCap()
                );
            } else if (addProductDTO.getLoaiHang().equals("Hàng Điện Máy")) {
                hangHoa = new HangDienMay(
                        null,
                        addProductDTO.getTenHang(),
                        addProductDTO.getSoLuong(),
                        addProductDTO.getDonGia(),
                        addProductDTO.getLoaiHang(),
                        addProductDTO.getThoiGianBaoHanh(),
                        addProductDTO.getCongSuat()
                );
            } else {
                hangHoa = new HangSanhSu(
                        null,
                        addProductDTO.getTenHang(),
                        addProductDTO.getSoLuong(),
                        addProductDTO.getDonGia(),
                        addProductDTO.getLoaiHang(),
                        addProductDTO.getNhaSanXuat(),
                        addProductDTO.getNgayNhapKho()
                );
            }

            boolean isSave = database.saveProduct(hangHoa);
            if (isSave) {
                addProductOutputDTO.setMessage("Thêm sản phẩm thành công");
            } else {
                addProductOutputDTO.setMessage("Thêm sản phẩm thất bại");
            }

            // Xuất kết quả
            outputBoundary.presenter(addProductOutputDTO);
        }catch (Exception e){
            addProductOutputDTO.setMessage(e.getMessage());
            outputBoundary.presenter(addProductOutputDTO);
        }
    }


    public String validate(AddProductDTO data) {
        // Kiểm tra các thuộc tính chung
        if (data.getTenHang() == null || data.getTenHang().isEmpty()) {
            return "Tên hàng không được để trống.";
        }
        if (data.getTenHang().length() > 30) {
            return "Tên hàng không được dài quá 30 ký tự.";
        }

        if (data.getSoLuong() <= 0) {
            return "Số lượng phải lớn hơn 0.";
        }

        if (data.getDonGia() <= 0) {
            return "Đơn giá phải lớn hơn 0.";
        }

        // Kiểm tra thuộc tính đặc thù dựa vào loại sản phẩm
        if (data.getLoaiHang().equals("Hàng Thực Phẩm")) {

            if (isValidDate(data.getNgaySanXuat())) {
                return "Ngày sản xuất không hợp lệ hoặc là ngày trong tương lai.";
            }

            if (data.getNgaySanXuat() == null) {
                return "Ngày hết hạn không hợp lệ";
            }

            if (!data.getNgayHetHan().isAfter(data.getNgaySanXuat())) {
                return "Ngày hết hạn phải sau ngày sản xuất.";
            }

            if (data.getNhaCungCap() == null || data.getNhaCungCap().isEmpty()) {
                return "Nhà cung cấp không được để trống.";
            }
        } else if (data.getLoaiHang().equals("Hàng Điện Máy")) {

            if (data.getThoiGianBaoHanh() < 0) {
                return "Thời gian bảo hành không được âm.";
            }
            if (data.getCongSuat() <= 0) {
                return "Công suất phải lớn hơn 0.";
            }
        } else {

            if (data.getNhaSanXuat() == null || data.getNhaSanXuat().isEmpty()) {
                return "Nhà sản xuất không được để trống.";
            }

            if (isValidDate(data.getNgayNhapKho())) {
                return "Ngày nhập kho không hợp lệ hoặc là ngày trong tương lai.";
            }
        }

        // Nếu tất cả kiểm tra đều hợp lệ
        return null;
    }

    // Hàm kiểm tra ngày hợp lệ (đúng định dạng, không để trống, và không phải ngày trong tương lai)
    private boolean isValidDate(LocalDate date) {
        if (date == null) {
            return true;
        }
        return date.isAfter(LocalDate.now());
    }

   




}
