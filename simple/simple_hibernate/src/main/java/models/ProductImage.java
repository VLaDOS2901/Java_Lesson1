package models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_product_images")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dateCreated;
    protected boolean isDelete;
    @Column(length = 500, nullable = false)
    private String name;
    private int priority;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false) // об'днує колонки двох різних таблиць. Зв'язок між таблицями tbl_products і tbl_product_images
    private Product product;

    public ProductImage(Date dateCreated, boolean isDelete, String name, int priority, Product product) {
        this.dateCreated = dateCreated;
        this.isDelete = isDelete;
        this.name = name;
        this.priority = priority;
        this.product = product;
    }
}
