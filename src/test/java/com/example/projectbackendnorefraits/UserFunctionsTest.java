package com.example.projectbackendnorefraits;

import business.UsuarioService;
import com.example.projectbackendnorefraits.ProjectBackendNoRefraitsApplication;
import data.entities.Usuario;
import data.entities.UsuarioDisplay;
import data.repositories.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest(classes = ProjectBackendNoRefraitsApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserFunctionsTest {
    @MockBean
    UsuarioRepository repository;

    @Autowired
    UsuarioService service;

    static final String TEST_GMAIL = "esteban.villacorta@utec.edu.pe";
    static final String TEST_GID = "$thisistechnicallypossibleasAnOutputOfhashingaString/u";

    @Test
    void getAllUserToDisplay() {
        List<UsuarioDisplay> retval = service.getAllUserToDisplay(TEST_GMAIL);
        Assertions.assertTrue(retval.stream().noneMatch(x -> x.getEmail().equals(TEST_GMAIL)));
    }


    @Test
    void getUsersUnderDirection() {

    }

    @Test
    void getPrettyNameRolebyId() {
    }

    @Test
    void findAll() {
    }

    @Test
    void existsByEmail() {
    }

    @Test
    void isSysAdmin() {
    }

    @Test
    void isDirAdmin() {
    }

    @Test
    void findOne() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findOneByEmail() {
    }

    @Test
    void findUsuarioByEmailAndNombreNotNull() {
    }

    @Test
    void findOneByNombre() {
    }

    @Test
    void save() {
    }

    @Test
    void deleteById() {
    }
}