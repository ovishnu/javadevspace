//package com.ora.springdataes.load;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.ora.springdataes.model.User;
//import com.ora.springdataes.repo.UserRepository;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class Loaders {
//
//    @Autowired
//    ElasticsearchOperations operations;
//
//    @Autowired
//    UserRepository usersRepository;
//
//    @PostConstruct
//    @Transactional
//    public void loadAll(){
//
//        operations.putMapping(User.class);
//        System.out.println("Loading Data");
//        usersRepository.save(getData());
//        System.out.printf("Loading Completed");
//
//    }
//
//    private User getData() {
//      User  user = new User(1L, "Ajay", "Accounting");
//        return user;
//    }
//}
//
