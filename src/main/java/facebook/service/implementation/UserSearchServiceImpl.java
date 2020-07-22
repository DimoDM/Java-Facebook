package facebook.service.implementation;

import facebook.dto.UserSearchDTO;
import facebook.entity.User;
import facebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSearchServiceImpl {

    private final UserRepository userRepository;

    @Autowired
    public UserSearchServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findByTwoNames(UserSearchDTO userSearchDTO){
        String firstName = userSearchDTO.getName().split(" ")[0];
        String secondName = userSearchDTO.getName().split(" ")[1];

        return userRepository.findAllByFirstNameAndSecondNameIgnoreCase(firstName, secondName);
    }

    public List<User> findByFirstName(UserSearchDTO userSearchDTO){
        String firstName = userSearchDTO.getName().split(" ")[0];
        String secondName = userSearchDTO.getName().split(" ")[1];

        return userRepository.findAllByFirstNameIgnoreCaseAndSecondNameNotIgnoreCase(firstName, secondName);
    }

    public List<User> findBySecondName(UserSearchDTO userSearchDTO){
        String firstName = userSearchDTO.getName().split(" ")[0];
        String secondName = userSearchDTO.getName().split(" ")[1];

        return userRepository.findAllBySecondNameIgnoreCaseAndFirstNameNotIgnoreCase(secondName, firstName);
    }

}
