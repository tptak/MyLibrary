package pl.spoldzielnia.mylibrary;

import pl.spoldzielnia.mylibrary.db.DBProvider;
import pl.spoldzielnia.mylibrary.db.ItemsDB;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItem extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item);
		Button addItem = (Button) findViewById(R.id.addButton);
		
		addItem.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				EditText author = (EditText)findViewById(R.id.editAutorName);
				EditText title = (EditText)findViewById(R.id.editTitle);
				Log.v("MyLibrary", "Adding item: Author: " + author.getText().toString() + ", Title: " + title.getText().toString());
				ItemsDB db = DBProvider.get();
				db.insertItem(author.getText().toString(), title.getText().toString());				
			}
		});
	}

}
