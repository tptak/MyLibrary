package pl.spoldzielnia.mylibrary;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;

public class MyLibrary extends ListActivity {

	private static String[] menuOptions = { "Lent items", "People", "All items" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_library);

		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, menuOptions));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_library, menu);
		return true;
	}

}
