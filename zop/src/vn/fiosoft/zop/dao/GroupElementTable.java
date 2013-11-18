package vn.fiosoft.zop.dao;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class GroupElementTable {
	// Database table
	  public static final String TABLE_GROUP_ELEMENT = "group";	  
	  public static final String COLUMN_ID_GROUP = "id_group";
	  public static final String COLUMN_ID_FRIEND = "id_friend";	  

	  // Database creation SQL statement
	  private static final String DATABASE_CREATE = "create table " 
	      + TABLE_GROUP_ELEMENT
	      + "(" 
	      + COLUMN_ID_GROUP + " integer not null, "
	      + COLUMN_ID_FRIEND + " integer not null"	       
	      + ");";

	  public static void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }

	  public static void onUpgrade(SQLiteDatabase database, int oldVersion,
	      int newVersion) { 
	    Log.w(GroupElementTable.class.getName(), "Upgrading database from version "
	        + oldVersion + " to " + newVersion
	        + ", which will destroy all old data");
	    database.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUP_ELEMENT);
	    onCreate(database);
	  }
}
