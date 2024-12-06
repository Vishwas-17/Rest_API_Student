package batch.e3.student_crud_boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import batch.e3.student_crud_boot.dto.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

	boolean existsByMobile(long mobile);

	List<Student> findByName(String name);

	List<Student> findByMobile(long mobile);

	List<Student> findByResult(String result);

	

}
