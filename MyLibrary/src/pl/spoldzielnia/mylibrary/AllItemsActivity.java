package pl.spoldzielnia.mylibrary;


import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

/**
 * The Class AllItemsActivity.
 */
public class AllItemsActivity extends AbstractListActivity {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AllItemsActivity.class);
	
	/** The Constant OBJECT_ID. */
	public static final String OBJECT_ID = "OBJECT_ID";
	
	/** The Constant EDIT_ITEM. */
	private static final int EDIT_ITEM = 0;
	
	/**
	 * Instantiates a new all items activity.
	 */
	public AllItemsActivity() {
		super(R.string.title_activity_all_items);
	}

	/* (non-Javadoc)
	 * @see pl.spoldzielnia.mylibrary.AbstractListActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_items);
		registerForContextMenu(getListView());
		LOG.debug("AttItems activity created.");
	}
	
	/* (non-Javadoc)
	 * @see pl.spoldzielnia.mylibrary.AbstractListActivity#onResume()
	 */
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

	/**
	 * Gets the items list.
	 *
	 * @return the items list
	 * @throws SQLException the sQL exception
	 */
	private void getItemsList() throws SQLException {
		Dao<Item, Integer> dao = getHelper().getDao(Item.class);
		List<Item> itemsList = dao.queryForAll();
		LOG.debug("Item list queried from DB. Number of items: " + itemsList.size());		
		setListAdapter(new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, itemsList));
	}
	

    /* (non-Javadoc)
     * @see com.actionbarsherlock.app.SherlockListActivity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add("Add new")
            .setIcon(R.drawable.ic_input_add)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        LOG.debug("AllItems menu created. " + menu.toString());
        
        return true;
    }
    
    /* (non-Javadoc)
     * @see com.actionbarsherlock.app.SherlockListActivity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Log.d(APP_TAG, "Menu item " + item.getTitle() + " selected.");
    	LOG.debug("Selected item: " + item.toString());
    	
    	if(item.getTitle().equals(getString(R.string.add_new_string))) {
    		startActivity(new Intent(getApplicationContext(), AddItemActivity.class));
    		LOG.debug("Add new item activity has started.");
    		return true;
    	}
    	// TODO: don't understand, why do we call here super method? 
    	return super.onOptionsItemSelected(item);
    }
    
    /* (non-Javadoc)
     * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu, android.view.View, android.view.ContextMenu.ContextMenuInfo)
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_contextual, menu);
    }
    
    /* (non-Javadoc)
     * @see android.app.Activity#onContextItemSelected(android.view.MenuItem)
     */
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
					LOG.info("Item: " + object.toString() + " deleted.");
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
				LOG.info("Item: " + object.toString() + " is going to be edited.");
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
    
    /* (non-Javadoc)
     * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
     */
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
