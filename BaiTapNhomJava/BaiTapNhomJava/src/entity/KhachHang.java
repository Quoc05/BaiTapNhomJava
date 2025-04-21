
package entity;
import java.text.NumberFormat;

public class KhachHang {
    private String maKH;
    private String tenKH;
    private String SDT;

    public KhachHang(String maKH, String tenKH, String SDT) throws Exception {
        setMaKH(maKH);
        setTenKH(tenKH);
        setSDT(SDT);
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) throws Exception {
        if (maKH.matches("KH\\d{3}")) {
            this.maKH = maKH;
        } else {
            throw new Exception("Mã khách hàng phải có dạng KHxxx với x là số từ 0-9");
        }
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) throws Exception {
        if (tenKH != null && tenKH.trim().matches("([A-ZÀ-Ỵ][a-zà-ỹ]+\\s?)+")) {
            this.tenKH = tenKH;
        } else {
            throw new Exception("Tên khách hàng phải bắt đầu bằng chữ hoa và có ít nhất 1 từ");
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

	public NumberFormat getNgaySinh() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCCCD() {
		// TODO Auto-generated method stub
		return null;
	}
}