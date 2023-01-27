package Services;

import Entities.Human;
import Repositories.HumanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HumanService {

    private final HumanRepository humanRepository;

    @Autowired
    public HumanService(HumanRepository humanRepository){
        this.humanRepository = humanRepository;
    }

    public void createHuman(Human human){
        humanRepository.save(human);
    }

    public Optional<Human> findHumanById(Long id){
        return humanRepository.findById(id);
    }

}
