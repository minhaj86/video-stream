package com.stream.video.payload;

import java.util.ArrayList;
import java.util.List;

public class VideoPayload {
	private int id;
	private String title;
	private String description;
	private List<String> genres = null;
	private List<GenrePayload> temp = null;

	public VideoPayload() {
		this.temp = new ArrayList<GenrePayload>();
		this.genres = new ArrayList<String>();
		this.title = "";
		this.description = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public void addGenre(String genre) {
		if (this.genres == null)
			this.genres = new ArrayList<String>();
		this.genres.add(genre);
	}

	public List<GenrePayload> getTemp() {
		return temp;
	}

	public void setTemp(List<GenrePayload> temp) {
		this.temp = temp;
	}

	public void addTemp(GenrePayload temp) {
		if (this.temp == null)
			this.temp = new ArrayList<GenrePayload>();
		this.temp.add(temp);
	}
}
