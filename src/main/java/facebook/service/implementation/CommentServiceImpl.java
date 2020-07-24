package facebook.service.implementation;

import constants.Constants;
import facebook.dto.CommentDTO;
import facebook.entity.Comment;
import facebook.entity.Picture;
import facebook.entity.Post;
import facebook.entity.User;
import facebook.repository.CommentRepository;
import facebook.repository.PictureRepository;
import facebook.repository.PostRepository;
import facebook.service.contract.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final  ImageUploadService imageUploadService;
    private final PictureRepository pictureRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ImageUploadService imageUploadService, PictureRepository pictureRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.imageUploadService = imageUploadService;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public void createComment(CommentDTO commentDTO, User authUser) throws IOException {
        Comment comment = new Comment();
        Post post = postRepository.findFirstById(commentDTO.getPostID());
        Picture picture = new Picture();
        if(commentDTO.getCommentPhoto() != null && !commentDTO.getCommentPhoto().isEmpty()) {
            Path path = imageUploadService.uploadImageAndGetPath(commentDTO.getCommentPhoto());
            String filePathFromFolder = path.toString().replace(Constants.PATH_REFORMER, "");
            picture.setImageURL(filePathFromFolder);
            pictureRepository.save(picture);
            comment.setPicture(picture);
        }

        if(commentDTO.getText() != null && !commentDTO.getText().isEmpty())
            comment.setText(commentDTO.getText());

        comment.setCommenter(authUser);
        comment.setPost(post);
        commentRepository.save(comment);
        post.getComments().add(comment);
        postRepository.save(post);
    }
}
