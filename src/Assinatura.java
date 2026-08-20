/**
 * Representa a assinatura (plano) contratada por um usuário.
 * Responsabilidade: guardar as regras do plano (preço, download,
 * qualidade máxima) e informar se um determinado benefício está incluso.
 * Foi separada de Usuario porque plano é uma informação comercial,
 * com suas próprias regras, e não um dado "pessoal" do usuário.
 */
public class Assinatura {

    private String tipoPlano; // Básico, Padrão ou Premium
    private double precoMensal;
    private boolean permiteDownload;
    private String qualidadeMaxima; // ex: "SD", "HD", "4K"

    public Assinatura(String tipoPlano, double precoMensal, boolean permiteDownload, String qualidadeMaxima) {
        this.tipoPlano = tipoPlano;
        this.precoMensal = precoMensal;
        this.permiteDownload = permiteDownload;
        this.qualidadeMaxima = qualidadeMaxima;
    }

    // Comportamento real do objeto: verifica se o plano cobre um benefício pedido
    public boolean verificarBeneficio(String beneficio) {
        if (beneficio.equalsIgnoreCase("download")) {
            return permiteDownload;
        }
        if (beneficio.equalsIgnoreCase("4K")) {
            return qualidadeMaxima.equalsIgnoreCase("4K");
        }
        return false;
    }

    public void renovar() {
        System.out.println("Assinatura " + tipoPlano + " renovada. Cobrança de R$"
                + String.format("%.2f", precoMensal) + " efetuada.");
    }

    public void exibirDetalhes() {
        System.out.println("Plano: " + tipoPlano);
        System.out.println("Preço mensal: R$" + String.format("%.2f", precoMensal));
        System.out.println("Download offline: " + (permiteDownload ? "Sim" : "Não"));
        System.out.println("Qualidade máxima: " + qualidadeMaxima);
    }

    public String getTipoPlano() {
        return tipoPlano;
    }
}
