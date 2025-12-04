package controller;

import dao.EspacoDAO;
import model_.Auditorio;
import model_.CabineIndividual;
import model_.Espaco;
import model_.SalaDeReuniao;

public class EspacoController {

    private EspacoDAO espacoDAO = new EspacoDAO();

   //Validação e correção de erros.
    private String validarEspaco(String nome, Integer capacidade, Double precoPorHora) {

        if (nome == null || nome.trim().isEmpty()) {
            return "Erro: Nome do espaço inválido.";
        }

        if (capacidade == null || capacidade <= 0) {
            return "Erro: Capacidade deve ser maior que zero.";
        }

        if (precoPorHora == null || precoPorHora <= 0) {
            return "Erro: Preço por hora inválido.";
        }

        return null; 
    }

    public String salvarSalaDeReuniao(String nome, Integer capacidade, Double precoPorHora) {

        String erro = validarEspaco(nome, capacidade, precoPorHora);
        if (erro != null) {
            return erro;
        }

        Espaco espaco = new SalaDeReuniao();
        espaco.setNome(nome);
        espaco.setCapacidade(capacidade);
        espaco.setPrecoPorHora(precoPorHora);

        espacoDAO.salvar(espaco);
        return "Sala de reunião cadastrada com sucesso!";
    }

    public String salvarCabineIndividual(String nome, Integer capacidade, Double precoPorHora) {

        String erro = validarEspaco(nome, capacidade, precoPorHora);
        if (erro != null) {
            return erro;
        }

        Espaco espaco = new CabineIndividual();
        espaco.setNome(nome);
        espaco.setCapacidade(capacidade);
        espaco.setPrecoPorHora(precoPorHora);

        espacoDAO.salvar(espaco);
        return "Cabine individual cadastrada com sucesso!";
    }

    public String salvarAuditorio(String nome, Integer capacidade, Double precoPorHora) {

        String erro = validarEspaco(nome, capacidade, precoPorHora);
        if (erro != null) {
            return erro;
        }

        Espaco espaco = new Auditorio();
        espaco.setNome(nome);
        espaco.setCapacidade(capacidade);
        espaco.setPrecoPorHora(precoPorHora);

        espacoDAO.salvar(espaco);
        return "Auditório cadastrado com sucesso!";
    }
}
