package runtheshow.resource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import runtheshow.resource.entities.FileUpload;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {
    FileUpload findByFilename(String filename);
}
