package ru.project.fitstyle.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "subscription")
public class Subscription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional=false, cascade = CascadeType.ALL)
	@JoinColumn(name = "subscriptionType_id")
	private SubscriptionType subscriptionType;

	@NotBlank(message = "beginDate should not be blank")
	private Date beginDate;

	@NotBlank(message = "endDate should not be blank")
	private Date endDate;

	@NotBlank(message = "contract should not be blank")
	@Size(max = 16, message = "contract should be less or equal than 16 chars")
	private String contract;

	public Subscription() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public Date getBeginDate() { return beginDate; }

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}
}