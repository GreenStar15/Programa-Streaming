/**
 * Superclasse que representa qualquer conteúdo do catálogo de streaming.
 * Responsabilidade: guardar os dados comuns a qualquer mídia (título,
 * gênero, classificação, disponibilidade) e os comportamentos que não
 * dependem de ser filme ou série.
 *
 * Decisão de modelagem (Etapa 3): Filme e Serie sempre tiveram atributos
 * e métodos praticamente idênticos (titulo, genero, classificacaoIndicativa,
 * disponivel, exibirFicha(), estaDisponivel(), retirarDoCatalogo()). A frase
 * "Filme é uma Midia" e "Serie é uma Midia" faz sentido no domínio — por
 * isso migramos esse conteúdo comum para cá, em vez de deixar duplicado.
 */
public class Midia {

    private String titulo;
    private String genero;
    private String classificacaoIndicativa;
    private boolean disponivel;

    public Midia(String titulo, String genero, String classificacaoIndicativa) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título não pode ser vazio.");
        }
        if (genero == null || genero.isBlank()) {
            throw new IllegalArgumentException("Gênero não pode ser vazio.");
        }
        if (classificacaoIndicativa == null || classificacaoIndicativa.isBlank()) {
            throw new IllegalArgumentException("Classificação indicativa não pode ser vazia.");
        }
        this.titulo = titulo;
        this.genero = genero;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.disponivel = true; // por padrão, toda mídia cadastrada entra disponível
    }

    // Comportamento comum: imprime os dados básicos de qualquer mídia.
    // As subclasses sobrescrevem isso (@Override) para acrescentar seus
    // próprios dados específicos (duração, temporadas, etc).
    public void exibirFicha() {
        System.out.println("Título: " + titulo);
        System.out.println("Gênero: " + genero);
        System.out.println("Classificação: " + classificacaoIndicativa);
        System.out.println("Disponível: " + (disponivel ? "Sim" : "Não"));
    }

    // Sobrecarga (Atividade 5): mesma ideia de "exibirResumo", duas
    // assinaturas diferentes. Faz sentido no domínio porque às vezes
    // (lista de resultados de busca) queremos só o título; outras vezes
    // (tela de detalhes) queremos o resumo completo.
    public void exibirResumo() {
        System.out.println(titulo + " (" + genero + ")");
    }

    public void exibirResumo(boolean detalhado) {
        if (!detalhado) {
            exibirResumo();
            return;
        }
        exibirFicha();
    }

    public boolean estaDisponivel() {
        return disponivel;
    }

    public void retirarDoCatalogo() {
        this.disponivel = false;
    }

    // Método de domínio, no lugar de um setDisponivel(boolean) genérico.
    // Agora Serie ganha esse método de graça, por herança — antes da
    // Etapa 3 essa era uma lacuna real da classe Serie.
    public void reincluirNoCatalogo() {
        this.disponivel = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public String getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }
}
