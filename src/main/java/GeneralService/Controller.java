package GeneralService;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

@RestController
public class Controller {

    public List<String> validCarUserIds = new ArrayList<>(Arrays.asList("50075", "15080"));

    @Async
    @PostMapping("/request")
    public Future<Boolean> processRequest(@RequestBody Request request) {
        Boolean result = validCarUserIds.contains(request.getUserId());
        return new AsyncResult<>(result);
    }

}
