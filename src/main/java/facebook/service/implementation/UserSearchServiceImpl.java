package facebook.service.implementation;

import facebook.dto.UserSearchDTO;
import facebook.entity.User;
import facebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class UserSearchServiceImpl {

    private final UserRepository userRepository;

    @Autowired
    public UserSearchServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private List<User> findByTwoNames(UserSearchDTO userSearchDTO){
        String firstName = userSearchDTO.getName().split(" ")[0];
        String secondName = userSearchDTO.getName().split(" ")[1];

        return userRepository.findAllByFirstNameIgnoreCaseAndSecondNameIgnoreCase(firstName, secondName);
    }

    private List<User> findByFirstName(UserSearchDTO userSearchDTO){
        String firstName = userSearchDTO.getName().split(" ")[0];
        String secondName = userSearchDTO.getName().split(" ")[1];

        return userRepository.findAllByFirstNameIgnoreCaseAndSecondNameNotIgnoreCase(firstName, secondName);
    }

    private List<User> findBySecondName(UserSearchDTO userSearchDTO){
        String firstName = userSearchDTO.getName().split(" ")[0];
        String secondName = userSearchDTO.getName().split(" ")[1];

        return userRepository.findAllBySecondNameIgnoreCaseAndFirstNameNotIgnoreCase(secondName, firstName);
    }

    private List<User> findByAnyOfNames(UserSearchDTO userSearchDTO){
        String firstOrLastName = userSearchDTO.getName();

        return userRepository.findAllByFirstNameIgnoreCaseOrSecondNameIgnoreCase(firstOrLastName, firstOrLastName);
    }

    public ModelAndView setModelAndViewForTwoNames(ModelAndView modelAndView, UserSearchDTO userSearchDTO){

        List<User> usersFoundByBothNames = this.findByTwoNames(userSearchDTO);
        List<User> usersFoundByFirstName = this.findByFirstName(userSearchDTO);
        List<User> usersFoundBySecondName = this.findBySecondName(userSearchDTO);
        List<User> usersFoundByAnyOfNames = null;

        modelAndView.addObject("fullMatchUsers", usersFoundByBothNames);
        modelAndView.addObject("firstNameMatchUsers", usersFoundByFirstName);
        modelAndView.addObject("secondNameMatchUsers", usersFoundBySecondName);
        modelAndView.addObject("matchByAnyOfNames", usersFoundByAnyOfNames);

        return modelAndView;
    }

    public ModelAndView setModelAndViewForOneName(ModelAndView modelAndView, UserSearchDTO userSearchDTO){

        List<User> usersFoundByBothNames = null;
        List<User> usersFoundByFirstName = null;
        List<User> usersFoundBySecondName = null;
        List<User> usersFoundByAnyOfNames = this.findByAnyOfNames(userSearchDTO);

        modelAndView.addObject("fullMatchUsers", usersFoundByBothNames);
        modelAndView.addObject("firstNameMatchUsers", usersFoundByFirstName);
        modelAndView.addObject("secondNameMatchUsers", usersFoundBySecondName);
        modelAndView.addObject("matchByAnyOfNames", usersFoundByAnyOfNames);

        return modelAndView;
    }

}
