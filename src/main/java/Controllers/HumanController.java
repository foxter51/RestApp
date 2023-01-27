package Controllers;

import Entities.Human;
import Services.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HumanController {

    private final HumanService humanService;

    @Autowired
    public HumanController(HumanService humanService) {
        this.humanService = humanService;
    }

    @GetMapping("/humans/{id}")
    Human getHumanInfo(@PathVariable Long id){
        return humanService.getHumanById(id);
    }
}
