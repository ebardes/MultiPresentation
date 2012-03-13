package org.bardes.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "cues")
public class Cue {
	@Id
	@Column(name = "cue")
	BigDecimal cue;

	@Column(length = 200)
	String description;

	public BigDecimal getCue() {
		return cue;
	}

	public void setCue(BigDecimal cue) {
		this.cue = cue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Slide> getSlides() {
		return slides;
	}

	public void setSlides(List<Slide> slides) {
		this.slides = slides;
	}

	@OneToMany(cascade = ALL)
	@JoinColumn
	List<Slide> slides;
}
