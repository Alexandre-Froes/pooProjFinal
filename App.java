import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static final String MENU =
        """
        1 - adicionar produto
        2 - adicionar categoria
        3 - listar produtos
        4 - montar carrinho 
        5 - sair
        """;

    public static final String MENUCARRINHO =
        """
        1 - adicionar item no carrinho
        2 - remover item do carrinho
        3 - valor total do carrinho
        4 - adicionar desconto 
        5 - sair
        """;

    public static void adicionarCategorias(Scanner ler){
        Categoria.addCategorias("Eletronico");
        Categoria.addCategorias("Alimento");
        Categoria.addCategorias("Brinquedo");

        do{
            System.out.println("Qual categoria deseja adicionar?");
            
            if(!Categoria.addCategorias(ler.next())){
                System.out.println("Categoria já existe");
            
            }else{
                System.out.println("Categoria adicionada com sucesso!!");
            }
            
            System.out.println("Deseja adicionar mais categorias? (s/n)");
            String opcao = ler.next();

            if(opcao.equalsIgnoreCase("s")){
                continue;
            
            }else{
                break;
            }

        }while(true);
    }

    public static void adicionarProduto(Scanner ler) {
        System.out.println("Digite o nome do produto: ");
        String nome = ler.nextLine();
    
        boolean precoValido = false;
        double preco = 0;
        do {
            try {
                System.out.println("Digite o preço do produto: ");
                preco = ler.nextDouble();
                ler.nextLine();
                Produto.validarPrecoEstoque(preco);
                precoValido = true;
            } catch (InputMismatchException e) {
                System.out.println("Preço inválido");
                ler.nextLine();
            } catch (EstoqueException e) {
                System.out.println(e.getMessage());
            }
        } while (!precoValido);
    
        boolean quantidadeValida = false;
        int quantidade = 0;
        do {
            try {
                System.out.println("Digite a quantidade do produto: ");
                quantidade = ler.nextInt();
                ler.nextLine();
                Produto.validarEstoque(quantidade);
                quantidadeValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Quantidade inválida");
                ler.nextLine();
            } catch (EstoqueException e) {
                System.out.println(e.getMessage());
            }
        } while (!quantidadeValida);

        String categoria;
        boolean categoriaValida = false;
        do{
            System.out.println("\nDigite a categoria do produto: ");
            Categoria.listarCategorias();

            if(Categoria.isCategoriasEmpty()){
                System.out.println("Nenhuma categoria cadastrada");

                System.out.println("Deseja cadastrar uma nova categoria? (adicionará esse categoria ao produto atual) (s/n)");
                String cadastro = ler.next();

                if(cadastro.equalsIgnoreCase("s")){
                    System.out.println("Digite o nome da categoria: ");
                    categoria = ler.next();
                    Categoria.addCategorias(categoria);

                    Categoria cat = new Categoria(categoria);
                    Produto produto = new Produto(nome, preco, quantidade, cat);
                    Produto.addProduto(produto);
                    System.out.println("\nProduto e categoria cadastrados com sucesso!!!");
                }else{
                    System.out.println("Cadastro cancelado");
                    return;
                }
                return;

            }else{
                categoria = ler.next();
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

    public static void adicionarItem(Scanner ler) {
        Carrinho carrinho = new Carrinho();
        Produto produto = new Produto();
        HashSet<Produto> produtos = Produto.getListaProdutos();

        System.out.println("Produtos:");
        Produto.listarProdutosQuantidade();

        boolean nomeValido = false;
        do {
            System.out.println("Digite o nome do produto que deseja adicionar: "); 
            String nome = ler.nextLine();
            for (Produto p : produtos) {
                if (p.getNome().equalsIgnoreCase(nome)) {
                    produto = p;
                    nomeValido = true;
                    break;
                } else {
                    System.out.println("Produto não encontrado. Digite novamente");
                    continue;
                }
            }
        } while (!nomeValido);

        boolean quantidadeValida = false;
        int quantidade = 0;
        do {
            try {
                System.out.println("Digite a quantidade do produto que deseja adicionar: ");
                quantidade = ler.nextInt();
                ler.nextLine();
                carrinho.adicionarItem(produto, quantidade);
                produto.diminuirEstoque(quantidade);
                quantidadeValida = true;
                System.out.println("Produto adicionado ao carrinho com sucesso");
                System.out.println("Estoque atualizado: " + produto.getNome() + " x" + produto.getEstoque());
            } catch (InputMismatchException e) {
                System.out.println("Quantidade inválida");
                ler.nextLine();
            } catch (EstoqueException e) {
                System.out.println(e.getMessage());
            }
        } while (!quantidadeValida);
    }
<<<<<<< HEAD
    
=======

    public static void montarCarrinho(Scanner ler) {
        int opcao = 0;

        do {
            System.out.println(MENUCARRINHO);
            opcao = ler.nextInt();
            ler.nextLine();

            switch (opcao) {
                case 1:
                    adicionarItem(ler);
                    break;
                case 2:
                    // Carrinho.listarCarrinho();
                    // System.out.println("Digite o nome do produto que deseja remover: ");

                    break;
                case 3:
                    
                    break;
                case 4:
                    
                    break;
                case 5:
                    System.out.println("Voltando pro menu principal");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (opcao != 5);
    }

>>>>>>> 32b6c4cae25ae64fdc286e0789e4db314341f05f
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        int opcao;
        do{
            System.out.println(MENU);
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
                    Produto.listarProdutos();
                    break;
                case 4:    
                    montarCarrinho(ler);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
            
        }while(opcao != 5);
    }
}
