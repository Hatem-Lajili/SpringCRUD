package com.lajili.SpringCRUD.service;

import com.lajili.SpringCRUD.Exception.UserNotFoundException;
import com.lajili.SpringCRUD.entity.User;
import com.lajili.SpringCRUD.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> listAll(){
        return (List<User>) userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }
    public User get(Integer id) throws UserNotFoundException {
        Optional<User> res = userRepository.findById(id);
        if(res.isPresent()){
            return res.get();
        }
        throw new UserNotFoundException("Could not find any users with ID" + id);
    }
    public void delete(Integer id) throws UserNotFoundException {
        Long count = userRepository.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("could not find any users with ID" + id);
        }
        userRepository.deleteById(id);
    }
}
