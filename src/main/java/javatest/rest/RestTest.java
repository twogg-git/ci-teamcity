package javatest.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class RestTest {

    @RequestMapping("/")
    public String entryPoint() {
        return "Java REST app ready to CI TeamCity!!! "+new Date().toString();
    }

    @RequestMapping("/hello")
    public String helloRest() {
        return "Hello endpoint responding OK!";
    }

    @RequestMapping(value = "/test/{idTest}", method = RequestMethod.GET, produces = "application/json")
    public String testRest(@PathVariable String idTest) {
        if(idTest != null){
            return "Calling for test id : "+idTest+" at: "+new Date().toString();
        } else{
            return "You must provide a test ID: "+new Date().toString();
        }
    }

}
