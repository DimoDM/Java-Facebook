package facebook.service.implementation;

import constants.Constants;
import facebook.dto.ChangeInfoDTO;
import facebook.dto.ImageUploadDTO;
import facebook.entity.FriendRequest;
import facebook.entity.Picture;
import facebook.entity.Post;
import facebook.entity.User;
import facebook.exception.DateNotValidException;
import facebook.exception.NoCoverPhotoException;
import facebook.exception.NoProfilePictureException;
import facebook.exception.UserByIdNotFoundException;
import facebook.repository.*;
import facebook.service.contract.FacebookProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Date;
import java.util.Set;

@Transactional
@Service
public class FacebookProfileServiceImpl implements FacebookProfileService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final FriendRequestRepository friendRequestRepository;
    private final ImageUploadService imageUploadService;
    private final PictureRepository pictureRepository;

    @Autowired
    public FacebookProfileServiceImpl(UserRepository userRepository,
                                      PostRepository postRepository,
                                      FriendRequestRepository friendRequestRepository,
                                      ImageUploadService imageUploadService,
                                      PictureRepository pictureRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.friendRequestRepository = friendRequestRepository;
        this.imageUploadService = imageUploadService;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public User getProfile(Long id) throws UserByIdNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserByIdNotFoundException("User not found"));
    }

    @Override
    public Set<Post> getUserPosts(User user) {
        return postRepository.findAllByPoster(user);
    }

    @Override
    public FriendRequest getFriendRequestWithId(Long id) throws UserByIdNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserByIdNotFoundException("User not found"));
        return friendRequestRepository.findFriendRequestByRequesterOrReceiver(user, user);
    }

    @Override
    public void changeUserInfo(User user, ChangeInfoDTO changeInfoDTO) throws DateNotValidException {
        if (isElementValid(changeInfoDTO.getFirstName()))
            user.setFirstName(changeInfoDTO.getFirstName());
        if (isElementValid(changeInfoDTO.getSecondName()))
            user.setSecondName(changeInfoDTO.getSecondName());
        if (isElementValid(changeInfoDTO.getTelNum()))
            user.setPhoneNumber(changeInfoDTO.getTelNum());
        if (isElementValid(changeInfoDTO.getJob()))
            user.setJob(changeInfoDTO.getJob());
        if (isElementValid(changeInfoDTO.getCity()))
            user.setCity(changeInfoDTO.getCity());
        if (isElementValid(changeInfoDTO.getSchool()))
            user.setSchool(changeInfoDTO.getSchool());
        if (changeInfoDTO.getBirthday() != null)
            if (!isDateValid(changeInfoDTO.getBirthday()))
                throw new DateNotValidException("You must be at least 14 year old!");
        user.setDateOfBirth(changeInfoDTO.getBirthday().toString());

        userRepository.save(user);

    }

    @Override
    public void changeProfilePicture(User authUser, ImageUploadDTO imageUploadDTO) throws NoProfilePictureException, IOException {
        if (imageUploadDTO.getImage() == null)
            throw new NoProfilePictureException("You didn't pick a picture");

        Picture picture = new Picture();
        Path path = imageUploadService.uploadImageAndGetPath(imageUploadDTO.getImage());
        String filePathFromFolder = path.toString().replace(Constants.PATH_REFORMER, "");
        picture.setImageURL(filePathFromFolder);
        picture.setPictureHolder(authUser);
        pictureRepository.save(picture);
        authUser.setProfilePicture(picture);
        userRepository.save(authUser);

    }

    @Override
    public void changeCoverPhoto(User authUser, ImageUploadDTO imageUploadDTO) throws IOException, NoCoverPhotoException {
        if (imageUploadDTO.getImage() == null)
            throw new NoCoverPhotoException("You didn't pick a picture");

        Picture picture = new Picture();
        Path path = imageUploadService.uploadImageAndGetPath(imageUploadDTO.getImage());
        String filePathFromFolder = path.toString().replace(Constants.PATH_REFORMER, "");
        picture.setImageURL(filePathFromFolder);
        picture.setPictureHolder(authUser);
        pictureRepository.save(picture);
        authUser.setBackPicture(picture);
        userRepository.save(authUser);
    }


    private boolean isElementValid(String element) {
        return element != null && !element.isEmpty();
    }

    private boolean isDateValid(Date date) {
        long dateToday = new java.util.Date().getTime();
        return dateToday - date.getTime() >= Constants.MINIMAL_AGE;
    }

}
