package controller;
import java.util.List;

//1 erro abaixo
import dao.EspacoDAO;
//import dao.ReservaDAO;
import model_.Auditorio;
import model_.CabineIndividual;
import model_.Espaco;
//import model_.Reserva;
import model_.SalaDeReuniao;

public class EspacoController {

    private EspacoDAO espacoDAO = new EspacoDAO();

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

    
    private String gerarNovoId() {
        int novoId = espacoDAO.listar(Espaco.class).stream() 
                .mapToInt(e -> {
                    try {
                        return e.getId() == null ? 0 : Integer.parseInt(e.getId());
                    } catch (NumberFormatException ex) {
                        return 0;
                    }
                })
                .max()
                .orElse(0) + 1;

        return String.valueOf(novoId);
    }

    
    public String salvarAuditorio(String nome, Integer capacidade, Double precoPorHora) {

        String erro = validarEspaco(nome, capacidade, precoPorHora);
        if (erro != null) return erro;

        Espaco espaco = new Auditorio();
        espaco.setId(gerarNovoId());     
        espaco.setNome(nome);
        espaco.setCapacidade(capacidade);
        espaco.setDisponivel(true);
        espaco.setPrecoPorHora(precoPorHora);

        espacoDAO.salvar(espaco);

        return "Auditório cadastrado com sucesso! ID = " + espaco.getId();
    }

    
    public String salvarCabineIndividual(String nome, Integer capacidade, Double precoPorHora) {

        String erro = validarEspaco(nome, capacidade, precoPorHora);
        if (erro != null) return erro;

        Espaco espaco = new CabineIndividual();
        espaco.setId(gerarNovoId());
        espaco.setNome(nome);
        espaco.setCapacidade(capacidade);
        espaco.setDisponivel(true);
        espaco.setPrecoPorHora(precoPorHora);

        espacoDAO.salvar(espaco);

        return "Cabine individual cadastrada com sucesso! ID = " + espaco.getId();
    }

    
    public String salvarSalaDeReuniao(String nome, Integer capacidade, Double precoPorHora) {

        String erro = validarEspaco(nome, capacidade, precoPorHora);
        if (erro != null) return erro;

        Espaco espaco = new SalaDeReuniao();
        espaco.setId(gerarNovoId());
        espaco.setNome(nome);
        espaco.setCapacidade(capacidade);
        espaco.setDisponivel(true);
        espaco.setPrecoPorHora(precoPorHora);

        espacoDAO.salvar(espaco);

        return "Sala de reunião cadastrada com sucesso! ID = " + espaco.getId();
    }
    
    public void removerPorId(String id) {
        
        if (espacoDAO.buscarPorId(id) == null) {
            return; 
        }
        
        espacoDAO.excluir(id);
    }
    
    public Espaco buscarPorNome(String nome) {
        
        List<Espaco> espacos = espacoDAO.listar();
        
        if (espacos == null) {
        	return null;
        }
        
        String id = "0";
        
        boolean espacoExiste = false;
        
        for (Espaco espaco : espacos) {
            if (espaco.getNome().equals(nome)) {
                
                id = espaco.getId();
                
                espacoExiste = true;
                
                break;
            }
        }
        
        if (!espacoExiste) {
            return null; 
        }
        
        return espacoDAO.buscarPorId(id);
    }
    
    
    public List<Espaco> listar() {
        return espacoDAO.listar(Espaco.class);
    }
}