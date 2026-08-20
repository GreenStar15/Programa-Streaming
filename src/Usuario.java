/**
 * Representa o usuário assinante do serviço de streaming.
 * Responsabilidade: guardar o plano do usuário e seu consumo
 * de conteúdo (o que assistiu, quanto tempo assistiu).
 */
public class Usuario {

    private String nome;
    private String plano; // Básico, Padrão ou Premium
    private int minutosAssistidosNoMes;

    public Usuario(String nome, String plano) {
        this.nome = nome;
        this.plano = plano;
        this.minutosAssistidosNoMes = 0;
    }

    // Comportamento real do objeto: assistir a um filme altera o estado do usuário
    public void assistirFilme(Filme filme) {
        if (!filme.estaDisponivel()) {
            System.out.println(nome + " tentou assistir \"" + filme.getTitulo()
                    + "\", mas o filme não está disponível.");
            return;
        }
        minutosAssistidosNoMes += filme.getDuracaoMinutos();
        System.out.println(nome + " assistiu \"" + filme.getTitulo() + "\" ("
                + filme.getDuracaoMinutos() + " min).");
    }

    public void exibirResumoDeConsumo() {
        System.out.println("Usuário: " + nome);
        System.out.println("Plano: " + plano);
        System.out.println("Minutos assistidos no mês: " + minutosAssistidosNoMes);
    }

    public String getNome() {
        return nome;
    }

    public int getMinutosAssistidosNoMes() {
        return minutosAssistidosNoMes;
    }
}
