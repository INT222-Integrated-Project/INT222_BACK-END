package swst.application.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orderdetail")
public class OrderDetail implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderdetailID;

	private int quantityOrder;

	@Column(name = "unitPrice")
	private float unitPrice;

	private long productcolorID;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "orderid", nullable = false)
	@JsonIgnore
	private Orders orders;

}
