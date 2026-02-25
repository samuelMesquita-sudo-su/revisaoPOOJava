package service;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class HelpDesk {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Chamado> chamados = new ArrayList<>();

    public void cadastrarCliente(String nome, String email){

        Cliente c = new Cliente();
        c.setNome(nome);
        c.setEmail(email);
        clientes.add(c);
    }

    public Cliente buscarCliente(String email){
        for (Cliente c : clientes){
            if(c.getEmail().equalsIgnoreCase(email)) {
                return c;
            }
        }
        return null;
    }

    public void abrirChamada(Integer numero, String titulo, String descricao, PrioridadeChamado prioridade, Cliente cliente){
        if (numero == null) return;



        Chamado ch = new Chamado();
        ch.setNumero(numero);
        ch.setTitulo(titulo);
        ch.setData(LocalDate.now());
        ch.setDescricao(descricao);
        ch.setPrioridade(prioridade);
        ch.setStatus(StatusChamado.ABERTO);
        ch.setCliente(cliente);

        chamados.add(ch);
    }

    public Chamado buscarChamado(Integer numero){
        for (Chamado c : chamados){
            if (c.getNumero().equals(numero)){
                return c;
            }
        }
        return null;
    }

    public void alterarStatus(Integer numero, StatusChamado statusChamado){
        Chamado chamado = buscarChamado(numero);
        StatusChamado antigo = chamado.getStatus();

        if (chamado != null) {
            chamado.setStatus(statusChamado);
            System.out.println( antigo + " PARA ->" + chamado.getStatus());
        }
        else {
            System.out.println("Não encontrado");
        }
    }


}
