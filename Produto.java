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

    public void validarPreco() throws EstoqueException {
        if (this.preco <= 0) {
            throw new EstoqueException("Preço inválido");
        }
        if (this.quantidade <= 0){
            throw new EstoqueException("Item fora de estoque");
        }
    }

}