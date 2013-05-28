package pl.spoldzielnia.mylibrary;

import android.os.Bundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.android.BasicLogcatConfigurator;

public class MainActivity extends AbstractListActivity {
	
	private Logger LOG = LoggerFactory.getLogger(MainActivity.class);
	
	public MainActivity() {
		super(R.string.title_activity_main);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		BasicLogcatConfigurator.configureDefaultContext();
		
		setContentView(R.layout.activity_main);
		LOG.info("Oh yeah! MyLibrary has started already.");
		
	}

}
