
package entity;

import java.time.LocalDate;
import java.util.Objects;

public class NhanVien {
    private String maNV;
    private String matKhau;
    private String tenNV;
    private LocalDate ngaySinh;
    private String SDT;
    private String cccd;

  
    public NhanVien(String maNV, String matKhau, String tenNV, LocalDate ngaySinh, String sDT, String cccd) {
		super();
		this.maNV = maNV;
		this.matKhau = matKhau;
		this.tenNV = tenNV;
		this.ngaySinh = ngaySinh;
		SDT = sDT;
		this.cccd = cccd;
	}

	public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) throws Exception {
        if (maNV.matches("NV\\d{3}")) {
            this.maNV = maNV;
        } else {
            throw new Exception("Mã nhân viên phải có dạng NVxxx với x là số từ 0-9");
        }
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) throws Exception {
        if (matKhau.matches("\\w{6,}")) {
            this.matKhau = matKhau;
        } else {
            throw new Exception("Mật khẩu phải có ít nhất 6 ký tự");
        }
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) throws Exception {
        if (tenNV.trim().matches("([A-ZÀ-Ỵ][a-zà-ỹ]+\\s?)+")) {
            this.tenNV = tenNV;
        } else {
            throw new Exception("Tên nhân viên phải có ít nhất 2 từ, mỗi từ bắt đầu bằng chữ hoa");
        }
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) throws Exception {
        if (ngaySinh.isBefore(LocalDate.now())) {
            this.ngaySinh = ngaySinh;
        } else {
            throw new Exception("Ngày sinh không hợp lệ");
        }
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) throws Exception {
        if (SDT.matches("0\\d{9}")) {
            this.SDT = SDT;
        } else {
            throw new Exception("Số điện thoại phải có 10 chữ số và bắt đầu bằng số 0");
        }
    }
    
    public void setCCCD(String cccd) throws Exception {
        if (cccd.matches("\\d{12}")) {
            this.cccd = cccd;
        } else {
            throw new Exception("CCCD phải gồm đúng 12 chữ số");
        }
    }

    public String getCCCD() { 
        return cccd;
    }

	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNV, other.maNV);
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", matKhau=" + matKhau + ", tenNV=" + tenNV + ", ngaySinh=" + ngaySinh
				+ ", SDT=" + SDT + ", cccd=" + cccd + "]";
	}
    
    

    
	
}
