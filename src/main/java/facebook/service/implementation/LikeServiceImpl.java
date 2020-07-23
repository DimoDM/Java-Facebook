package facebook.service.implementation;

import facebook.dto.LikeDTO;
import facebook.entity.Like;
import facebook.entity.Post;
import facebook.entity.User;
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

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void likePost(LikeDTO likeDTO, User authUser) {
        Post post = postRepository.findFirstById(likeDTO.getPosId());
        if(!likeRepository.existsByPost(post)){
            Like like = new Like();
            like.setPost(post);
            like.setLiker(authUser);
            likeRepository.save(like);
            post.getLikes().add(like);
            postRepository.save(post);
        }else {
            Like like = likeRepository.findFirstByPost(post);
            post.getLikes().remove(like);
            likeRepository.delete(like);
        }
    }
}
