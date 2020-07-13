package facebook.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageAvatarUploadService {
    public void uploadImage(MultipartFile imageAvatar) throws IOException {
        Path filepath = Paths.get("", imageAvatar.getOriginalFilename());
        imageAvatar.transferTo(filepath);
    }
}
