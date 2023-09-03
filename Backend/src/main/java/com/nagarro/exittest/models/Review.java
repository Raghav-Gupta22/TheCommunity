package com.nagarro.exittest.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Review implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private int rating;
	private String title;

	private String description;
	private Long productId;
	private Boolean approved = false;

	public Review() {
		super();
	}

	public Review(@NotNull @Min(1) @Max(5) int rating, String heading, @Size(min = 20, max = 400) String review,
				  Long productId) {
		super();
		this.rating = rating;
		this.title = heading;
		this.description = review;
		this.productId = productId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String heading) {
		this.title = heading;
	}

	public String getReview() {
		return description;
	}

	public void setReview(String review) {
		this.description = review;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public long getId() {
		return id;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

}
