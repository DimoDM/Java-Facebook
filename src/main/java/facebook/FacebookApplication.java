package facebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FacebookApplication {
    private int maxUploadSizeInMb = 10 * 1024 * 1024;

    public static void main(String[] args) {
        SpringApplication.run(FacebookApplication.class, args);
    }

}
