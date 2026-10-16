package com.klu.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.netflix.appinfo.EurekaInstanceConfig;

@Service
public class TouristService {

    @Autowired
    private Environment environment;

    @Autowired(required = false)
    private ServletWebServerApplicationContext serverContext;

    @Autowired(required = false)
    private EurekaInstanceConfig eurekaInstanceConfig;

    public Map<String, Object> getTouristDetails() {
        Map<String, Object> response = new LinkedHashMap<>();

        // Obtain actual running port
        int port = 0;
        if (serverContext != null && serverContext.getWebServer() != null) {
            port = serverContext.getWebServer().getPort();
        } else {
            String localPort = environment.getProperty("local.server.port");
            if (localPort != null && !localPort.isEmpty()) {
                try {
                    port = Integer.parseInt(localPort);
                } catch (NumberFormatException ignored) {
                }
            }
        }

        // Obtain actual Eureka instance ID
        String instanceId = null;
        if (eurekaInstanceConfig != null) {
            instanceId = eurekaInstanceConfig.getInstanceId();
        }
        if (instanceId == null || instanceId.isEmpty()) {
            instanceId = environment.getProperty("eureka.instance.instance-id");
        }
        if (instanceId == null || instanceId.isEmpty()) {
            instanceId = "TOURIST-SERVICE:" + port;
        }

        response.put("message", "Tourist Service is running");
        response.put("instanceId", instanceId);
        response.put("port", port);

        return response;
    }
}
