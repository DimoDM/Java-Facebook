package facebook.service.implementation;

import facebook.dto.ChangeInfoDTO;
import facebook.entity.FriendRequest;
import facebook.entity.Post;
import facebook.entity.User;
import facebook.exception.UserByIdNotFoundException;
import facebook.exception.WrongPasswordException;
import facebook.repository.FriendRequestRepository;
import facebook.repository.PostRepository;
import facebook.repository.UserLoginDataRepository;
import facebook.repository.UserRepository;
import facebook.service.contract.FacebookProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FacebookProfileServiceImpl implements FacebookProfileService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final FriendRequestRepository friendRequestRepository;
    private final UserLoginDataRepository uLogDataRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public FacebookProfileServiceImpl(UserRepository userRepository,
                                      PostRepository postRepository,
                                      FriendRequestRepository friendRequestRepository,
                                      UserLoginDataRepository uLogDataRepo,
                                      BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.friendRequestRepository = friendRequestRepository;
        this.uLogDataRepo = uLogDataRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
    public void changeUserInfo(User user, ChangeInfoDTO changeInfoDTO) throws WrongPasswordException {
        if (!uLogDataRepo.existsByPassword(bCryptPasswordEncoder.encode(changeInfoDTO.getPasswordAuth())) || !isElementValid(changeInfoDTO.getPasswordAuth()))
            throw new WrongPasswordException("Wrong password! Changes cannot be applied!");
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

        userRepository.save(user);

    }

    private boolean isElementValid(String element){
        return element != null && !element.isEmpty();
    }

}
