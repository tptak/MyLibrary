package pl.spoldzielnia.mylibrary.db;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.spoldzielnia.mylibrary.MainActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * The Class ItemsDBHelper.
 */
public class ItemsDBHelper extends OrmLiteSqliteOpenHelper {

	/** The log. */
	private Logger LOG = LoggerFactory.getLogger(ItemsDBHelper.class);
	
	// name of the database file for your application -- change to something appropriate for your app
	/** The Constant DATABASE_NAME. */
	private static final String DATABASE_NAME = "my_library.db";
	// any time you make changes to your database objects, you may have to increase the database version
	/** The Constant DATABASE_VERSION. */
	private static final int DATABASE_VERSION = 1;

	/**
	 * Instantiates a new items db helper.
	 *
	 * @param context the context
	 */
	public ItemsDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/* (non-Javadoc)
	 * @see com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase, com.j256.ormlite.support.ConnectionSource)
	 */
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(ItemsDBHelper.class.getName(), "onCreate");
			TableUtils.createTable(connectionSource, Category.class);
			TableUtils.createTable(connectionSource, Item.class);
			Dao<Category, Integer> dao = getDao(Category.class);
			Category category;
			category = new Category();
			category.setName("Book");
			dao.createIfNotExists(category);
			category = new Category();
			category.setName("Video");
			dao.createIfNotExists(category);
			category = new Category();
			category.setName("Music");
			dao.createIfNotExists(category);
		} catch (SQLException e) {
			Log.e(ItemsDBHelper.class.getName(), "Can't create database", e);
			LOG.error("DB helper onCreate method failed.", e);
			throw new RuntimeException(e);
		}

	}

	/* (non-Javadoc)
	 * @see com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, com.j256.ormlite.support.ConnectionSource, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		try {
			LOG.info("DB upgrade. db.path=" + db.getPath() + ", db.version=" + db.getVersion());
			Log.i(ItemsDBHelper.class.getName(), "onUpgrade");
			TableUtils.dropTable(connectionSource, Item.class, true);
			TableUtils.dropTable(connectionSource, Category.class, true);
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			// TODO: is it safe to call db.getXXX in try-catch statement?
			LOG.error("DB upgrade failed. db.path=" + db.getPath() + ", db.version=" + db.getVersion(), e);
			Log.e(ItemsDBHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}

	}

}
