import com.alibaba.fastjson.JSONObject;
import com.hai.ws.api.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * HelloWorld WebService Test
 * Created by Administrator on 2017/12/13.
 */
public class App {

    IHelloWorldService helloWorldService = null;
    IHelloWorld helloWorld = null;

    @Before
    public void before() {
        helloWorldService = new IHelloWorldService();
        helloWorld = helloWorldService.getIHelloWorldPort();
    }

    @Test
    public void say() {
        String result = helloWorld.say("hai");
        System.out.println("result: " + result);
    }

    @Test
    public void getRoleByUser() {
        User user = new User();
        user.setId(1);
        user.setUserName("java1234");
//        user.setUserName("jack");
        user.setPassword("123456");

        List<Role> roles = helloWorld.getRoleByUser(user);
        for (Role role : roles) {
            System.out.println("role id:\t" + role.getId());
            System.out.println("role name:\t" + role.getName());
            System.out.println();
        }
    }

    @Test
    public void getRoles() {
        MyRoleArray roleArray = helloWorld.getRoles();
        List<MyRole> roles = roleArray.getItem();
        System.out.println("helloWorld.getRoles result:\n" + JSONObject.toJSONString(roles, true));
    }
}
