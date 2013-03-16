package pl.spoldzielnia.mylibrary.db;

import static pl.spoldzielnia.mylibrary.db.MyLibDBTables.ITEMS_TABLE_NAME;
import static pl.spoldzielnia.mylibrary.db.MyLibDBTables.ITEM_AUTHOR;
import static pl.spoldzielnia.mylibrary.db.MyLibDBTables.ITEM_CATEGORY;
import static pl.spoldzielnia.mylibrary.db.MyLibDBTables.ITEM_TITLE;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

	
	public void addItem(int category, String title, String author) {
		ContentValues values = new ContentValues();
		values.put(ITEM_AUTHOR, author);
		values.put(ITEM_TITLE, title);
		values.put(ITEM_CATEGORY, category);
		long res = db.insert(ITEMS_TABLE_NAME, null, values);
		
		if(res==-1) {
			Log.e("MyLibrary", "Insert item failed.");
		} else {
			Log.d("MyLibrary", "Insert item done. Row number: " + res);
		}
	}
	
	public List<Item> getItemsByCategory(int category) {
		String [] columns = {ITEM_AUTHOR, ITEM_TITLE, ITEM_CATEGORY};
		Cursor c = db.query(ITEMS_TABLE_NAME, columns, ITEM_CATEGORY + "=?",
				new String[] { Integer.toString(category) }, null, null, null);
		
		List<Item> items = new ArrayList<Item>();
		int size = c.getCount();
		Log.v("MyLibrary", "Number of queried items: " + size);
		if(size==0) {
			return null;
		}
		
		while(c.moveToNext()) {
			String author = c.getString(c.getColumnIndex(ITEM_AUTHOR));
			String title = c.getString(c.getColumnIndex(ITEM_TITLE));
			int item_category = c.getInt(c.getColumnIndex(ITEM_CATEGORY));
			Log.v("MyLibrary", "Author:" + author + ", title:" + title + ", category:" + item_category);
			items.add(new Item(author, title, item_category));
		}
		
		c.close();
		
		return items;
	}
	
}
