package vn.fiosoft.zop.dto;

public class AccountDTO {
	
	private int id;
	private String username;
	private String password;	
	
	public AccountDTO(){
		this.id = 0;
		this.username = "";
		this.password = "";		
	}
	
	public AccountDTO(int id, String username, String password){
		this.id = id;
		this.username = username;
		this.password = password;		
		
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getUserName(){
		return this.username;
	}
	
	public void setUserName(String username){
		this.username = username;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
}
