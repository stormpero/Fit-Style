package ru.project.fitstyle.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "news")
public class News {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "header should not be blank")
	@Size(max = 50, message = "header size should be less or equal than 50 chars")
	private String header;

	@NotBlank(message = "content should not be blank")
	@Size(max = 500, message = "content size should be less or equal then 500 chars")
	private String content;

	//TODO Make another Timestamp for last news update??
	private Timestamp dateTime;

	@NotBlank(message = "imgUrl should not be blank")
	@Size(max = 100, message = "imgUrl should be less or equal than 100 chars")
	private String imgURL;

	public News() {

	}

	public News(String header, String content, Timestamp dateTime, String imgURL) {
		this.header = header;
		this.content = content;
		this.dateTime=dateTime;
		this.imgURL=imgURL;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public java.sql.Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(java.sql.Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

}