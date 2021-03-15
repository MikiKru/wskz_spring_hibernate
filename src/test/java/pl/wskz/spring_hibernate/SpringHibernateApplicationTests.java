package pl.wskz.spring_hibernate;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wskz.spring_hibernate.model.Role;
import pl.wskz.spring_hibernate.model.User;
import pl.wskz.spring_hibernate.repository.UserRepository;
import pl.wskz.spring_hibernate.service.UserService;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class SpringHibernateApplicationTests {
    @Autowired
    private UserService userService;
    @Test
    void contextLoads() {
        assertThat(userService).isNotNull();
    }
    @Test
    void getUserByIdTest(){
        User user = new User("Michał", "Kruczkowski", "mk@mk.pl", "mk", "mk");
        User getUser = userService.getUserById(1).orElse(new User());
        assertEquals(user.getEmail(), getUser.getEmail());
    }
    @Test
    void getUserRoleTest(){
        User getUser = userService.getUserById(1).orElse(new User());
        assertTrue(getUser.getRoles().isEmpty());
    }
    @Test
    void usersCountTest(){
        UserRepository localUserRepository = Mockito.mock(UserRepository.class);
        Mockito.when(localUserRepository.count()).thenReturn(9L);
        long usersCount = localUserRepository.count();
        assertEquals(9L, usersCount);
    }
    @Test
    void addUserTest(){
        User user = new User("Michał", "Kruczkowski", "mk@mk.pl", "mk", "mk");
        UserService localUserService = Mockito.mock(UserService.class);
        Mockito.when(localUserService.addUser(user)).thenReturn(user);
        User userToTest = localUserService.addUser(user);
        assertEquals(user.getUserId(), userToTest.getUserId());
    }
}
