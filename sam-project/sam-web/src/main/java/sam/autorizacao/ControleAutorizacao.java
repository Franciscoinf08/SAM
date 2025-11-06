package sam.autorizacao;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import sam.model.domain.util.UsuarioTipo;
import sam.model.domain.AcessosBloqueios;

public class ControleAutorizacao {
    private final static Map<String, Permissao> permissoes;

    static {
        permissoes = new HashMap();
        ControleAutorizacao.inicializarPermissoes();
    }
    
    private static void inicializarPermissoes() {
        Permissao permissao = new Permissao("empresas");
        permissao.addUsuarioGrupo(UsuarioTipo.GESTOR);
        permissao.addUsuarioGrupo(UsuarioTipo.DESENVOLVEDOR);
        permissoes.put(permissao.getRecurso(), permissao);
        
        permissao = new Permissao("acesso-clientes");
        permissao.addUsuarioGrupo(UsuarioTipo.GESTOR);
        permissao.addUsuarioGrupo(UsuarioTipo.DESENVOLVEDOR);
        permissoes.put(permissao.getRecurso(), permissao);
        
        permissao = new Permissao("dashboard-gestor");
        permissao.addUsuarioGrupo(UsuarioTipo.GESTOR);
        permissao.addUsuarioGrupo(UsuarioTipo.DESENVOLVEDOR);
        permissoes.put(permissao.getRecurso(), permissao);
        
        permissao = new Permissao("associacoes");
        permissao.addUsuarioGrupo(UsuarioTipo.GESTOR);
        permissao.addUsuarioGrupo(UsuarioTipo.DESENVOLVEDOR);
        permissoes.put(permissao.getRecurso(), permissao);
        
        permissao = new Permissao("campanhas");
        permissao.addUsuarioGrupo(UsuarioTipo.GESTOR);
        permissao.addUsuarioGrupo(UsuarioTipo.DESENVOLVEDOR);
        permissoes.put(permissao.getRecurso(), permissao);
        
        permissao = new Permissao("computar-milhas");
        permissao.addUsuarioGrupo(UsuarioTipo.GESTOR);
        permissao.addUsuarioGrupo(UsuarioTipo.DESENVOLVEDOR);
        permissoes.put(permissao.getRecurso(), permissao);
        
        permissao = new Permissao("mensagens-avisos");
        permissao.addUsuarioGrupo(UsuarioTipo.GESTOR);
        permissao.addUsuarioGrupo(UsuarioTipo.DESENVOLVEDOR);
        permissoes.put(permissao.getRecurso(), permissao);
        
        permissao = new Permissao("orcamentos");
        permissao.addUsuarioGrupo(UsuarioTipo.GESTOR);
        permissao.addUsuarioGrupo(UsuarioTipo.DESENVOLVEDOR);
        permissoes.put(permissao.getRecurso(), permissao);
        
        permissao = new Permissao("planos");
        permissao.addUsuarioGrupo(UsuarioTipo.GESTOR);
        permissao.addUsuarioGrupo(UsuarioTipo.DESENVOLVEDOR);
        permissoes.put(permissao.getRecurso(), permissao);
        
        permissao = new Permissao("programa");
        permissao.addUsuarioGrupo(UsuarioTipo.GESTOR);
        permissao.addUsuarioGrupo(UsuarioTipo.DESENVOLVEDOR);
        permissoes.put(permissao.getRecurso(), permissao);
        
        permissao = new Permissao("transacoes-gestor");
        permissao.addUsuarioGrupo(UsuarioTipo.GESTOR);
        permissao.addUsuarioGrupo(UsuarioTipo.DESENVOLVEDOR);
        permissoes.put(permissao.getRecurso(), permissao);
        
        permissao = new Permissao("transacoes-clientes");
        permissao.addUsuarioGrupo(UsuarioTipo.CLIENTE);
        permissao.addUsuarioGrupo(UsuarioTipo.DESENVOLVEDOR);
        permissoes.put(permissao.getRecurso(), permissao);
        
        permissao = new Permissao("suporte");
        permissao.addUsuarioGrupo(UsuarioTipo.GESTOR);
        permissao.addUsuarioGrupo(UsuarioTipo.CLIENTE);
        permissao.addUsuarioGrupo(UsuarioTipo.DESENVOLVEDOR);
        permissoes.put(permissao.getRecurso(), permissao);
        
        permissao = new Permissao("relatorios");
        permissao.addUsuarioGrupo(UsuarioTipo.GESTOR);
        permissao.addUsuarioGrupo(UsuarioTipo.CLIENTE);
        permissao.addUsuarioGrupo(UsuarioTipo.DESENVOLVEDOR);
        permissoes.put(permissao.getRecurso(), permissao);
        
        permissao = new Permissao("perfil");
        permissao.addUsuarioGrupo(UsuarioTipo.GESTOR);
        permissao.addUsuarioGrupo(UsuarioTipo.CLIENTE);
        permissao.addUsuarioGrupo(UsuarioTipo.DESENVOLVEDOR);
        permissoes.put(permissao.getRecurso(), permissao);
        
        permissao = new Permissao("notificacoes");
        permissao.addUsuarioGrupo(UsuarioTipo.GESTOR);
        permissao.addUsuarioGrupo(UsuarioTipo.CLIENTE);
        permissao.addUsuarioGrupo(UsuarioTipo.DESENVOLVEDOR);
        permissoes.put(permissao.getRecurso(), permissao);
        
        permissao = new Permissao("avaliacoes");
        permissao.addUsuarioGrupo(UsuarioTipo.GESTOR);
        permissao.addUsuarioGrupo(UsuarioTipo.CLIENTE);
        permissao.addUsuarioGrupo(UsuarioTipo.DESENVOLVEDOR);
        permissoes.put(permissao.getRecurso(), permissao);
        
        permissao = new Permissao("dashboard-cliente");
        permissao.addUsuarioGrupo(UsuarioTipo.CLIENTE);
        permissao.addUsuarioGrupo(UsuarioTipo.DESENVOLVEDOR);
        permissoes.put(permissao.getRecurso(), permissao);
        
        permissao = new Permissao("ver-usuarios");
        permissao.addUsuarioGrupo(UsuarioTipo.DESENVOLVEDOR);
        permissoes.put(permissao.getRecurso(), permissao);
    }
    
    public static boolean checkPermissao(String recurso, UsuarioTipo usuario) {
        return permissoes.get(recurso).check(usuario);
    }

    public static boolean checkPermissao(String recurso, List<UsuarioTipo> tipoList) {
        for (UsuarioTipo usuario: tipoList)
            if (permissoes.get(recurso).check(usuario))
                return true;
        
        return false;
    }
    
    public static boolean checkBloqueio(String recurso, String tipoList) { //CONECTAR COM A CLASS AcessosService 
        for (UsuarioTipo usuario: tipoList)
            if (permissoes.get(recurso).check(usuario))
                return true;
        
        return false; /*ALTERAR CONFORME FOR PRECISO*/
    }
    
}
