package in.fssa.sportshub.model;

public class Address {
	private int id;
	private String area;
	private String district;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", area=" + area + ", district=" + district + "]";
	}
	
}
