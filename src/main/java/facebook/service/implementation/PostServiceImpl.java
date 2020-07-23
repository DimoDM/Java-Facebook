package facebook.service.implementation;

import constants.Constants;
import facebook.dto.PostDTO;
import facebook.entity.Picture;
import facebook.entity.Post;
import facebook.entity.User;
import facebook.exception.BlankPostException;
import facebook.repository.PictureRepository;
import facebook.repository.PostRepository;
import facebook.service.contract.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Path;

@Transactional
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PictureRepository pictureRepository;
    private final ImageUploadService imageUploadService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, PictureRepository pictureRepository, ImageUploadService imageUploadService) {
        this.postRepository = postRepository;
        this.pictureRepository = pictureRepository;
        this.imageUploadService = imageUploadService;
    }

    @Override
    public void createPost(PostDTO postDTO, User authUser) throws BlankPostException, IOException {
        if (postDTO.getPostImage() == null && postDTO.getPostText() == null || postDTO.getPostText().isEmpty())
            throw new BlankPostException("The post does not contain any text or image");

        Post newPost = new Post();
        Picture picture = new Picture();
        if(postDTO.getPostImage() != null && !postDTO.getPostImage().isEmpty()){
            Path path = imageUploadService.uploadImageAndGetPath(postDTO.getPostImage());
            String filePathFromFolder = path.toString().replace(Constants.PATH_REFORMER,"");
            picture.setImageURL(filePathFromFolder);
            picture.setPictureHolder(authUser);
            pictureRepository.save(picture);
            newPost.setPicture(picture);
        }
        if(postDTO.getPostText() != null && !postDTO.getPostText().isEmpty())
            newPost.setText(postDTO.getPostText());

        newPost.setPoster(authUser);
        postRepository.save(newPost);

    }
}
