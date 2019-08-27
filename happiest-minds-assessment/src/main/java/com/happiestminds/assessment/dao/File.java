package com.happiestminds.assessment.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "file")
public class File implements Serializable {

    private static final long serialVersionUID = 414310647232363296L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="type")
    private String type;

    @Column(name="size")
    private Integer size;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "file")
    private Set<FileContent> fileContents;

    public File() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Set<FileContent> getFileContents() {
        return fileContents;
    }

    public void setFileContents(Set<FileContent> fileContents) {
        this.fileContents = fileContents;
    }

    public File(Long id, String name, String type, Integer size, Set<FileContent> fileContents) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size = size;
        this.fileContents = fileContents;
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", fileContents=" + fileContents +
                '}';
    }
}
