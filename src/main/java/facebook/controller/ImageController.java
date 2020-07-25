package facebook.controller;


import com.dropbox.core.DbxException;
import facebook.dto.ImageUploadDTO;
import facebook.service.implementation.DropBoxService;
import facebook.service.implementation.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import java.io.FileNotFoundException;
import java.io.IOException;


@Controller
public class ImageController extends BaseController {

    private final ImageUploadService imageUploadService;
    private final DropBoxService dropBoxService;

    @Autowired
    public ImageController(ImageUploadService imageUploadService, DropBoxService dropBoxService) {
        this.imageUploadService = imageUploadService;
        this.dropBoxService = dropBoxService;
    }

    @GetMapping("/image/upload")
    public ModelAndView imageUpload() {
        return send("upload");
    }


    @PostMapping("/image/upload")
    public ModelAndView imageUpload(@ModelAttribute ImageUploadDTO imageUploadDTO) throws IOException, DbxException {
        imageUploadService.uploadImage(imageUploadDTO.getImage());
        return redirect("/");
    }

}
