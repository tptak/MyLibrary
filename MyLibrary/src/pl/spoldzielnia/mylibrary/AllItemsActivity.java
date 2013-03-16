package pl.spoldzielnia.mylibrary;

import android.os.Bundle;

public class AllItemsActivity extends AbstractListActivity {
	
	public AllItemsActivity() {
		super(R.string.title_activity_all_items);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_items);
	}
}
