package pl.spoldzielnia.mylibrary;

import pl.spoldzielnia.mylibrary.db.ItemsDB;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyLibrary extends ListActivity {

	private static String[] menuOptions = { "Lent items", "People", "All items", "Insert" };

	private ItemsDB db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		db = new ItemsDB(this);
		db.open();
		
		setContentView(R.layout.activity_my_library);

		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, menuOptions));

		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					Log.v("#### MyLibrary", "Lent items");
					break;
				case 1:
					Log.v("#### MyLibrary", "Peaple");
					db.insertItem();
					break;
				case 2:
					Log.v("#### MyLibrary", "All Items");
					
					Cursor c = db.getAllItems();
					startManagingCursor(c);
					StringBuilder sb = new StringBuilder();
					if(c.moveToFirst()) {
						do {
							sb.append("id: ");
							sb.append(c.getString(0));
							sb.append(", name: ");
							sb.append(c.getString(1));
							sb.append(", issue date: ");
							sb.append(c.getString(2));
							sb.append(", status: ");
							sb.append(c.getString(3));
							sb.append("\n");
						} while(c.moveToNext());
					}
					Log.v("##### MyLibrary",sb.toString());
					break;
				default:
					break;
				}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_library, menu);
		return true;
	}

}
