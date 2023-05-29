package org.example;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @PostMapping("/request")
    public String processRequest(@RequestBody Request request) {
        if (!Verifiers.verifyUserId(request.getUserId()))
            return "Request denied. User not authorized. \n" + request.toString();

        if (!RequestProcessor.processRequest(request))
            return "Request processing failed. \n" + request.toString();
        else
            return "Request processed. \n" + request.toString();
    }

}
