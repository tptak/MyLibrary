package pl.spoldzielnia.mylibrary.db;

import static pl.spoldzielnia.mylibrary.db.DBConstants.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ItemsDB {
	private SQLiteDatabase db;
	private final ItemsDBHelper dbHelper;

	public ItemsDB(Context context) {
		dbHelper = new ItemsDBHelper(context, DBConstants.DB_NAME, null,
				DBConstants.DB_VERSION);
	}

	public void open() {
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
		db.close();
	}

	public Cursor getAllItems() {
		Cursor c = db.rawQuery("SELECT * from items;", null);
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
		value.put(ITEM_AUTHOR_NAME, author);
		value.put(ITEM_TITLE, title);
		return db.insert(ITEM_TABLE_NAME, null, value);
	}
	
	public long insertItem() {

		ContentValues value = new ContentValues();
		//value.put(ITEM_ID, 1);
		value.put(ITEM_AUTHOR_NAME, "Hugh Grant");
		value.put(ITEM_TITLE, "Chata wuja Toma");
		value.put(ITEM_STATUS, 1);
		return db.insert(ITEM_TABLE_NAME, null, value);
	}
	
	public long insertItem2() {

		ContentValues value = new ContentValues();
		//value.put(ITEM_ID, 1);
		value.put(ITEM_AUTHOR_NAME, "Tomek koloz");
		value.put(ITEM_TITLE, "Ble ble ble");
		value.put(ITEM_STATUS, 1);
		return db.insert(ITEM_TABLE_NAME, null, value);
	}

	public int deleteItem() {
		return -1;
	}
}
