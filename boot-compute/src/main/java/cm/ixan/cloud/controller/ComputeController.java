package cm.ixan.cloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ComputeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ComputeController.class);

    @Autowired
    private DiscoveryClient client;

    @GetMapping(value = "/add")
    public Integer add(@RequestParam(value = "a") Integer a,@RequestParam(value = "b") Integer b){
        List<ServiceInstance> instances = client.getInstances("compute-service");
        int i = a + b;
        LOGGER.info("/add,host:{},service.id:{},result:{}", instances.get(0).getHost(), instances.get(0).getServiceId(), i);
        return i;
    }
}
