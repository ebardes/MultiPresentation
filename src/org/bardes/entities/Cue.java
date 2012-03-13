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
@Table(name="cues")
public class Cue 
{
	@Id
	@Column(name="cue")
	BigDecimal cue;
	
	@OneToMany(cascade = ALL)
	@JoinColumn
	List<Slide> slides;
}
