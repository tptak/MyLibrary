package pl.spoldzielnia.mylibrary.db;

public class DBConstants {

	public static final String DB_NAME = "mylibrarydb";
	public static final int DB_VERSION = 1; 

	// Borrower table
	public static final String BORROWER_TABLE_NAME = "borrowers";
	public static final String BORROWER_ID = "borrower_id";
	public static final String BORROWER_NAME = "name";
	public static final String BORROWER_PHONE_NUM = "phone_number";
	public static final String BORROWER_EMAIL = "email";
	public static final String BORROWER_ITEM_ID = "item_id";

	// Items table
	public static final String ITEM_TABLE_NAME = "items";
	public static final String ITEM_ID = "item_id";
	public static final String ITEM_NAME = "name";
	public static final String ITEM_ISSUE_DATE = "issue_date";
	public static final String ITEM_STATUS = "status";

	public static final String CREATE_ITEMS_TABLE =
			"CREATE " + ITEM_TABLE_NAME + " ("
			+ ITEM_ID + " INTEGER PRIMARY KEY,"
			+ ITEM_NAME + " text,"
			+ ITEM_ISSUE_DATE + " INTEGER,"
			+ ITEM_STATUS + "INETEGR);";
	
	public static final String CREATE_BORROWERS_TABLE =
			"CREATE " + BORROWER_TABLE_NAME + " (" 
			+ BORROWER_ID + " INTEGER PRIMARY KEY,"
			+ BORROWER_NAME + " TEXT,"
			+ BORROWER_PHONE_NUM + " TEXT,"
			+ BORROWER_EMAIL + " TEXT"
			+ BORROWER_ITEM_ID + " INTEGER REFERENCES " + ITEM_TABLE_NAME + " (" + ITEM_ID + ");";

}