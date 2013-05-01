package pl.spoldzielnia.mylibrary.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class ItemsDBHelper extends OrmLiteSqliteOpenHelper {

	// name of the database file for your application -- change to something appropriate for your app
	private static final String DATABASE_NAME = "my_library.db";
	// any time you make changes to your database objects, you may have to increase the database version
	private static final int DATABASE_VERSION = 1;

	public ItemsDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

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
			throw new RuntimeException(e);
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		try {
			Log.i(ItemsDBHelper.class.getName(), "onUpgrade");
			TableUtils.dropTable(connectionSource, Item.class, true);
			TableUtils.dropTable(connectionSource, Category.class, true);
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(ItemsDBHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}

	}

}
