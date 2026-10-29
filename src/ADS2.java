import java.util.Scanner;

/**
 * ADS2 - Executavel Console do GreenStar Streaming (Grupo 4).
 * Menu interativo das Etapas 1-4. Coexiste com o Main.java original.
 * Saida em ASCII puro para maxima compatibilidade com terminais.
 */
public class ADS2 {

    private static final Scanner SC = new Scanner(System.in);
    private static final Catalogo catalogo = new Catalogo();
    private static final Usuario[] usuarios = new Usuario[2];
    private static final String[] nomesUsuarios = {"Ana", "Bruno"};

    private static Usuario usuarioLogado;
    private static String nomeUsuarioLogado;
    private static ListaPessoal listaPessoal;

    public static void main(String[] args) {
        carregarDadosIniciais();
        exibirBanner();

        if (!escolherUsuario()) {
            System.out.println("\nEncerrando.");
            SC.close();
            return;
        }

        listaPessoal = new ListaPessoal(nomeUsuarioLogado);
        menuPrincipal();

        System.out.println("\nSessao encerrada. Ate a proxima!");
        SC.close();
    }

    // ================ INICIALIZACAO ================

    private static void carregarDadosIniciais() {
        Filme f1 = new Filme("Cidade das Estrelas", 128, "Drama", "12 anos");
        Filme f2 = new Filme("Guardioes do Amanha", 95, "Ficcao Cientifica", "Livre");
        f2.retirarDoCatalogo();

        Serie s1 = new Serie("Misterios do Norte", 3, 24, "Suspense", "16 anos");
        Serie s2 = new Serie("Risadas em Serie", 1, 10, "Comedia", "Livre");
        s2.retirarDoCatalogo();

        cadastrarSeguro(f1);
        cadastrarSeguro(f2);
        cadastrarSeguro(s1);
        cadastrarSeguro(s2);

        usuarios[0] = new Usuario(nomesUsuarios[0], "Premium");
        usuarios[1] = new Usuario(nomesUsuarios[1], "Básico");
    }

    private static void cadastrarSeguro(Midia m) {
        try { catalogo.cadastrar(m); }
        catch (IllegalArgumentException ignored) { /* dados iniciais sao unicos */ }
    }

    private static void exibirBanner() {
        System.out.println();
        System.out.println("+==============================================+");
        System.out.println("|       Sistema de VOD do Grupo 4 - ADS2       |");
        System.out.println("+==============================================+");
    }

    private static boolean escolherUsuario() {
        while (true) {
            System.out.println("\nQuem esta assistindo?");
            System.out.println("  1 - " + nomesUsuarios[0] + "  (Premium)");
            System.out.println("  2 - " + nomesUsuarios[1] + "  (Basico)");
            System.out.println("  0 - Sair");

            int op = lerInteiro("\n  Escolha o perfil: ");
            switch (op) {
                case 1 -> { usuarioLogado = usuarios[0]; nomeUsuarioLogado = nomesUsuarios[0]; }
                case 2 -> { usuarioLogado = usuarios[1]; nomeUsuarioLogado = nomesUsuarios[1]; }
                case 0 -> { return false; }
                default -> { System.out.println("  [X] Perfil invalido."); continue; }
            }
            System.out.println("\n  [OK] Bem-vindo(a), " + nomeUsuarioLogado + "!");
            return true;
        }
    }

    // ================ MENU PRINCIPAL ================

    private static void menuPrincipal() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println();
            System.out.println("+-------------- MENU PRINCIPAL ----------------+");
            System.out.println("| 1 - Ver catalogo completo                    |");
            System.out.println("| 2 - Escolher e assistir uma midia            |");
            System.out.println("| 3 - Ver detalhes de uma midia                |");
            System.out.println("| 4 - Minha lista pessoal                      |");
            System.out.println("| 5 - Meu historico                            |");
            System.out.println("| 6 - Planos e assinaturas                     |");
            System.out.println("| 7 - Executar testes (demonstracao tecnica)   |");
            System.out.println("| 0 - Sair                                     |");
            System.out.println("+----------------------------------------------+");

            opcao = lerInteiro("  Escolha: ");

            switch (opcao) {
                case 1 -> verCatalogo();
                case 2 -> escolherEAssistir();
                case 3 -> verDetalhes();
                case 4 -> menuListaPessoal();
                case 5 -> menuHistorico();
                case 6 -> verPlanos();
                case 7 -> executarTestes();
                case 0 -> System.out.println("\n  Encerrando...");
                default -> System.out.println("  [X] Opcao invalida.");
            }
        }
    }

    // ================ OPCOES ================

    private static void verCatalogo() {
        System.out.println("\n----- CATALOGO -----");
        catalogo.listar();
    }

    private static void escolherEAssistir() {
        System.out.println("\n----- ESCOLHER MIDIA -----");
        catalogo.listar();
        System.out.println();
        int id = lerInteiro("  Informe o ID da midia que deseja assistir: ");

        Midia m = buscarMidia(id);
        if (m == null) {
            System.out.println("  [X] Nenhuma midia com esse ID.");
            return;
        }

        if (!(m instanceof Filme)) {
            System.out.println("\n  [!] No momento, o metodo assistirFilme do usuario aceita apenas filmes.");
            System.out.println("      (Series ainda nao sao registradas no historico.)");
            return;
        }

        Filme filme = (Filme) m;

        try {
            usuarioLogado.assistirFilme(filme);
            System.out.println("\n  > Reproduzindo:");
            filme.exibirFicha();
            System.out.println("\n  [OK] Reproducao registrada no seu historico.");
        } catch (ConteudoIndisponivelException e) {
            System.out.println("\n  [!] Nao foi possivel reproduzir: " + e.getMessage());
        }
    }

    private static void verDetalhes() {
        System.out.println("\n----- DETALHES -----");
        catalogo.listar();
        System.out.println();
        int id = lerInteiro("  Informe o ID da midia: ");

        Midia m = buscarMidia(id);
        if (m == null) {
            System.out.println("  [X] Nenhuma midia com esse ID.");
            return;
        }
        System.out.println();
        m.exibirFicha();
    }

    private static void menuListaPessoal() {
        int op = -1;
        while (op != 0) {
            System.out.println();
            System.out.println("  -- MINHA LISTA PESSOAL --");
            System.out.println("  1 - Adicionar midia do catalogo");
            System.out.println("  2 - Ver minha lista");
            System.out.println("  3 - Remover midias indisponiveis (Iterator)");
            System.out.println("  0 - Voltar");

            op = lerInteiro("  Escolha: ");

            switch (op) {
                case 1 -> adicionarNaLista();
                case 2 -> { System.out.println(); listaPessoal.exibirLista(); }
                case 3 -> removerIndisponiveis();
                case 0 -> { }
                default -> System.out.println("  [X] Opcao invalida.");
            }
        }
    }

    private static void adicionarNaLista() {
        System.out.println();
        catalogo.listar();
        System.out.println();
        int id = lerInteiro("  ID da midia a adicionar: ");

        Midia m = buscarMidia(id);
        if (m == null) {
            System.out.println("  [X] Nenhuma midia com esse ID.");
            return;
        }
        listaPessoal.adicionarItem(m);
        System.out.println("\n  Operacao concluida. Sua lista atual:");
        listaPessoal.exibirLista();
    }

    private static void removerIndisponiveis() {
        System.out.println("\n  Removendo midias indisponiveis da sua lista...");
        listaPessoal.removerIndisponiveis();
        System.out.println("  Lista apos a remocao:");
        listaPessoal.exibirLista();
    }

    private static void menuHistorico() {
        int op = -1;
        while (op != 0) {
            System.out.println();
            System.out.println("  -- MEU HISTORICO --");
            System.out.println("  1 - Ver historico completo");
            System.out.println("  2 - Ver resumo de consumo");
            System.out.println("  0 - Voltar");

            op = lerInteiro("  Escolha: ");

            switch (op) {
                case 1 -> { System.out.println(); usuarioLogado.exibirHistorico(); }
                case 2 -> { System.out.println(); usuarioLogado.exibirResumoDeConsumo(); }
                case 0 -> { }
                default -> System.out.println("  [X] Opcao invalida.");
            }
        }
    }

    private static void verPlanos() {
        System.out.println("\n----- PLANOS DISPONIVEIS -----");
        Assinatura premium = new Assinatura("Premium", 39.90, true, "4K");
        Assinatura basico = new Assinatura("Básico", 19.90, false, "SD");

        premium.exibirDetalhes();
        System.out.println("  Suporta 4K? " + premium.verificarBeneficio("4K"));
        System.out.println();
        basico.exibirDetalhes();
        System.out.println("  Suporta download? " + basico.verificarBeneficio("download"));
    }

    // ================ TESTES ================

    private static void executarTestes() {
        System.out.println("\n+---- TESTES DE VALIDACAO (Etapa 4) ----+");
        System.out.println("| Demonstracao dos conceitos tecnicos   |");
        System.out.println("+---------------------------------------+");

        System.out.println("\n[1] Construtor de Filme com dados invalidos:");
        try { new Filme("", -10, "Terror", "18 anos"); }
        catch (IllegalArgumentException e) { System.out.println("  [OK] Bloqueado: " + e.getMessage()); }

        System.out.println("\n[2] Usuario com plano inexistente:");
        try { new Usuario("Carla", "Plano Inexistente"); }
        catch (IllegalArgumentException e) { System.out.println("  [OK] Bloqueado: " + e.getMessage()); }

        System.out.println("\n[3] Reproduzir midia indisponivel:");
        Filme ind = new Filme("Midia Bloqueada", 100, "Drama", "Livre");
        ind.retirarDoCatalogo();
        try { ((Reproduzivel) ind).reproduzir(); }
        catch (ConteudoIndisponivelException e) { System.out.println("  [OK] Excecao: " + e.getMessage()); }

        System.out.println("\n[4] Set recusando titulo duplicado:");
        Catalogo tmp = new Catalogo();
        try {
            tmp.cadastrar(new Filme("Titulo Unico", 100, "Drama", "Livre"));
            tmp.cadastrar(new Filme("titulo unico", 100, "Drama", "Livre"));
        } catch (IllegalArgumentException e) { System.out.println("  [OK] Set recusou: " + e.getMessage()); }

        System.out.println("\n[5] Buscar ID inexistente no Map:");
        try { tmp.buscarPorId(9999); }
        catch (ConteudoIndisponivelException e) { System.out.println("  [OK] Nao encontrado: " + e.getMessage()); }

        System.out.println("\n[6] Iterator removendo itens indisponiveis:");
        ListaPessoal t = new ListaPessoal("Teste");
        Filme ok  = new Filme("Disponivel", 100, "Acao", "Livre");
        Filme off = new Filme("Indisponivel", 100, "Drama", "Livre");
        off.retirarDoCatalogo();
        t.adicionarItem(ok);
        t.adicionarItem(off);
        System.out.println("  Antes:");  t.exibirLista();
        t.removerIndisponiveis();
        System.out.println("  Depois:"); t.exibirLista();
    }

    // ================ UTILITARIOS ================

    private static Midia buscarMidia(int id) {
        try { return catalogo.buscarPorId(id); }
        catch (ConteudoIndisponivelException e) { return null; }
    }

    private static int lerInteiro(String prompt) {
        while (true) {
            System.out.print(prompt);
            String linha = SC.nextLine().trim();
            try { return Integer.parseInt(linha); }
            catch (NumberFormatException e) { System.out.println("  [X] Digite apenas numeros."); }
        }
    }
}