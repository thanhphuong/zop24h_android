package vn.fiosoft.zop.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GroupElementDTO implements Serializable{

	private int idGroup;
	private int idFriend;
		
	
	public GroupElementDTO(){
		this.idGroup = 0;
		this.idFriend = 0;
	}
	
	public GroupElementDTO(int id_group, int id_friend){
		this.idGroup = id_group;
		this.idFriend = id_friend;
	}	
	
	public int getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(int idGroup) {
		this.idGroup = idGroup;
	}

	public int getIdFriend() {
		return idFriend;
	}

	public void setIdFriend(int idFriend) {
		this.idFriend = idFriend;
	}
	
}
