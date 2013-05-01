package pl.spoldzielnia.mylibrary;

import java.sql.SQLException;

import pl.spoldzielnia.mylibrary.db.Category;
import pl.spoldzielnia.mylibrary.db.Item;
import pl.spoldzielnia.mylibrary.db.ItemsDBHelper;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

public class AddItemActivity extends OrmLiteBaseActivity<ItemsDBHelper> implements Constants {

	private Spinner categorySpinner;
	private Button saveButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item);

		setupActionBar();
		
		categorySpinner = (Spinner) findViewById(R.id.category_field);
		
		saveButton = (Button) findViewById(R.id.save_button);
		
		saveButton.setOnClickListener(new SaveItemListener());
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		fillSpinnerFields();
	}
	
	

	/**
	 * Fills the spinner with categories
	 */
	private void fillSpinnerFields() {
		try {
			ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(this, android.R.layout.simple_spinner_item, getHelper().getDao(Category.class).queryForAll());
			categorySpinner.setAdapter(adapter);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * sets up the action bar (whatever's needed in it)
	 */
	private void setupActionBar() {
		//Do nothing here right now, add something if needed
	}
	
	public class SaveItemListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			Log.d(APP_TAG, "Save item triggerred");
			EditText autorField = (EditText) findViewById(R.id.author_field);
			EditText titleField = (EditText) findViewById(R.id.title_field);
			Spinner categoryField = (Spinner) findViewById(R.id.category_field);
			
			
			Item item = new Item();
			item.setAuthor(autorField.getText().toString());
			item.setTitle(titleField.getText().toString());
			item.setCategory((Category) categoryField.getSelectedItem());
			try {
				getHelper().getDao(Item.class).create(item);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			finish();
		}
		
	}

}
