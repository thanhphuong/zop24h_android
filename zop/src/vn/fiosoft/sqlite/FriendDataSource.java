package vn.fiosoft.sqlite;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vn.fiosoft.zop.data.Friend;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class FriendDataSource {
	// Database fields
	private SQLiteDatabase	database;
	private MySQLiteHelper	dbHelper;
	private String[]		allColumns	= { FriendTable.COLUMN_ID, FriendTable.COLUMN_TIME,
			FriendTable.COLUMN_IMAGE, FriendTable.COLUMN_NAME, FriendTable.COLUMN_LATITUDE,
			FriendTable.COLUMN_LONGITUDE };

	public FriendDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Friend create(Friend friend) {

		ContentValues values = new ContentValues();
		
		values.put(FriendTable.COLUMN_TIME, friend.getTime().getTime());		
		if (friend.getImage() != null) {

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Bitmap bitmap = friend.getImage();
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
			byte[] image = baos.toByteArray();
			values.put(FriendTable.COLUMN_IMAGE, image);
		} else {
			values.putNull(FriendTable.COLUMN_IMAGE);
		}

		values.put(FriendTable.COLUMN_NAME, friend.getName());
		values.put(FriendTable.COLUMN_LATITUDE, friend.getLatitude());
		values.put(FriendTable.COLUMN_LONGITUDE, friend.getLongitude());
		long insertId = database.insert(FriendTable.TABLE_FRIENDS, null, values);
		Cursor cursor = database.query(FriendTable.TABLE_FRIENDS, allColumns, FriendTable.COLUMN_ID
				+ " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		Friend newFriend = cursorToObject(cursor);
		cursor.close();
		return newFriend;
	}

	public void delete(Friend friend) {
		long id = friend.getId();
		database.delete(FriendTable.TABLE_FRIENDS, FriendTable.COLUMN_ID + " = " + id, null);
	}

	public List<Friend> listAll() {
		List<Friend> list = new ArrayList<Friend>();

		Cursor cursor = database.query(FriendTable.TABLE_FRIENDS, allColumns, null, null, null,
				null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Friend friend = cursorToObject(cursor);
			list.add(friend);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return list;
	}

	private Friend cursorToObject(Cursor cursor) {
		Friend result = new Friend();
		result.setId(cursor.getInt(cursor.getColumnIndex(FriendTable.COLUMN_ID)));
		result.setTime(new Date(cursor.getLong(cursor.getColumnIndex(FriendTable.COLUMN_TIME))));
		byte[] outImage = cursor.getBlob(cursor.getColumnIndex(FriendTable.COLUMN_IMAGE));
		if (outImage != null && outImage.length != 0) {

			ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
			Bitmap image = BitmapFactory.decodeStream(imageStream);
			result.setImage(image);
		}
		result.setName(cursor.getString(cursor.getColumnIndex(FriendTable.COLUMN_NAME)));
		result.setLatitude(cursor.getDouble(cursor.getColumnIndex(FriendTable.COLUMN_LATITUDE)));
		result.setLongitude(cursor.getDouble(cursor.getColumnIndex(FriendTable.COLUMN_LONGITUDE)));

		return result;
	}
}
