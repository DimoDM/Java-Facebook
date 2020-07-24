package facebook.service.implementation;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.sharing.RequestedVisibility;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import com.dropbox.core.v2.sharing.SharedLinkSettings;
import facebook.component.RandomString;
import facebook.entity.Picture;
import facebook.repository.PictureRepository;
import facebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
public class DropBoxService {

    private static final String ACCESS_TOKEN = "-EG7OJoOdMAAAAAAAAAAMswJ36gQA-rD2GBRaKMFQsyccyQnDFi-Xf46tG3EdgfA";
    private final DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
    private final DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;

    @Autowired
    public DropBoxService(UserRepository userRepository, PictureRepository pictureRepository) throws DbxException {
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
    }

    static String url;

    public String upload(MultipartFile multipartFile) throws DbxException, FileNotFoundException {

        String randomName = RandomString.getAlphaNumericString(15);

        try (InputStream in = multipartFile.getInputStream()) {
            FileMetadata metadata = client.files().uploadBuilder("/" + randomName + "." + multipartFile.getContentType().split("/")[1])
                    .uploadAndFinish(in);
           url = getURL(multipartFile, randomName);
            savePicture(url);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

    private String getURL(MultipartFile multipartFile, String randomName) throws DbxException {
        String newFileRandomNamePath = "/" + randomName + "." + multipartFile.getContentType().split("/")[1];
        SharedLinkMetadata slm = this.client.sharing()
                .createSharedLinkWithSettings(newFileRandomNamePath,
                            SharedLinkSettings.newBuilder().withRequestedVisibility(RequestedVisibility.PUBLIC).build());
        String url = slm.getUrl();

        return url.replace("dropbox", "dl.dropboxusercontent");
    }

    private void savePicture(String url){
        Picture picture = new Picture();
        picture.setImageURL(url);
        pictureRepository.save(picture);
    }
}