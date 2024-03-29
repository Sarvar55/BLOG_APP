package com.example.blog;

import com.example.blog.config.AppConstant;
import com.example.blog.entities.Role;
import com.example.blog.repositories.RoleRepo;
import com.example.blog.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {
    @Autowired
    private PasswordEncoder encoder;


    @Autowired
    private UserRepo repo;

    @Autowired
    private RoleRepo roleRepo;


    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("password:" + encoder.encode("sarvar"));



        try {
            Role role = new Role();
            role.setRoleId(AppConstant.ADMIN_USER);
            role.setName("ADMIN_USER");

            Role role1 = new Role();
            role1.setRoleId(AppConstant.NORMAL_USER);
            role1.setName("NORMAL_USER");
            List<Role> roles = List.of(role, role1);
            List<Role> result = this.roleRepo.saveAll(roles);

            result.forEach((r) -> {
                System.out.println(r.getName());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
