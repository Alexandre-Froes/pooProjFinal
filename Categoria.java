import java.util.HashSet;

public class Categoria {
    private String nome;
    private static HashSet<Categoria> categorias = new HashSet<>();

    public Categoria() {
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static void adiocionarCategoria(Categoria cat){
        categorias.add(cat);
    }

    public static void  listarCategorias(){
        for(Categoria c : categorias){
            System.out.println(c.getNome());
        }
    }

    public static boolean categoriaExiste(String nome){
        for(Categoria c : categorias){
            if(c.getNome().equalsIgnoreCase(nome)){
                return true;
            }
        }
        return false;
    }

}
