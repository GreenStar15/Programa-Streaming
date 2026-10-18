/**
 * Classe de entrada do programa. Demonstra, no console, as funcionalidades
 * das etapas 1 a 4. As exceções são TRATADAS aqui (fluxo de interação).
 */
public class Main {

    public static void main(String[] args) {

        // ===== Etapas 1 e 2: classes, objetos e validação =====
        Filme filme1 = new Filme("Cidade das Estrelas", 128, "Drama", "12 anos");
        Filme filme2 = new Filme("Guardiões do Amanhã", 95, "Ficção Científica", "Livre");
        filme2.retirarDoCatalogo(); // filme2 passa a ser indisponível

        System.out.println("===== Fichas dos filmes =====");
        filme1.exibirFicha();
        System.out.println();
        filme2.exibirFicha();

        Usuario usuario1 = new Usuario("Ana", "Premium");
        Usuario usuario2 = new Usuario("Bruno", "Básico");

        Assinatura planoPremium = new Assinatura("Premium", 39.90, true, "4K");
        Assinatura planoBasico = new Assinatura("Básico", 19.90, false, "SD");
        System.out.println("\n===== Planos =====");
        planoPremium.exibirDetalhes();
        System.out.println("Permite 4K? " + planoPremium.verificarBeneficio("4K"));
        planoBasico.exibirDetalhes();
        System.out.println("Permite download? " + planoBasico.verificarBeneficio("download"));

        SessaoDeReproducao sessaoAna = new SessaoDeReproducao(filme1, usuario1);
        SessaoDeReproducao sessaoBruno = new SessaoDeReproducao(filme1, usuario2);
        System.out.println("\n===== Sessões de reprodução =====");
        sessaoAna.avancarTempo(40);
        sessaoAna.exibirStatus();
        sessaoBruno.avancarTempo(10);
        sessaoBruno.pausar();
        sessaoBruno.avancarTempo(20); // não avança: sessão pausada
        sessaoBruno.exibirStatus();

        System.out.println("\n===== Testes de validação (construtores) =====");
        try {
            new Filme("", -10, "Terror", "18 anos");
        } catch (IllegalArgumentException e) {
            System.out.println("Falha esperada ao criar Filme: " + e.getMessage());
        }
        try {
            new Usuario("Carla", "Plano Inexistente");
        } catch (IllegalArgumentException e) {
            System.out.println("Falha esperada ao criar Usuario: " + e.getMessage());
        }

        // ===== Etapa 3: herança, sobrecarga e polimorfismo =====
        Serie serie1 = new Serie("Mistérios do Norte", 3, 24, "Suspense", "16 anos");
        Serie serie2 = new Serie("Risadas em Série", 1, 10, "Comédia", "Livre");
        serie2.retirarDoCatalogo(); // serie2 passa a ser indisponível

        System.out.println("\n===== Polimorfismo (Midia aponta para Filme e Serie) =====");
        Midia midiaA = filme1;
        Midia midiaB = serie1;
        midiaA.exibirFicha();
        System.out.println();
        midiaB.exibirFicha();

        System.out.println("\n===== Sobrecarga: exibirResumo() =====");
        filme1.exibirResumo();
        filme1.exibirResumo(true);

        // ===== Etapa 4: interface + exceção personalizada (throw / throws / catch) =====
        System.out.println("\n===== Interface Reproduzivel + exceção =====");
        Reproduzivel r1 = filme1; // cenário válido
        Reproduzivel r2 = serie1; // cenário válido
        Reproduzivel r3 = filme2; // cenário inválido (indisponível)
        try {
            r1.reproduzir();
            r2.reproduzir();
            r3.reproduzir(); // lança ConteudoIndisponivelException
            System.out.println("Esta linha não é executada.");
        } catch (ConteudoIndisponivelException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("\n===== Usuario.assistirFilme (throws) + histórico em List =====");
        try {
            usuario1.assistirFilme(filme1);
            usuario1.assistirFilme(filme1); // reassistir: a List aceita repetição
            usuario2.assistirFilme(filme2); // lança: consumo do Bruno NÃO é alterado
        } catch (ConteudoIndisponivelException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        usuario1.exibirHistorico();
        usuario2.exibirResumoDeConsumo();

        // ===== Etapa 4: Catalogo com Map e Set =====
        System.out.println("\n===== Catalogo: Map (busca por id) + Set (título único) =====");
        Catalogo catalogo = new Catalogo();
        int idFilme = catalogo.cadastrar(filme1);
        int idSerie = catalogo.cadastrar(serie1);
        catalogo.cadastrar(filme2);
        catalogo.listar();

        try {
            catalogo.cadastrar(new Filme("cidade das estrelas", 100, "Drama", "12 anos"));
        } catch (IllegalArgumentException e) {
            System.out.println("Cadastro recusado (Set): " + e.getMessage());
        }

        try {
            System.out.print("Busca por id " + idSerie + ": ");
            catalogo.buscarPorId(idSerie).exibirResumo();
            catalogo.buscarPorId(99); // id inexistente
        } catch (ConteudoIndisponivelException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        try {
            catalogo.remover(idFilme); // remove do Map e do Set
            System.out.println("Removido o id " + idFilme + ". Restam " + catalogo.contarItens() + " conteúdo(s).");
            catalogo.cadastrar(new Filme("Cidade das Estrelas", 100, "Drama", "12 anos")); // agora o título está livre
            System.out.println("Título liberado no Set e cadastrado de novo.");
        } catch (ConteudoIndisponivelException | IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // ===== Etapa 4: ListaPessoal com Set + Iterator =====
        System.out.println("\n===== ListaPessoal: Set (sem repetição) + Iterator (remoção segura) =====");
        ListaPessoal listaAna = new ListaPessoal("Ana");
        listaAna.adicionarItem(filme1);
        listaAna.adicionarItem(serie1);
        listaAna.adicionarItem(serie1); // repetido: o Set recusa
        listaAna.adicionarItem(serie2); // está indisponível
        listaAna.exibirLista();

        System.out.println();
        listaAna.removerIndisponiveis(); // Iterator.remove()
        listaAna.exibirLista();
    }
}
