package Services;

import Entities.Human;
import Exceptions.HumanNotFoundException;
import Repositories.HumanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class HumanServiceTest {

    private final HumanRepository humanRepository = Mockito.mock(HumanRepository.class);
    private HumanService humanService;

    @BeforeEach
    void setHumanService() {
        humanService = new HumanService(humanRepository);
    }

    @Test
    void createHuman() {
        Human human = new Human("Ihor", "Fedorchenko", (byte)20);
        when(humanRepository.save(any(Human.class))).then(returnsFirstArg());
        Human savedHuman = humanService.createHuman(human);
        assertThat(savedHuman.getName()).isNotNull();
    }

    @Test
    void findHumanById() {
        Human human = new Human("Ihor", "Fedorchenko", (byte)20);
        when(humanRepository.save(any(Human.class))).then(returnsFirstArg());
        Human savedHuman = humanService.createHuman(human);
        when(humanRepository.findById(savedHuman.getId())).thenReturn(Optional.of(savedHuman));
        Human foundHuman = humanService.findHumanById(savedHuman.getId()).orElseThrow(() -> new HumanNotFoundException(savedHuman.getId()));
        assertThat(foundHuman).isEqualTo(savedHuman);
    }
}