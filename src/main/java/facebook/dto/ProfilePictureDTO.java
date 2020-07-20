package facebook.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProfilePictureDTO {
    private MultipartFile imageAvatar;

    public ProfilePictureDTO() {
    }

    public MultipartFile getImageAvatar() {
        return imageAvatar;
    }

    public void setImageAvatar(MultipartFile imageAvatar) {
        this.imageAvatar = imageAvatar;
    }
}
