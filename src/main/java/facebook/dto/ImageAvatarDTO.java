package facebook.dto;

import org.springframework.web.multipart.MultipartFile;

public class ImageAvatarDTO {
    private MultipartFile imageAvatar;

    public ImageAvatarDTO() {
    }

    public MultipartFile getImageAvatar() {
        return imageAvatar;
    }

    public void setImageAvatar(MultipartFile imageAvatar) {
        this.imageAvatar = imageAvatar;
    }
}
