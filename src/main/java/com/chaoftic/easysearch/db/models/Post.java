package com.chaoftic.easysearch.db.models;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Post extends BaseEntity {
    private int id;
    private Date date;
    private String content;
    private Blogger bloggerByBloggerId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "content", length = 9999)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id &&
                Objects.equals(date, post.date) &&
                Objects.equals(content, post.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, content);
    }

    @ManyToOne
    @JoinColumn(name = "blogger_id", referencedColumnName = "id")
    public Blogger getBloggerByBloggerId() {
        return bloggerByBloggerId;
    }

    public void setBloggerByBloggerId(Blogger bloggerByBloggerId) {
        this.bloggerByBloggerId = bloggerByBloggerId;
    }
}
