package batch.e3.student_crud_boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import batch.e3.student_crud_boot.dto.Student;
import batch.e3.student_crud_boot.service.StudentService;
@RestController
@RequestMapping("/api")
public class StudentController {
	@Autowired
	StudentService service;
	//Adding One Student record at a time
	@PostMapping("/students")
	public ResponseEntity<Object> addStudent(@RequestBody Student student){
		return service.add(student);
	}
	//Adding Multiple Students at a time
	@PostMapping("/students/multiple")
	public ResponseEntity<Object> addMultipleStudents(@RequestBody List<Student> students){
		return service.addMultiple(students);
	}
	
	
	@GetMapping("/students")
	public ResponseEntity<Object> fetchStudents(){
		return service.fetchAll();
	}
	@GetMapping("/students/{id}")
	public ResponseEntity<Object> fetchById(@PathVariable int id){
		return service.fetchById(id);
	}
	
	@GetMapping("/students/name/{name}")
	public ResponseEntity<Object> fetchByName(@PathVariable String name){
		return service.fetchByName(name);
	}
	
	@GetMapping("/students/mobile/{mobile}")
	public ResponseEntity<Object> fetchByMobile(@PathVariable long mobile){
		return service.fetchByMobile(mobile);
		
	}
	@GetMapping("/students/result/{result}")
	public ResponseEntity<Object> fetchByResult(@PathVariable String result){
		return service.fetchByResult(result);
	}
	
	@DeleteMapping("/students/delete")
	public ResponseEntity<Object> DeleteAllRecords(){
		return service.deleteAllRecords();
	}
	@DeleteMapping("/students/delete/{id}")
	public ResponseEntity<Object> DeleteRecordById(@PathVariable int id){
		return service.deleteRecordById(id);
	}
	
	@PutMapping("/students/update")
	public ResponseEntity<Object> UpdateRecord(@RequestBody Student student){
		return service.updateRecord(student);
	}
	
	@PatchMapping("/students/update/{id}")
	public ResponseEntity<Object> UpdateRecord(@RequestBody Student student,@PathVariable int id){
		return service.updateRecord(student,id);
	}

}
