package facebook.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "second_name", nullable = false)
    private String secondName;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "profile_picture", unique = true)
    private String profilePicture; //This string will store the path to the picture

    @Column(name = "back_picture", unique = true)
    private String backPicture;

    //Тука няма да има нищо предполагам
    //Остават приятелите и снимките

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "poster")
    private Set<Post> posts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "liker")
    private Set<Like> likes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "commenter")
    private Set<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creator")
    private Set<Page> pages;

    @ManyToMany(targetEntity = Page.class, fetch = FetchType.EAGER)
    @JoinTable(name = "users_liked_pages",
    joinColumns = { @JoinColumn(name = "user_id")},
    inverseJoinColumns = {@JoinColumn(name = "page_id")})
    private Set<Page> followedPages;

    public Set<Post> getPosts() {
        return posts;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public void setLikes(Set<Like> likes) {
        this.likes = likes;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Page> getPages() {
        return pages;
    }

    public void setPages(Set<Page> pages) {
        this.pages = pages;
    }

    public Set<Page> getFollowedPages() {
        return followedPages;
    }

    public void setFollowedPages(Set<Page> followedPages) {
        this.followedPages = followedPages;
    }

    public User() {
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBackPicture() {
        return backPicture;
    }

    public void setBackPicture(String backPicture) {
        this.backPicture = backPicture;
    }
}
