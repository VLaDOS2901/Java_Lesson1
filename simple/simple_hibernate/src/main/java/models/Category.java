package models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_categories")
public class Category /*extends BaseModel*/{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dateCreate;
    protected boolean isDelete;

    @Column(length = 500, nullable = false)
    private String name;
    @Column(length = 200)
    private String image;
    @OneToMany(mappedBy = "category")// створює зв'зок один до багатьох з таблицею tbl_products
    private List<Product> products;
    public Category(){
        products = new ArrayList<>();
    }

    public Category(String name, String image, Date dateCreate, boolean isDelete) {
        super(); //виклик конструктора по замовчуванню
        this.name = name;
        this.image = image;
        this.dateCreate = dateCreate;
        this.isDelete = isDelete;
    }
}
