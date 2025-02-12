public class Produto {

    //Atributos encapsulados
    private String nome;
    private double preco;
    private int estoque;
    //Associação com a classe Categoria
    private Categoria categoria;

    public Produto(String nome, double preco, int estoque, Categoria categoria) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.categoria = categoria;
    }
    
    //Getters e Setters publicos
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

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Produto other = (Produto) obj;
        return nome.equalsIgnoreCase(other.nome); // Comparação case-insensitive
    }
    
    @Override
    public int hashCode() {
        return nome.toLowerCase().hashCode();
    }
}