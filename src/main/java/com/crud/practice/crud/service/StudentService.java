package com.crud.practice.crud.service;

import com.crud.practice.crud.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private List<User> userList = new ArrayList<>();
    private int idCounter = 1;

    public User addStudent(User user){
        if (userList.size() >= 1){
            for (User userInList : userList){
                if (userInList.getId() == user.getId()){
                    return null;
                }else {
                    user.setId(idCounter++);
                    userList.add(user);
                    System.out.println(userList.toString() + "User added successfully");
                    return user;
                }
            }
        }
        user.setId(idCounter++);
        userList.add(user);
        System.out.println(userList.toString() + "User added successfully");
        return user;
    }

    public List<User> getAllUserList(){
        return userList;
    }

    public User getUserById(int id){
        for (User user : userList){
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public User updateStudentList(int id , User UpdateUser){
        for (User user : userList){
            if (user.getId() == id ){
                user.setName(UpdateUser.getName());
                user.setAge(UpdateUser.getAge());
                return user;
            }else {
                return null;
            }
        }
        return null;
    }

    public String deleteStudentFromList(int id){
        for (User user: userList){
            if (user.getId()==id){
                userList.remove(user);
                return "User delete Successfully";
            }
        }
        return "User not available";
    }

}
