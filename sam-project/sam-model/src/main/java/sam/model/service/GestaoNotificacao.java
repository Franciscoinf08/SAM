package sam.model.service;


import sam.model.dao.NotificacaoDAO;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GestaoNotificacao {
    private ScheduledExecutorService executor;

    public void iniciar() {
        executor = Executors.newScheduledThreadPool(1);

        NotificacaoService service = new NotificacaoService();

        Runnable tarefa = () -> {
            System.out.println("Scheduler executando: " + LocalDateTime.now());
            try {
                service.enviarNotificacoesProgramaExpirando();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        executor.scheduleAtFixedRate(tarefa, 0, 1, TimeUnit.HOURS);
    }

    public void finalizar() {
        if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
        }
    }
}

