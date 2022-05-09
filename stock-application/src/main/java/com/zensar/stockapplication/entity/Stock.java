package com.zensar.stockapplication.entity;



import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

//import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


@Entity(name="MyStock")
@Table(name="Stock")
/*@NamedQueries(value = {
@NamedQuery(name = "Stock.findStockByItsName", query = "FROM MyStock s where s.name=?1"),
@NamedQuery(name = "Stock.findStockByItsNameAndPrice", query = "FROM MyStock s where s.name=?1 and s.price=?2")})*/
//?1 is a place holder,s.name is a property, stock is the entity
public class Stock implements Serializable{
//@ApiModelProperty("StockId of integer type")
//@id is used as a primary key for the database purpose
@GeneratedValue(strategy=GenerationType.AUTO)

//@Column(name="Id")


    @Id
	private long stockId;
	private String name;
	private String marketName;
	private double price;

}
