package br.com.zupacademy.enricco.proposta.utils.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component(value = "customHealthCheckHealthCheck")
public class CustomHealthCheck implements HealthIndicator {
    @Override
    public Health getHealth(boolean includeDetails) {
        return HealthIndicator.super.getHealth(includeDetails);
    }

    @Override
    public Health health() {
        Map<String, Object> details = new HashMap<>();
        details.put("health-check", "Healthcheck customizado pra fazer algo nesse Desafio!");

        return Health.status(Status.UP).withDetails(details).build();
    }
}
