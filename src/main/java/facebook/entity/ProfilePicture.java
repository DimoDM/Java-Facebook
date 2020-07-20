package facebook.entity;

import javax.persistence.*;

@Entity
@Table
public class ProfilePicture {
    @Id
    @Column(name = "profile_pictures")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "imager_url")
    private String imageURL;

    @OneToOne(mappedBy = "userProfilePicture")
    private User userProfilePicture;

    public ProfilePicture() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public User getUserProfilePicture() {
        return userProfilePicture;
    }

    public void setUserProfilePicture(User userProfilePicture) {
        this.userProfilePicture = userProfilePicture;
    }
}
