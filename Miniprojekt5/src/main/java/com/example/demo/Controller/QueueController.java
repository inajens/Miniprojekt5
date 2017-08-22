package com.example.demo.Controller;

import com.example.demo.Domain.QueueItem;
import com.example.demo.Interfaces.QueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

@Controller
public class QueueController {

    @Autowired
    private QueueRepository queueRepository;


    @ResponseBody
    @GetMapping("/queue")
    public ModelAndView Queue() {
        return new ModelAndView("Queue")
                .addObject("queue", queueRepository.getQueueItems());
    }

    @PostMapping("/delete/{id}")
    public String RefreshQueue(@PathVariable int id) {
        queueRepository.deleteItem(id);
        return "redirect:/queue";
    }

    @GetMapping("/submissions")
    public ModelAndView Submissions() {
        return new ModelAndView("Submissions");
    }

    @PostMapping("/submissions")
    public String queueItem(@RequestParam String studentName, @RequestParam String location, @RequestParam String question)
                                  //@RequestParam boolean teacher1, @RequestParam boolean teacher2, @RequestParam boolean anyTeacher)
                            {

        queueRepository.addItem(studentName, location, question);
        return "redirect:/queue";
    }

    @GetMapping("/newuser")
    public ModelAndView newUser() {
        return new ModelAndView("Newuser");
    }

    @PostMapping("/newuser")
    public String addUser(@RequestParam String studentName, @RequestParam String username, @RequestParam String password) {
        queueRepository.addUser(studentName, username, password);
        return "redirect:/queue";
    }
}
