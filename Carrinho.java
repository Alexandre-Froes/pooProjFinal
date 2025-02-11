import java.util.List;

public class Carrinho {
    private List<Item> itens;

    public void adicionarItem(Produto produto, int quantidade) {
        Item item = new Item(produto, quantidade);
        itens.add(item);
    }

    public void adicionarItem(Produto produto) {
        Item item = new Item(produto, 1);
        itens.add(item);
    }

    public double calcularTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    public void listarCarrinho() {
        for (Item item : itens) {
            System.out.println(item.getProduto().getNome() +
            " - " + item.getQuantidade() + "x" +
            " - R$ " + item.calcularSubtotal());
        }
    }

    public double calcularTotal(int desconto) {
        double total = 0;
        for (Item item : itens) {
            total += item.calcularSubtotal();
        }

        aplicarDesconto(desconto, total);
        return total;
    }

    public double aplicarDesconto(int percentual, double total) {
        double desconto = total * percentual / 100;
        return desconto;
    }
}
