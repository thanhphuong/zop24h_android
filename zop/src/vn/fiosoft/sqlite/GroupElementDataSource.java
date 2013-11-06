package vn.fiosoft.sqlite;

import java.util.ArrayList;
import java.util.List;

import vn.fiosoft.zop.data.GroupElement;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class GroupElementDataSource {
	// Database fields
	private SQLiteDatabase	database;
	private MySQLiteHelper	dbHelper;
	private String[]		allColumns	= { GroupElementTable.COLUMN_ID_GROUP,
			GroupElementTable.COLUMN_ID_FRIEND };

	public GroupElementDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public GroupElement create(GroupElement group) {
		ContentValues values = new ContentValues();

		values.put(GroupElementTable.COLUMN_ID_GROUP, group.getIdGroup());
		values.put(GroupElementTable.COLUMN_ID_FRIEND, group.getIdFriend());
		long insertId = database.insert(GroupTable.TABLE_GROUPS, null, values);
		Cursor cursor = database.query(GroupElementTable.TABLE_GROUP_ELEMENT, allColumns,
				GroupTable.COLUMN_ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		GroupElement newGroupElement = cursorToObject(cursor);
		cursor.close();
		return newGroupElement;
	}

	public void delete(GroupElement group) {
		int id_group = group.getIdGroup();
		int id_friend = group.getIdFriend();
		database.delete(GroupTable.TABLE_GROUPS, GroupElementTable.COLUMN_ID_GROUP + " = "
				+ id_group + " && " + GroupElementTable.COLUMN_ID_FRIEND + " = " + id_friend, null);
	}

	public List<GroupElement> listAll() {
		List<GroupElement> list = new ArrayList<GroupElement>();

		Cursor cursor = database.query(GroupTable.TABLE_GROUPS, allColumns, null, null, null, null,
				null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			GroupElement group = cursorToObject(cursor);
			list.add(group);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return list;
	}

	private GroupElement cursorToObject(Cursor cursor) {
		GroupElement result = new GroupElement();
		result.setIdGroup(cursor.getInt(cursor.getColumnIndex(GroupElementTable.COLUMN_ID_GROUP)));
		result.setIdFriend(cursor.getInt(cursor.getColumnIndex(GroupElementTable.COLUMN_ID_FRIEND)));
		return result;
	}
}
