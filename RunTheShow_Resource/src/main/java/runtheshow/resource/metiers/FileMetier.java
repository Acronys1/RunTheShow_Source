package runtheshow.resource.metiers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtheshow.resource.entities.FileUpload;
import runtheshow.resource.repository.FileUploadRepository;

@Service("file_metier")
public class FileMetier implements IFileMetier{

    @Autowired
    FileUploadRepository fileUploadRepository;

    // Retrieve file
    @Override
    public FileUpload findByFilename(String filename) {
        return fileUploadRepository.findByFilename(filename);
    }
    
    // Retrieve all file
    @Override
    public List<FileUpload> findAll() {
        return fileUploadRepository.findAll();
    }

    // Store the file reference to database
    @Override
    public String UploadFile(FileUpload doc) throws IOException {

        SaveFileDirectory(doc);

        fileUploadRepository.saveAndFlush(doc);
        
        return getFilePathFromDirectory(doc.getFilename());
    }

    // Upload the file to disk
    @Override
    public void SaveFileDirectory(FileUpload doc) throws IOException {

        //Generate uuid for hashing file
        UUID idOne = UUID.randomUUID();
        int timestamp = Calendar.getInstance().hashCode();
        
        //Create sub path for directory
        String deepPath1 = idOne.toString().substring(0, 2);
        //String deepPath2 = idOne.toString().substring(2, 4);
        String filePath = idOne.toString().substring(3)+"-"+timestamp;
        String ext = "";

        int i = doc.getFilename().lastIndexOf('.');
        if (i > 0) {
            ext = doc.getFilename().substring(i + 1);
        }

        //Create directory if not exist
        File files = new File("/home/files_import/"/* + deepPath1 */);
        if (!files.exists()) {
            if (files.mkdirs()) {
                System.out.println("sub directories created successfully");
            } else {
                System.out.println("failed to create sub directories");
            }
        }

        doc.setFilename(idOne.toString()+"-"+timestamp+"." + ext);

        FileOutputStream stream = new FileOutputStream("/home/files_import/"/*+deepPath1 + "\\" */+ filePath + "." + ext);

        try {
            stream.write(doc.getFile());
        } finally {
            stream.close();
        }
    }
    
    // Retrive filename for controller
    @Override
    public String getFileFromDirectory(String fileName){
        
        String deepPath1 = fileName.substring(0, 2);
        //String deepPath2 = fileName.substring(2, 4);
        String filePath = fileName.substring(3);
        
        return "files_import/"/*+deepPath1+"\\"*/+filePath;
    }
    
    // Retrive filename for webservice
    @Override
    public String getFilePathFromDirectory(String fileName){
        
        String deepPath1 = fileName.substring(0, 2);
        //String deepPath2 = fileName.substring(2, 4);
        String filePath = fileName.substring(3);
        
        return "/files_import/"/*+deepPath1+"/"*/+filePath;
    }
}
