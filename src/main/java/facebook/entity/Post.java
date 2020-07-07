package facebook.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "image_id", nullable = false)
    private long imageId;

    @Column(name = "text")
    private String text;


    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User poster;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private Set<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private Set<Like> likes;

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public void setLikes(Set<Like> likes) {
        this.likes = likes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getPoster() {
        return poster;
    }

    public void setPoster(User poster) {
        this.poster = poster;
    }

    public Post() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
