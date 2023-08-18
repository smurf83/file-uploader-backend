package com.mockedms.fileupload.service.impl;

import com.mockedms.fileupload.model.DocumentFile;
import com.mockedms.fileupload.repository.DocumentFileRepository; // You might also need to rename this to DocumentFileRepository
import com.mockedms.fileupload.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentFileRepository documentFileRepository; // You might also need to rename this to documentFileRepository

    @Override
    public void postDocument(MultipartFile file, DocumentFile details) throws IOException {
        DocumentFile document = new DocumentFile();
        document.setTitle(details.getTitle());
        document.setDescription(details.getDescription());
        document.setFileData(file.getBytes());
        // Assuming the file extension is set at the controller level, so no need to set it here again.

        documentFileRepository.save(document); // If you renamed the repository, make sure to adjust this line too.
    }

    /*@Override
    public List<DocumentFile> getDocumentsById(Long id) {
        return documentRepository.findAllById(Collections.singleton(id)); // Rename to DocumentFile if you renamed the repository.
    }*/

    @Override
    public List<DocumentFile> getAllDocuments() {
        return documentFileRepository.findAll(); // Rename to DocumentFile if you renamed the repository.
    }
}
