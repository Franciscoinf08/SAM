package sam.controller;

import sam.model.common.exception.PersistenciaException;
import sam.model.domain.Transacao;
import sam.model.domain.util.TransacaoTipo;
import sam.model.service.GestaoTransacoesService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;

@WebServlet(name = "CadastroTransacaoController", urlPatterns = {"/CadastroTransacaoController"})
public class CadastroTransacaoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long idProgramaFidelidade;
        Long idCliente;
        Date data;
        Date dataExpiracao;
        TransacaoTipo tipo;
        int quantidade;
        BigDecimal valor;
        int bonus;

        try {
            idProgramaFidelidade = Long.parseLong(request.getParameter("programa"));
            idCliente = Long.parseLong(request.getParameter("cliente"));
            data = Date.valueOf(request.getParameter("data"));
            dataExpiracao = Date.valueOf(request.getParameter("dataExpiracao"));
            tipo = TransacaoTipo.strTo(request.getParameter("tipo"));
            quantidade = Integer.parseInt(request.getParameter("quantidade"));
            valor = new BigDecimal(request.getParameter("valor"));
            bonus = Integer.parseInt(request.getParameter("bonus"));

            if (valor.scale() < 2)
                valor = valor.setScale(2, RoundingMode.HALF_UP);
        } catch (RuntimeException e) {
            e.printStackTrace();

            String erro = "Dados inválidos";
            request.setAttribute("erro", erro);

            RequestDispatcher rd = request.getRequestDispatcher("/core/geral/transacoes.jsp");
            rd.forward(request, response);
            return;
        }

        GestaoTransacoesService manterTransacao = new GestaoTransacoesService();

        try {
            Transacao transacao = new Transacao(idProgramaFidelidade, idCliente, data, dataExpiracao, quantidade, tipo, valor, bonus);

            manterTransacao.cadastrar(transacao);
        } catch (PersistenciaException e) {
            e.printStackTrace();
            String erro = e.getLocalizedMessage();
            request.setAttribute("erro", erro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("/core/geral/transacoes.jsp");
        rd.forward(request, response);
    }
}
