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

	// Items table
	public static final String ITEM_TABLE_NAME = "items";
	public static final String ITEM_ID = "item_id";
	public static final String ITEM_AUTHOR_NAME = "author_name";
	public static final String ITEM_TITLE = "title";	
	public static final String ITEM_STATUS = "status";

	//Issue table
	public static final String ISSUE_TABLE_NAME = "issues";
	public static final String ISSUE_ID = "issue_id";		
	public static final String ISSUE_ITEM_ID = "item_id";
	public static final String ISSUE_DATE = "issue_date";
	public static final String ISSUE_BORROWER_ID = "borrower_id";

	
	public static final String CREATE_ITEMS_TABLE =
			"CREATE TABLE " + ITEM_TABLE_NAME + " ("
			+ ITEM_ID + " INTEGER PRIMARY KEY,"
			+ ITEM_AUTHOR_NAME + " TEXT NOT NULL,"
			+ ITEM_TITLE + " TEXT NOT NULL,"
			+ ITEM_STATUS + " INTEGER);";	
	
	public static final String CREATE_BORROWERS_TABLE =
			"CREATE TABLE " + BORROWER_TABLE_NAME + " (" 
			+ BORROWER_ID + " INTEGER PRIMARY KEY,"
			+ BORROWER_NAME + " TEXT,"
			+ BORROWER_PHONE_NUM + " TEXT,"
			+ BORROWER_EMAIL + " TEXT;";

	public static final String CREATE_ISSUES_TABLE = "CREATE TABLE "
			+ ISSUE_TABLE_NAME + " (" + ISSUE_ID + " INTEGER PRIMARY KEY,"
			+ ISSUE_DATE + " TIMESTAMP," + ISSUE_ITEM_ID
			+ " INTEGER REFERENCES " + ITEM_TABLE_NAME + " (" + ITEM_ID + ")),"
			+ ISSUE_BORROWER_ID + " INTEGER REFERENCES " + BORROWER_TABLE_NAME
			+ " (" + BORROWER_ID + "))";
	
}
