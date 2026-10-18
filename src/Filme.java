/**
 * Representa um filme disponível no catálogo de streaming (VOD).
 * Responsabilidade: guardar o que é específico de um filme (duração) —
 * os dados comuns a qualquer mídia (título, gênero, classificação,
 * disponibilidade) agora vivem na superclasse Midia.
 *
 * "Filme é uma Midia" faz sentido no domínio: todo filme É um tipo de
 * mídia do catálogo, então usamos herança (extends), não composição.
 */
public class Filme extends Midia implements Reproduzivel {

    private int duracaoMinutos;

    public Filme(String titulo, int duracaoMinutos, String genero, String classificacaoIndicativa) {
        super(titulo, genero, classificacaoIndicativa); // valida e inicializa os dados comuns
        if (duracaoMinutos <= 0) {
            throw new IllegalArgumentException("Duração deve ser maior que zero.");
        }
        this.duracaoMinutos = duracaoMinutos;
    }

    // Sobrescrita (Atividade 8): reaproveita o comportamento comum da
    // superclasse com super.exibirFicha(), e acrescenta o que é
    // específico de Filme (a duração).
    @Override
    public void exibirFicha() {
        super.exibirFicha();
        System.out.println("Duração: " + duracaoMinutos + " min");
    }

    // Implementa o método abstrato de Midia.
    @Override
    public String getTipo() {
        return "Filme";
    }

    // Implementa o contrato da interface Reproduzivel.
    // A regra (conteúdo indisponível não toca) é lançada AQUI, no local
    // responsável por ela; quem trata é o fluxo do programa (Main).
    @Override
    public void reproduzir() throws ConteudoIndisponivelException {
        if (!estaDisponivel()) {
            throw new ConteudoIndisponivelException(getTitulo() + " não está disponível no catálogo.");
        }
        System.out.println("Reproduzindo filme: " + getTitulo() + " (" + duracaoMinutos + " min)");
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }
}
