package pl.spoldzielnia.mylibrary.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "items")
public class Item {

	@DatabaseField(generatedId = true, columnName = "item_id")
	private Long id;
	
	@DatabaseField(canBeNull = false, columnName = "author")
	private String author;
	
	@DatabaseField(canBeNull = false, columnName = "title")
	private String title;
	
	@DatabaseField(canBeNull = false, foreign = true, columnName = "category_id")
	private Category category;

	// FIXME nie ma added_date
	
	public Item() {
		super();
	}
	
	public Item(String author, String title, Category category) {
		this.author = author;
		this.title = title;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
