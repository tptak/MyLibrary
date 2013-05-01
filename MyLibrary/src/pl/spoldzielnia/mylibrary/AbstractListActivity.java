package pl.spoldzielnia.mylibrary;

import pl.spoldzielnia.mylibrary.db.ItemsDBHelper;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockListActivity;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

public abstract class AbstractListActivity extends SherlockListActivity implements OnNavigationListener, Constants {

	private volatile ItemsDBHelper helper;
	private volatile boolean created = false;
	private volatile boolean destroyed = false;
	
	protected int activityResourceId = 0;
	protected int activityListIndex = 0;
	protected String activityName;

	protected ArrayAdapter<CharSequence> list;
	
	public AbstractListActivity(int activityStringResourceId) {
		this.activityResourceId = activityStringResourceId;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (helper == null) {
			helper = getHelperInternal(this);
			created = true;
		}
		super.onCreate(savedInstanceState);
		overridePendingTransition(0, 0);
		
		activityName = getString(activityResourceId);


		Context context = getSupportActionBar().getThemedContext();
		
		ActivityNavigator.init(this);
		
		list = ArrayAdapter.createFromResource(context, R.array.locations, R.layout.sherlock_spinner_item);
		list.setDropDownViewResource(R.layout.sherlock_spinner_dropdown_item);
		
		activityListIndex = list.getPosition(activityName);
		
		// Here's temporary hiding of title bar. May need a rework as described in
		// http://stackoverflow.com/questions/9326337/android-3-2-remove-title-from-action-bar
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		getSupportActionBar().setListNavigationCallbacks(list, this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		getSupportActionBar().setSelectedNavigationItem(this.activityListIndex);
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		String activityName = list.getItem(itemPosition).toString();
		Log.d(APP_TAG, "Selected element: " + activityName);
		
		if(itemPosition != this.activityListIndex) {
			Log.d(APP_TAG, "Performing activity switch");
			
			Class<?> activityClass = ActivityNavigator.getMapping(activityName);
			
			if(null!=activityClass) {
				Intent myIntent = new Intent(this, activityClass );
	            startActivityForResult(myIntent, 0);
	            return true;
			} else {
				Log.w(APP_TAG, "Activity mapping not found: " + activityName);
				getSupportActionBar().setSelectedNavigationItem(this.activityListIndex);
			}
			
		}
		
		return false;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		releaseHelper(helper);
		destroyed = true;
	}

	/**
	 * Get a helper for this action.
	 */
	public ItemsDBHelper getHelper() {
		if (helper == null) {
			if (!created) {
				throw new IllegalStateException("A call has not been made to onCreate() yet so the helper is null");
			} else if (destroyed) {
				throw new IllegalStateException(
						"A call to onDestroy has already been made and the helper cannot be used after that point");
			} else {
				throw new IllegalStateException("Helper is null for some unknown reason");
			}
		} else {
			return helper;
		}
	}

	/**
	 * Get a connection source for this action.
	 */
	public ConnectionSource getConnectionSource() {
		return getHelper().getConnectionSource();
	}

	/**
	 * This is called internally by the class to populate the helper object instance. This should not be called directly
	 * by client code unless you know what you are doing. Use {@link #getHelper()} to get a helper instance. If you are
	 * managing your own helper creation, override this method to supply this activity with a helper instance.
	 * 
	 * <p>
	 * <b> NOTE: </b> If you override this method, you most likely will need to override the
	 * {@link #releaseHelper(OrmLiteSqliteOpenHelper)} method as well.
	 * </p>
	 */
	protected ItemsDBHelper getHelperInternal(Context context) {
		ItemsDBHelper newHelper = (ItemsDBHelper) OpenHelperManager.getHelper(context, ItemsDBHelper.class);
		return newHelper;
	}

	/**
	 * Release the helper instance created in {@link #getHelperInternal(Context)}. You most likely will not need to call
	 * this directly since {@link #onDestroy()} does it for you.
	 * 
	 * <p>
	 * <b> NOTE: </b> If you override this method, you most likely will need to override the
	 * {@link #getHelperInternal(Context)} method as well.
	 * </p>
	 */
	protected void releaseHelper(ItemsDBHelper helper) {
		OpenHelperManager.releaseHelper();
		this.helper = null;
	}
}
