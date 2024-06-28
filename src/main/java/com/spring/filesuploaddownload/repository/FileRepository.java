package com.spring.filesuploaddownload.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.spring.filesuploaddownload.entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

	  Optional<FileEntity> findByName(String fileName);
	    
	    @Query(value ="SELECT file_path FROM file_data",nativeQuery = true)
	    List<String> getAllImageFilenames();
	
}
