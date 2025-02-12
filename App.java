import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static final String MENU =
        """
        1 - adicionar produto
        2 - adicionar categoria
        3 - listar produtos no estoque
        4 - aumentar estoque 
        5 - diminuir estoque
        6 - sair
        """;


    //Sobrecarga de métodos
    public static void adicionarCategorias(Scanner ler) {    
        do {
            System.out.println("Qual categoria deseja adicionar?");
            String novaCat = ler.next();
            adicionarCategorias(novaCat);
            
            System.out.println("Deseja adicionar mais? (s/n)");
            if(!ler.next().equalsIgnoreCase("s")) break;
        } while(true);
    }
    
    public static void adicionarCategorias(String nomeCategoria) {
        if(!Categoria.addCategorias(nomeCategoria)) {
            System.out.println("Categoria " + nomeCategoria + " já existe");
        } else {
            System.out.println("Categoria " + nomeCategoria + " adicionada!");
        }
    }

    public static void adicionarProduto(Scanner ler) {
        System.out.println("Digite o nome do produto: ");
        String nome = ler.nextLine();
    
        boolean precoValido = false;
        double preco = 0;
        do {
            //Tratamento de exceção
            try {
                System.out.println("Digite o preço do produto: ");
                preco = ler.nextDouble();
                ler.nextLine();
                Estoque.validarPrecoEstoque(preco);
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
                Estoque.validarEstoque(quantidade);

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
                    String novaCat = ler.next();
                    adicionarCategorias(novaCat);
                    Categoria cat = new Categoria(novaCat);

                    try {
                        Produto produto = Estoque.criarProduto(nome, preco, quantidade, cat);
                        System.out.println("\nProduto cadastrado com sucesso!!");
                        Estoque.addProduto(produto);
                    } catch (EstoqueException e) {
                        System.out.println("Erro ao criar produto: " + e.getMessage());
                    }

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
        try {
            Produto produto = Estoque.criarProduto(nome, preco, quantidade, cat);
            System.out.println("\nProduto cadastrado com sucesso!!");
            Estoque.addProduto(produto);
        } catch (EstoqueException e) {
            System.out.println("Erro ao criar produto: " + e.getMessage());
        }
        System.out.println("\nProduto cadastrado com sucesso!!");
    }

    public static void aumentarEstoque(Scanner ler) {
        boolean produtoExiste = false;
        do {
            Estoque.listarProdutos();
            System.out.println("Digite o nome do produto:");
            String nome = ler.nextLine();
            Produto p = Estoque.buscarProduto(nome);
        
            if (p == null) {
                System.out.println("Produto não encontrado");
            } else {
                System.out.println("Digite a quantidade que deseja adicionar:");
                int quantidade = ler.nextInt();
                ler.nextLine();
        
                Estoque.aumentarEstoque(p, quantidade);
                System.out.println("Estoque atualizado, nova quantidade de " + p.getNome() + ": " + p.getEstoque());
                produtoExiste = true;
            }
        } while (!produtoExiste);
    }

    public static void diminuirEstoque(Scanner ler) {
        boolean produtoExiste = false;
        do {
            Estoque.listarProdutos();
            System.out.println("Digite o nome do produto:");
            String nome = ler.nextLine();
            Produto p = Estoque.buscarProduto(nome);
        
            if (p == null) {
                System.out.println("Produto não encontrado");
            } else {
                System.out.println("Digite a quantidade que deseja diminuir do estoque:");
                int quantidade = ler.nextInt();
                ler.nextLine();
                
                try {
                    Estoque.diminuirEstoque(p, quantidade);
                    System.out.println("Estoque atualizado, nova quantidade de " + p.getNome() + ": " + p.getEstoque());
                    produtoExiste = true;
                } catch (EstoqueException e) {
                    System.out.println(e.getMessage());
                }
            }
        } while (!produtoExiste);
    }
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
                    Estoque.listarProdutos();
                    break;
                case 4: 
                    aumentarEstoque(ler);
                    break;
                case 5:
                    diminuirEstoque(ler);
                    break;
                case 6:
                    System.out.println("Saindo do sistema!");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
            
        }while(opcao != 7);
    }
}
