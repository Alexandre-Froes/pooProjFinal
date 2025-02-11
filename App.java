import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static String menu(){
        return 
        "1 - adicionar produto\n" +
        "2 - adicionar categoria\n" +
        "3 - listar produtos\n" +
        "4 - montar carrinho\n" +
        "5 - sair\n";
    }

    public static void adicionarCategorias(Scanner ler){
        Categoria.addCategorias("Eletronico");
        Categoria.addCategorias("Alimento");
        Categoria.addCategorias("Brinquedo");

        do{
            System.out.println("Qual categoria deseja adicionar?");
            
            if(!Categoria.addCategorias(ler.nextLine())){
                System.out.println("Categoria já existe");
            
            }else{
                System.out.println("Categoria adicionada com sucesso!!");
            }
            
            System.out.println("Deseja adicionar mais categorias? (s/n)");
            String opcao = ler.nextLine();

            if(opcao.equalsIgnoreCase("s")){
                continue;
            
            }else{
                break;
            }

        }while(true);

    }

    public static void adicionarProduto(Scanner ler){
        System.out.println("Digite o nome do produto: ");

        String nome = ler.nextLine();

        boolean precoValido = false;
        double preco = 0;
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
        int quantidade = 0;
        do{
            try{
                System.out.println("Digite a quantidade do produto: ");
                quantidade = ler.nextInt();
                Produto.validarEstoque(quantidade);
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
        boolean categoriaValida = false;
        do{
            System.out.println("\nDigite a categoria do produto: ");
            Categoria.listarCategorias();

            if(Categoria.isCategoriasEmpty()){
                System.out.println("Nenhuma categoria cadastrada");

                System.out.println("Deseja cadastrar uma nova categoria? (s/n)");
                String cadastro = ler.nextLine();

                if(cadastro.equalsIgnoreCase("s")){
                    System.out.println("Digite o nome da categoria: ");
                    categoria = ler.nextLine();
                    Categoria.addCategorias(categoria);
                }else{
                    System.out.println("Cadastro cancelado");
                    return;
                }
                return;

            }else{
                categoria = ler.nextLine();
            }
    
            if(!Categoria.categoriaExiste(categoria)){
                System.out.println("Categoria não encontrada");
                categoriaValida = false;
            }else{
                categoriaValida = true;
            }
            
        }while(!categoriaValida);

        Categoria cat = new Categoria(categoria);
        Produto produto = new Produto(nome, preco, quantidade, cat);
        Produto.addProduto(produto);
        System.out.println("\nProduto cadastrado com sucesso!!");
    }

    public static void listarProdutos(){
        Produto.listarProdutos();
    }
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        int opcao;
        do{
            System.out.println(menu());
            opcao = ler.nextInt();
            ler.nextLine();
    
            switch (opcao) {
                case 1:
                    adicionarProduto(ler);
                    break;
                case 2:
                    adicionarCategorias(ler);
                    break;
                case 3:
                    listarProdutos();
                    break;
                case 4:    
                    // montarCarrinho(ler);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
            
        }while(true);
    }
}
