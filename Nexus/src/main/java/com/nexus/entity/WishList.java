package com.nexus.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "wishlist")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "renter_id", unique = true)
    private User renter;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "wishlist_office", // Name of the join table
            joinColumns = @JoinColumn(name = "wishlist_id"), // Column for WishList ID
            inverseJoinColumns = @JoinColumn(name = "office_id") // Column for Office ID
    )
    private Set<Office> offices = new HashSet<>();

    public WishList() {
    }

    public WishList(User renter) {
        this.renter = renter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getRenter() {
        return renter;
    }

    public void setRenter(User renter) {
        this.renter = renter;
    }

    public Set<Office> getOffices() {
        return offices;
    }

    public void setOffices(Set<Office> offices) {
        this.offices = offices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishList wishList = (WishList) o;
        return Objects.equals(getRenter(), wishList.getRenter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRenter());
    }

    @Override
    public String toString() {
        return "WishList{" +
                "id=" + id +
                ", renter=" + renter +
                ", offices=" + offices +
                '}';
    }
}
