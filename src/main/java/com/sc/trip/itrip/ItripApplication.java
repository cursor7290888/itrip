package com.sc.trip.itrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

@SpringBootApplication
public class ItripApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItripApplication.class, args);
    }
    @Bean
    public RedisScript<Long> geoAddScript() {
        DefaultRedisScript<Long> geoAdd = new DefaultRedisScript<>();
        geoAdd.setScriptText("return redis.call('GEOADD',KEYS[1],ARGV[1],ARGV[2],ARGV[3])");
        geoAdd.setResultType(Long.class);
        return geoAdd;
    }

}
