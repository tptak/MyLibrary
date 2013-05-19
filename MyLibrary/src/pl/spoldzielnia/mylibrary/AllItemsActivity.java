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
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.j256.ormlite.dao.Dao;

public class AllItemsActivity extends AbstractListActivity {
	
	public static final String OBJECT_ID = "OBJECT_ID";
	private static final int EDIT_ITEM = 0;
	
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
			getItemsList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getItemsList() throws SQLException {
		Dao<Item, Integer> dao = getHelper().getDao(Item.class);
		
		List<Item> itemsList = dao.queryForAll();
		
		setListAdapter(new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, itemsList));
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
    	boolean result = false;
    	AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
    	Item object = (Item) getListAdapter().getItem(menuInfo.position);
    	switch(item.getItemId()) {
	    	case R.id.item_delete:
				try {
					Dao<Item, ?> dao = getHelper().getDao(Item.class);
					
					result = dao.delete(object) == 1;
					if (result) {
						getItemsList();
					}
				} catch (SQLException e) {
					e.printStackTrace();
					result = false;
				}
				break;
	    	case R.id.item_edit:
				Intent intent = new Intent(getApplicationContext(), AddItemActivity.class);
				intent.putExtra(OBJECT_ID, object.getId());
				startActivityForResult(intent, EDIT_ITEM);
				result = true;
	    		break;
	    	case R.id.item_view:
				// TODO
	    		break;
	    	default:
	    		result = super.onContextItemSelected(item);
    	}
    	return result;
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	switch(requestCode) {
    	case EDIT_ITEM:
    		if(RESULT_OK==resultCode) {
				try {
					getItemsList();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		break;
    	}
    }
	
}
