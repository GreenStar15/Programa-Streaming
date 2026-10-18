import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Representa a lista pessoal (favoritos / "assistir mais tarde") de um usuário.
 * Responsabilidade: guardar quais mídias (filmes ou séries) o usuário salvou.
 *
 * Decisão de modelagem (Etapa 4): a lista era uma List<Midia> e usava
 * contains() para evitar repetição. Mas "não repetir" é exatamente o que um
 * Set garante sozinho. Usamos LinkedHashSet para manter também a ordem em que
 * o usuário foi salvando os itens.
 */
public class ListaPessoal {

    private String nomeDoUsuario;
    private Set<Midia> itensSalvos;

    public ListaPessoal(String nomeDoUsuario) {
        if (nomeDoUsuario == null || nomeDoUsuario.isBlank()) {
            throw new IllegalArgumentException("Nome do usuário não pode ser vazio.");
        }
        this.nomeDoUsuario = nomeDoUsuario;
        this.itensSalvos = new LinkedHashSet<>();
    }

    // Set.add() devolve false se o item já estava na lista.
    public void adicionarItem(Midia item) {
        if (!itensSalvos.add(item)) {
            System.out.println(item.getTitulo() + " já está na lista de " + nomeDoUsuario + ".");
            return;
        }
        System.out.println(item.getTitulo() + " adicionado à lista de " + nomeDoUsuario + ".");
    }

    public void removerItem(Midia item) {
        if (itensSalvos.remove(item)) {
            System.out.println(item.getTitulo() + " removido da lista de " + nomeDoUsuario + ".");
        }
    }

    // Iterator explícito: precisamos REMOVER elementos enquanto percorremos.
    // Fazer itensSalvos.remove(...) dentro de um for-each lançaria
    // ConcurrentModificationException; it.remove() é o jeito seguro.
    public void removerIndisponiveis() {
        Iterator<Midia> it = itensSalvos.iterator();
        while (it.hasNext()) {
            Midia item = it.next();
            if (!item.estaDisponivel()) {
                it.remove();
                System.out.println(item.getTitulo() + " saiu do catálogo e foi removido da lista de "
                        + nomeDoUsuario + ".");
            }
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
