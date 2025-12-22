package sam.controller;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import sam.model.service.GestaoNotificacao;

@WebListener
public class AppListener implements ServletContextListener {

    private GestaoNotificacao gestao;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        gestao = new GestaoNotificacao();
        gestao.iniciar();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        gestao.finalizar();
    }
}
