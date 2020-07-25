package facebook.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;

public class TestDTO {

    private MultipartFile postImage;

    public TestDTO() {
    }

    public MultipartFile getPostImage() {
        return postImage;
    }

    public void setPostImage(MultipartFile postImage) {
        this.postImage = postImage;
    }
}
