package vn.fiosoft.zop.bus;

import java.util.ArrayList;
import java.util.List;

import vn.fiosoft.zop.dao.FriendDAO;
import vn.fiosoft.zop.dto.FriendDTO;
import android.content.Context;

public class FriendBUS {

	private Context context;

	public FriendBUS(Context context) {
		this.context = context;
	}

	/**
	 * 
	 * @param ojbect
	 * @return if successful it return new Friend object, otherwise return null.
	 */
	public FriendDTO create(FriendDTO ojbect) {

		try {
			FriendDAO datasource = new FriendDAO(this.context);
			datasource.open();

			FriendDTO result = datasource.create(ojbect);

			datasource.close();
			return result;
		} catch (Exception e) {
			return null;
		}

	}

	public void delete(FriendDTO object) {
		try {
			FriendDAO datasource = new FriendDAO(this.context);
			datasource.open();

			datasource.delete(object);

			datasource.close();

		} catch (Exception e) {

		}
	}

	
	public List<FriendDTO> listAll() {
		List<FriendDTO> objects;
		try {
			FriendDAO datasource = new FriendDAO(this.context);
			datasource.open();

			objects = datasource.listAll();

			datasource.close();

		} catch (Exception e) {
			objects = new ArrayList<FriendDTO>();
		}
		return objects;
	}
	
	/**
	 * 
	 * @param id of Friend
	 * @return if successful it return Friend object, otherwise return null.
	 */
	public FriendDTO getFriendByID(int id) {
		FriendDTO object;
		try {
			FriendDAO datasource = new FriendDAO(this.context);
			datasource.open();

			object = datasource.getFriendByID(id);

			datasource.close();

		} catch (Exception e) {
			object = null;
		}
		return object;
	}
}
