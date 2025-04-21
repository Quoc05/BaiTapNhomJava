package entity;
import java.time.LocalDate;
import java.util.Objects;

public class HoaDon {
    private String maHD;
    private String maNV;
    private String maKH;
    private double tongTien;
    private LocalDate ngayLapHD;
	public HoaDon(String maHD, String maNV, String maKH, double tongTien, LocalDate ngayLapHD) {
		super();
		this.maHD = maHD;
		this.maNV = maNV;
		this.maKH = maKH;
		this.tongTien = tongTien;
		this.ngayLapHD = ngayLapHD;
	}
	public HoaDon(String maHD) {
		super();
		this.maHD = maHD;
	}
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	public LocalDate getNgayLapHD() {
		return ngayLapHD;
	}
	public void setNgayLapHD(LocalDate ngayLapHD) {
		this.ngayLapHD = ngayLapHD;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maHD);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHD, other.maHD);
	}
	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", maNV=" + maNV + ", maKH=" + maKH + ", tongTien=" + tongTien + ", ngayLapHD="
				+ ngayLapHD + "]";
	}

    

    
}