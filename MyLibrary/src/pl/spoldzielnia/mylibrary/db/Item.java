package pl.spoldzielnia.mylibrary.db;

public class Item {

	private String author;
	private String title;
	private int catrgory;
	
	public Item(String author, String title, int category) {
		this.author = author;
		this.title = title;
		this.catrgory = category;
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
	public int getCatrgory() {
		return catrgory;
	}
	public void setCatrgory(int catrgory) {
		this.catrgory = catrgory;
	}
	
	
}
