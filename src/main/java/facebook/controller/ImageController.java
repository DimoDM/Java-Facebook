package facebook.controller;


import facebook.dto.ImageUploadDTO;
import facebook.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import java.io.IOException;


@Controller
public class ImageController  extends BaseController {

    private final ImageUploadService imageUploadService;

    @Autowired
    public ImageController(ImageUploadService imageUploadService) {
        this.imageUploadService = imageUploadService;
    }

    @GetMapping("/image/upload")
    public ModelAndView imageUpload() {
        return send("upload");
    }

    @PostMapping("/image/upload")
    public ModelAndView imageUpload(@ModelAttribute ImageUploadDTO imageUploadDTO) throws IOException {
        imageUploadService.uploadImage(imageUploadDTO.getImage());
        return redirect("/");
    }

}
