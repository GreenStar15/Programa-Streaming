/**
 * Representa uma sessão de reprodução: o momento em que um usuário
 * está assistindo a um filme específico.
 * Responsabilidade: controlar o estado da reprodução (tempo assistido,
 * se está pausada ou não) — não é responsabilidade do Filme nem do
 * Usuario saber "em que ponto" a exibição está.
 */
public class SessaoDeReproducao {

    private Filme filme;
    private Usuario usuario;
    private int tempoAssistidoMinutos;
    private boolean pausado;

    public SessaoDeReproducao(Filme filme, Usuario usuario) {
        this.filme = filme;
        this.usuario = usuario;
        this.tempoAssistidoMinutos = 0;
        this.pausado = false;
    }

    // Comportamento real do objeto: avança o tempo assistido, se não estiver pausado
    public void avancarTempo(int minutos) {
        if (pausado) {
            System.out.println("Sessão pausada. Não é possível avançar o tempo.");
            return;
        }
        tempoAssistidoMinutos += minutos;
        if (tempoAssistidoMinutos > filme.getDuracaoMinutos()) {
            tempoAssistidoMinutos = filme.getDuracaoMinutos();
        }
    }

    public void pausar() {
        pausado = true;
    }

    public void retomar() {
        pausado = false;
    }

    public double calcularPercentualAssistido() {
        return (tempoAssistidoMinutos * 100.0) / filme.getDuracaoMinutos();
    }

    public void exibirStatus() {
        System.out.printf("%s assistindo \"%s\": %d/%d min (%.1f%%) - %s%n",
                usuario.getNome(), filme.getTitulo(), tempoAssistidoMinutos,
                filme.getDuracaoMinutos(), calcularPercentualAssistido(),
                pausado ? "pausado" : "em reprodução");
    }
}
