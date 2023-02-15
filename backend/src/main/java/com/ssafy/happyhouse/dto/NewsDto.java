package com.ssafy.happyhouse.dto;

public class NewsDto {
	
	private String photo;
	private String title;
	private String url;


	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
