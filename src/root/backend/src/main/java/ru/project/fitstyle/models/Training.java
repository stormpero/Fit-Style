package ru.project.fitstyle.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "training")
public class Training {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private Long coachId;

	private Long userId;

	@NotBlank
	private Timestamp trainingDate;

	@NotBlank
	@Size(max = 30)
	private String trainingName;

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private ETrainingType trainingType;

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private ETrainingStatus trainingStatus;

	@ManyToOne(optional=false, cascade = CascadeType.ALL)
	@JoinColumn(name = "trainingUser_id")
	private TrainingUser trainingUser;

	public Training() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCoachId() {
		return coachId;
	}

	public void setCoachId(Long coachId) {
		this.coachId = coachId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public java.sql.Timestamp getTrainingDate() { return trainingDate; }

	public void setTrainingDate(java.sql.Timestamp trainingDate) {
		this.trainingDate = trainingDate;
	}

	public String getTrainingName() {
		return trainingName;
	}

	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}

	public ETrainingType getTrainingType() {
		return trainingType;
	}

	public void setTrainingType(ETrainingType trainingType) {
		this.trainingType = trainingType;
	}

	public ETrainingStatus getTrainingStatus() {
		return trainingStatus;
	}

	public void setTrainingStatus(ETrainingStatus trainingStatus) {
		this.trainingStatus = trainingStatus;
	}

	public TrainingUser getTrainingUser() {
		return trainingUser;
	}

	public void setTrainingUser(TrainingUser trainingUser) {
		this.trainingUser = trainingUser;
	}
}