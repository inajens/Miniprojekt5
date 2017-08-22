package com.example.demo.Controller;

import com.example.demo.Domain.QueueItem;
import com.example.demo.Interfaces.QueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public ModelAndView Queue() {
        return new ModelAndView("Queue")
                .addObject("queue", queueRepository.getQueueItems());
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
}
