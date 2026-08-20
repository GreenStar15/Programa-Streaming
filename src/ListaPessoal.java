import java.util.ArrayList;
import java.util.List;

/**
 * Representa a lista pessoal (favoritos / "assistir mais tarde") de um usuário.
 * Responsabilidade: guardar quais filmes e séries o usuário salvou,
 * separado do histórico de consumo (que fica em Usuario/SessaoDeReproducao).
 *
 * Guardamos filmes e séries em duas listas separadas, e não em uma lista
 * única, porque Filme e Serie não compartilham uma superclasse — o grupo
 * decidiu não usar herança/interfaces nesta fase do projeto.
 */
public class ListaPessoal {

    private String nomeDoUsuario;
    private List<Filme> filmesSalvos;
    private List<Serie> seriesSalvas;

    public ListaPessoal(String nomeDoUsuario) {
        this.nomeDoUsuario = nomeDoUsuario;
        this.filmesSalvos = new ArrayList<>();
        this.seriesSalvas = new ArrayList<>();
    }

    // Comportamento real do objeto: adiciona um filme, evitando duplicado
    public void adicionarFilme(Filme filme) {
        if (filmesSalvos.contains(filme)) {
            System.out.println(filme.getTitulo() + " já está na lista de " + nomeDoUsuario + ".");
            return;
        }
        filmesSalvos.add(filme);
        System.out.println(filme.getTitulo() + " adicionado à lista de " + nomeDoUsuario + ".");
    }

    public void adicionarSerie(Serie serie) {
        if (seriesSalvas.contains(serie)) {
            System.out.println(serie.getTitulo() + " já está na lista de " + nomeDoUsuario + ".");
            return;
        }
        seriesSalvas.add(serie);
        System.out.println(serie.getTitulo() + " adicionada à lista de " + nomeDoUsuario + ".");
    }

    public void removerFilme(Filme filme) {
        if (filmesSalvos.remove(filme)) {
            System.out.println(filme.getTitulo() + " removido da lista de " + nomeDoUsuario + ".");
        }
    }

    public void exibirLista() {
        System.out.println("Lista pessoal de " + nomeDoUsuario + ":");
        System.out.println("  Filmes (" + filmesSalvos.size() + "):");
        for (Filme f : filmesSalvos) {
            System.out.println("   - " + f.getTitulo());
        }
        System.out.println("  Séries (" + seriesSalvas.size() + "):");
        for (Serie s : seriesSalvas) {
            System.out.println("   - " + s.getTitulo());
        }
    }

    public int contarItens() {
        return filmesSalvos.size() + seriesSalvas.size();
    }
}
