package pl.spoldzielnia.mylibrary;

import static pl.spoldzielnia.mylibrary.db.MyLibDBTables.TYPE_NAME;
import pl.spoldzielnia.mylibrary.db.DBProvider;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.actionbarsherlock.app.SherlockActivity;

public class AddItemActivity extends SherlockActivity implements Constants {

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
		DBProvider.get().open();
		fillSpinnerFields();
	}
	
	

	/**
	 * Fills the spinner with categories
	 */
	private void fillSpinnerFields() {
		String [] columns = {TYPE_NAME};
		
		int [] ids = {android.R.id.text1};
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, DBProvider.get().getCategoriesCursor(), columns, ids, 0);
		categorySpinner.setAdapter(adapter);
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
			
			// TODO I only get the ID from the list. It may be wrong. I don't care right now.
			DBProvider.get().addItem((int) categoryField.getSelectedItemId(), titleField.getText().toString(), autorField.getText().toString());
			finish();
		}
		
	}

}
