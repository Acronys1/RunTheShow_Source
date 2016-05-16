package runtheshow.resource.metiers;

import java.io.IOException;
import java.util.List;
import runtheshow.resource.entities.FileUpload;

public interface IFileMetier {

	 public FileUpload findByFilename(String filename);
         
         public List<FileUpload> findAll();
         
         public String UploadFile(FileUpload doc) throws IOException;
         
         public void SaveFileDirectory(FileUpload doc) throws IOException;
         
         public String getFileFromDirectory(String fileName);
         
         public String getFilePathFromDirectory(String fileName);
}