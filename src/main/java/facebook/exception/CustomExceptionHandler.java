package facebook.exception;

import facebook.entity.User;
import facebook.service.contract.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.security.Principal;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);
    private final UserService userService;

    @Autowired
    public CustomExceptionHandler(UserService userService) {
        this.userService = userService;
    }

    @ExceptionHandler(value = {UserByIdNotFoundException.class})
    public ModelAndView handleUserByIdNotFoundException(UserByIdNotFoundException ex, Principal principal) {
        log.error(ex.getLocalizedMessage(), ex);
        return sendAuth(ex.getMessage(), userService.getAuthUser(principal.getName()));
    }

    @ExceptionHandler(value = {BlankPostException.class})
    public ModelAndView handleBlankPostException(BlankPostException ex, Principal principal) {
        log.error(ex.getLocalizedMessage(), ex);
        return sendAuth(ex.getMessage(), userService.getAuthUser(principal.getName()));
    }

    @ExceptionHandler(value = {FriendRequestNotFoundException.class})
    public ModelAndView handleFriendRequestNotFoundException(FriendRequestNotFoundException ex, Principal principal) {
        log.error(ex.getLocalizedMessage(), ex);
        return sendAuth(ex.getMessage(), userService.getAuthUser(principal.getName()));
    }

    @ExceptionHandler(value = {InvalidLoginException.class})
    public ModelAndView handleInvalidLoginException(InvalidLoginException ex) {
        log.error(ex.getLocalizedMessage(), ex);
        return send(ex.getLocalizedMessage());
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ModelAndView handleUserNotFoundException(UserNotFoundException ex) {
        log.error(ex.getLocalizedMessage(), ex);
        return send(ex.getLocalizedMessage());
    }

    private ModelAndView send(String msg){
        ModelAndView modelAndView = new ModelAndView("error\\errors.html");
        modelAndView.addObject("errorMsg", msg);
        return modelAndView;
    }

    private ModelAndView sendAuth(String msg, User authUser){
        ModelAndView modelAndView = new ModelAndView("error\\authUserErrors.html");
        modelAndView.addObject("errorMsg", msg);
        modelAndView.addObject("authUser", authUser);
        return modelAndView;
    }

}