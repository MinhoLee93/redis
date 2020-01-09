package com.pratice.redis;

import com.pratice.redis.entity.Point;
import com.pratice.redis.repository.PointRedisRepository;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;


@SpringBootTest
@RunWith(SpringRunner.class)
class RedisApplicationTests {

    @Autowired
    private PointRedisRepository pointRedisRepository;

    @After
    public void tearDown() throws Exception{
        pointRedisRepository.deleteAll();
    }

    @Test
    public void 기본_등록_조회기능() {
        // given
        String id = "minho";
        LocalDateTime refreshTime = LocalDateTime.of(2020,1,9,0,0);
        Point point = Point.builder()
                        .id(id)
                        .amount(1000L)
                        .refreshTime(refreshTime)
                        .build();
        // when
        pointRedisRepository.save(point);

        // then
        Point savedPoint = pointRedisRepository.findById(id).get();
        assertThat(savedPoint.getAmount(), is(equalTo(1000L)));
        assertThat(savedPoint.getRefreshTime(), is(equalTo(refreshTime)));
    }

}
