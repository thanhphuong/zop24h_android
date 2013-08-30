package vn.fiosoft.zop.data;

public class Friend {
	
	private long id;
	private String name;
	
	public Friend(){
		this.id = 0;
		this.name = "";
	}
	
	public Friend(long id, String name){
		this.id = id;
		this.name = name;
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
}
