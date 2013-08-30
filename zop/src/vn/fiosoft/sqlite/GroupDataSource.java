package vn.fiosoft.sqlite;

import java.util.ArrayList;
import java.util.List;

import vn.fiosoft.zop.data.Group;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class GroupDataSource {
	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { GroupTable.COLUMN_ID,
			GroupTable.COLUMN_NAME };

	public GroupDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Group createGroup(String name) {
		ContentValues values = new ContentValues();
		values.put(GroupTable.COLUMN_NAME, name);
		long insertId = database.insert(GroupTable.TABLE_GROUPS, null, values);
		Cursor cursor = database
				.query(GroupTable.TABLE_GROUPS, allColumns,
						GroupTable.COLUMN_ID + " = " + insertId, null, null,
						null, null);
		cursor.moveToFirst();
		Group newFriend = cursorToGroup(cursor);
		cursor.close();
		return newFriend;
	}

	public void deleteGroup(Group group) {
		long id = group.getId();
		database.delete(GroupTable.TABLE_GROUPS, GroupTable.COLUMN_ID + " = "
				+ id, null);
	}

	public List<Group> getAllGroups() {
		List<Group> groups = new ArrayList<Group>();

		Cursor cursor = database.query(GroupTable.TABLE_GROUPS, allColumns,
				null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Group group = cursorToGroup(cursor);
			groups.add(group);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return groups;
	}

	private Group cursorToGroup(Cursor cursor) {
		Group group = new Group();
		group.setId(cursor.getLong(0));
		group.setName(cursor.getString(1));
		return group;
	}
}
