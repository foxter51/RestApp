package Controllers;

import Entities.Human;
import Repositories.HumanRepository;
import Services.HumanService;
import com.task.RestApp.RestAppApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(HumanController.class)
@ContextConfiguration(classes = RestAppApplication.class)
class HumanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final HumanRepository humanRepository = Mockito.mock(HumanRepository.class);
    private HumanService humanService;

    @BeforeEach
    void setHumanService() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new HumanController(humanRepository)).build();
        humanService = new HumanService(humanRepository);
    }

    @Test
    void getHumanInfo() throws Exception {
        Human human = new Human("Ihor", "Fedorchenko", (byte)20);
        human.setId(1L);

        when(humanService.findHumanById(human.getId())).thenReturn(Optional.of(human));

        mockMvc.perform(get("/humans/{id}", human.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ihor"))
                .andExpect(jsonPath("$.surname").value("Fedorchenko"))
                .andExpect(jsonPath("$.age").value(20));
    }

    @Test
    void getHumanInfoWrong() throws Exception {
        when(humanService.findHumanById(0L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/humans/"+0))
                .andExpect(status().isNotFound());
    }
}