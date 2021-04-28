package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/eureka")
public class EurekaController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @ResponseBody
    @RequestMapping("/app")
    public List<List<ServiceInstance>> getApp() {
        List<List<ServiceInstance>> serviceInstances = new ArrayList<>();
        List<String> services = discoveryClient.getServices();
        for (String serviceName : services) {
            serviceInstances.add(discoveryClient.getInstances(serviceName));
        }
        return serviceInstances;
    }

}