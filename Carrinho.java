import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private static List<Item> itens;
    
        public Carrinho() {
            Carrinho.itens = new ArrayList<>();
        }
    
        public void adicionarItem(Produto produto, int quantidade) throws EstoqueException {
            if (quantidade <= 0) {
                throw new EstoqueException("Quantidade inválida!");
            }
            
            produto.diminuirEstoque(quantidade);
            itens.add(new Item(produto, quantidade));
        }
    
        public void adicionarItem(Produto produto) throws EstoqueException {
            produto.diminuirEstoque(1);
            adicionarItem(produto, 1);
        }
    
        public void removerItem(Item item) {
            itens.remove(item);
            item.getProduto().aumentarEstoque(item.getQuantidade());
        }
    
        public double calcularTotal(int desconto) {
            double total = 0;
            for (Item item : itens) {
                total += item.calcularSubtotal();
            }
            
            aplicarDesconto(desconto, total);
            return total;
        }
    
        public static void listarCarrinho() {
            for (Item item : itens) {
            System.out.println(item.getProduto().getNome() +
            " - " + item.getQuantidade() + "x" +
            " - R$ " + item.calcularSubtotal());
        }
    }

    public double aplicarDesconto(int percentual, double total) {
        double desconto = total * percentual / 100;
        return desconto;
    }
}
