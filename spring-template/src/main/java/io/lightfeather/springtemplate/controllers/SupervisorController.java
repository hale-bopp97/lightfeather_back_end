package io.lightfeather.springtemplate.controllers;

import com.google.gson.Gson;

import io.lightfeather.springtemplate.entities.Submit;
import io.lightfeather.springtemplate.entities.Supervisor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Stream;

@RestController
public class SupervisorController {



    @GetMapping("/api/supervisors")
    public String getSupervisors() {

        String uri                      = "https://o3m5qixdng.execute-api.us-east-1.amazonaws.com/api/managers";
        RestTemplate restClient         = new RestTemplate();
        HttpEntity<String> request      = new HttpEntity<String>("");
        ResponseEntity<String> response = restClient.exchange(uri,
                                                              HttpMethod.GET,
                                                              request,
                                                              String.class);

        Gson gson                = new Gson();
        Supervisor[] supervisors = gson.fromJson(response.getBody(),
                Supervisor[].class);

        String[] result = Stream.of(supervisors)
                                .filter(supervisor -> supervisor.isValid())
                                .map(Supervisor::getFormatted)
                                .sorted()
                                .toArray(String[]::new);

        return gson.toJson(result);

    }

    @PostMapping(path = "/api/submit", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<String> postSubmit (Submit submit) throws Exception {

        if (submit.firstName.isEmpty()) {

            return new ResponseEntity<String>("Missing First Name",
                                              HttpStatus.UNPROCESSABLE_ENTITY);

        }

        if (submit.lastName.isEmpty()) {

            return new ResponseEntity<String>("Missing Last Name",
                                              HttpStatus.UNPROCESSABLE_ENTITY);

        }

        if (submit.supervisor.isEmpty()) {

            return new ResponseEntity<String>("Missing Supervisor",
                                              HttpStatus.UNPROCESSABLE_ENTITY);

        }

        System.out.println("NAME: " + submit.firstName + " " + submit.lastName);
        System.out.println("SUPERVISOR: " + submit.supervisor);

        if (submit.phone != null && !submit.phone.isEmpty()) {

            System.out.println("PHONE: " + submit.phone);

        }

        if (submit.email != null && !submit.email.isEmpty()) {

            System.out.println("EMAIL: " + submit.email);

        }

        return new ResponseEntity<String>("SUBMISSION SUCCESSFUL",
                                          HttpStatus.OK);

    }

}