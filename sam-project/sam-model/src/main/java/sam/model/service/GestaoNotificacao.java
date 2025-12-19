package sam.model.service;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GestaoNotificacao {
    private ScheduledExecutorService executor;

    public void iniciar() {
        executor = Executors.newScheduledThreadPool(1);

        NotificacaoService service = new NotificacaoService();

        Runnable notificacaoProgramaExpirando = () -> {
            try {
                service.enviarNotificacoesProgramaExpirando();
            } catch (Exception e) {
                throw new RuntimeException("erro ao mandar notificacao automatica" + e.getMessage(), e);
            }
        };

        Runnable notificacaoMilhasExpirando = () -> {
          try{
              service.enviarMilhasExpirando();
          } catch (Exception e) {
              throw new RuntimeException("erro ao mandar notificacao automatica" + e.getMessage(), e);
          }
        };
        executor.scheduleAtFixedRate(notificacaoMilhasExpirando, 0, 1, TimeUnit.HOURS);
        executor.scheduleAtFixedRate(notificacaoProgramaExpirando, 0, 1, TimeUnit.HOURS);
    }

    public void finalizar() {
        if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
        }
    }
}

