package br.healthx.Healthx.session.model.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.healthx.Healthx.session.model.service.SessionService;

@Component
public class SessionScheduler {

    @Autowired
    public SessionService service;

    @Scheduled(fixedRate = 60000)
    public void schedulerSession() {
        service.updateStatus();
    }
}
