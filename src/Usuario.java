/**
 * Representa o usuário assinante do serviço de streaming.
 * Responsabilidade: guardar o plano do usuário e seu consumo
 * de conteúdo (o que assistiu, quanto tempo assistiu).
 */
import java.util.List;

public class Usuario {

    private static final List<String> PLANOS_VALIDOS = List.of("Básico", "Padrão", "Premium");

    private String nome;
    private String plano; // Básico, Padrão ou Premium
    private int minutosAssistidosNoMes;

    public Usuario(String nome, String plano) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        if (!PLANOS_VALIDOS.contains(plano)) {
            throw new IllegalArgumentException("Plano inválido: " + plano);
        }
        this.nome = nome;
        this.plano = plano;
        this.minutosAssistidosNoMes = 0;
    }

    // Método de domínio, no lugar de um setPlano(String) genérico
    public void fazerUpgradeDePlano(String novoPlano) {
        if (!PLANOS_VALIDOS.contains(novoPlano)) {
            System.out.println("Plano inválido, upgrade não realizado.");
            return;
        }
        this.plano = novoPlano;
        System.out.println(nome + " atualizou o plano para " + novoPlano + ".");
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
