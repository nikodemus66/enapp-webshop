package ch.hslu.edu.enapp.webshop.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String description;

	private String mediapath;

	private String name;

	private BigDecimal unitprice;

	public ProductDTO() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMediapath() {
		return this.mediapath;
	}

	public void setMediapath(String mediapath) {
		this.mediapath = mediapath;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getUnitprice() {
		return this.unitprice;
	}

	public void setUnitprice(BigDecimal unitprice) {
		this.unitprice = unitprice;
	}

}