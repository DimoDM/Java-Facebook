package facebook.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "second_name", nullable = false)
    private String secondName;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @OneToOne(targetEntity = UserLoginData.class)
    private UserLoginData userLoginData;

    @OneToOne
    private Picture profilePicture;
    //This string will store the path to the picture

    @OneToOne
    private Picture backPicture;

    @Column(name = "city")
    private String city;

    @Column(name = "job")
    private String job;

    @Column(name = "school")
    private String school;


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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pictureHolder")
    private Set<Picture> profilePictures;

    @ManyToMany
    @JoinTable(name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id") ,
            inverseJoinColumns = @JoinColumn(name = "friendId") )
    private Set<User> userFriends;

    @ManyToMany
    @JoinTable(name = "friends_requests",
            joinColumns = @JoinColumn(name = "requester_id"),
            inverseJoinColumns = @JoinColumn(name = "receiver_id"))
    private Set<FriendRequest> friendRequests;


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public UserLoginData getUserLoginData() {
        return userLoginData;
    }

    public void setUserLoginData(UserLoginData userLoginData) {
        this.userLoginData = userLoginData;
    }


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

    public Long getId() {
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

    public Picture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Picture profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Picture getBackPicture() {
        return backPicture;
    }

    public void setBackPicture(Picture backPicture) {
        this.backPicture = backPicture;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Set<Picture> getProfilePictures() {
        return profilePictures;
    }

    public void setProfilePictures(Set<Picture> profilePictures) {
        this.profilePictures = profilePictures;
    }

    public Set<User> getUserFriends() {
        return userFriends;
    }

    public void setUserFriends(Set<User> userFriends) {
        this.userFriends = userFriends;
    }

    public Set<FriendRequest> getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(Set<FriendRequest> friendRequests) {
        this.friendRequests = friendRequests;
    }
}
