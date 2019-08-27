package com.happiestminds.assessment.controller;

import com.happiestminds.assessment.dao.Word;
import com.happiestminds.assessment.service.DictionaryDomainService;
import com.happiestminds.assessment.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/happiest-minds-assessment/1.0.0")
public class SearchController {

    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private DictionaryDomainService dictionaryDomainService;

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = fileStorageService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    @GetMapping("/searchByCommaSeparateWords/{commaSeparateWords}")
    public ResponseEntity<List<Word>> searchByCommaSeparateWords(@PathVariable String commaSeparateWords) {

        List<Word> words = dictionaryDomainService.fetchWordsBySearchString(commaSeparateWords);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(words);
    }


    @GetMapping("/searchBySubWord/{word}")
    public ResponseEntity<List<Word>> searchBySubWord(@PathVariable String word) {

        List<Word> words = dictionaryDomainService.searchByPregMatch(word);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(words);
    }
}
