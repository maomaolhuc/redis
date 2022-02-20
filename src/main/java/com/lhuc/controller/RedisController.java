package com.lhuc.controller;

import com.lhuc.domain.Student;
import com.lhuc.service.StudentService;
import com.lhuc.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Redis控制器
 *
 * @author lhucstart
 * @create 2021-04-23 13:59
 */
@Slf4j
@RestController
public class RedisController {


    @Resource
    private RedisUtils redisUtils;

    @Resource
    private RedisTemplate redisTemplate;


    @Resource
    private StudentService studentService;


    @RequestMapping("/test")
    public String test(String k, String v) {
        redisUtils.set(k, v);
        return (String) redisUtils.get(k);
    }


    @RequestMapping("/list")
    public List<Student> list() {
        try {
            //首先查询redis中是否存在数据列表
            List<Student> dictList = (List<Student>) redisTemplate.opsForValue().get("srb:core:dictList:");
            if (dictList != null) {
                //如果存在则从redis中直接返回数据列表
                log.info("从redis中获取数据列表");
                return dictList;
            }
        } catch (Exception e) {
            log.error("redis服务器异常");
        }

        //如果不逊在则查询数据库
        log.info("从数据库中获取数据列表");
        List<Student> listStudent = studentService.findStudentList();
        try {
            //将数据存入redis
            log.info("将数据存入redis");
            redisTemplate.opsForValue().set("srb:core:dictList:", listStudent, 5, TimeUnit.MINUTES);
        } catch (Exception e) {
            log.error("redis服务器异常");
        }

        //返回数据列表
        return listStudent;


    }

    @Cacheable(cacheNames = "student", key = "#root.method.name")
    @GetMapping("testCatch")
    public Object testCatch() {
        List<Student> listStudent = studentService.findStudentList();
        return listStudent;
    }


}
