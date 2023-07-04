package model.servicos;

import java.util.*;
import model.constantes.Constantes;
import model.entidades.Comparavel;
import model.entidades.Livro;
import model.exceptions.LivrariaException;

public class ServicoGerenciarLivraria {
    
    private ServicoEnvioMensagem sem;
    private ServicoOrdenacao so;
    
    List<Livro> livros;

    //Inversão de controle
    public ServicoGerenciarLivraria(ServicoEnvioMensagem sem, ServicoOrdenacao so, List<Livro> livros) {

        //Programação defensiva
        if (!(sem instanceof ServicoEnvioMensagem)) {
            throw new LivrariaException("Erro ao iniciar gerenciador: Sem serviço para envio de mensagens!");
        }
        
        if (!(so instanceof ServicoOrdenacao)) {
            throw new LivrariaException("Erro ao iniciar gerenciador: Sem serviço para organização de livros!");
        }
        
        this.so = so;
        this.sem = sem;
        this.livros = livros;
        
    }
    
    public void imprimirOpcoesLivraria() {

        StringBuilder sb = new StringBuilder();
        sb.append("1 - Adicionar livro").append("\n");
        sb.append("2 - Organizar livros adicionados");

        System.out.println();
        System.out.println(sb.toString());
    }
    
    public void adicionarLivros(Livro livro) {
        
        if (!autorValido(livro)) {
            throw new LivrariaException("Erro ao adicionar livro: Nome do autor inválido!");
        }
        
        if (livroJaExiste(livro)) {
            throw new LivrariaException("Erro ao adicionar livro: Livro já existe!");
        }
        
        
        livros.add(livro);
        
        sem.enviarMensagem("Livro adicionado!");
        System.out.println(livro);
        
    }
    
     private boolean autorValido(Livro livro) {
        return livro.getAutor().matches("[a-zA-Z\\s]+");
    }
     
     private boolean livroJaExiste(Livro livro) {
        
        Livro l = livros.stream()
                .filter(x -> x.getTitulo().equals(livro.getTitulo()))
                .findFirst()
                .orElse(null);
        
        return l != null;
    }
    
    public void organizarLivros(Integer opcOrganizacao, String opcForma) {

        if (!opcaoOrganizacaoValida(opcOrganizacao)) {
            throw new LivrariaException("Erro ao organizar livros: Opççao de organização inválida (1 a 3)!");
        }

        if (!opcaoFormaValida(opcForma)) {
            throw new LivrariaException("Erro ao organizar livros: Forma de organização inválida (crescente ou decrescente)!");
        }

        List<Comparavel> c = new ArrayList<>();

        for (Livro l : livros) {

            Comparavel cc = (Comparavel) l;
            c.add(cc);
        }

        so.ordenar(c, opcOrganizacao, opcForma);
        livros.clear();

        for (Comparavel cc : c) {

            Livro l = (Livro) cc;
            livros.add(l);
        }

        sem.enviarMensagem("Livros organizados!");
        imprimirLivros();

    }
    
    private boolean opcaoOrganizacaoValida(Integer opcOrganizacao) {
        
        return opcOrganizacao.equals(Constantes.OPC_POR_AUTOR)
                || opcOrganizacao.equals(Constantes.OPC_POR_TITULO)
                || opcOrganizacao.equals(Constantes.OPC_POR_PAGINAS);
        
    }
    
    private boolean opcaoFormaValida(String opcForma) {
        return opcForma.equalsIgnoreCase("crescente") || opcForma.equalsIgnoreCase("decrescente");
    }
    
    public void imprimirLivros() {
        
        for (Livro l : livros) {
            System.out.println(l);
        }
        
    }
    
    public void verificarListaLivros() {
        
        if (livros.size() < 1) {
            throw new LivrariaException("Erro ao organizar livros: Não há livros para serem organizados!");
        }
        
        if (livros.size() == 1) {
            throw new LivrariaException("Erro ao organizar livros: Só há um livro na livraria!");
        }
        
    }
    
    public void imprimirOpcoesOrganizacao() {
        
        StringBuilder sb = new StringBuilder();
        sb.append("1 Por autor").append("\n");
        sb.append("2 - Por título").append("\n");
        sb.append("3 - Por quantidade de páginas").append("\n");
        
        System.out.println(sb.toString());
        
    }
    
}
