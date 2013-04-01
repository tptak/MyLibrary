package pl.spoldzielnia.mylibrary.db;

import android.database.sqlite.SQLiteDatabase;

public class MyLibDBTables {

	public static final String DB_NAME = "mylibrarydb";
	public static final int DB_VERSION = 1; 
	public static final String TABLE_PRIMARY_KEY_TYPE = " INTEGER PRIMARY KEY ";
	public static final String TABLE_REFERENCE_KEY_TYPE = " INTEGER REFERENCES ";

	// Types of items table
	public static final String TYPES_TABLE_NAME = "Types";
	public static final String TYPE_ID = "type_id"; 
	public static final String TYPE_NAME = "type_name"; 
	
	// Items table
	public static final String ITEMS_TABLE_NAME = "items";
	public static final String ITEM_ID = "item_id";
	public static final String ITEM_TITLE = "title";
	public static final String ITEM_AUTHOR = "author";
	public static final String ITEM_ADDED_DATE = "added_date";	
	public static final String ITEM_CATEGORY = "category";
	
	// Items Rentals table
	public static final String ITEMS_RENTALS_TABLE_NAME = "Items_Rentals";
	
	// Rentals table
	public static final String RENTALS_TABLE_NAME = "Rentals";
	public static final String RENTAL_ID = "rental_id";
	public static final String RENTAL_DATE = "rental_date";
	public static final String RENTAL_END_DATE = "end_date"; 
	
	// People table
	public static final String PEOPLE_TABLE_NAME = "People";
	public static final String PERSON_ID = "person_id";
	public static final String PERSON_NAME = "person_name";
	public static final String PERSON_PHONE_NUM = "phone_number";
	public static final String PERSON_EMAIL = "email";

	
	// Reminders
	public static final String REMINDERS_TABLE_NAME = "Reminders";
	public static final String REMINDER_ID = "reminder_id";
	public static final String REMINDER_TIME_AFTER = "time_after";
	public static final String REMINDER_DISSMISS = "dissmiss";

	
	public static final String CREATE_TYPES_TABLE =
			"CREATE TABLE " + TYPES_TABLE_NAME + " (" +
			TYPE_ID + TABLE_PRIMARY_KEY_TYPE + ", " +
			TYPE_NAME + " TEXT);";
	
	public static final String CREATE_ITEMS_TABLE =
			"CREATE TABLE " + ITEMS_TABLE_NAME + " (" +
			ITEM_ID + TABLE_PRIMARY_KEY_TYPE + ", " +
			ITEM_AUTHOR + " TEXT, " +
			ITEM_TITLE + " TEXT, " +
			ITEM_ADDED_DATE + " DATETIME, " +
			ITEM_CATEGORY + TABLE_REFERENCE_KEY_TYPE + TYPES_TABLE_NAME + " );";
	
	public static final String CREATE_ITEMS_RENTALS_TABLE =
			"CREATE TABLE " + ITEMS_RENTALS_TABLE_NAME + " (" +
			ITEM_ID + TABLE_REFERENCE_KEY_TYPE + ITEMS_TABLE_NAME + ", " +
			RENTAL_ID + TABLE_REFERENCE_KEY_TYPE + RENTALS_TABLE_NAME + ");";
	
	public static final String CREATE_PEOPLE_TABLE =
			"CREATE TABLE " + PEOPLE_TABLE_NAME + " (" +
			PERSON_ID + TABLE_PRIMARY_KEY_TYPE + ", " +
			PERSON_NAME + " TEXT," +
			PERSON_PHONE_NUM + " TEXT," +
			PERSON_EMAIL + " TEXT);";
	
	public static final String CREATE_RENTALS_TABLE =
			"CREATE TABLE " + RENTALS_TABLE_NAME + " (" +
			RENTAL_ID + TABLE_PRIMARY_KEY_TYPE + ", " +
			PERSON_ID + TABLE_REFERENCE_KEY_TYPE + PEOPLE_TABLE_NAME + ", " +
			RENTAL_DATE + " DATETIME, " +
			RENTAL_END_DATE + " DATETIME );";
	
	
	public static final String CREATE_REMINDERS_TABLE =
			"CREATE TABLE " + REMINDERS_TABLE_NAME + " ( " +
			REMINDER_ID + TABLE_PRIMARY_KEY_TYPE + ", " +
			RENTAL_ID + TABLE_REFERENCE_KEY_TYPE + RENTALS_TABLE_NAME + ", " +
			REMINDER_TIME_AFTER + " DATETIME, " +
			REMINDER_DISSMISS + " BOOLEAN);";
	
	public static final String PREPOPULATE_TYPES = 
			"INSERT INTO '" + TYPES_TABLE_NAME +	"'" +
			" SELECT 1 AS '" + TYPE_ID + "', 'BOOK' AS '" + TYPE_NAME + "'" + 
			" UNION SELECT 2, 'VIDEO'" +
			" UNION SELECT 3, 'MUSIC'" +
			" UNION SELECT 4, 'OTHER';";

	  public static void onCreate(SQLiteDatabase database) {
	    database.execSQL(CREATE_TYPES_TABLE);
	    database.execSQL(CREATE_ITEMS_TABLE);
	    database.execSQL(CREATE_PEOPLE_TABLE);
	    database.execSQL(CREATE_REMINDERS_TABLE);
	    database.execSQL(CREATE_ITEMS_RENTALS_TABLE);
	    database.execSQL(CREATE_RENTALS_TABLE);
	    database.execSQL(PREPOPULATE_TYPES);
	  }

	  public static void onUpgrade(SQLiteDatabase database, int oldVersion,
	      int newVersion) {
	    //database.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
	    onCreate(database);
	  }
	
}
