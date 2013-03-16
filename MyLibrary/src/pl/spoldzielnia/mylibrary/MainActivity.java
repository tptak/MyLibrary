package pl.spoldzielnia.mylibrary;

import java.util.List;

import pl.spoldzielnia.mylibrary.db.DBProvider;
import pl.spoldzielnia.mylibrary.db.Item;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockActivity;

public class MainActivity extends SherlockActivity implements
		OnNavigationListener {
	// private String[] mLocations;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		super.onCreate(savedInstanceState);

		// Initialize if needed and open database
		DBProvider.createDb(this);
		DBProvider.get().open();

		setContentView(R.layout.activity_main);

		// Here's temporary hiding of title bar. May need a rework as described
		// in
		// http://stackoverflow.com/questions/9326337/android-3-2-remove-title-from-action-bar

		// mLocations = getResources().getStringArray(R.array.locations);

		Context context = getSupportActionBar().getThemedContext();
		ArrayAdapter<CharSequence> list = ArrayAdapter.createFromResource(
				context, R.array.locations, R.layout.sherlock_spinner_item);
		list.setDropDownViewResource(R.layout.sherlock_spinner_dropdown_item);

		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		getSupportActionBar().setListNavigationCallbacks(list, this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		DBProvider.get().close();
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Real code comes here
		Log.v("MyLibrary", "onNavigationItemSelected + pos:" + itemPosition
				+ ", id:" + itemId);
		if (itemPosition == 1) {
			DBProvider.get().addItem(1, "Alien", "R. Scott");
			DBProvider.get().addItem(2, "Load", "Metallica");
			DBProvider.get().addItem(1, "Forrest Gump", "Jakis rezyser");
			DBProvider.get().addItem(2, "Load II", "Metallica");
			DBProvider.get().addItem(2, "Ikikiki", "lokolo");
		}
		if (itemPosition == 2) {
			List<Item> items1 = DBProvider.get().getItemsByCategory(1);
			List<Item> items2 = DBProvider.get().getItemsByCategory(2);
			for (Item i : items1) {
				Log.v("MyLibrary",
						"Type:" + i.getCatrgory() + ", author: "
								+ i.getAuthor() + ", title: " + i.getTitle());
			}
			
			for (Item i : items2) {
				Log.v("MyLibrary",
						"Type:" + i.getCatrgory() + ", author: "
								+ i.getAuthor() + ", title: " + i.getTitle());
			}
		}
		return false;
	}

}
