package com.mockedms.fileupload.service;

import com.mockedms.fileupload.model.DocumentFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentService {

    void postDocument(MultipartFile file, DocumentFile details) throws IOException;

    //List<Document> getDocumentsById(Long id);

    List<DocumentFile> getAllDocuments();
}
