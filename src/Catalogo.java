import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Catálogo central da plataforma: reúne todas as mídias cadastradas.
 *
 * Duas coleções, cada uma com um motivo:
 *  - Map<Integer, Midia> porId: localizar uma mídia pelo id. TreeMap
 *    para listar sempre em ordem de id.
 *  - Set<String> titulosUnicos: impedir dois conteúdos com o mesmo
 *    título (ignorando maiúsculas/minúsculas).
 *
 * As coleções guardam objetos do domínio; elas não substituem as classes.
 */
public class Catalogo {

    private Map<Integer, Midia> porId = new TreeMap<>();
    private Set<String> titulosUnicos = new HashSet<>();
    private int proximoId = 1;

    // Rejeita título repetido. Set.add() devolve false se já existia.
    public int cadastrar(Midia midia) {
        String chave = midia.getTitulo().trim().toLowerCase();
        if (!titulosUnicos.add(chave)) {
            throw new IllegalArgumentException("Já existe conteúdo com o título: " + midia.getTitulo());
        }
        int id = proximoId++;
        porId.put(id, midia);
        return id;
    }

    public Midia buscarPorId(int id) throws ConteudoIndisponivelException {
        Midia midia = porId.get(id);
        if (midia == null) {
            throw new ConteudoIndisponivelException("Não existe conteúdo com id " + id + " no catálogo.");
        }
        return midia;
    }

    // Remove das DUAS coleções, para o Map e o Set nunca ficarem inconsistentes.
    public void remover(int id) throws ConteudoIndisponivelException {
        Midia midia = buscarPorId(id);
        porId.remove(id);
        titulosUnicos.remove(midia.getTitulo().trim().toLowerCase());
    }

    public void listar() {
        System.out.println("Catálogo (" + porId.size() + " conteúdo(s)):");
        for (Map.Entry<Integer, Midia> entrada : porId.entrySet()) {
            System.out.print("  #" + entrada.getKey() + " ");
            entrada.getValue().exibirResumo();
        }
    }

    public int contarItens() {
        return porId.size();
    }
}
