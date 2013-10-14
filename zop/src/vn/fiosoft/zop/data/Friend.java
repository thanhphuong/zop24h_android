package vn.fiosoft.zop.data;

public class Friend {
	
	private long id;
	private String name;
	private double latitude;
	private double longitude;
	
	public Friend(){
		this.id = 0;
		this.name = "";
		this.latitude = 0;
		this.longitude = 0;
	}
	
	public Friend(long id, String name, double latitude, double longitude){
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public long getId(){
		return this.id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
}
