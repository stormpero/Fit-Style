package ru.project.fitstyle.models;


import javax.persistence.*;

@Entity
@Table(name = "subscription")
public class Subscription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(optional=false, cascade = CascadeType.ALL)
	@JoinColumn(name = "subscriptionType_id")
	private SubscriptionType subscriptionType;

	@NotBlank
	private java.sql.Date begindate;

	@NotBlank
	private java.sql.Date enddate;

	@NotBlank
	@Size(max = 16)
	private String contract;

	@OneToOne(optional = false, mappedBY = "subscription")
	private User owner;

	public Role() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public java.sql.Date getBegindate() { return begindate; }

	public void setBegindate(java.sql.Date begindate) {
		this.begindate = begindate;
	}

	public java.sql.Date getEnddate() {
		return enddate;
	}

	public void setEnddate(java.sql.Date enddate) {
		this.enddate = enddate;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}