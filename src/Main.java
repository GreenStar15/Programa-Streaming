/**
 * Classe de entrada do programa.
 * Cria objetos de Filme, Usuario, Assinatura e SessaoDeReproducao
 * e demonstra, no console, estados e comportamentos diferentes entre eles.
 */
public class Main {

    public static void main(String[] args) {

        // Dois objetos da mesma classe (Filme) com estados diferentes
        Filme filme1 = new Filme("Cidade das Estrelas", 128, "Drama", "12 anos");
        Filme filme2 = new Filme("Guardiões do Amanhã", 95, "Ficção Científica", "Livre");
        filme2.retirarDoCatalogo(); // muda o estado de filme2 para indisponível

        System.out.println("===== Catálogo =====");
        filme1.exibirFicha();
        System.out.println();
        filme2.exibirFicha();

        // Dois objetos da mesma classe (Usuario) com estados diferentes
        Usuario usuario1 = new Usuario("Ana", "Premium");
        Usuario usuario2 = new Usuario("Bruno", "Básico");

        System.out.println("\n===== Sessão de uso =====");
        usuario1.assistirFilme(filme1);
        usuario2.assistirFilme(filme2); // indisponível: não altera o estado de usuario2

        System.out.println();
        usuario1.exibirResumoDeConsumo();
        System.out.println();
        usuario2.exibirResumoDeConsumo();

        // Dois objetos da mesma classe (Assinatura) com estados diferentes
        Assinatura planoPremium = new Assinatura("Premium", 39.90, true, "4K");
        Assinatura planoBasico = new Assinatura("Básico", 19.90, false, "SD");

        System.out.println("\n===== Planos =====");
        planoPremium.exibirDetalhes();
        System.out.println("Permite 4K? " + planoPremium.verificarBeneficio("4K"));
        System.out.println();
        planoBasico.exibirDetalhes();
        System.out.println("Permite download? " + planoBasico.verificarBeneficio("download"));
        planoBasico.renovar();

        // Dois objetos da mesma classe (SessaoDeReproducao) com estados diferentes
        SessaoDeReproducao sessaoAna = new SessaoDeReproducao(filme1, usuario1);
        SessaoDeReproducao sessaoBruno = new SessaoDeReproducao(filme1, usuario2);

        System.out.println("\n===== Sessões de reprodução =====");
        sessaoAna.avancarTempo(40);
        sessaoAna.exibirStatus();

        sessaoBruno.avancarTempo(10);
        sessaoBruno.pausar();
        sessaoBruno.avancarTempo(20); // não deve avançar, pois está pausada
        sessaoBruno.exibirStatus();

        // Dois objetos da mesma classe (Serie) com estados diferentes
        Serie serie1 = new Serie("Mistérios do Norte", 3, 24, "Suspense", "16 anos");
        Serie serie2 = new Serie("Risadas em Série", 1, 10, "Comédia", "Livre");

        System.out.println("\n===== Séries =====");
        serie1.exibirFicha();
        serie1.assistirEpisodio();
        serie1.assistirEpisodio();

        System.out.println();
        serie2.exibirFicha();
        serie2.retirarDoCatalogo(); // muda o estado de serie2
        serie2.assistirEpisodio(); // deve avisar que não está disponível

        // Dois objetos da mesma classe (ListaPessoal) com estados diferentes
        ListaPessoal listaAna = new ListaPessoal("Ana");
        ListaPessoal listaBruno = new ListaPessoal("Bruno");

        System.out.println("\n===== Listas pessoais =====");
        listaAna.adicionarFilme(filme1);
        listaAna.adicionarSerie(serie1);
        listaAna.exibirLista();

        System.out.println();
        listaBruno.adicionarSerie(serie2);
        listaBruno.exibirLista();

        System.out.println("\nAna tem " + listaAna.contarItens() + " item(ns) salvo(s).");
        System.out.println("Bruno tem " + listaBruno.contarItens() + " item(ns) salvo(s).");
    }
}
