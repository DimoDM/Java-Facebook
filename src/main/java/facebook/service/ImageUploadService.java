package facebook.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageUploadService {

    public void uploadImage(MultipartFile image) throws IOException {
        Path filepath = Paths.get("", image.getOriginalFilename());
        image.transferTo(filepath);
    }
}