package model;

import java.math.BigDecimal;

/*
 *@author Yakovlev Alexandr
 */
public class Product {
    private Long id;
    private Id<Long> productId;
    private String title;
    private BigDecimal cost;

    public Product(Long id, Long productId, String title, BigDecimal cost){
        this.id = id;
        this.productId = new Id<Long> (productId);
        this.title =title;
        this.cost = cost;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id =id;
    }

    public Id<Long> getProductId() {return productId;}

    public void setProductId(Long productId){
        this.productId = new Id<Long>(productId);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public BigDecimal getCost(){
        return cost;
    }

    public void setCost(BigDecimal cost){
        this.cost = cost;
    }

    @Override
    public String toString(){
        return "Product("+
                "id="+id+
                ", productId="+productId+
                ", title" + title+
                "cost=" + cost +
                ')';
    }
}
