package sam.model.service;

import sam.model.dao.FormObjetivosDao;
import sam.model.domain.FormObjetivos;
import sam.model.domain.Usuario;

import java.util.List;

public class GestaoFormulariosService {

    public static List<FormObjetivos> listarForms(Usuario usuario) {
        List<FormObjetivos> formularios = FormObjetivosDao.buscarTodos(usuario);
        return formularios;
    }
}

