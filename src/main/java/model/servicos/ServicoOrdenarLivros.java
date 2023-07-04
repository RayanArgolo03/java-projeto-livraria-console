package model.servicos;


import java.util.List;
import model.entidades.Comparavel;

public class ServicoOrdenarLivros implements ServicoOrdenacao {

    @Override
    public void ordenar(List<Comparavel> objetosComparaveis, Integer opcOrganizacao, String opcForma) {
        
        for (int i = 0; i < objetosComparaveis.size(); i++) {
            for (int j = i + 1; j < objetosComparaveis.size(); j++) {

                if (objetosComparaveis.get(i).comparar(objetosComparaveis.get(j), opcForma, opcOrganizacao) > 0) {

                    Comparavel c = objetosComparaveis.get(i);
                    objetosComparaveis.set(i, objetosComparaveis.get(j));
                    objetosComparaveis.set(j, c);

                }
            }
        }
        
    }


}
