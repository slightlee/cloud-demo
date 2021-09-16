import com.slightlee.user.UserApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

@SpringBootTest(classes = UserApplication.class)
public class UserApplicationTest {

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 获取 nacos实例
     */
    @Test
    void contextLoads() {
        List<ServiceInstance> instances = discoveryClient.getInstances("order-server");
        System.out.println("实例--" + instances.toString());
    }

}
