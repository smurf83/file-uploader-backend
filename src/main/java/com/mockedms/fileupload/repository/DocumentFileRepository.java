package com.mockedms.fileupload.repository;

import com.mockedms.fileupload.model.DocumentFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentFileRepository extends JpaRepository<DocumentFile, Long> {
}

