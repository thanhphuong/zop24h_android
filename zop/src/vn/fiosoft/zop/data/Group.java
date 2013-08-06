package vn.fiosoft.zop.data;

public class Group {

	private int id;
	private String name;
	
	public Group(){
		this.id = 0;
		this.name = "";
	}
	
	public Group(int id, String name){
		this.id = id;
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
	
}
