package com.example.wordladder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {

    @Autowired
    private RestTemplate restTemplate;
//    @RequestMapping("/main")
//    public String wordladder() {
//        return "wordladder";
//    }
//
//
//    @RequestMapping("/hello")
//    public String hello() {
//        return "hello";
//    }

    @RequestMapping("/auth")
    public @ResponseBody
    Integer auth(@RequestParam String username, @RequestParam String password) {

        String url = "http://192.168.99.100:8081/login";
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
//  也支持中文
        params.add("username", username);
        params.add("password", password);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
//  执行HTTP请求
        ResponseEntity<String> response = client.exchange(url, HttpMethod.POST, requestEntity, String.class);
//  输出结果
//        System.out.println(response.getBody());
//        System.out.println(response.getHeaders());

        HttpHeaders responseHeaders = response.getHeaders();

        if (responseHeaders.containsKey("login")) {
            return 200;
        }


        return 401;
    }
}
