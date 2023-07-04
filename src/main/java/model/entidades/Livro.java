package model.entidades;

import model.constantes.Constantes;

public class Livro implements Comparavel {

    private String autor;
    private String titulo;
    private Integer paginas;

    public Livro(String titulo, String autor, Integer paginas) {

        if (paginas < 1) {
            throw new IllegalArgumentException("Número de páginas inválido!");
        }

        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;

    }

    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getPaginas() {
        return paginas;
    }

    @Override
    public int comparar(Object o) {
        return 1;
    }

    @Override
    public int comparar(Object o, String opcForma, Integer opcOrganizacao) {
        
        int comparacao = 0;
        
        if (!(o instanceof Livro)){
            throw new IllegalArgumentException("Erro ao comparar livros: Objeto passado não é um livro!");
        }
        
        Livro l = (Livro) o;
        
        if (opcOrganizacao.equals(Constantes.OPC_POR_AUTOR)){
            comparacao = this.getAutor().compareTo(l.getAutor());
        }
        
        else if (opcOrganizacao.equals(Constantes.OPC_POR_TITULO)){
            comparacao = this.getTitulo().compareTo(l.getTitulo());
        }
        
        else if (opcOrganizacao.equals(Constantes.OPC_POR_PAGINAS)){
         comparacao = this.getPaginas().compareTo(l.getPaginas());
        }
        
    
        //Caso seja decrescente: inverte a ordem
        if (opcForma.equalsIgnoreCase("decrescente")){
            comparacao = -comparacao;
        }
        
        return comparacao;
    }

    
    @Override
    public String toString() {
        return String.format(" '%s' ", titulo) + " de " + autor + " com " + paginas + " páginas";
    }
}
