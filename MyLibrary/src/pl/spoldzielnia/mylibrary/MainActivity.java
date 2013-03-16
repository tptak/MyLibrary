package pl.spoldzielnia.mylibrary;

import android.os.Bundle;

public class MainActivity extends AbstractActivity {
	
	public MainActivity() {
		super(R.string.title_activity_main);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
	}

}
