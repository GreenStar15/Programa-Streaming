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
        listaAna.adicionarItem(filme1);
        listaAna.adicionarItem(serie1);
        listaAna.exibirLista();

        System.out.println();
        listaBruno.adicionarItem(serie2);
        listaBruno.exibirLista();

        System.out.println("\nAna tem " + listaAna.contarItens() + " item(ns) salvo(s).");
        System.out.println("Bruno tem " + listaBruno.contarItens() + " item(ns) salvo(s).");

        // ===== Testes de estado inválido (Atividade 8) =====
        System.out.println("\n===== Testes de validação =====");

        try {
            Filme filmeInvalido = new Filme("", -10, "Terror", "18 anos");
        } catch (IllegalArgumentException e) {
            System.out.println("Falha esperada ao criar Filme: " + e.getMessage());
        }

        try {
            Usuario usuarioInvalido = new Usuario("Carla", "Plano Inexistente");
        } catch (IllegalArgumentException e) {
            System.out.println("Falha esperada ao criar Usuario: " + e.getMessage());
        }

        // Comparando método de negócio x setter genérico
        System.out.println("\n--- Método de negócio protegendo o objeto ---");
        usuario1.fazerUpgradeDePlano("Plano Vip Fake"); // recusado, com aviso
        usuario1.fazerUpgradeDePlano("Padrão");         // aceito, plano válido

        // ===== Polimorfismo (Atividade 9) =====
        // Referências do tipo da SUPERCLASSE (Midia) apontando para
        // objetos de subclasses diferentes (Filme e Serie).
        System.out.println("\n===== Polimorfismo =====");
        Midia midiaA = filme1;
        Midia midiaB = serie1;

        // Mesma chamada de método (exibirFicha), comportamento diferente,
        // dependendo do tipo REAL do objeto por trás da referência Midia.
        midiaA.exibirFicha();
        System.out.println();
        midiaB.exibirFicha();

        // ===== Sobrecarga (Atividade 5) =====
        // Mesmo nome de método, exibirResumo(), duas assinaturas diferentes.
        System.out.println("\n===== Sobrecarga: exibirResumo() =====");
        filme1.exibirResumo();          // versão sem parâmetros: resumo curto
        System.out.println();
        filme1.exibirResumo(true);      // versão com parâmetro: ficha completa
    }
}
