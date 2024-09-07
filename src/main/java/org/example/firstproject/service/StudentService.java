package org.example.firstproject.service;

import lombok.RequiredArgsConstructor;
import org.example.firstproject.exception.StudentAlreadyExistsException;
import org.example.firstproject.exception.StudentNotFoundException;
import org.example.firstproject.model.Student;
import org.example.firstproject.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {
    private final StudentRepository studentRepository;
    @Override
    public Student addStudent(Student student) {
        if(studentAlreadyExists(student.getEmail())){
            throw new StudentAlreadyExistsException(student.getEmail()+"already exists");
        }
        return studentRepository.save(student);
    }



    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        return studentRepository.findById(Math.toIntExact(id)).map(st ->{
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setEmail(student.getEmail());
            st.setDepartment(student.getDepartment());
            return studentRepository.save(st);
        }).orElseThrow(() ->new StudentNotFoundException("sorry, This student counld not found"));
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException("sorry ,student not found with id"+ id));
    }

    @Override
    public void deleteStudent(Long id) {

        if(!studentRepository.existsById(Math.toIntExact(id))){
          throw new StudentNotFoundException("sorry, student not found with id"+ id);
        }

    }
    private boolean studentAlreadyExists(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }
}
