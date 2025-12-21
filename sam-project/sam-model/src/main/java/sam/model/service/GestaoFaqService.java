package sam.model.service;

import sam.model.common.exception.PersistenciaException;
import sam.model.dao.FaqDAO;
import sam.model.domain.FaqEntry;
import sam.model.helper.FaqHelper;

import java.sql.SQLException;
import java.util.List;

public class GestaoFaqService {

    private final FaqDAO faqDAO;

    public GestaoFaqService() {
        faqDAO = FaqDAO.getInstance();
    }

    public void cadastrar(FaqEntry faq) throws SQLException, PersistenciaException {
        if (!"".equals(FaqHelper.validarFaq(faq)))
            throw new PersistenciaException(FaqHelper.validarFaq(faq));

        try {
            faqDAO.inserir(faq);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void atualizar(FaqEntry faq) throws SQLException, PersistenciaException {
        if (!"".equals(FaqHelper.validarFaq(faq)))
            throw new  PersistenciaException(FaqHelper.validarFaq(faq));

        try {
            faqDAO.atualizar(faq);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public List<FaqEntry> listarFaq() throws SQLException {
        List<FaqEntry> listaFaq;

        try {
            listaFaq = faqDAO.pesquisarAtivos();
        } catch (SQLException e) {
            throw new SQLException(e);
        }

        return listaFaq;
    }

    public void remover(Long id) throws SQLException, PersistenciaException {
        if (faqDAO.pesquisar(id) == null)
            throw new PersistenciaException("A entrada do FAQ não existe");

        try {
            faqDAO.remover(id);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
