import java.util.Scanner;
import java.util.Random;
import java.text.DecimalFormat;
public class Main {

    public static void main(String[] args) {

        final int N_INICIAL_DE_PLAYLISTS = 5;

        Player player = new Player(N_INICIAL_DE_PLAYLISTS);
        Scanner scan = new Scanner(System.in);

        int opcao;
        do
        {
            escreveMenu();
            opcao = scan.nextInt();
            scan.nextLine();
            
            switch (opcao){
                case 1:
                    criarPlayList(scan, player);
                    confirmContinue(scan);
                    break;
                case 2:
                    adicionarMusica(scan, player);
                    confirmContinue(scan);
                    break;
                case 3:
                    removerMusica(scan, player);
                    confirmContinue(scan);
                    break;
                case 4: 
                    imprimirPlayList(scan, player);
                    confirmContinue(scan);
                    break;
                //--------- Outros métodos -----------
                case 5:
                    listPlaylists(player);
                    confirmContinue(scan);
                    break;
                case 6:
                    deletePlaylist(scan, player);
                    confirmContinue(scan);
                    break;
                case 7:
                    player = createTestPlayer(scan, player, N_INICIAL_DE_PLAYLISTS);
                    confirmContinue(scan);
                    break;
                //--------- /Outros métodos -----------
                case 0: 
                    System.out.println("Adeus, volte sempre!!");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    confirmContinue(scan);
            }
        }while (opcao != 0);
        scan.close();
        System.exit(0);
    }

    public static void escreveMenu(){
        System.out.println("Selecione uma das opções:");
        System.out.println("1 - Criar Playlist");
        System.out.println("2 - Adicionar Música");
        System.out.println("3 - Remover Música");
        System.out.println("4 - Imprimir Playlist");
        System.out.println("----------------------");
        System.out.println("5 - Listar Playlists");
        System.out.println("6 - Apagar Playlist");
        System.out.println("7 - Carregar dados para teste");
        System.out.println("0 - Sair");
    }
    
    private static void criarPlayList(Scanner scan, Player player){
        String nome;
        int capacidade;
         
        System.out.println("Introduza o nome da Playlist:");
        nome = scan.nextLine();
          
        System.out.println("Introduza a capacidade da playlist " + nome + ":");
        capacidade = scan.nextInt();
        scan.nextLine();
        
        try{
            player.adicionarPlaylist(new Playlist(nome, capacidade));        
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Ocorreu um excepção:\n" + e);
            System.out.println("O seu Player não tem mais capacidade para novas playlists.");
            int opcao;
            do{
                System.out.println("Deseja aumentar a capacidade?");
                System.out.println("1 - Sim");
                System.out.println("2 - Não");
                opcao = scan.nextInt();
                scan.nextLine();
                switch (opcao)
                {
                    case 1:
                        player.increasePlaylistArray();
                        player.adicionarPlaylist(new Playlist(nome, capacidade));
                        System.out.println("Playlist criada com sucesso.");
                        break;
                    case 2:
                        System.out.println("O número de playlists não foi aumentado.");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            }while(opcao != 1 && opcao!=2);
        }
    }
    
    public static void adicionarMusica(Scanner scan, Player player){

        int playlistNumber;
        int playlistIndex;
        System.out.println("Introduza o número da Playlist:\n");
        playlistNumber = scan.nextInt();
        playlistIndex = playlistNumber - 1;
        scan.nextLine();
        
        if(!player.playlistIndexExists(playlistIndex))
        {
            System.out.println("A playlist seleccionada não existe!");
            System.out.println("Por favor introduza um número válido.");
        }
        else{
            String titulo;
            String autor;
            Double duracao;
            String estilo;

            System.out.println("Introduza o título da Música:");
            titulo = scan.nextLine();

            System.out.println("Introduza o autor da Música \""+titulo+"\":");
            autor = scan.nextLine();
            
            System.out.println("Introduza a duração da Música \""+titulo+"\":");
            String text = scan.nextLine();
            // https://stackoverflow.com/questions/5769669/convert-string-to-double-in-java
            text = text.replace("\n", "").replace(",", ".");
            duracao = Double.parseDouble(text);

            System.out.println("Introduza o estilo da Música \""+titulo+"\":");
            estilo = scan.nextLine();

            try
            {
                player.getListaDePlaylists()[playlistIndex].adicionaMusica(new Musica(titulo,autor,duracao,estilo));
                System.out.println("Música criada com sucesso.");
            }
            catch(IllegalArgumentException e)
            {
                System.out.println("Ocorreu um excepção:\n" + e);
                System.out.println("A playlist não tem capacidade para novas músicas.");
                int opcao;
                do{
                    System.out.println("Deseja aumentar a capacidade da playlist e inserir a música?");
                    System.out.println("0 - Sim");
                    System.out.println("1 - Não");
                    opcao = scan.nextInt();
                    scan.nextLine();
                    switch (opcao)
                    {
                        case 0:
                            player.getListaDePlaylists()[playlistIndex].increaseMusicArray();
                            player.getListaDePlaylists()[playlistIndex].adicionaMusica(new Musica(titulo,autor,duracao,estilo));
                            System.out.println("A capacidade da playlist foi aumentada. Música criada com sucesso.");
                            break;
                        case 1:
                            System.out.println("A capacidade da playlist não foi aumentada. A música não foi introduzida");
                            break;
                        default:
                            System.out.println("Opção inválida.");
                            break;
                    }
                }while(opcao != 0 && opcao!=1);
            }
        }
    }

    public static void removerMusica(Scanner scan, Player player){
        int playlistNumber;
        int playlistIndex;
        System.out.println("Introduza o número da Playlist:\n");
        playlistNumber = scan.nextInt();
        scan.nextLine();
        playlistIndex = playlistNumber-1;
        if(!player.playlistIndexExists(playlistIndex))
        {
            System.out.println("A playlist seleccionada não existe!");
            System.out.println("Por favor introduza um número válido.");
        }
        else
        {
            int musicNumber;
            int musicIndex;
            System.out.println("Introduza o número da Playlist:\n");
            musicNumber = scan.nextInt();
            scan.nextLine();
            musicIndex = musicNumber-1;
            if(player.getListaDePlaylists()[playlistIndex].musicIndexExists(musicIndex))
            {
                player.getListaDePlaylists()[playlistIndex].removeMusica(musicIndex);
                System.out.println("Música removida com sucesso!");
            }
            else
            {
                System.out.println("A música seleccionada não existe!");
                System.out.println("Por favor introduza um número válido.");
            }
        }
    }

    public static void imprimirPlayList(Scanner scan, Player player){
        int playlistNumber;
        int playlistIndex;
        System.out.println("Introduza o número da playlist que quer listar");
        playlistNumber = scan.nextInt();
        scan.nextLine();
        playlistIndex = playlistNumber-1;
        if(!player.playlistIndexExists(playlistIndex))
        {
            System.out.println("A playlist seleccionada não existe!");
            System.out.println("Por favor introduza um número válido.");
        }
        else
        {
            System.out.println(player.getListaDePlaylists()[playlistIndex].toString());
        }
    }

    //===============================================================
    // Mêtodos adicionais / Other methods
    //===============================================================
    
    public static void listPlaylists(Player player)
    {
        if(player.getNumDePlaylists()>0)
        {
            String txt = "Lista de Playlists:\n";
            for(int i = 0; i<player.getNumDePlaylistsOcupadas(); i++)
            {
                Playlist playlist = player.getListaDePlaylists()[i];
                txt += (i + 1) + " - " + playlist.getNome() + "  (contem " + playlist.getNumActualMusicas() +" músicas)" + "\n"; 
            }
            System.out.println(txt);
        }
        else{
            System.out.println("Não existem playlists");
        }
    }

    public static void deletePlaylist(Scanner scan, Player player)
    {
        int playlistNumber;
        int playlistIndex;
        System.out.println("Introduza o número da Playlist:");
        playlistNumber = scan.nextInt();
        scan.nextLine();
        playlistIndex = playlistNumber-1;
        
        try{
            player.deletePlaylist(playlistIndex);
            System.out.println("Playlist apagada com successo.");
        }catch(IllegalArgumentException e){
            System.out.println("A playlist seleccionada não existe!");
            System.out.println("Por favor introduza um número válido.");
        }
    }
    
    public static Player createTestPlayer(Scanner scan, Player player, int N_INICIAL_DE_PLAYLISTS)
    {
        
        // Seed pré definida permite obter os mesmo resultados de forma consistente
        Random r = new Random(12345);
        
        //https://www.mkyong.com/java/java-display-double-in-2-decimal-points/
        final DecimalFormat decimalFormatter2DecimalPlaces = new DecimalFormat(".##");

        String[] estilos = new String[] {"Jazz", "Clássica", "Bossa-Nova", "Drum&Bass", "Pop"};
        String[] nomes = new String[] {"Arlindo", "Bruno", "Carlos", "Diogo", "Estevão", "Fernando"};
        String[] apelidos = new String[] {"Sousa", "Gomes", "Santos", "Pereira", "Fernandes", "Lopes"};

        for(int i = 0; i<N_INICIAL_DE_PLAYLISTS; i++)
        {
            int playlistSize = r.nextInt(10) + 1;
            player.adicionarPlaylist(new Playlist("Playlist "+(i+1), playlistSize));
            
            for(int c = 0; c < playlistSize; c++)
            {
                //Generate a random duration for the music
                double duration = 1 + (r.nextDouble() * (8));
                String durationString = decimalFormatter2DecimalPlaces.format(duration);
                durationString = durationString.replace(",", ".");
                duration = Double.parseDouble(durationString);
                //----------------------------------------
                player.getListaDePlaylists()[i].adicionaMusica(
                    new Musica(
                        "Titulo "+(c+1),
                        nomes[r.nextInt(nomes.length-1)] + " " + apelidos[r.nextInt(apelidos.length-1)],
                        duration,
                        estilos[r.nextInt(estilos.length-1)]
                    )
                );
            }
        }
        System.out.println("Dados criados!");
        return player;
    }
    
    public static void confirmContinue(Scanner scan)
    {
        System.out.println("Prima enter para continuar");
        scan.nextLine();
    }
}
