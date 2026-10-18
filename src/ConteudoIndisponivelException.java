/**
 * Exceção personalizada do domínio de streaming.
 * Regra de negócio que a provoca: tentar reproduzir (ou buscar) um
 * conteúdo que não está disponível no catálogo.
 *
 * É "checked" (extends Exception) de propósito: queremos obrigar quem
 * chama a decidir o que fazer quando o conteúdo está indisponível,
 * em vez de deixar o erro passar despercebido.
 */
public class ConteudoIndisponivelException extends Exception {

    public ConteudoIndisponivelException(String mensagem) {
        super(mensagem);
    }
}
