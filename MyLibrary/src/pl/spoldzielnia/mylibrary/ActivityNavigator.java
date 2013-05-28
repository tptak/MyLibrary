package pl.spoldzielnia.mylibrary;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.Context;

public class ActivityNavigator {
	private final static Logger LOG = LoggerFactory.getLogger(ActivityNavigator.class);
	private static Map<String, Class<?>> mapping = null;
	
	public static void init(Context context) {
		LOG.info("Initialize activity navogator");
		if(null==mapping) {
			mapping = new HashMap<String, Class<?>>();
			mapping.put(context.getString(R.string.title_activity_all_items), AllItemsActivity.class);
			mapping.put(context.getString(R.string.title_activity_main), MainActivity.class);
			LOG.info("Added activites: " + AllItemsActivity.class.getSimpleName() + MainActivity.class.getSimpleName());
		}
	}
	
	public static Class<?> getMapping(String activityName) {
		return mapping.get(activityName);
	}

}
