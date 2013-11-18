package vn.fiosoft.zop.dto;

public class MenuItemDTO {
	
	public static int MENU_LOGIN = 1;
	public static int MENU_HELP = 2;
	
	private int id;
	private int image;
	private String item;
	
	/**
	 * @param id of ZOPMenuItem
	 * @param image this is id in drawable
	 * @param item  the name of menu
	 */
	public MenuItemDTO(int id, int image, String item){
		this.id = id;
		this.image = image;
		this.item = item; 
	}
	
	public int getImage() {
		return image;
	}
	public void setImage(int image) {
		this.image = image;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
