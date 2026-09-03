/**
 * Representa uma série disponível no catálogo de streaming (VOD).
 * Responsabilidade: guardar o que é específico de uma série (temporadas,
 * episódios, progresso) — os dados comuns a qualquer mídia agora vivem
 * na superclasse Midia.
 *
 * "Serie é uma Midia" faz sentido no domínio, pelo mesmo motivo de Filme.
 */
public class Serie extends Midia {

    private int numeroTemporadas;
    private int totalEpisodios;
    private int episodiosAssistidos;

    public Serie(String titulo, int numeroTemporadas, int totalEpisodios, String genero, String classificacaoIndicativa) {
        super(titulo, genero, classificacaoIndicativa); // valida e inicializa os dados comuns
        if (numeroTemporadas <= 0 || totalEpisodios <= 0) {
            throw new IllegalArgumentException("Temporadas e episódios devem ser maiores que zero.");
        }
        this.numeroTemporadas = numeroTemporadas;
        this.totalEpisodios = totalEpisodios;
        this.episodiosAssistidos = 0;
    }

    // Sobrescrita (Atividade 8): mesmo contrato (exibirFicha), comportamento
    // diferente do de Filme — acrescenta temporadas/episódios em vez de duração.
    @Override
    public void exibirFicha() {
        super.exibirFicha();
        System.out.println("Temporadas: " + numeroTemporadas);
        System.out.println("Episódios: " + totalEpisodios);
    }

    // Comportamento que só Serie tem: acompanhar progresso episódio a episódio.
    // Antes da Etapa 3, Serie não tinha reincluirNoCatalogo() — agora ganha
    // esse método de graça, herdado de Midia.
    public void assistirEpisodio() {
        if (!estaDisponivel()) {
            System.out.println(getTitulo() + " não está disponível no momento.");
            return;
        }
        if (episodiosAssistidos >= totalEpisodios) {
            System.out.println("Você já assistiu a todos os episódios de " + getTitulo() + ".");
            return;
        }
        episodiosAssistidos++;
        System.out.println("Assistindo episódio " + episodiosAssistidos + "/" + totalEpisodios + " de " + getTitulo());
        if (episodiosAssistidos == totalEpisodios) {
            System.out.println(getTitulo() + " concluída!");
        }
    }

    public int getEpisodiosAssistidos() {
        return episodiosAssistidos;
    }
}
