package swst.application.entities;

import javax.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "brands")
public class Brands {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codeBrand;

	private String caseBrand;

}
