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


    //Criamos métodos estáticos
    public static void  listarCategorias(){
        for(Categoria c : categorias){
            System.out.println("- "+ c.getNome());
        }
    }

    public static boolean categoriaExiste(String nome){
        return categorias.contains(new Categoria(nome));
    }

    public static boolean isCategoriasEmpty(){
        return categorias.isEmpty();
    }

    public static boolean addCategorias(String nome){
        Categoria cat = new Categoria(nome);
        return categorias.add(cat);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Categoria c = (Categoria) obj;
        return nome.equalsIgnoreCase(c.nome);
    }

    @Override
    public int hashCode() {
        return nome.toLowerCase().hashCode();
    }
}
