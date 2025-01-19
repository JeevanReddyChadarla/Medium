package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserByUserName(String userName){
        User user = userRepository.findByUserName(userName);
        if(user!=null){
            return user;
        }
        return null;
    }

    public boolean addNewUser(User newUser){
        userRepository.save(newUser);
        return true;
    }

    public boolean updateUserByName(User newUser){
        User oldUser = getUserByUserName(newUser.getUserName());
        if(oldUser!=null){
            oldUser.setUserName(newUser.getUserName());
            oldUser.setPassword(newUser.getPassword());
            userRepository.save(oldUser);
            return true;
        }
        return false;
    }
}
