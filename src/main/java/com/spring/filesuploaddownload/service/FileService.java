package com.spring.filesuploaddownload.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.filesuploaddownload.entity.FileEntity;
import com.spring.filesuploaddownload.repository.FileRepository;

@Service
public class FileService {

    @Autowired
    private FileRepository fileDataRepository;

    private final String FOLDER_PATH= "C:\\imageProject\\";

 

    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath=FOLDER_PATH+file.getOriginalFilename();

        FileEntity fileData=fileDataRepository.save(FileEntity.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileEntity> fileData = fileDataRepository.findByName(fileName);
        String filePath=fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

    public List<String> getAllImageFilenames() {
        return fileDataRepository.getAllImageFilenames(); // Implement this method in your repository
    }
	
}
