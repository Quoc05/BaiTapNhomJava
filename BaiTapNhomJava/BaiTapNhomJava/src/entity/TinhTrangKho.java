/*
 * @ (#) TinhTrangKho.java    1.0   Apr 5, 2025
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

import java.time.LocalDate;

public class TinhTrangKho {
    private String maSP;
    private int soLuong;
    private LocalDate ngayNhap;

    public TinhTrangKho(String maSP, int soLuong, LocalDate ngayNhap) throws Exception {
        setMaSP(maSP);
        setSoLuong(soLuong);
        setNgayNhap(ngayNhap);
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) throws Exception {
        if (soLuong >= 0) {
            this.soLuong = soLuong;
        } else {
            throw new Exception("Số lượng không được nhỏ hơn 0");
        }
    }

    public LocalDate getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(LocalDate ngayNhap) throws Exception {
        if (ngayNhap.isAfter(LocalDate.now())) {
            throw new Exception("Ngày nhập kho không được lớn hơn ngày hiện tại");
        }
        this.ngayNhap = ngayNhap;
    }
}