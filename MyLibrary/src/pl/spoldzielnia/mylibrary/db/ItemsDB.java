package pl.spoldzielnia.mylibrary.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ItemsDB {
	private SQLiteDatabase db;
	private final Context context;
	private final ItemsDBHelper dbHelper;

	public ItemsDB(Context context) {
		this.context = context;
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
		Cursor c = db.query("itemsdb", null, null, null, null, null, null);
		return c;
	}

	public long insertItem() {

		ContentValues value = new ContentValues();
		value.put("name", "Tomek Kozlowski");
		value.put("phone_num", "123456");
		value.put("email", "riczi@ricz-art.org");
		value.put("book_title", "Takie tam");

		return db.insert("itemsdb", null, value);
	}

	public int deleteItem() {
		return -1;
	}
}
