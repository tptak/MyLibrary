package pl.spoldzielnia.mylibrary;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

public class ActivityNavigator {
	private static Map<String, Class<?>> mapping = null;
	
	public static void init(Context context) {
		if(null==mapping) {
			mapping = new HashMap<String, Class<?>>();
			mapping.put(context.getString(R.string.title_activity_all_items), AllItemsActivity.class);
			mapping.put(context.getString(R.string.title_activity_main), MainActivity.class);
		}
	}
	
	public static Class<?> getMapping(String activityName) {
		return mapping.get(activityName);
	}

}
