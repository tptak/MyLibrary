package pl.spoldzielnia.mylibrary;


import java.sql.SQLException;
import java.util.List;

import pl.spoldzielnia.mylibrary.db.Item;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.j256.ormlite.dao.Dao;

public class AllItemsActivity extends AbstractListActivity {
	
	public AllItemsActivity() {
		super(R.string.title_activity_all_items);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_items);
		registerForContextMenu(getListView());
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		//FIXME move below line to a better place
		getHelper().getWritableDatabase();
		try {
			Dao<Item, Integer> dao = getHelper().getDao(Item.class);
			
			List<Item> itemsList = dao.queryForAll();
			
			setListAdapter(new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, itemsList));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_contextual, menu);
    }
    
    @Override
    public boolean onContextItemSelected(android.view.MenuItem item) {
    	// TODO Auto-generated method stub
    	return super.onContextItemSelected(item);
    }
    
	
}
