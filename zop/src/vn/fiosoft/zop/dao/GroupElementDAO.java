package vn.fiosoft.zop.dao;

import java.util.ArrayList;
import java.util.List;

import vn.fiosoft.zop.dto.GroupDTO;
import vn.fiosoft.zop.dto.GroupElementDTO;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class GroupElementDAO {
	// Database fields
	private SQLiteDatabase	database;
	private MySQLiteHelper	dbHelper;
	private String[]		allColumns	= { GroupElementTable.COLUMN_ID_GROUP,
			GroupElementTable.COLUMN_ID_FRIEND };

	public GroupElementDAO(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public GroupElementDTO create(GroupElementDTO object) {
		ContentValues values = new ContentValues();

		values.put(GroupElementTable.COLUMN_ID_GROUP, object.getIdGroup());
		values.put(GroupElementTable.COLUMN_ID_FRIEND, object.getIdFriend());
		long insertId = database.insert(GroupTable.TABLE_GROUPS, null, values);
		Cursor cursor = database.query(GroupElementTable.TABLE_GROUP_ELEMENT, allColumns,
				GroupTable.COLUMN_ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		GroupElementDTO newGroupElement = cursorToObject(cursor);
		cursor.close();
		return newGroupElement;
	}

	public void delete(GroupElementDTO object) {
		int id_group = object.getIdGroup();
		int id_friend = object.getIdFriend();
		database.delete(GroupTable.TABLE_GROUPS, GroupElementTable.COLUMN_ID_GROUP + " = "
				+ id_group + " and " + GroupElementTable.COLUMN_ID_FRIEND + " = " + id_friend, null);
	}

	public List<GroupElementDTO> listAllElementOfGroup(GroupDTO group) {
		List<GroupElementDTO> list = new ArrayList<GroupElementDTO>();

		String selection = GroupElementTable.COLUMN_ID_GROUP + " = ?";
		String[] selectionArgs = {String.valueOf(group.getId())};
		Cursor cursor = database.query(GroupTable.TABLE_GROUPS, allColumns, selection, selectionArgs, null, null,
				null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			GroupElementDTO object = cursorToObject(cursor);
			list.add(object);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return list;
	}

	private GroupElementDTO cursorToObject(Cursor cursor) {
		GroupElementDTO result = new GroupElementDTO();
		result.setIdGroup(cursor.getInt(cursor.getColumnIndex(GroupElementTable.COLUMN_ID_GROUP)));
		result.setIdFriend(cursor.getInt(cursor.getColumnIndex(GroupElementTable.COLUMN_ID_FRIEND)));
		return result;
	}
}
