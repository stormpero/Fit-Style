package ru.project.fitstyle.model.subscription;


import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "subscription_type")
public class SubscriptionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "validity",
            nullable = false)
    private Date validity;

    @Enumerated(EnumType.STRING)
    @Column(name = "placement_time", length = 10,
            nullable = false)
    private ESubsPlacementTime placementTime;

    @Column(name = "cost", length = 5,
            nullable = false)
    private String cost;

    @OneToMany(mappedBy = "subscriptionType", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Collection<Subscription> owner;

    public SubscriptionType() {
    }

    public SubscriptionType(ESubsPlacementTime placementTime) {
        this.placementTime = placementTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ESubsPlacementTime getPlacementTime() {
        return placementTime;
    }

    public void setPlacementTime(ESubsPlacementTime placementTime) {
        this.placementTime = placementTime;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Collection<Subscription> getOwner() {
        return owner;
    }

    public void setOwner(Collection<Subscription> owner) {
        this.owner = owner;
    }

    public Date getValidity() {
        return validity;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }
}