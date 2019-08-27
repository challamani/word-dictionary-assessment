package com.happiestminds.assessment.service;

import com.happiestminds.assessment.dao.File;
import com.happiestminds.assessment.dao.FileContent;
import com.happiestminds.assessment.dao.Word;
import com.happiestminds.assessment.repository.FileRepository;
import com.happiestminds.assessment.repository.WordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DictionaryDomainServiceImpl implements DictionaryDomainService{

    private static final Logger logger = LoggerFactory.getLogger(DictionaryDomainServiceImpl.class);

    @Autowired
    FileRepository fileRepository;

    @Autowired
    WordRepository wordRepository;

    @Override
    public File storeFileContent(String fileName, Long fileSize, List<String> paragraphs) {

        File file = new File();
        file.setName(fileName);
        file.setSize(fileSize.intValue());
        file.setType("txt");

        Set<FileContent> fileContentSet = new HashSet<>();
        for (String paragraph : paragraphs) {
            FileContent fileContent = new FileContent();
            fileContent.setContent(paragraph.trim());
            fileContent.setFile(file);
            fileContentSet.add(fileContent);
        }
        file.setFileContents(fileContentSet);
        return fileRepository.save(file);
    }

    @Override
    public void storeWords(Set<Word> words) {
        //to save bulk words objects
        wordRepository.saveAll(words);
    }

    @Override
    public List<Word> fetchWordsBySearchString(String searchString) {

        List<Word> words = new ArrayList<>();
        for(String str:searchString.split("\\,")) {
            String quickIndex = str.length() >= 2 ? str.substring(0, 2) : "" + (str.charAt(0));
            Optional<Word> wordOptional = wordRepository.findByQuickIndexAndWord(quickIndex.toUpperCase(), str.trim().toUpperCase());
            if (wordOptional.isPresent()) {
                logger.info("search found {}", wordOptional.get());
                words.add(wordOptional.get());
            }
        }

        return words;
    }

    @Override
    public  List<Word> searchByPregMatch(String str) {

        List<Word> words = wordRepository.findByQuickIndexAndWordStartsWith(str.substring(0, 2).toUpperCase(), str.trim().toUpperCase());
        logger.info("search {} records {}", str, words);
        return words;
    }
}
