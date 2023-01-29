package Controllers;

import Entities.Human;
import Exceptions.HumanNotFoundException;
import Repositories.HumanRepository;
import Services.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class HumanController {

    private final HumanService humanService;

    @Autowired
    public HumanController(HumanRepository humanRepository) {
        this.humanService = new HumanService(humanRepository);
    }

    @GetMapping("/humans/{id}")
    ResponseEntity<Human> getHumanInfo(@PathVariable Long id){
        Optional<Human> human = humanService.findHumanById(id);
        if(human.isPresent()){
            return new ResponseEntity<>(human.get(), HttpStatus.OK);
        }
        else {
            throw new HumanNotFoundException(id);
        }
    }
}
