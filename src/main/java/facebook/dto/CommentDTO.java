package facebook.dto;

import facebook.entity.User;

public class CommentDTO {

    private Long postID;
    private String text;

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
}
