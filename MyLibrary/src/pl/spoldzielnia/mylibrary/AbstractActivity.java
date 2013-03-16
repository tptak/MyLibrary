package pl.spoldzielnia.mylibrary;

import pl.spoldzielnia.mylibrary.db.DBProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockActivity;

public abstract class AbstractActivity extends SherlockActivity implements OnNavigationListener {
	protected static final String APP_TAG = "MyLibrary";
	protected int activityResourceId = 0;
	protected int activityListIndex = 0;
	protected String activityName;

	protected ArrayAdapter<CharSequence> list;
	
	public AbstractActivity(int activityStringResourceId) {
		this.activityResourceId = activityStringResourceId;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
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

		// Initialize if needed and open database
		DBProvider.createDb(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		DBProvider.get().open();
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
	protected void onPause() {
		super.onPause();
		DBProvider.get().close();
	}
	
}
