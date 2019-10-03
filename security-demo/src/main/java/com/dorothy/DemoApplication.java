package com.dorothy;

import com.dorothy.domain.FileInfo;
import com.dorothy.domain.User;
import com.dorothy.error.UserNotExistException;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/user")
    @JsonView(User.SimpleUserView.class)
    public List<User> hello() {
        List<User> res = new ArrayList<>();
        res.add(new User());
        res.add(new User());
        res.add(new User());
        return res;
    }

    @GetMapping("/user/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable(required = false, name = "id") Long id) {
        User user = new User();
        user.setId(1L);
        user.setUsername("DorothyWang");
        user.setPassword("1234");
        return user;
    }

    @PostMapping("/user")
    public User create(@Valid @RequestBody User user, BindingResult erros) {

        if (erros.hasErrors()) {
            erros.getAllErrors()
                    .stream().
                    forEach(error -> System.out.println(error));
        }

        System.out.println(user.getUsername());
        System.out.println(user.getAge());
        System.out.println(user.getPassword());
        System.out.println(user.getDate());


        user.setId(1L);
        return user;
    }

    @PutMapping("/user/{id}")
    public User update(@PathVariable(required = false, name = "id") Long id, @Valid @RequestBody User user,
                       BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().forEach(error -> {
                FieldError fieldError = (FieldError) error;
                System.out.println(fieldError.getField() + "" + fieldError.getDefaultMessage());
            });
        }
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getAge());
        System.out.println(user.getPassword());
        System.out.println(user.getDate());

        return user;
    }

    @DeleteMapping("/user/{id:\\d+}")
    public User delete(@PathVariable(required = true, name = "id") Long id) throws UserNotExistException {
        User user = new User();
        if (user == null)
            throw new UserNotExistException(id, "用户不存在");
        user.setId(id);
        return user;
    }

    public String folder = "E:\\eclipse\\spring-security\\security-demo\\src\\main\\resources";

    @PostMapping("/file")
    public FileInfo fileUpload(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());


        File localFile = new File(folder, new Date().getTime() + ".txt");
        file.transferTo(localFile);

        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void fileDownLoad(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try (
                InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
                OutputStream outputStream = response.getOutputStream();
        ) {
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition","attachment;filename=text.txt");
            IOUtils.copy(inputStream, outputStream);
        }

    }
}
