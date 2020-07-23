package facebook.service.implementation;

import facebook.dto.LikeDTO;
import facebook.entity.Comment;
import facebook.entity.Like;
import facebook.entity.Post;
import facebook.entity.User;
import facebook.repository.CommentRepository;
import facebook.repository.LikeRepository;
import facebook.repository.PostRepository;
import facebook.service.contract.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void likeComment(LikeDTO likeDTO, User authUser) {
        Comment comment = commentRepository.findFirstById(likeDTO.getCommentId());
        if(!likeRepository.existsByComment(comment)){
            Like like = new Like();
            like.setComment(comment);
            like.setLiker(authUser);
            likeRepository.save(like);
            comment.getLikes().add(like);
            commentRepository.save(comment);
        }else {
            Like like = likeRepository.findFirstByCommentAndLiker(comment,authUser);
            comment.getLikes().remove(like);
            likeRepository.delete(like);
        }
    }

    @Override
    public void likePost(LikeDTO likeDTO, User authUser) {
        Post post = postRepository.findFirstById(likeDTO.getPostId());
        if(!likeRepository.existsByPost(post)){
            Like like = new Like();
            like.setPost(post);
            like.setLiker(authUser);
            likeRepository.save(like);
            post.getLikes().add(like);
            postRepository.save(post);
        }else {
            Like like = likeRepository.findFirstByPostAndLiker(post,authUser);
            post.getLikes().remove(like);
            likeRepository.delete(like);
        }
    }
}
