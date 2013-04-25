package pl.spoldzielnia.mylibrary.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Types")
public class Category {
	@DatabaseField(generatedId = true, columnName = "type_id")
	private long id;
	
	@DatabaseField(canBeNull = false, columnName = "type_name")
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
