import java.util.HashSet;
import java.util.LinkedHashSet;

public class Estoque {

    //Utilizando a relação de composição
    private static HashSet<Produto> listaProdutos = new LinkedHashSet<>();
    
    public static Produto criarProduto(String nome, double preco, int estoqueInicial, Categoria categoria) 
        throws EstoqueException {
        
        validarPrecoEstoque(preco);
        validarEstoque(estoqueInicial);
        
        Produto novoProduto = new Produto(nome, preco, estoqueInicial, categoria);
        if(!listaProdutos.add(novoProduto)) {
            throw new EstoqueException("Produto já existe no estoque");
        }
        return novoProduto;
    }

    public static void addProduto(Produto produto) {
        listaProdutos.add(produto);
    }

    public static void removerProduto(Produto produto) {
        listaProdutos.remove(produto);
    }
    
    public static void listarProdutos(){
        for(Produto p : listaProdutos){
            System.out.println("- " + p.getNome() + 
                            " x" + p.getEstoque() + " unidades" + 
                            " R$ " + p.getPreco() + 
                            " Categoria: " + p.getCategoria().getNome());
        }
        System.out.println();
    }

    //Método estático com um throws para tratamento de exceção
    public static void diminuirEstoque(Produto produto, int quantidade) throws EstoqueException {
        if (quantidade <= 0) {
            throw new EstoqueException("Quantidade deve ser maior que zero.");
        } else if (quantidade > produto.getEstoque()) {
            throw new EstoqueException("Estoque insuficiente para " + produto.getNome() + " escolha uma quantidade menor que " + produto.getEstoque());
        } else {
            int novoEstoque = produto.getEstoque() - quantidade;
            produto.setEstoque(novoEstoque);
        }
    }

    public static void aumentarEstoque(Produto produto, int quantidade) {
        int novoEstoque = produto.getEstoque() + quantidade;
        produto.setEstoque(novoEstoque);
    }
    
    public static void aumentarEstoque(Produto produto) {
        aumentarEstoque(produto, 1);
    }

    
    public static void validarPrecoEstoque(double preco) throws EstoqueException {
        if (preco <= 0) {
            throw new EstoqueException("Preço precisa ser maior que 0");
        }
    }

    public static void validarEstoque(int estoque) throws EstoqueException {
        if (estoque <= 0) {
            throw new EstoqueException("produto.getEstoque() precisa ser maior que 0");
        }
    }

    public static Produto buscarProduto(String nome) {
        for (Produto p : listaProdutos) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }

    public static HashSet<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public static void setListaProdutos(HashSet<Produto> listaProdutos) {
        Estoque.listaProdutos = listaProdutos;
    }
}
