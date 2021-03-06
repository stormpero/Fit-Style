package ru.project.fitstyle.model.entity.news;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "header", length = 50,
            nullable = false)
    private String header;

    @Column(name = "content", length = 1500,
            nullable = false)
    private String content;

    //TODO Make another Timestamp for last news update??

    //TODO Make date with @CreatedDate annotation
    //@CreatedDate
    @Column(name = "date_time",
            nullable = false)
    private Date dateTime;


    @Column(name = "img_URL", length = 100)
    private String imgURL;

    public News() {
    }

    public News(String header, String content, Date dateTime, String imgURL) {
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}