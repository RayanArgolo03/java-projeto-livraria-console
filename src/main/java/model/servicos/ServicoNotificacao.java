package model.servicos;

public class ServicoNotificacao implements ServicoEnvioMensagem {

    @Override
    public void enviarMensagem(String menssagem) {
        System.out.println();
        System.out.println(menssagem);
    }
}
