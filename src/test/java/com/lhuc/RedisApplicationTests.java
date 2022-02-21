package com.lhuc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class RedisApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void getDistance() {
        GeoOperations geoOperations = redisTemplate.opsForGeo();
        //首先存入客户端上传的经纬度和指定地点的经纬度
        Map<String, Object> map = new HashMap<>();
        // 假如客户端的传的是北京西站的经纬度,指定地点是北京南站
        map.put("BJXZ", new Point(116.321275, 39.895096));
        map.put("BJNZ", new Point(116.378438, 39.864666));
        // 将这些地址数据保存到redis中
        geoOperations.add("GET_DISTANCE", map);
        // 调用方法,计算北京西站与北京南站之间的距离;
        double value = geoOperations.distance("GET_DISTANCE", "BJXZ", "BJNZ", RedisGeoCommands.DistanceUnit.METERS).getValue();
        System.out.println("北京西站到南站的距离是" + value + "米");
        System.out.println("与高德地图的误差是:" + (5927 - value) + "米");
    }


}
