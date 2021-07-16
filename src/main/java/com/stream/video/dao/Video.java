package com.stream.video.dao;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.core.*;

@Entity
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String title;
	
	@Column
	private String description;
    
	@OneToMany(mappedBy="video",cascade=CascadeType.ALL)
    private List<VideoGenre> genres;

	public Video() {
	}

	public Video(Video v) {
		this.id=v.id;
		this.title=v.title;
		this.description=v.description;
		v.getGenres().forEach(x-> {
			VideoGenre t = new VideoGenre(x);
			this.getGenres().add(t);
		});
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

	public List<VideoGenre> getGenres() {
		return genres;
	}

	public void setGenres(List<VideoGenre> genres) {
		this.genres = genres;
	}

	@Override
	public String toString() {
		JsonFactory factory = new JsonFactory();
		StringWriter jsonObjectWriter = new StringWriter();
		try {
			JsonGenerator generator = factory.createGenerator(jsonObjectWriter);
			generator.useDefaultPrettyPrinter();
			generator.writeStartObject();
			generator.writeFieldName("id");
			generator.writeString(Integer.toString(id));
			generator.writeFieldName("title");
			generator.writeString(this.title);
			generator.writeFieldName("description");
			generator.writeString(this.description);

			generator.writeFieldName("genres");
			generator.writeStartArray();
			this.genres.forEach(x -> {
				try {
					generator.writeStartObject();
					generator.writeFieldName("id");
					generator.writeString(Integer.toString(x.getId()));
					generator.writeFieldName("title");
					generator.writeString(x.getTitle());
					generator.writeEndObject();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			generator.writeEndArray();

			generator.writeEndObject();
			generator.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonObjectWriter.toString();
	}
}
