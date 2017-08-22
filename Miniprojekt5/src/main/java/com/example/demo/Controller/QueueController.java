package com.example.demo.Controller;

import com.example.demo.Domain.QueueItem;
import com.example.demo.Interfaces.QueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

@Controller
public class QueueController {

    @Autowired
    private QueueRepository queueRepository;

    @ResponseBody
    @GetMapping("/queue")
    public String Queue() {
        return "Yay!";
        //return new ModelAndView("Queue");
//                .addObject("name", studentName)
//                .addObject("question", question)
//                .addObject("location", location)
//                .addObject("teacher1", teacher1)
//                .addObject("teacher2", teacher2)
//                .addObject("anyTeacher", anyTeacher);
    }

    @GetMapping("/submissions")
    public ModelAndView Submissions() {
        return new ModelAndView("Submissions");
    }

    @PostMapping("/submissions")
    public String queueItem(@RequestParam String studentName, @RequestParam String location, @RequestParam String question)
                                  //@RequestParam boolean teacher1, @RequestParam boolean teacher2, @RequestParam boolean anyTeacher)
                            {

        queueRepository.addItem(studentName, location, question, true, false, false);
        return "redirect:/queue";
    }
}
