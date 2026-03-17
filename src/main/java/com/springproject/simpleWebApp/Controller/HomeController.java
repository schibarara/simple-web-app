package com.springproject.simpleWebApp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
@RestController
public class HomeController {
    @RequestMapping("/")
  public String greet(){
      return("welcome ....");
  }

    @RequestMapping("/about")
    public String greet1(){
        return("welcome to about....");
    }


}
