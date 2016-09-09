package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Collection;


/**
 * The persistent class for the actor database table.
 * 
 */
@Entity
@Table(name="film")
@NamedQuery(name="Film.findAll", query="SELECT a FROM Film a")
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="film_id", unique=true, nullable=false)
	private int filmId;
	
	@Column(name="title", nullable=false, length=255)
	private String title;

	@Column(name="description", nullable=true, length=65535)
	private String description;

	@Column(name="release_year", nullable=true, length=4)
	private int releaseYear;

	@Column(name="language_id", nullable=false)
	private int languageId;

	@Column(name="original_language_id", nullable=true)
	private int originalLanguageId;

	@Column(name="rental_duration", nullable=false)
	private int rentalDuration;

	@Column(name="rental_rate", nullable=false)
	private double rentalRate;

	@Column(name="length", nullable=true, length=4)
	private int length;

	@Column(name="replacement_cost", nullable=false)
	private double replacementCost;

	@Column(name="rating", nullable=true)
	private String rating;

	@Column(name="special_features", nullable=true)
	private String specialFeatures;

	@Column(name="last_update", nullable=false)
	private Timestamp lastUpdate;

	@JoinTable(name="film_actor" , 
            joinColumns = { 
                   @JoinColumn(name = "actor_id", referencedColumnName = "actor_id")
            }, 
            inverseJoinColumns = { 
                   @JoinColumn(name = "film_id", referencedColumnName = "film_id")
            })
	@ManyToMany 
	private Collection<Actor> actorCollection;

	public Film() {
	}

	public int getFilmId() {
		return this.filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReleaseYear() {
		return this.releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLanguageId() {
		return this.languageId;
	}
	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public int getOriginalLanguageId() {
		return this.originalLanguageId;
	}
	
	public void setOriginalLanguageId(int originalLanguageId) {
		this.originalLanguageId = originalLanguageId;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Collection<Actor> getActorCollection() {
		return this.actorCollection;
	}
	
}