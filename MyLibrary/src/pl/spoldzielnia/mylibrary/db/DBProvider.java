package pl.spoldzielnia.mylibrary.db;

import android.content.Context;

public class DBProvider {
	private static ItemsDB db;
	
	public static void createDb(Context cx) {
		db = new ItemsDB(cx);
	}
	
	public static ItemsDB get() { 
		return db;
	}
}
