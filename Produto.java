public class Produto {

    private String nome;
    private double preco;
    private int quantidade;
    private Categoria categoria;
    
    public Produto() {
    }

    public Produto(String nome, double preco, int quantidade, Categoria categoria) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


    public void calcularValorTotal() {
        System.out.println("Valor total: " + this.preco * this.quantidade);
    }

    public void getPrecoComImposto() {
        CalculadoraTrib.calcularImposto(preco);
    }

    public static void validarPrecoEstoque(double preco) throws EstoqueException {
        if (preco <= 0) {
            throw new EstoqueException("Preço precisa ser maior que 0");
        }
    }

    public static void validarQuantidadeEstoque(int quantidade) throws EstoqueException {
        if (quantidade <= 0) {
            throw new EstoqueException("Quantidade precisa ser maior que 0");
        }
    }

}