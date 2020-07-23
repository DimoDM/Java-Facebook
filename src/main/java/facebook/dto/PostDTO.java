package facebook.dto;

import facebook.entity.User;
import org.springframework.web.multipart.MultipartFile;

public class PostDTO {

    private String postText;
    private MultipartFile postImage;

    public PostDTO() {
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public MultipartFile getPostImage() {
        return postImage;
    }

    public void setPostImage(MultipartFile postImage) {
        this.postImage = postImage;
    }
}
