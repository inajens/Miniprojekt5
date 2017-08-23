package com.example.demo.Controller;

import com.example.demo.Domain.QueueItem;
import com.example.demo.Interfaces.QueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;

@Controller
public class QueueController {

    @Autowired
    private QueueRepository queueRepository;


    @ResponseBody
    @GetMapping("/login")
    public ModelAndView Index() {
        return new ModelAndView("Index");
    }

    @PostMapping("/login")
    public String verifyUser(HttpSession session, @RequestParam String username, @RequestParam String password) {
        if (queueRepository.verifyUser(username,password)) {
           session.setAttribute("user",username);
           return "redirect:/queue";
        }
        return "redirect:/login";
    }

    @GetMapping("/queue")
    public ModelAndView Queue(HttpSession session) {
        if(session.getAttribute("user")!=null) {
            return new ModelAndView("Queue")
                    .addObject("queue", queueRepository.getQueueItems());
        }
        else {
            return new ModelAndView ("Index");
        }
    }

    @PostMapping("/delete/{id}")
    public String RefreshQueue(@PathVariable int id) {
        queueRepository.deleteItem(id);
        return "redirect:/queue";
    }

    @PostMapping("/help/{id}")
    public String RefreshStatus(@PathVariable int id) {
        queueRepository.chooseItem(id);
        return "redirect:/queue";
    }

    @GetMapping("/submissions")
    public ModelAndView Submissions(HttpSession session) {
        if(session.getAttribute("user") !=null) {
            return new ModelAndView("Submissions");
        }
        else {
            return new ModelAndView ("Index");
        }
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
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session, HttpServletResponse res) {
        session.invalidate();
        Cookie cookie = new Cookie("jsessionid", "");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
        return new ModelAndView("Index");
    }
}
