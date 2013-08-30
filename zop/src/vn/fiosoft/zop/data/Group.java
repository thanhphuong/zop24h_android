package vn.fiosoft.zop.data;

public class Group {

	private long id;
	private String name;
	
	public Group(){
		this.id = 0;
		this.name = "";
	}
	
	public Group(long id, String name){
		this.id = id;
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
