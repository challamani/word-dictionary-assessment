package com.happiestminds.assessment.dao;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "word")
public class Word {

    private static final long serialVersionUID = 414310647232363296L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="word")
    private String word;

    @Column(name="quick_index")
    private String quickIndex;

    @Column(name="description")
    private String description;

    public Word() {
    }

    public Word(String word, String quickIndex) {
        this.word = word;
        this.quickIndex = quickIndex;
    }

    public Word(Long id, String word, String quickIndex, String description) {
        this.id = id;
        this.word = word;
        this.quickIndex = quickIndex;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getQuickIndex() {
        return quickIndex;
    }

    public void setQuickIndex(String quickIndex) {
        this.quickIndex = quickIndex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", quickIndex='" + quickIndex + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return word.equals(word1.word) &&
                quickIndex.equals(word1.quickIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, quickIndex);
    }
}
