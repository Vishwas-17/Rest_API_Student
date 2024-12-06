package batch.e3.student_crud_boot.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String standard;
	private long mobile;
	private int maths;
	private int science;
	private int english;
	private double percentage;
	private String result;

}
