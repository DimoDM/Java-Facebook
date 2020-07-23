package facebook.dto;

import facebook.entity.User;
import org.springframework.web.multipart.MultipartFile;

public class CommentDTO {

    private Long postID;
    private String text;
    private MultipartFile commentPhoto;

    public CommentDTO() {
    }

    public Long getPostID() {
        return postID;
    }

    public void setPostID(Long postID) {
        this.postID = postID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MultipartFile getCommentPhoto() {
        return commentPhoto;
    }

    public void setCommentPhoto(MultipartFile commentPhoto) {
        this.commentPhoto = commentPhoto;
    }
}
