/**
 * Contrato: "algo que pode ser reproduzido".
 *
 * Por que interface e não só um método abstrato em Midia? Porque
 * "poder ser reproduzido" é uma CAPACIDADE, não um tipo. Qualquer classe
 * futura (um Trailer, um Podcast) poderia assumir esse contrato sem
 * precisar herdar de Midia.
 */
public interface Reproduzivel {

    // Lança a exceção quando o conteúdo não está disponível.
    void reproduzir() throws ConteudoIndisponivelException;
}
