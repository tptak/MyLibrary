package pl.spoldzielnia.mylibrary.db;

import static pl.spoldzielnia.mylibrary.db.MyLibDBTables.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ItemsDB {
	private SQLiteDatabase db;
	private final ItemsDBHelper dbHelper;

	public ItemsDB(Context context) {
		dbHelper = new ItemsDBHelper(context, MyLibDBTables.DB_NAME, null,
				MyLibDBTables.DB_VERSION);
	}

	public void open() {
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
		db.close();
	}

	public Cursor getAllItems() {
		Cursor c = db.rawQuery("SELECT * from " + ITEMS_TABLE_NAME + ";", null);
		return c;
	}
	
	public Cursor getAllAvailableItems() {
		return null;
	}
	
	public Cursor getAllBorrowedItems() {
		return null;
	}

	
	public long insertItem(String author, String title) {
		ContentValues value = new ContentValues();
		value.put(ITEM_NAME, author + " " + title);
		return db.insert(ITEMS_TABLE_NAME, null, value);
	}

	public int deleteItem() {
		return -1;
	}
}
