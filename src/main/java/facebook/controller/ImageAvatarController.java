package facebook.controller;

import facebook.dto.ImageAvatarDTO;
import facebook.service.implementation.ImageAvatarUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class ImageAvatarController {
    private final ImageAvatarUploadService imageAvatarUploadService;

    @Autowired
    public ImageAvatarController(ImageAvatarUploadService imageAvatarUploadService) {
        this.imageAvatarUploadService = imageAvatarUploadService;
    }

    @GetMapping("/imageAvatar/upload")
    public ModelAndView imageAvatarUpload() {
        return send("upload");
    }

    @PostMapping("/imageAvatar/upload")
    public ModelAndView imageAvatarUpload(@ModelAttribute ImageAvatarDTO imageAvatarDTO) throws IOException {
        imageAvatarUploadService.uploadImage(imageAvatarDTO.getImageAvatar());
        return redirect("/");
    }
}
