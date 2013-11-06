package vn.fiosoft.zop.data;

import java.io.Serializable;
import java.util.Date;

import android.graphics.Bitmap;

@SuppressWarnings("serial")
public class Friend implements Serializable{
	
	private int id;
	private Date time;
	private Bitmap image;
	private String name;
	private double latitude;
	private double longitude;
	
	public Friend(){
		this.id = 0;
		this.time = null;
		this.image = null;
		this.name = "";
		this.latitude = 0;
		this.longitude = 0;
	}
	
	public Friend(int id, Date time, Bitmap image, String name, double latitude, double longitude){
		this.id = id;
		this.time = time;
		this.image = image;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public long getId(){
		return this.id;
	}
	
	public void setId(int id){
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

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	
}
