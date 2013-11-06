package vn.fiosoft.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class FriendTable {
	// Database table
	  public static final String TABLE_FRIENDS = "friends";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_TIME = "time";
	  public static final String COLUMN_IMAGE = "image";
	  public static final String COLUMN_NAME = "name";
	  public static final String COLUMN_LATITUDE = "latitude";
	  public static final String COLUMN_LONGITUDE = "longitude";

	  // Database creation SQL statement
	  private static final String DATABASE_CREATE = "create table " 
	      + TABLE_FRIENDS
	      + "(" 
	      + COLUMN_ID + " integer primary key autoincrement, "
	      + COLUMN_TIME + " long not null,"
	      + COLUMN_IMAGE + " BLOB,"
	      + COLUMN_NAME + " text not null,"
	      + COLUMN_LATITUDE + " double not null,"
	      + COLUMN_LONGITUDE + " double not null" 
	      + ");";

	  public static void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }

	  public static void onUpgrade(SQLiteDatabase database, int oldVersion,
	      int newVersion) { 
	    Log.w(FriendTable.class.getName(), "Upgrading database from version "
	        + oldVersion + " to " + newVersion
	        + ", which will destroy all old data");
	    database.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIENDS);
	    onCreate(database);
	  }
}
