//package com.sms.demo.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.sms.demo.model.Student;
//
//public interface StudentRepository  extends JpaRepository<Student, Integer>{
//
//}
package com.sms.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.sms.demo.model.Student;

public interface StudentRepository extends MongoRepository<Student, String> {
    // You can add custom Mongo queries here if needed
}

