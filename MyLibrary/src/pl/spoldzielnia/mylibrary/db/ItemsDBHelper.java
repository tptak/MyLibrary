package pl.spoldzielnia.mylibrary.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemsDBHelper extends SQLiteOpenHelper {

	public ItemsDBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		MyLibDBTables.onCreate(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		MyLibDBTables.onUpgrade(db, oldVersion, newVersion);
	}
	
}
