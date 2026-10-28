package com.vj.controller;

import com.vj.dto.StudentSummary;
import com.vj.model.Grade;
import com.vj.model.Student;
import com.vj.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // ---------- Summary report ----------

    @GetMapping
    public String list(@RequestParam(defaultValue = "") String q, Model model) {
        List<StudentSummary> summaries = service.summaries(q);
        model.addAttribute("students", summaries);
        model.addAttribute("stats", service.classStats(summaries));
        model.addAttribute("q", q);
        return "students/list";
    }

    // ---------- Create / edit student ----------

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("action", "/students");
        return "students/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("student") Student student, BindingResult result,
                         Model model, RedirectAttributes flash) {
        if (!result.hasFieldErrors("rollNumber") && service.isRollNumberTaken(student.getRollNumber(), null)) {
            result.rejectValue("rollNumber", "duplicate", "This roll number is already in use");
        }
        if (result.hasErrors()) {
            model.addAttribute("action", "/students");
            return "students/form";
        }
        Student saved = service.create(student);
        flash.addFlashAttribute("message", saved.getName() + " was added. Add their grades below.");
        return "redirect:/students/" + saved.getId();
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", service.get(id));
        model.addAttribute("action", "/students/" + id);
        return "students/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("student") Student student,
                         BindingResult result, Model model, RedirectAttributes flash) {
        if (!result.hasFieldErrors("rollNumber") && service.isRollNumberTaken(student.getRollNumber(), id)) {
            result.rejectValue("rollNumber", "duplicate", "This roll number is already in use");
        }
        if (result.hasErrors()) {
            student.setId(id);
            model.addAttribute("action", "/students/" + id);
            return "students/form";
        }
        service.update(id, student);
        flash.addFlashAttribute("message", "Student details saved.");
        return "redirect:/students/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes flash) {
        service.delete(id);
        flash.addFlashAttribute("message", "Student deleted.");
        return "redirect:/students";
    }

    // ---------- Student detail & grades ----------

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        populateDetail(id, model);
        model.addAttribute("grade", new Grade());
        return "students/detail";
    }

    @PostMapping("/{studentId}/grades")
    public String addGrade(@PathVariable("studentId") Long studentId,
                           @Valid @ModelAttribute("grade") Grade grade,
                           BindingResult result, Model model, RedirectAttributes flash) {
        if (result.hasErrors()) {
            populateDetail(studentId, model);
            return "students/detail";
        }
        service.addGrade(studentId, grade);
        flash.addFlashAttribute("message", "Grade added.");
        return "redirect:/students/" + studentId;
    }

    @PostMapping("/{id}/grades/{gradeId}/delete")
    public String deleteGrade(@PathVariable Long id, @PathVariable Long gradeId, RedirectAttributes flash) {
        service.deleteGrade(id, gradeId);
        flash.addFlashAttribute("message", "Grade removed.");
        return "redirect:/students/" + id;
    }

    private void populateDetail(Long id, Model model) {
        Student student = service.get(id);
        model.addAttribute("student", student);
        model.addAttribute("summary", service.summarize(student));
    }
}
