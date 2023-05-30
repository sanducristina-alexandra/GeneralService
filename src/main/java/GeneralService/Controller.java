package GeneralService;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    @GetMapping("/data/{id}")
    public String getData(@PathVariable("id") String id) {
        return "Data with ID " + id;
    }

    @PostMapping("/data")
    public String processData(@RequestBody String data) {
        return "Received data: " + data;
    }

    @PutMapping("/data/{id}")
    public String updateData(@PathVariable("id") String id, @RequestBody String data) {
        return "Updated data with ID " + id + ": " + data;
    }

    @PatchMapping("/data/{id}")
    public String patchData(@PathVariable("id") String id, @RequestBody String data) {
        return "Patched data with ID " + id + ": " + data;
    }

}
