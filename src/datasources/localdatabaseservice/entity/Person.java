package datasources.localdatabaseservice.entity;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import datasources.localdatabaseservice.dao.LifeCoachDao;
import systemlogic.businesslogicservices.dto.HealthProfileDto;
import systemlogic.businesslogicservices.dto.MeasureTypeDto;
import systemlogic.businesslogicservices.dto.PersonDto;
/**
 * The persistent class for the "Person" database table.
 * 
 */
@Entity // indicates that this class is an entity to persist in DB
@Table(name = "Person") // to whole table must be persisted
@NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p ORDER BY p.idPerson ASC ")
@XmlRootElement
public class Person implements Serializable {

	private static final long serialVersionUID = -7439379398075877771L;
	@Id // defines this attributed as the one that identifies the entity
	@GeneratedValue(generator = "sqlite_person")
	@TableGenerator(name = "sqlite_person", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "Person")
	@Column(name = "idPerson")
	private int idPerson;
	@Column(name = "lastname")
	private String lastname;
	@Column(name = "name")
	private String name;

	@Temporal(TemporalType.DATE) // defines the precision of the date attribute
	@Column(name = "birthdate")
	private Date birthdate;

	public int getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthdate() {

		return birthdate;
	}

	public void setBirthdate(Date birthdate) {

		this.birthdate = birthdate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPerson;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (idPerson != other.idPerson)
			return false;
		return true;
	}



}