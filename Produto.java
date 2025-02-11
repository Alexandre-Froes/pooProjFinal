public class Produto {

    private String nome;
    private double preco;
    private int estoque;
    private Categoria categoria;
    
    public Produto() {
    }

    public Produto(String nome, double preco, int estoque, Categoria categoria) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
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

    public int getestoque() {
        return estoque;
    }

    public void setestoque(int estoque) {
        this.estoque = estoque;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void calcularValorTotal() {
        System.out.println("Valor total: " + this.preco * this.estoque);
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

    public void diminuirEstoque(int quantidade) throws EstoqueException {
        if (quantidade > estoque) {
            throw new EstoqueException("Estoque insuficiente para " + nome);
        }
        estoque -= quantidade;
    }

    public void aumentarEstoque(int quantidade) {
        estoque += quantidade;
    }

}