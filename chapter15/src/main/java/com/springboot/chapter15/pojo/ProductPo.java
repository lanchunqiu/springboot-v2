package com.springboot.chapter15.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @Author lancq
 * @Description 商品POJO
 * @Date 2019/1/12
 **/
@Alias("product")
public class ProductPo implements Serializable {

    private Long id;
    private String productName;
    private int stock;
    private double price;
    private int version;
    private String note;
    /**** setter and getter ****/
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "ProductPo{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                ", version=" + version +
                ", note='" + note + '\'' +
                '}';
    }
}
