package facebook.entity;

import javax.persistence.*;

@Entity
@Table(name = "friends_requests")
public class FriendRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "requester_id", nullable = false, unique = false)
    private User requester;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false, unique = false)
    private User receiver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}