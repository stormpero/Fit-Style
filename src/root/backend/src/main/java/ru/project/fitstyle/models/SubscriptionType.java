package ru.project.fitstyle.models;


import javax.persistence.*;

@Entity
@Table(name = "subscriptionType")
public class SubscriptionType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Size(max = 3)
	private String validity;

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private ESubsPlacementTime placementTime;

	@NotBlank
	@Size(max = 5)
	private String coast;

	@OneToOne(optional = false, mappedBy = "subscriptionType")
	private Subscription owner;

	public SubscriptionType() {

	}

	public SubscriptionType(ESubsPlacementTime name) {
		this.placementTime = placementTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Subscription getOwner() {
		return owner;
	}

	public void setOwner(Subscription owner) {
		this.owner = owner;
	}
}