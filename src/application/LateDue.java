package application;

import java.time.LocalDate;

public class LateDue {
	
	private String id;
	private String name;
	private String book;
	private LocalDate due;
	private long lateDue;

	LateDue(String id, String name, String book, LocalDate due){
		this.id = id;
		this.name = name;
		this.book = book;
		this.due = due;
		this.lateDue = 0;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getBook() {
		return book;
	}
	
	public LocalDate getDue() {
		return due;
	}
	
	public long getLateDue() {
		return lateDue;
	}
	
	public void setlateDue(long daysBetween) {
		lateDue = daysBetween;
	}
	
}
