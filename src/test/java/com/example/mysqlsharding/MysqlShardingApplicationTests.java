package com.example.mysqlsharding;

import com.example.mysqlsharding.mapper.DBModelMappr;
import com.example.mysqlsharding.mapper.DeviceHourReportMapper;
import com.example.mysqlsharding.model.DBModel;
import com.example.mysqlsharding.model.DeviceHourReport;
import com.example.mysqlsharding.model.User;
import com.example.mysqlsharding.util.LoginThreadCacheUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootTest
@RunWith(SpringRunner.class)
class MysqlShardingApplicationTests {

    @Autowired
    private DBModelMappr dbModelMappr;

    @Autowired
    private DeviceHourReportMapper deviceHourReportMapper;

    private CountDownLatch countDownLatch = new CountDownLatch(50);

    @Test
    public void testLoginSearch() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 1; i <= 50; i++) {
            User user = new User();
            user.setSqlSessionBeanName("base"+ i);
            executorService.submit(new SelectRunnable(user,countDownLatch));
        }
        Thread.sleep(10000);
    }

    class SelectRunnable implements Runnable{
        private User user;
        private CountDownLatch countDownLatch;
        SelectRunnable(User user,CountDownLatch countDownLatch){
            this.user = user;
            this.countDownLatch = countDownLatch;
        }
        @Override
        public void run() {
            LoginThreadCacheUtil.setUser(user);
            countDownLatch.countDown();
            try {
                countDownLatch.await();
                List<DeviceHourReport> deviceHourReports = deviceHourReportMapper.selectDeviceHourReportByLimit();
                System.out.println(deviceHourReports);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    @Test
    public void insertDB(){

        for (int i = 5; i <= 100; i++) {
            DBModel dbModel = new DBModel();
            dbModel.setBeanName("base"+ i);
            dbModel.setDriver("com.mysql.jdbc.Driver");
            dbModel.setUrl("jdbc:mysql:///mall" + i);
            dbModel.setUsername("root");
            dbModel.setPassword("yzh123");
            dbModelMappr.insert(dbModel);
        }

    }
}
