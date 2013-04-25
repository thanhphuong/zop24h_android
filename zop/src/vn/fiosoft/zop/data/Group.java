package vn.fiosoft.zop.data;

import java.util.ArrayList;
import java.util.List;

public class Group {
	
	private String name;
	public List<Friend> friends;
	
	public Group(){
		setName("");
		friends = new ArrayList<Friend>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	
}
