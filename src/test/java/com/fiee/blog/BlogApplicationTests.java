package com.fiee.blog;

import com.fiee.blog.service.RedisService;
import com.fiee.blog.service.impl.RedisServiceImpl;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class BlogApplicationTests {
    @Test
    void contextLoads() {
        List<String> list1 = List.of("aaa", "bbb", "ccc");
        List<String> list2 = List.of("aaa", "bbb");
        System.out.println(list1.containsAll(list2));
    }

    public static void main(String[] args) {
        List<String> list1 = List.of("aaa", "bbb", "ccc");
        List<String> list2 = List.of("bbb");
        for (String s : list2)
            if (list1.contains(s))
                System.out.println(s);
    }
}
