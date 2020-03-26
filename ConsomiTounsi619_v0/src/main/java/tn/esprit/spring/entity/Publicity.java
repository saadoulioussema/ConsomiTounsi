package tn.esprit.spring.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Publicity {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	private Date startDate;
	private Date endDate;

	
}
