package com.springboot.chapter11.main;

import com.springboot.chapter11.pojo.User;
import com.springboot.chapter11.vo.UserVo;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/8
 **/
public class RestTemplateMain {

    public static void main(String[] args) {
        //getUser(3L);

        findUser("u", "n", 0, 10);

        UserVo vo = new UserVo();
        vo.setUserName("user_name_vo");
        vo.setNote("user_name_note");
        vo.setSexCode(0);
        vo.setSexName("男");
        insertUserEntity(vo);

        //deleteUser(12L);
        userExchange(vo, 13L);
    }
    // 获取用户
    public static UserVo getUser(Long id) {
        // 创建一个RestTemplate对象
        RestTemplate restTmpl = new RestTemplate();
        // 消费服务，第一个参数为url，第二个是返回类型，第三个是URI路径参数
        UserVo userVo = restTmpl.getForObject(
                "http://localhost:5555/user/{id}", UserVo.class, id);
        // 打印用户名称
        System.out.println(userVo.getUserName());
        return userVo;
    }

    /**
     * RestTemplate使用多参数的HTTP GET请求
     * @param userName
     * @param note
     * @param start
     * @param limit
     * @return
     */
    public static List<UserVo> findUser(String userName, String note, int start, int limit){
        RestTemplate restTemplate = new RestTemplate();
        //使用Map封装多个参数，以调可读性
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userName", userName);
        params.put("note", note);
        params.put("start", start);
        params.put("limit", limit);
        //Map 中的key和URL中的参数一一对应
        String url = "http://localhost:5555/users/{userName}/{note}/{start}/{limit}";
        //请求后端
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class, params);
        List<UserVo> userVos = responseEntity.getBody();
        System.out.println(userVos);
        return userVos;
    }

    /**
     * 通过POST请求传递JSON请求体
     * @param userVo
     * @return
     */
    public static User insertUser(UserVo userVo){
        HttpHeaders headers = new HttpHeaders();
        //设置请求内容为JSON类型
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<UserVo> request = new HttpEntity<>(userVo, headers);
        RestTemplate template = new RestTemplate();
        //请求是传递请求实体对象，并放回回填id的用户
        User user = template.postForObject("http://localhost:5555/user", request, User.class);
        System.out.println("user = [" + user + "]");
        return user;
    }

    public static void deleteUser(Long id){
        RestTemplate template = new RestTemplate();
        template.delete("http://localhost:5555/user/{id}", id);
    }

    /**
     * 获取服务器相应头属性和HTTP状态码
     * @param userVo
     * @return
     */
    public static User insertUserEntity(UserVo userVo){
        //请求头
        HttpHeaders headers = new HttpHeaders();
        //请求类型
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        //绑定请求体和头
        HttpEntity<UserVo> request = new HttpEntity<>(userVo, headers);

        RestTemplate template = new RestTemplate();
        //请求服务器
        ResponseEntity<User> userEntity = template.postForEntity("http://localhost:5555/user2/entity", request, User.class);
        //获取相应体
        User user = userEntity.getBody();
        //获取相应头
        HttpHeaders respHeaders = userEntity.getHeaders();
        //获取相应属性
        List<String> success = respHeaders.get("success");
        //HTTP状态码
        int status = userEntity.getStatusCodeValue();
        System.out.println("user = [" + user + "]");
        System.out.println(status);
        return user;
    }

    public static User userExchange(UserVo userVo, Long id){
        //请求头
        HttpHeaders headers = new HttpHeaders();
        //请求类型
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        // 绑定请求体和头
        HttpEntity<UserVo> request = new HttpEntity<>(userVo, headers);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:5555/user2/entity";

        ResponseEntity<User> userEntity = restTemplate.exchange(url, HttpMethod.POST, request, User.class);

        User user = userEntity.getBody();
        System.out.println(user);
        //获取相应头
        HttpHeaders respHeaders = userEntity.getHeaders();
        //响应头属性
        List<String> success = respHeaders.get("success");
        //响应的http状态码
        int status = userEntity.getStatusCodeValue();
        System.out.println(status);
        url = "http://localhost:5555/user/{id}";
        ResponseEntity<UserVo> userVoResponseEntity = restTemplate.exchange(url, HttpMethod.GET,null,UserVo.class, id);
        UserVo uservo = userVoResponseEntity.getBody();
        System.out.println(uservo);
        return user;
    }

}
