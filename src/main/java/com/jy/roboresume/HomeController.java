package com.jy.roboresume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    ResumeRepository resumeRepository;

    @RequestMapping("/")
    public String listResumes(Model model)
    {
        model.addAttribute("resumes",resumeRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String resumeForm(Model model)
    {
        model.addAttribute("resume",new Resume());
        return "resumeform";
    }

    @GetMapping("/recruit")
    public String recruitList(Model model)
    {
        model.addAttribute("resume",new Resume());
        return "recruiterlist";
    }

    @PostMapping("/process")
    public String processForm(@Valid Resume resume, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "resumeform";
        }
        resumeRepository.save(resume);
        return "redirect:/";
    }

    @RequestMapping("/update/{id}")
    public String updateResume(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("resume", resumeRepository.findById(id).get());
        return "resumeform";
    }

    @RequestMapping("/delete/{id}")
    public String delTodo(@PathVariable("id") long id)
    {
        resumeRepository.deleteById(id);
        return "redirect:/";
    }

}

