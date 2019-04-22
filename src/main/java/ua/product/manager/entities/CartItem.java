package ua.product.manager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem extends Item {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    private boolean isAddToOrder;

    public boolean isAddToOrder() {
        return isAddToOrder;
    }

    public void setAddToOrder(boolean addToOrder) {
        isAddToOrder = addToOrder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }
}
