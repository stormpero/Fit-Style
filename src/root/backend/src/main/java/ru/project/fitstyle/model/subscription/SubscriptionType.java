package ru.project.fitstyle.model.subscription;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subscription_type")
public class SubscriptionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "name", length = 200,
            nullable = false)
    private String name;

    @Column(name = "validity",
            nullable = false)
    private int validityMonths;

    @Enumerated(EnumType.STRING)
    @Column(name = "placement_time", length = 10,
            nullable = false)
    private ESubsPlacementTime placementTime;

    @Column(name = "cost", length = 5,
            nullable = false)
    private String cost;

    @JsonIgnore
    @OneToMany(mappedBy = "subscriptionType", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Subscription> owner;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Subscription> getOwner() {
        return owner;
    }

    public void setOwner(List<Subscription> owner) {
        this.owner = owner;
    }

    public int getValidityMonths() {
        return validityMonths;
    }

    public void setValidityMonths(int validityMonths) {
        this.validityMonths = validityMonths;
    }
}