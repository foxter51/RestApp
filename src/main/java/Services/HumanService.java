package Services;

import Entities.Human;
import Repositories.HumanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Human getHumanById(Long id){
        return humanRepository.getHumanById(id);
    }

}
