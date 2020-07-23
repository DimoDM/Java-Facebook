package facebook.service.implementation;

import constants.Constants;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageUploadService {

    public void uploadImage(MultipartFile image) throws IOException {
        Path filepath = Paths.get(Constants.PICTURE_PATH, image.getOriginalFilename());
        image.transferTo(filepath);
    }

    public Path uploadImageAndGetPath(MultipartFile image) throws IOException {
        Path filepath = Paths.get(Constants.PICTURE_PATH, image.getOriginalFilename());
        image.transferTo(filepath);
        return filepath;
    }
}