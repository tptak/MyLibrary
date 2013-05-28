package pl.spoldzielnia.mylibrary;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.spoldzielnia.mylibrary.db.Category;
import pl.spoldzielnia.mylibrary.db.Item;
import pl.spoldzielnia.mylibrary.db.ItemsDBHelper;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;

public class AddItemActivity extends OrmLiteBaseActivity<ItemsDBHelper>
		implements Constants, OnClickListener {

	private final static Logger LOG = LoggerFactory.getLogger(AddItemActivity.class);
	public static final String OBJECT_ID = "OBJECT_ID";
	private Spinner categorySpinner;
	private Button saveButton;
	private EditText autorField;
	private EditText titleField;
	private Item instance = null;
	private List<Category> categories;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item);

		setupActionBar();

		autorField = (EditText) findViewById(R.id.author_field);
		titleField = (EditText) findViewById(R.id.title_field);

		categorySpinner = (Spinner) findViewById(R.id.category_field);

		saveButton = (Button) findViewById(R.id.save_button);

		saveButton.setOnClickListener(this);
		LOG.info("Activity created succesfully.");
	}

	@Override
	protected void onResume() {
		super.onResume();
		fillSpinnerFields();
		fillObjectFields();
	}

	private void fillObjectFields() {
		LOG.debug("Fill object fields.");
		Intent intent = getIntent();
		if (intent.hasExtra(OBJECT_ID)) {
			int objectId = (int) (intent.getLongExtra(OBJECT_ID, -1) & 0xffffffff);
			try {
				Dao<Item, Integer> dao = getHelper().getDao(Item.class);
				instance = dao.queryForId(objectId);
				autorField.setText(instance.getAuthor());
				titleField.setText(instance.getTitle());
				int categoryIndex = categories.indexOf(instance.getCategory());
				LOG.debug("Queried data. Author: " + instance.getAuthor()
						+ ", Title: " + instance.getTitle() + ", categoryIndex: " + categoryIndex);
				if (categoryIndex == -1) {
					LOG.debug("New category, will be added to spinner.");
					categoryIndex = 0;
				}
				categorySpinner.setSelection(categoryIndex);

			} catch (SQLException e) {
				e.printStackTrace();
				LOG.error("Database query failed.", e);
				// TODO: handle exception
			}
		}
	}

	/**
	 * Fills the spinner with categories
	 */
	private void fillSpinnerFields() {
		LOG.debug("Fill spinner fields.");
		try {
			categories = getHelper().getDao(Category.class).queryForAll();
			LOG.debug("Queried categories: " + categories.toString());
			ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(this,
					android.R.layout.simple_spinner_item, categories);
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
		// Do nothing here right now, add something if needed
	}

	@Override
	public void onClick(View arg0) {
		Log.d(APP_TAG, "Save item triggerred");
		
		if (null==instance) {
			instance = new Item();
		}
		instance.setAuthor(autorField.getText().toString());
		instance.setTitle(titleField.getText().toString());
		instance.setCategory((Category) categorySpinner.getSelectedItem());
		LOG.debug("Item is saving: " + instance.toString());
		try {
			getHelper().getDao(Item.class).createOrUpdate(instance);
			// TODO ignoruje czy nastapila zmiana czy nie. Moze nie powinienem.
			
			getIntent().putExtra(OBJECT_ID, instance.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finish();
	}

}
