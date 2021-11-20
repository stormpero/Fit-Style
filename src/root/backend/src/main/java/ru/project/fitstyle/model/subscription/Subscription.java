package ru.project.fitstyle.model.subscription;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional=false, cascade = CascadeType.ALL)
    @JoinColumn(name = "subscription_type_id")
    private SubscriptionType subscriptionType;

    @NotBlank(message = "beginDate should not be blank")
    @Column(name = "begin_date")
    private Date beginDate;

    @NotBlank(message = "endDate should not be blank")
    @Column(name = "end_date")
    private Date endDate;

    @NotBlank(message = "contract should not be blank")
    @Size(max = 16, message = "contract should be less or equal than 16 chars")
    @Column(name = "contract")
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