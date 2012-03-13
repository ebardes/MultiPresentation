package org.bardes.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

@Entity
@Table(name="slides")
public class Slide 
{
	@Id
	int id;
	
	@ManyToOne
	Cue cue;
	
	String type;
	
	String imageFile;
}
