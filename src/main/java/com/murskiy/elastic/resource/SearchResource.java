package com.murskiy.elastic.resource;

import com.murskiy.elastic.repository.UsersRepository;
import com.murskiy.elastic.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/search")
public class SearchResource {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping(value = "/name/{text}")
    public List<Users> searchName(@PathVariable final String text) {
        return usersRepository.findByName(text);
    }


    @GetMapping(value = "/salary/{salary}")
    public List<Users> searchSalary(@PathVariable final Long salary) {
        return usersRepository.findBySalary(salary);
    }


    @GetMapping(value = "/all")
    public List<Users> searchAll() {
        List<Users> usersList = new ArrayList<>();
        Iterable<Users> userses = usersRepository.findAll();
        userses.forEach(usersList::add);
        return usersList;
    }
    @GetMapping(value = "/add/{name}/{id}")
    public List<Users> addUser(@PathVariable final String name, @PathVariable final long id) {
        Users users = new Users(name, id, "test", 20L);
        usersRepository.save(users);
        List<Users> usersList = new ArrayList<>();
        Iterable<Users> userses = usersRepository.findAll();
        userses.forEach(usersList::add);
        return usersList;
    }
    @GetMapping(value = "/remove/{id}")
    public List<Users> deleteUser(@PathVariable final long id){
        usersRepository.deleteById(id);
        return searchAll();
    }

}
