package vn.fiosoft.sqlite;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import vn.fiosoft.zop.data.Group;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GroupDataSource {
	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { GroupTable.COLUMN_ID,
			GroupTable.COLUMN_IMAGE,
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

	public Group create(Group group) {
		ContentValues values = new ContentValues();
		
		if (group.getImage() != null) {

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Bitmap bitmap = group.getImage();
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
			byte[] image = baos.toByteArray();
			values.put(GroupTable.COLUMN_IMAGE, image);
		} else {
			values.putNull(GroupTable.COLUMN_IMAGE);
		}
		
		values.put(GroupTable.COLUMN_NAME, group.getName());
		long insertId = database.insert(GroupTable.TABLE_GROUPS, null, values);
		Cursor cursor = database
				.query(GroupTable.TABLE_GROUPS, allColumns,
						GroupTable.COLUMN_ID + " = " + insertId, null, null,
						null, null);
		cursor.moveToFirst();
		Group newFriend = cursorToObject(cursor);
		cursor.close();
		return newFriend;
	}

	public void delete(Group group) {
		int id = group.getId();
		database.delete(GroupTable.TABLE_GROUPS, GroupTable.COLUMN_ID + " = "
				+ id, null);
	}

	public List<Group> listAll() {
		List<Group> list = new ArrayList<Group>();

		Cursor cursor = database.query(GroupTable.TABLE_GROUPS, allColumns,
				null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Group group = cursorToObject(cursor);
			list.add(group);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return list;
	}

	private Group cursorToObject(Cursor cursor) {
		Group result = new Group();
		result.setId(cursor.getInt(cursor.getColumnIndex(GroupTable.COLUMN_ID)));
		byte[] outImage = cursor.getBlob(cursor.getColumnIndex(GroupTable.COLUMN_IMAGE));
		if (outImage != null && outImage.length != 0) {

			ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
			Bitmap image = BitmapFactory.decodeStream(imageStream);
			result.setImage(image);
		}
		result.setName(cursor.getString(cursor.getColumnIndex(GroupTable.COLUMN_NAME)));
		return result;
	}
}
