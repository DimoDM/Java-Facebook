package facebook.entity;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
public class PictureForPosts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "imager_url")
    private String imageURL;

    @Column(name = "status")
    private Integer status;//1-Visible for all; 2-Visible to friends; 3-visible only for the user

    public PictureForPosts() {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
