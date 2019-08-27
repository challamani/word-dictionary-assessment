package com.happiestminds.assessment.controller;

import com.happiestminds.assessment.model.UploadFileResponse;
import com.happiestminds.assessment.service.AsynchronousFileProcessingService;
import com.happiestminds.assessment.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.channels.AsynchronousByteChannel;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Two web-methods are there but uploadFile is having file-processing capabilities
 * @@see  #FileStorageService ##AsynchronousFileProcessingService
 * */
@RestController
@RequestMapping("/happiest-minds-assessment/1.0.0")
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private AsynchronousFileProcessingService asynchronousFileProcessingService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/happiest-minds-assessment/1.0.0/downloadFile/")
                .path(fileName)
                .toUriString();

        //Async file-processing
        asynchronousFileProcessingService.doUploadedFileContentProcessing(fileName);

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }


}
