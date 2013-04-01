package pl.spoldzielnia.mylibrary;

import static pl.spoldzielnia.mylibrary.db.MyLibDBTables.ITEM_AUTHOR;
import static pl.spoldzielnia.mylibrary.db.MyLibDBTables.ITEM_TITLE;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import pl.spoldzielnia.mylibrary.db.DBProvider;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;

public class AllItemsActivity extends AbstractListActivity {
	
	public AllItemsActivity() {
		super(R.string.title_activity_all_items);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_items);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		//TODO obtain the needed data, columns here are an example
		String [] columns = {ITEM_AUTHOR, ITEM_TITLE};
		
		//TODO use a nicer layout for list item (future)
		int [] ids = {android.R.id.text2, android.R.id.text1};
		setListAdapter(new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_2, DBProvider.get()
				.getAllItemsCursor(), columns, ids, 0));
	}
	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add("Add new")
            .setIcon(R.drawable.ic_input_add)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Log.d(APP_TAG, "Menu item " + item.getTitle() + " selected.");
    	
    	if(item.getTitle().equals(getString(R.string.add_new_string))) {
    		
    		startActivity(new Intent(getApplicationContext(), AddItemActivity.class));
    		
    		return true;
    	}
    	return super.onOptionsItemSelected(item);
    }
    
	
}
