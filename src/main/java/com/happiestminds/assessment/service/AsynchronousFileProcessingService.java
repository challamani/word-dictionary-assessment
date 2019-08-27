package com.happiestminds.assessment.service;


import com.happiestminds.assessment.dao.File;
import com.happiestminds.assessment.dao.FileContent;
import com.happiestminds.assessment.dao.Word;
import com.happiestminds.assessment.property.FileStorageConstraints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AsynchronousFileProcessingService {

    @Autowired
    private DictionaryDomainService dictionaryDomainService;

    private final Path fileStorageLocation;

    private static final Logger logger = LoggerFactory.getLogger(AsynchronousFileProcessingService.class);

    @Autowired
    public AsynchronousFileProcessingService(FileStorageConstraints fileStorageConstraints) {
        fileStorageLocation = Paths.get(fileStorageConstraints.getUploadDir())
                .toAbsolutePath().normalize();

    }

    @Async("threadPoolTaskExecutor")
    public void doUploadedFileContentProcessing(String fileName) {

        Pattern pattern = Pattern.compile("^[A-Za-z]{1,60}$");
        String specialCharacterPatter ="[^a-zA-Z\\s+]";

        try {
            Path targetFile = fileStorageLocation.resolve(fileName);
            byte[] bytes = Files.readAllBytes(targetFile);

            logger.info("file content {} ", new String(bytes));
            Scanner scanner = new Scanner(new String(bytes)).useDelimiter("\n");
            List<String> paragraphs = new ArrayList<>();
            while (scanner.hasNext()) {
                String paragraph = scanner.next();
                logger.info("paragraph {} " + paragraph);
                if(paragraph.trim().length() == 0){
                    continue;
                }
                paragraphs.add(paragraph.trim());
            }

            Long fileSize = Files.size(targetFile);
            File file = dictionaryDomainService.storeFileContent(fileName, fileSize, paragraphs);
            logger.info("stored file {}", file);

            Set<Word> wordSet = new HashSet<>();
            for (String paragraph : paragraphs) {
                String[] words = paragraph.trim().split(" ");
                for (String word : words) {
                    if (word.trim().length() < 1) {
                        continue;
                    }

                    Matcher matcher = pattern.matcher(word);
                    if (!matcher.find()) {
                        logger.info("skipped word {}", word);
                        word = word.replaceAll(specialCharacterPatter, "");
                        logger.info("after replace word {}", word);

                    }

                    if (word.trim().length() < 1) {
                        continue;
                    }
                    wordSet.add(new Word(word.toUpperCase(), (word.length() >= 2 ? word.substring(0, 2) : word.substring(0, 1)).toUpperCase()));
                }
            }
            dictionaryDomainService.storeWords(wordSet);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }
}
