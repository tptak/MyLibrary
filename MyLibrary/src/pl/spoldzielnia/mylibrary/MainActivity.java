package pl.spoldzielnia.mylibrary;

import pl.spoldzielnia.mylibrary.db.DBProvider;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;

public class MainActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Initialize if needed and open database
		DBProvider.createDb(this);
		DBProvider.get().open();
		
		setContentView(R.layout.activity_main);
	}

}
