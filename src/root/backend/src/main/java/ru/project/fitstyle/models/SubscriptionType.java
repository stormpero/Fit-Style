package ru.project.fitstyle.models;


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

	@NotBlank
	@Size(max = 3)
	private String validity;

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private ESubsPlacementTime placementTime;

	@NotBlank
	@Size(max = 5)
	private String coast;

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

	public String getCoast() {
		return coast;
	}

	public void setCoast(String coast) {
		this.coast = coast;
	}

	public Collection<Subscription> getOwner() {
		return owner;
	}

	public void setOwner(Collection<Subscription> owner) {
		this.owner = owner;
	}
}