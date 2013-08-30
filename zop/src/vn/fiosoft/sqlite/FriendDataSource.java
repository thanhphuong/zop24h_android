package vn.fiosoft.sqlite;

import java.util.ArrayList;
import java.util.List;

import vn.fiosoft.zop.data.Friend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class FriendDataSource {
	// Database fields
	  private SQLiteDatabase database;
	  private MySQLiteHelper dbHelper;
	  private String[] allColumns = { FriendTable.COLUMN_ID,
			  FriendTable.COLUMN_NAME };

	  public FriendDataSource(Context context) {
	    dbHelper = new MySQLiteHelper(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public Friend createFriend(String name) {
	    ContentValues values = new ContentValues();
	    values.put(FriendTable.COLUMN_NAME, name);
	    long insertId = database.insert(FriendTable.TABLE_FRIENDS, null,
	        values);
	    Cursor cursor = database.query(FriendTable.TABLE_FRIENDS,
	        allColumns, FriendTable.COLUMN_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Friend newFriend = cursorToFriend(cursor);
	    cursor.close();
	    return newFriend;
	  }

	  public void deleteFriend(Friend friend) {
	    long id = friend.getId();	    
	    database.delete(FriendTable.TABLE_FRIENDS, FriendTable.COLUMN_ID
	        + " = " + id, null);
	  }

	  public List<Friend> getAllFriends() {
	    List<Friend> friends = new ArrayList<Friend>();

	    Cursor cursor = database.query(FriendTable.TABLE_FRIENDS,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Friend friend = cursorToFriend(cursor);
	      friends.add(friend);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return friends;
	  }

	  private Friend cursorToFriend(Cursor cursor) {
	    Friend friend = new Friend();
	    friend.setId(cursor.getLong(0));
	    friend.setName(cursor.getString(1));
	    return friend;
	  }
}
