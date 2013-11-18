package vn.fiosoft.zop.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

	  private static final String DATABASE_NAME = "zop.db";
	  private static final int DATABASE_VERSION = 1;

	  public MySQLiteHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) {
		  FriendTable.onCreate(database);
		  GroupTable.onCreate(database);
	  }

	  @Override
	  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
	    FriendTable.onUpgrade(database, oldVersion, newVersion);
	    GroupTable.onUpgrade(database, oldVersion, newVersion);
	  }

	} 