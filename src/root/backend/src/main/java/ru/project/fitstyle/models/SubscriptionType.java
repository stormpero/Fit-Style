package ru.project.fitstyle.models;


import javax.persistence.*;

@Entity
@Table(name = "subscriptionType")
public class SubscriptionType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.INTEGER)
	private ESubsValidity validity;

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private ESubsPlacementTime placementTime;

	@Enumerated(EnumType.INTEGER)
	private ESubsCoast coast;

	@OneToOne(optional = false, mappedBY = "subscriptionType")
	private Subscription owner;

	public SubscriptionType() {

	}

	public SubscriptionType(ESubsValidity name) {
		this.validity = validity;
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

	public ESubsValidity getValidity() {
		return validity;
	}

	public void setValidity(ESubsValidity validity) {
		this.validity = validity;
	}

	public ESubsPlacementTime getPlacementTime() {
		return placementTime;
	}

	public void setPlacementTime(ESubsPlacementTime placementTime) {
		this.placementTime = placementTime;
	}

	public ESubsCoast getCoast() {
		return coast;
	}

	public void setCoast(ESubsCoast coast) {
		this.coast = coast;
	}

	public Subscription getOwner() {
		return owner;
	}

	public void setOwner(Subscription owner) {
		this.owner = owner;
	}
}