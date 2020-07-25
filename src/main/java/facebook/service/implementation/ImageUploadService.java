package facebook.service.implementation;

import com.dropbox.core.DbxException;
import constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageUploadService {

    private final DropBoxService dropBoxService;

    @Autowired
    public ImageUploadService(DropBoxService dropBoxService) {
        this.dropBoxService = dropBoxService;
    }

    public String uploadImageAndGetURL(MultipartFile image) throws IOException, DbxException {
        String url = dropBoxService.upload(image);
        return url;
    }

    public Path uploadImageAndGetPath(MultipartFile image) throws IOException {
        Path filepath = Paths.get(Constants.PICTURE_PATH, image.getOriginalFilename());
        image.transferTo(filepath);
        return filepath;
    }

    public void uploadImage(MultipartFile image) throws IOException {
        Path filepath = Paths.get(Constants.PICTURE_PATH, image.getOriginalFilename());
        image.transferTo(filepath);
    }

    public Path getPathOfImage(MultipartFile image) throws IOException {
        return Paths.get(Constants.PICTURE_PATH, image.getOriginalFilename());
    }
}