/**
 * Representa uma série disponível no catálogo de streaming (VOD).
 * Diferente de Filme, uma série tem progresso por episódio, não uma
 * duração única — por isso não reaproveitamos a classe Filme, mesmo
 * sendo parecidas: a responsabilidade (e o estado que cada uma guarda)
 * é diferente.
 */
public class Serie {

    private String titulo;
    private int numeroTemporadas;
    private int totalEpisodios;
    private String genero;
    private String classificacaoIndicativa;
    private boolean disponivel;
    private int episodiosAssistidos;

    public Serie(String titulo, int numeroTemporadas, int totalEpisodios, String genero, String classificacaoIndicativa) {
        this.titulo = titulo;
        this.numeroTemporadas = numeroTemporadas;
        this.totalEpisodios = totalEpisodios;
        this.genero = genero;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.disponivel = true;
        this.episodiosAssistidos = 0;
    }

    // Comportamento real do objeto: imprime a ficha técnica da série
    public void exibirFicha() {
        System.out.println("Título: " + titulo);
        System.out.println("Temporadas: " + numeroTemporadas);
        System.out.println("Episódios: " + totalEpisodios);
        System.out.println("Gênero: " + genero);
        System.out.println("Classificação: " + classificacaoIndicativa);
        System.out.println("Disponível: " + (disponivel ? "Sim" : "Não"));
    }

    // Comportamento que Filme não tem: acompanhar progresso episódio a episódio
    public void assistirEpisodio() {
        if (!disponivel) {
            System.out.println(titulo + " não está disponível no momento.");
            return;
        }
        if (episodiosAssistidos >= totalEpisodios) {
            System.out.println("Você já assistiu a todos os episódios de " + titulo + ".");
            return;
        }
        episodiosAssistidos++;
        System.out.println("Assistindo episódio " + episodiosAssistidos + "/" + totalEpisodios + " de " + titulo);
        if (episodiosAssistidos == totalEpisodios) {
            System.out.println(titulo + " concluída!");
        }
    }

    public boolean estaDisponivel() {
        return disponivel;
    }

    public void retirarDoCatalogo() {
        this.disponivel = false;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getEpisodiosAssistidos() {
        return episodiosAssistidos;
    }
}
