package facebook.controller;

import com.dropbox.core.DbxException;
import facebook.dto.TestDTO;
import facebook.service.implementation.DropBoxService;
import facebook.service.implementation.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class ControllerTEst extends BaseController{

     private final ImageUploadService imageUploadService;
     private final DropBoxService dropBoxService;

    @Autowired
    public ControllerTEst(ImageUploadService imageUploadService, DropBoxService dropBoxService) {
        this.imageUploadService = imageUploadService;
        this.dropBoxService = dropBoxService;
    }

    @PostMapping("/testTest")
    public ModelAndView testTest(@ModelAttribute TestDTO testDTO) throws IOException, DbxException {
        String url = imageUploadService.getPathOfImage(testDTO.getPostImage()).toString();
        testDTO.getPostImage().getInputStream();
        System.out.println(testDTO.getPostImage().getInputStream().toString());
        System.out.println(url);
        System.out.println(testDTO.getPostImage().getOriginalFilename());
        System.out.println(testDTO.getPostImage().getContentType().split("/")[1]);
        dropBoxService.upload(testDTO.getPostImage());
        return send("Test2");
    }

    @GetMapping("/testTest")
    public ModelAndView testTest2(){
        return send("Test2");
    }

}
