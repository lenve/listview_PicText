package com.example.listview;

public class News {

	private String imageUrl;
	private String title;
	private String summary;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public News(String imageUrl, String title, String summary) {
		this.imageUrl = imageUrl;
		this.title = title;
		this.summary = summary;
	}

	public News() {
	}
}
