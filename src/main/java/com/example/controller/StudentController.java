package com.example.controller;

import com.example.model.Student;
import com.example.model.StudentForm;
import com.example.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/students")
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @Value("${file_upload}")
    private String fileUpload;
    @GetMapping(value = "")
    public String home(Model model){
        List<Student>students=studentService.findAll();
        model.addAttribute("students",students);
        return "/home";
    }
    @GetMapping("/create")
    public String create(Model model){

        model.addAttribute("student",new StudentForm());
        return "/create";
    }
    @PostMapping("/save")
    public String save(StudentForm student){
        MultipartFile file =student.getImage();
        String nameImage = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + nameImage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Student student1=new Student(student.getId(),student.getName(),student.getAddress(),nameImage);
        studentService.save(student1);
        return "redirect:/students";
    }
    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable Long id,Model model){
        model.addAttribute("student",studentService.findById(id));
        return "/update";
    }
    @PostMapping("/update")
    public String update(StudentForm student,RedirectAttributes redirectAttributes){
        MultipartFile file =student.getImage();
        String nameImage = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + nameImage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Student student1=new Student(student.getId(),student.getName(),student.getAddress(),nameImage);
        studentService.save(student1);
        redirectAttributes.addFlashAttribute("message","Update Success");
        return "redirect:/students";
    }
    @GetMapping("/delete/{id}")
    public String delete(Student student,RedirectAttributes redirectAttributes){
        studentService.remove(student.getId());
        redirectAttributes.addFlashAttribute("message","Remove Success");
        return "redirect:/students";
    }
    @GetMapping("/search")
    public ModelAndView search(@RequestParam ("search") String search){
        List<Student> studentListSearch=studentService.findSByName(search);
        ModelAndView modelAndView=new ModelAndView("home");
        modelAndView.addObject("students",studentListSearch);
        return modelAndView;
    }
    @GetMapping("/information/{id}")
    public String view(@PathVariable long id, Model model){
        model.addAttribute("student",studentService.findById(id));
        return "/information";
    }




}
