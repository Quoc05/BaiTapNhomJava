/*
 * @ (#) SanPham.java    1.0   Apr 5, 2025
 *
 * Copyright (c) 2025 IUH. All rights reserved.  
 */
package entity;
/*
* @description:
* @author: Quoc Nguyen
* @date:  Apr 5, 2025
* @version:   1.0
*/
public class SanPham {
    private String maSP;
    private String tenSP;
    private double giaBan;
    private String loaiSP;

    public SanPham(String maSP, String tenSP, double giaBan, String loaiSP) throws Exception {
        setMaSP(maSP);
        setTenSP(tenSP);
        setGiaBan(giaBan);
        setLoaiSP(loaiSP);
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) throws Exception {
        if (maSP.matches("SP\\d{3}")) {
            this.maSP = maSP;
        } else {
            throw new Exception("Mã sản phẩm phải có dạng SPxxx với x là số từ 0-9");
        }
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) throws Exception {
        if (tenSP != null && !tenSP.trim().isEmpty()) {
            this.tenSP = tenSP;
        } else {
            throw new Exception("Tên sản phẩm không được để trống");
        }
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) throws Exception {
        if (giaBan >= 0) {
            this.giaBan = giaBan;
        } else {
            throw new Exception("Giá bán phải lớn hơn hoặc bằng 0");
        }
    }

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) throws Exception {
        if (loaiSP.equalsIgnoreCase("Thực phẩm") || loaiSP.equalsIgnoreCase("Đồ uống") || loaiSP.equalsIgnoreCase("Hàng gia dụng")) {
            this.loaiSP = loaiSP;
        } else {
            throw new Exception("Loại sản phẩm không hợp lệ (Thực phẩm, Đồ uống, Hàng gia dụng)");
        }
    }
}