package ru.project.fitstyle.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "subscription")
public class Subscription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(optional=false, cascade = CascadeType.ALL)
	@JoinColumn(name = "subscriptionType_id")
	private SubscriptionType subscriptionType;

	@NotBlank
	private java.sql.Date beginDate;

	@NotBlank
	private java.sql.Date endDate;

	@NotBlank
	@Size(max = 16)
	private String contract;

	//@OneToOne(optional = false, mappedBy = "subscription")
	//private User owner;

	public Subscription() {

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

	public java.sql.Date getBeginDate() { return beginDate; }

	public void setBeginDate(java.sql.Date beginDate) {
		this.beginDate = beginDate;
	}

	public java.sql.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	//public User getOwner() {
	//	return owner;
	//}

	//public void setOwner(User owner) {
	//	this.owner = owner;
	//}
}