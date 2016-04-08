package runtheshow.resource.webservice;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import runtheshow.resource.entities.FileUpload;
import runtheshow.resource.metiers.FileMetier;

@CrossOrigin
@RestController
public class FileController {

    @Autowired
    FileMetier fileUploadService;

    // Download a file
    @RequestMapping(
            value = "/download2",
            method = RequestMethod.GET
    )

    public FileUpload downloadFile2(@RequestParam("filename") String filename) {

        FileUpload fileUpload = fileUploadService.findByFilename(filename);
        
        File file = null;
         file = new File(fileUploadService.getFileFromDirectory(fileUpload.getFilename()));

            byte[] b = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(b);

        } catch (IOException e) {
            e.printStackTrace();
        }

        fileUpload.setFilename(fileUploadService.getFilePathFromDirectory(filename));
        fileUpload.setFile(b);

        return fileUpload;

    }
    
    // Download a file
    @RequestMapping(
            value = "/downloadAll",
            method = RequestMethod.GET
    )
    
    public List<FileUpload> downloadAllFile() {

        List<FileUpload> fileUpload = fileUploadService.findAll();
        
        fileUpload.stream().forEach(file -> file.setFilename(fileUploadService.getFilePathFromDirectory(file.getFilename())));
        
        return fileUpload;

    }

    // Download a file
    @RequestMapping(
            value = "/download",
            method = RequestMethod.GET
    )

    public ResponseEntity<?> downloadFile(@RequestParam("filename") String filename) {

        FileUpload fileUpload = fileUploadService.findByFilename(filename);

        // No file found based on the supplied filename
        if (fileUpload == null) {
            return new ResponseEntity<>("{}", HttpStatus.NOT_FOUND);
        }

        // Generate the http headers with the file properties
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "attachment; filename=" + fileUpload.getFilename());

        // Split the mimeType into primary and sub types
        String primaryType, subType;
        try {
            primaryType = fileUpload.getMimeType().split("/")[0];
            subType = fileUpload.getMimeType().split("/")[1];
        } catch (IndexOutOfBoundsException | NullPointerException ex) {
            return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        File file = null;
        file = new File(fileUploadService.getFileFromDirectory(fileUpload.getFilename()));

        byte[] b = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(b);

        } catch (IOException e) {
            e.printStackTrace();
        }

        headers.setContentType(new MediaType(primaryType, subType));

        return new ResponseEntity<>(b, headers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/upload",
            method = RequestMethod.POST
    )
    public ResponseEntity uploadFile(MultipartHttpServletRequest request) {

        try {
            Iterator<String> itr = request.getFileNames();

            while (itr.hasNext()) {
                String uploadedFile = itr.next();
                MultipartFile file = request.getFile(uploadedFile);
                String mimeType = file.getContentType();
                String filename = file.getOriginalFilename();
                byte[] bytes = file.getBytes();

                FileUpload newFile = new FileUpload(filename, bytes, mimeType);

                fileUploadService.UploadFile(newFile);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

}
