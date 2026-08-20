/**
 * Representa um filme disponível no catálogo de streaming (VOD).
 * Responsabilidade: guardar os dados do filme e informar se ele
 * está disponível para exibição no momento.
 */
public class Filme {

    private String titulo;
    private int duracaoMinutos;
    private String genero;
    private String classificacaoIndicativa;
    private boolean disponivel;

    public Filme(String titulo, int duracaoMinutos, String genero, String classificacaoIndicativa) {
        this.titulo = titulo;
        this.duracaoMinutos = duracaoMinutos;
        this.genero = genero;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.disponivel = true; // por padrão, todo filme cadastrado entra disponível
    }

    // Comportamento real do objeto: imprime a ficha técnica do filme
    public void exibirFicha() {
        System.out.println("Título: " + titulo);
        System.out.println("Duração: " + duracaoMinutos + " min");
        System.out.println("Gênero: " + genero);
        System.out.println("Classificação: " + classificacaoIndicativa);
        System.out.println("Disponível: " + (disponivel ? "Sim" : "Não"));
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

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }
}
