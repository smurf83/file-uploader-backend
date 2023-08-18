package com.mockedms.fileupload.api;

import com.mockedms.fileupload.model.DocumentFile;
import com.mockedms.fileupload.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping
    public ResponseEntity<DocumentFile> postDocument(@RequestParam("file") MultipartFile file,
                                                     @RequestParam("title") String title,
                                                     @RequestParam("description") String description) {
        try {
            DocumentFile document = new DocumentFile();
            document.setTitle(title);
            document.setDescription(description);

            // Assuming the file data is saved as a byte array in DocumentFile entity
            document.setFileData(file.getBytes());

            // If you have the filename or extension separately, set them too:
            // document.setFileName(file.getOriginalFilename());
            // OR just save the extension
            String extension = getFileExtension(file.getOriginalFilename());
            document.setFileExtension(extension);

            documentService.postDocument(file, document);
            return ResponseEntity.status(HttpStatus.CREATED).body(document);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<DocumentFile>> getAllDocuments() {
        List<DocumentFile> found = documentService.getAllDocuments();
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    private String getFileExtension(String filename) {
        if (filename == null || filename.isEmpty() || !filename.contains(".")) {
            return null;
        }
        return filename.substring(filename.lastIndexOf("."));
    }
}
