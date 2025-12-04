package sam.model.service;


import sam.model.dao.NotificacaoDAO;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GestaoNotificacao{
    private ScheduledExecutorService executor;

    public void iniciar(){
        executor = Executors.newScheduledThreadPool(1);

        NotificacaoDAO dao = new NotificacaoDAO();
        NotificacaoService service = new NotificacaoService();

        Runnable tarefa = new Runnable() {
            @Override
            public void run() {

            }
        };
        executor.scheduleAtFixedRate(tarefa, 0, 1, TimeUnit.HOURS);
    }
    public void finalizar(){
        if(executor != null){
            executor.shutdown();
        }
    }

}
