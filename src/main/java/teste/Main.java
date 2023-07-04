package teste;

import java.util.ArrayList;
import java.util.Scanner;
import model.constantes.Constantes;
import model.entidades.Livro;
import model.exceptions.LivrariaException;
import model.servicos.ServicoNotificacao;
import model.servicos.ServicoGerenciarLivraria;
import model.servicos.ServicoOrdenarLivros;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            //Injeção de dependências - Envio de mensagem e ordenação de livros
            ServicoGerenciarLivraria sgl = new ServicoGerenciarLivraria(new ServicoNotificacao(), new ServicoOrdenarLivros(), new ArrayList<>());

            Integer opcMenu = 0;
            do {

                sgl.imprimirOpcoesLivraria();

                System.out.print("Sua escolha: ");
                opcMenu = sc.nextInt();

                switch (opcMenu) {

                    case Constantes.OPC_ADICIONAR -> {

                        System.out.println();

                        System.out.print("Nome do livro: ");
                        sc.nextLine();
                        String nome = sc.nextLine();

                        //Passando nome para upper case
                        String[] aux = nome.split(" ");
                        for (int i = 0; i < aux.length; i++) {

                            if (!aux[i].equals("de") && !aux[i].equals("da")) {
                                aux[i] = String.valueOf(aux[i].charAt(0)).toUpperCase().concat(aux[i].substring(1).toLowerCase());
                            }
                        }

                        nome = String.join(" ", aux);

                        System.out.print("Autor de " + nome + ": ");
                        String autor = sc.nextLine();

                        //Passando autor para upper case
                        String[] temp = autor.split(" ");
                        for (int i = 0; i < temp.length; i++) {

                            if (!aux[i].equals("de") && !aux[i].equals("da")) {
                                temp[i] = String.valueOf(aux[i].charAt(0)).toUpperCase().concat(aux[i].substring(1).toLowerCase());
                            }

                        }

                        autor = String.join(" ", temp);

                        System.out.print("Número de páginas de " + nome + ": ");
                        Integer paginas = sc.nextInt();
                        sc.nextLine();

                        sgl.adicionarLivros(new Livro(nome, autor, paginas));
                    }

                    case Constantes.OPC_ORGANIZAR -> {

                        //Retorna Exceções
                        sgl.verificarListaLivros();

                        System.out.println();
                        System.out.println("Como deseja organizar seus livros?");
                        sgl.imprimirOpcoesOrganizacao();

                        System.out.print("Sua escolha: ");
                        Integer opcOrganizacao = sc.nextInt();

                        System.out.print("De forma crescente ou decrescente? ");
                        sc.nextLine();

                        String opcForma = sc.next();

                        sgl.organizarLivros(opcOrganizacao, opcForma);

                    }

                    default ->
                        throw new IllegalArgumentException("Opçao inexistente ou inválida!");
                }

            } while (opcMenu == 1 || opcMenu == 2);

        }
        
        catch (LivrariaException e) {
            System.out.println(e.getMessage());
        }
        
        catch (RuntimeException e) {
            System.out.println("Erro de execução: Entrada de dados inválida!");
        }
        
        finally {
            sc.close();
        }

    }

}
