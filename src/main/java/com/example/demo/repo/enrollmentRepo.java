package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.ENROLLMENT;

public interface enrollmentRepo extends CrudRepository<ENROLLMENT,String>{

	@Query(value = "SELECT  top 1 * FROM Enrollment WHERE  user_id = ?1 and course_id=?2", nativeQuery = true)
	ENROLLMENT findByCourseIdAndUserId(String userName,String courseId);
}
