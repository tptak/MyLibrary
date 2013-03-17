package pl.spoldzielnia.mylibrary;

import static pl.spoldzielnia.mylibrary.db.MyLibDBTables.ITEM_AUTHOR;
import static pl.spoldzielnia.mylibrary.db.MyLibDBTables.ITEM_TITLE;
import pl.spoldzielnia.mylibrary.db.DBProvider;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;

public class AllItemsActivity extends AbstractListActivity {
	private static int id = 0;
	
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
		
		//TODO remove adding items here
		DBProvider.get().addItem(id, String.format("tytul %d", id), String.format("autor %d", id));
		id++;
		
		//TODO obtain the needed data, columns here are an example
		String [] columns = {ITEM_AUTHOR, ITEM_TITLE};
		
		//TODO use a nicer layout for list item (future)
		int [] ids = {android.R.id.text2, android.R.id.text1};
		setListAdapter(new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_2, DBProvider.get()
				.getAllItemsCursor(), columns, ids, 0));
	}
	
}
