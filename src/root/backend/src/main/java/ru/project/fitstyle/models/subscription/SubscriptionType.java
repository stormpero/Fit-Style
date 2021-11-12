package ru.project.fitstyle.models.subscription;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "subscriptionType")
public class SubscriptionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "validity should not be empty")
    @Size(max = 3, message = "validity should be less or equal than 3 chars")
    private String validity;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private ESubsPlacementTime placementTime;

    @NotBlank(message = "cost should not be blank")
    @Size(max = 5, message = "cost should be less or equal than 5 chars")
    private String cost;

    @OneToMany(mappedBy = "subscriptionType", fetch = FetchType.EAGER)
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

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
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
}