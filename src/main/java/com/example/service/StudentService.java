package com.example.service;

import com.example.model.Student;
import com.example.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService implements IStudentService{
    @Autowired
    private IStudentRepository iStudentRepository;

    @Override
    public List<Student> findAll() {
        return iStudentRepository.findAll();
    }

    @Override
    public void save(Student student) {
     iStudentRepository.save(student);
    }

    @Override
    public Student findById(Long id) {
        return iStudentRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iStudentRepository.remove(id);

    }

    @Override
    public List<Student> findSByName(String name) {
        return iStudentRepository.findSByName(name);
    }
}
