package utn.tp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
  
//  @RequestMapping("/")
  
  @GetMapping("/hola")
  public String decirHola() {
    System.out.println("Hola mundo, en consola");
    return "Hola mundo";
  }
  
}
