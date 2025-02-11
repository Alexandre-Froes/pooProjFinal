import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void adicionarProduto(Scanner ler){
        System.out.println("Digite o nome do produto: ");
        String nome = ler.nextLine();

        boolean precoValido = false;
        double preco;
        do{
            try {
                System.out.println("Digite o preço do produto: ");
                preco = ler.nextDouble();
                Produto.validarPrecoEstoque(preco);
                precoValido = true;
                ler.nextLine();
    
            }catch(InputMismatchException e){
                System.out.println("Preço inválido");
                ler.nextLine();
    
            }catch(EstoqueException e){
                System.out.println(e.getMessage());
                ler.nextLine();
            }
        }while(!precoValido);
        
        boolean quantidadeValida = false;
        int quantidade;
        do{
            try{
                System.out.println("Digite a quantidade do produto: ");
                quantidade = ler.nextInt();
                Produto.validarQuantidadeEstoque(quantidade);
                quantidadeValida = true;
                ler.nextLine();
    
            }catch(InputMismatchException e){
                System.out.println("Quantidade inválida");
                ler.nextLine();
    
            }catch(EstoqueException e){
                System.out.println(e.getMessage());
                ler.nextLine();

            }
        }while (!quantidadeValida);
        
        String categoria;
        do{
            System.out.println("Digite a categoria do produto: ");
            Categoria.listarCategorias();
            categoria = ler.nextLine();
    
            if(!Categoria.categoriaExiste(categoria)){
                System.out.println("Categoria não encontrada");
                return;
    
            }
        }while(!Categoria.categoriaExiste(categoria));
    }

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        adicionarProduto(ler);
    }
}
