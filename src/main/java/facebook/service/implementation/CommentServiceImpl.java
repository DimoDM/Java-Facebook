package facebook.service.implementation;

import facebook.dto.CommentDTO;
import facebook.entity.Comment;
import facebook.entity.Post;
import facebook.entity.User;
import facebook.repository.CommentRepository;
import facebook.repository.PostRepository;
import facebook.service.contract.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void createComment(CommentDTO commentDTO, User authUser) {
        Comment comment = new Comment();
        Post post = postRepository.findFirstById(commentDTO.getPostID());
        comment.setCommenter(authUser);
        comment.setText(commentDTO.getText());
        comment.setPost(post);
        commentRepository.save(comment);
        post.getComments().add(comment);
        postRepository.save(post);
    }
}
