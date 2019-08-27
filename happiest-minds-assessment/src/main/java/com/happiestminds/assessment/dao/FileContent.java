package com.happiestminds.assessment.dao;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "file_content")
public class FileContent implements Serializable {

    private static final long serialVersionUID = 414310647232363293L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="content")
    @Lob
    private String content;

    @Column(name="tags")
    private String tags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id",nullable = false, referencedColumnName="id")
    private File file;

    public FileContent() {
    }

    public FileContent(Long id, String content, String tags, File file) {
        this.id = id;
        this.content = content;
        this.tags = tags;
        this.file = file;
    }

    @Override
    public String toString() {
        return "FileContent{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
