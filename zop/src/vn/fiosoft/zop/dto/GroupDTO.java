package vn.fiosoft.zop.dto;

import java.io.Serializable;
import android.graphics.Bitmap;

@SuppressWarnings("serial")
public class GroupDTO implements Serializable{

	private int id;
	private Bitmap image;
	private String name;	
	
	public GroupDTO(){
		this.id = 0;
		this.image = null;
		this.name = "";		
	}
	
	public GroupDTO(int id, Bitmap image, String name){
		this.id = id;
		this.image = image;
		this.name = name;		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}
	
}
