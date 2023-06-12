package GeneralService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private List<Integer> validCarUserIds;

    @PostMapping("/request")
    public Boolean processRequest(@RequestBody Request request) {
        return validCarUserIds.contains(request.getUserId());
    }

}
