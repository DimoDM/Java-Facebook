package facebook.controller;

import facebook.dto.ProfilePictureDTO;
import facebook.service.implementation.ProfilePictureUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class ProfilePictureController {
    private final ProfilePictureUploadService profilePictureUploadService;

    @Autowired
    public ProfilePictureController(ProfilePictureUploadService profilePictureUploadService) {
        this.profilePictureUploadService = profilePictureUploadService;
    }

    @GetMapping("/profilePicture/upload")
    public ModelAndView imageAvatarUpload() {
        return send("upload");
    }

    @PostMapping("/profilePicture/upload")
    public ModelAndView imageAvatarUpload(@ModelAttribute ProfilePictureDTO profilePictureDTO) throws IOException {
        profilePictureUploadService.uploadImage(profilePictureDTO.getImageAvatar());
        return redirect("/");
    }
}
