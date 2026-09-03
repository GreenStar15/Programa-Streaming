import java.util.ArrayList;
import java.util.List;

/**
 * Representa a lista pessoal (favoritos / "assistir mais tarde") de um usuário.
 * Responsabilidade: guardar quais mídias (filmes ou séries) o usuário salvou,
 * separado do histórico de consumo (que fica em Usuario/SessaoDeReproducao).
 *
 * Decisão de modelagem (Etapa 3): na Etapa 2, guardávamos filmes e séries em
 * duas listas separadas (List<Filme> e List<Serie>), porque as duas classes
 * não tinham nenhuma superclasse em comum. Agora que Filme e Serie são
 * ambas Midia, unificamos numa única List<Midia> — isso é exatamente o
 * tipo de duplicação que a herança existe para eliminar. Um Filme "é uma"
 * Midia, então pode entrar na mesma lista sem nenhum truque.
 */
public class ListaPessoal {

    private String nomeDoUsuario;
    private List<Midia> itensSalvos;

    public ListaPessoal(String nomeDoUsuario) {
        if (nomeDoUsuario == null || nomeDoUsuario.isBlank()) {
            throw new IllegalArgumentException("Nome do usuário não pode ser vazio.");
        }
        this.nomeDoUsuario = nomeDoUsuario;
        this.itensSalvos = new ArrayList<>();
    }

    // Um único método, graças ao polimorfismo: aceita tanto um Filme
    // quanto uma Serie, porque os dois "são" Midia.
    public void adicionarItem(Midia item) {
        if (itensSalvos.contains(item)) {
            System.out.println(item.getTitulo() + " já está na lista de " + nomeDoUsuario + ".");
            return;
        }
        itensSalvos.add(item);
        System.out.println(item.getTitulo() + " adicionado à lista de " + nomeDoUsuario + ".");
    }

    public void removerItem(Midia item) {
        if (itensSalvos.remove(item)) {
            System.out.println(item.getTitulo() + " removido da lista de " + nomeDoUsuario + ".");
        }
    }

    public void exibirLista() {
        System.out.println("Lista pessoal de " + nomeDoUsuario + " (" + itensSalvos.size() + " item(ns)):");
        for (Midia item : itensSalvos) {
            item.exibirResumo(); // polimorfismo: cada item imprime seu próprio resumo
        }
    }

    public int contarItens() {
        return itensSalvos.size();
    }
}
