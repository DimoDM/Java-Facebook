package facebook.service.implementation;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.sharing.RequestedVisibility;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import com.dropbox.core.v2.sharing.SharedLinkSettings;
import com.dropbox.core.v2.users.FullAccount;
import facebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
public class DropBoxGetFilesService {

    private static final String ACCESS_TOKEN = "-EG7OJoOdMAAAAAAAAAADBebpJsRpuSreSFaLo7M4b7yQHxRoSKCDuPatoFOpZ0z";
    private final DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
    private final DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
    private final UserRepository userRepository;

    @Autowired
    public DropBoxGetFilesService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void upload() throws DbxException, FileNotFoundException {
        // Create Dropbox client

        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());

        String newFileRandomName = "asd.jpg";
        SharedLinkMetadata slm = this.client.sharing()
                .createSharedLinkWithSettings(newFileRandomName,
                        SharedLinkSettings.newBuilder().withRequestedVisibility(RequestedVisibility.PUBLIC).build());

        ListFolderResult result = client.files().listFolder("");
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathDisplay());
            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor()); }

        try (InputStream in = new FileInputStream("avatar.png")) {
            FileMetadata metadata = client.files().uploadBuilder("/avatar.png")
                    .uploadAndFinish(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream in = new FileInputStream("asd.jpg")) {
            FileMetadata metadata = client.files().getTemporaryLink("/asd.jpg").getMetadata();
            System.out.println("asd: " +  metadata.getPathDisplay());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

