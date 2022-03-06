package com.lajili.SpringCRUD;

import com.lajili.SpringCRUD.entity.User;
import com.lajili.SpringCRUD.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
        void testAddnew(){
        User user = new User();
        user.setEmail("zied@gmail.com");
        user.setPassword("em123456*");
        user.setFirstName("zeid");
        user.setLastName("cj");

        User saveUser = userRepository.save(user);

        Assertions.assertThat(saveUser).isNotNull();
        Assertions.assertThat(saveUser.getId()).isGreaterThan(0);
    }
    @Test
    public  void testListAll() {
        Iterable<User> users = userRepository.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user: users){
            System.out.println(user);
        }

    }
    @Test
    public void testUpdate(){
     Integer userId = 1;
        Optional<User> optionalUser = userRepository.findById(userId);
        userRepository.findById(userId);
        User user =optionalUser.get();
        user.setPassword("hello123");
        userRepository.save(user);

        User updateUser = userRepository.findById(userId).get();
        Assertions.assertThat(updateUser.getPassword()).isEqualTo("hello123");

    }
    @Test
    public void testGet(){
        Integer userId = 2;
        Optional<User> optionalUser = userRepository.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());

    }
    @Test
    public void testDelete() {
        Integer userId = 2;
        userRepository.deleteById(userId);

        Optional<User> optionalUser = userRepository.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
