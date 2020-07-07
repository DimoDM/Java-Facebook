package facebook.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pages")
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "back_picture_url")
    private String backPicture;//URL for images

    @Column(name = "profile_picture_url")
    private String profilePicture;//URL for images

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "page")
    private Set<Like> likes;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User creator;

    public Page() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackPicture() {
        return backPicture;
    }

    public void setBackPicture(String backPicture) {
        this.backPicture = backPicture;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public void setLikes(Set<Like> likes) {
        this.likes = likes;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
