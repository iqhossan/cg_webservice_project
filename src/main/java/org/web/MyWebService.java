package org.web;


import Dao.UserDao;
import Db.ConnectionProvider;
import entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class MyWebService {

    @PostMapping("/")
    public User addUser(@RequestBody User user){
        UserDao dao = new UserDao(ConnectionProvider.getConnection());
        boolean f = dao.UserRegister(user);
        return user;
    }

    @PutMapping("/{userid}")
    public User updateUser(@RequestBody User user,@PathVariable("userid") int userid){
        UserDao dao = new UserDao(ConnectionProvider.getConnection());
        boolean f = dao.updateUser(user,userid);
        return user;
    }

    @GetMapping("/{userid}")
    public User updateUser(@PathVariable("userid") int userid){
        UserDao dao = new UserDao(ConnectionProvider.getConnection());
        User user = dao.getUser(userid);
        return user;
    }

    @GetMapping("/")
    public List<User> getAllUser(){
        UserDao dao = new UserDao(ConnectionProvider.getConnection());
        return dao.getAllUser();
    }


}
