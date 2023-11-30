package com.fauzan.springboot.springBootFauzan.model;

import java.util.Date;

import javax.persistence.*;

@Entity
public class PurchaseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_price")
    private Double productPrice;

    @Column(name = "purchase_date")
    private Date purchaseDate;


    @Column(name = "product_imageUrl")
    private String imageUrl;

    public PurchaseHistory() {
       
    }

   

    public PurchaseHistory(Long id, User user, Long productId, String productName, String productDescription,
			Double productPrice, Date purchaseDate) {
		super();
		this.id = id;
		this.user = user;
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.purchaseDate = purchaseDate;
	}



	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }



    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
