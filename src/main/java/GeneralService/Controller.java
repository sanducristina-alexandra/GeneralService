package GeneralService;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class Controller {
    public ArrayList<String> validCarUserIds = new ArrayList<>(Arrays.asList("50075", "15080"));

    @PostMapping("/request")
    public Boolean processRequest(@RequestBody Request request) {
        return validCarUserIds.contains(request.getUserId());
    }

}
