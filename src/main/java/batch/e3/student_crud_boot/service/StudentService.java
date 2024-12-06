package batch.e3.student_crud_boot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import batch.e3.student_crud_boot.dto.Student;
import batch.e3.student_crud_boot.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository repository;

	public ResponseEntity<Object> add(Student student) {
		double percentage=((student.getMaths()+student.getScience()+student.getEnglish())/3.0);
		student.setPercentage(percentage);
		if(student.getEnglish()>35 && student.getMaths()>35 && student.getScience()>35) {
			if(student.getPercentage()>35 && student.getPercentage()<=50) {
				student.setResult("Third Class");
			}
			else if(student.getPercentage()>50 && student.getPercentage()<=60){
				student.setResult("Second Class");
			}
			else if(student.getPercentage()>60 && student.getPercentage()<=84) {
				student.setResult("First Class");
			}
			else if(student.getPercentage()<35){
				student.setResult("Failed");	
			}
			else {
				student.setResult("Distinction");
			}
		}
		else {
			student.setResult("Failed");
			
		}
		
		
		
		if(!repository.existsByMobile(student.getMobile())) {
			repository.save(student);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("message", "Data created Success");
			map.put("statusCode",201);
			map.put("data", student);
			return new ResponseEntity<Object>(map,HttpStatus.CREATED);
		}
		else {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("error", "Mobile Number Already exists");
			return new ResponseEntity<Object>(map,HttpStatus.UNPROCESSABLE_ENTITY);
			
		}
		
	}

	public ResponseEntity<Object> fetchAll() {
		List<Student> students=repository.findAll();
		if(students.isEmpty()) {
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("empty","No records found");
			return new ResponseEntity<Object>(map,HttpStatus.UNPROCESSABLE_ENTITY);
		}
		else {
			 
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("data", students);
			return new ResponseEntity<Object>(map,HttpStatus.OK);
			
		}
   
	}

	public ResponseEntity<Object> fetchById(int id) {
		 Optional<Student> optional= repository.findById(id);
		 if(optional.isEmpty()) {
			 Map<String, Object> map=new HashMap<String, Object>();
				map.put("error", "The requested ID is not present");
				return new ResponseEntity<Object>(map,HttpStatus.NOT_FOUND);
		 }
		 else {
			 Map<String, Object> map=new HashMap<String, Object>();
				map.put("data", optional);
				return new ResponseEntity<Object>(map,HttpStatus.OK);
		 }
		 
	}

	public ResponseEntity<Object> fetchByName(String name) {
		List<Student> list=repository.findByName(name);
		if(list.isEmpty()) {
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("error", "The Requested name is not present in database");
			return new ResponseEntity<Object>(map,HttpStatus.NOT_FOUND);
		}
		else {
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("data", list);
			return new ResponseEntity<Object>(map,HttpStatus.OK);
			
		}
	}

	public ResponseEntity<Object> fetchByMobile(long mobile) {
		List<Student> list=repository.findByMobile(mobile);
		if(list.isEmpty()) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("error", "The requested Mobile Number Object does'nt Exists in database");
			return new ResponseEntity<Object>(map,HttpStatus.NOT_FOUND);
		}
		else {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("data", list);
			return new ResponseEntity<Object>(map,HttpStatus.OK);
			
		}
	}

	public ResponseEntity<Object> fetchByResult(String result) {
		List<Student> list=repository.findByResult(result);
		if(list.isEmpty()) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("error", "The result is not present");
			return new ResponseEntity<Object>(map,HttpStatus.NOT_FOUND);
		}
		else {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("data", list);
			return new ResponseEntity<Object>(map,HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> deleteAllRecords() {
		repository.deleteAll();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("Deleted", "All Records Deleted Successfully");
		return new ResponseEntity<Object>(map,HttpStatus.OK);
	}

	public ResponseEntity<Object> deleteRecordById(int id) {
		if(!repository.findById(id).isEmpty()) {
		repository.deleteById(id);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("message", "Record Deleted Success");
		return new ResponseEntity<Object>(map,HttpStatus.NO_CONTENT);
	}
	else {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("error", "No record with this id");
		return new ResponseEntity<Object>(map,HttpStatus.NOT_FOUND);
		
	}
	
	

	}

	public ResponseEntity<Object> addMultiple(List<Student> students) {
		for(Student student:students) {
			if(repository.existsByMobile(student.getMobile())) {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("error", "Mobile Number already exists");
				return new ResponseEntity<Object>(map,HttpStatus.UNPROCESSABLE_ENTITY);
			}
			else {
				double percentage=((student.getMaths()+student.getScience()+student.getEnglish())/3.0);
				student.setPercentage(percentage);
				if(student.getEnglish()>35 && student.getMaths()>35 && student.getScience()>35) {
					if(student.getPercentage()>35 && student.getPercentage()<=50) {
						student.setResult("Third Class");
					}
					else if(student.getPercentage()>50 && student.getPercentage()<=60){
						student.setResult("Second Class");
					}
					else if(student.getPercentage()>60 && student.getPercentage()<=84) {
						student.setResult("First Class");
					}
					else if(student.getPercentage()<35){
						student.setResult("Failed");	
					}
					else {
						student.setResult("Distinction");
					}
				}
				else {
					student.setResult("Failed");
					
				}
				
			}
		}
		repository.saveAll(students);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("message", "Record Saved Successfully");
		map.put("data", students);
		return new ResponseEntity<Object>(map,HttpStatus.CREATED);
		   
		
		
			
		}

	public ResponseEntity<Object> updateRecord(Student student) {
		double percentage=((student.getMaths()+student.getScience()+student.getEnglish())/3.0);
		student.setPercentage(percentage);
		if(student.getEnglish()>35 && student.getMaths()>35 && student.getScience()>35) {
			if(student.getPercentage()>35 && student.getPercentage()<=50) {
				student.setResult("Third Class");
			}
			else if(student.getPercentage()>50 && student.getPercentage()<=60){
				student.setResult("Second Class");
			}
			else if(student.getPercentage()>60 && student.getPercentage()<=84) {
				student.setResult("First Class");
			}
			else if(student.getPercentage()<35){
				student.setResult("Failed");	
			}
			else {
				student.setResult("Distinction");
			}
		}
		else {
			student.setResult("Failed");
			
		}
		repository.save(student);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("message", "Record Updated Successfully");
		return new ResponseEntity<Object>(map,HttpStatus.OK);
				
		
	}

	public ResponseEntity<Object> updateRecord(Student student, int id) {
		if(repository.findById(id).isEmpty()) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("error", "No Data Present with id"+id);
			return new ResponseEntity<Object>(map,HttpStatus.NOT_FOUND);
		}
		else {
			Student student1=repository.findById(id).get();
			if(student.getName()!=null)
				student1.setName(student.getName());
			if(student.getScience()!=0)
				student1.setEnglish(student.getEnglish());
			if(student.getMaths()!=0) 
				student1.setMaths(student.getMaths());
			if(student.getEnglish()!=0) 
				student1.setEnglish(student.getEnglish());
			if(student.getStandard()!=null)
				student1.setStandard(student.getStandard());
			if(student.getMobile()!=0)
				student1.setMobile(student.getMobile());
			
			double percentage=((student1.getMaths()+student1.getScience()+student1.getEnglish())/3.0);
			student1.setPercentage(percentage);
			if(student1.getEnglish()>35 && student1.getMaths()>35 && student1.getScience()>35) {
				if(student1.getPercentage()>35 && student1.getPercentage()<=50) {
					student1.setResult("Third Class");
				}
				else if(student1.getPercentage()>50 && student1.getPercentage()<=60){
					student1.setResult("Second Class");
				}
				else if(student1.getPercentage()>60 && student1.getPercentage()<=84) {
					student1.setResult("First Class");
				}
				else if(student1.getPercentage()<35){
					student1.setResult("Failed");	
				}
				else {
					student1.setResult("Distinction");
				}
			}
			else {
				student1.setResult("Failed");
				
			}
			repository.save(student1);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("message", "Record Updated Successfully");
			return new ResponseEntity<Object>(map,HttpStatus.OK);
				
			}
		
	}
}
	

