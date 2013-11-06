package vn.fiosoft.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class GroupTable {
	// Database table
	  public static final String TABLE_GROUPS = "groupproperties";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_IMAGE = "image";
	  public static final String COLUMN_NAME = "name";	  

	  // Database creation SQL statement
	  private static final String DATABASE_CREATE = "create table " 
	      + TABLE_GROUPS
	      + "(" 
	      + COLUMN_ID + " integer primary key autoincrement, " 
	      + COLUMN_IMAGE + " BLOB,"
	      + COLUMN_NAME + " text not null" 
	      + ");";

	  public static void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }

	  public static void onUpgrade(SQLiteDatabase database, int oldVersion,
	      int newVersion) { 
	    Log.w(GroupTable.class.getName(), "Upgrading database from version "
	        + oldVersion + " to " + newVersion
	        + ", which will destroy all old data");
	    database.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPS);
	    onCreate(database);
	  }
}
