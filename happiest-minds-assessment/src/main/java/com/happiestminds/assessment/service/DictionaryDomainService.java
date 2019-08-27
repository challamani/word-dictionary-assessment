package com.happiestminds.assessment.service;

import com.happiestminds.assessment.dao.File;
import com.happiestminds.assessment.dao.Word;

import java.util.List;
import java.util.Set;

public interface DictionaryDomainService {

    File storeFileContent(String fileName, Long fileSize, List<String> paragraphs);

    void storeWords(Set<Word> words);

    List<Word> fetchWordsBySearchString(String searchString);

    List<Word> searchByPregMatch(String str);
}
