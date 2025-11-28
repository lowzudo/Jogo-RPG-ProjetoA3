package src.game;
import src.training.TrainingManager;

import src.entities.Player;
import src.utils.GameUtils;
import src.battles.BattleManager;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Game {
    private Player player;
    private GameTree gameTree;
    private BattleManager battleManager;
    private Scanner scanner = new Scanner(System.in);
    
    public Game(String name, String className) {
        this.player = new Player(name, className);
        this.gameTree = new GameTree();
        this.battleManager = new BattleManager();
    }
    
    public void start() {
        navigateTree();
    }
    
    private void navigateTree() {
        boolean shouldContinue = true;
        
        while (shouldContinue && !gameTree.isFinalNode()) {
            TreeNode currentNode = gameTree.getCurrentNode();
            
            executeNode(currentNode);
            
            if (gameTree.isFinalNode()) {
                shouldContinue = false;
                continue;
            }
            
            if (gameTree.hasChoices()) {
                showChoices(currentNode);
            } else {
                gameTree.moveTo("");
            }
            
            if (gameTree.isFinalNode()) {
                shouldContinue = false;
            }
        }
        
        if (gameTree.isFinalNode()) {
            executeNode(gameTree.getCurrentNode());
        }
    }
    
    private void executeNode(TreeNode node) {
        System.out.println("\n".repeat(5));
        
        switch(node.getNodeId()) {
            case "begin":
                beginChapter();
                break;
            case "desafio":
                desafioInicial();
                break;
            case "part2":
                parte2();
                break;
            case "batalha_goblins":
                batalhaGoblins();
                break;
            case "part3": 
                parte3();
                break;
            case "game_over":
                gameOver();
                break;
            case "vitoria":
                vitoriaFinal();
                break;
            case "sistema":
                sistema();
                break;
            case "treinamento":
                iniciarTreinamento();
                break;
            case "part5":
                parte5();
                break;
            // NOVO: Caso para o capítulo 6
            case "part6":
                parte6();
                break;
            // NOVO: Caso para a batalha contra os deuses
            case "batalha_deuses":
                batalhaDeuses();
                break;
        }
    }

    private void parte5() {
        GameUtils.dramaticPrint("\n" + "=".repeat(60));
        GameUtils.dramaticPrint("CAPÍTULO 5: O CONFRONTO FINAL");
        GameUtils.dramaticPrint("=".repeat(60));
        
        GameUtils.dramaticPrint("TIME-SKIP DO FRACASSADO");
        GameUtils.dramaticPrint("\n2 meses se passam, e você agora se sente bem mais confiante.");
        GameUtils.dramaticPrint("Seus atributos estão bem mais altos do que antes, e você anseia, tem sede de poder.");
        GameUtils.dramaticPrint("Cada vez mais e mais, não quer apenas ser um jogador qualquer.");
        GameUtils.dramaticPrint("Você quer ser o melhor jogador que já pisou em Aincrad.");

        GameUtils.dramaticPrint("\nCom sua percepção, força, agilidade e resistência aumentadas, você tem noção do poder em suas mãos.");
        GameUtils.dramaticPrint("Graças a sua percepção aguçada, você consegue detectar inimigos a longas distâncias, antecipar ataques e encontrar pontos fracos em inimigos fácilmente.");
        GameUtils.dramaticPrint("Sua força aumentada permite que você cause danos devastadores com seus ataques, derrubando inimigos com facilidade.");
        GameUtils.dramaticPrint("Sua agilidade aprimorada torna você incrivelmente rápido e evasivo, permitindo que você desvie de ataques com graça e precisão.");
        GameUtils.dramaticPrint("E sua resistência elevada faz com que você suporte mais danos, permitindo que você continue lutando mesmo em situações difíceis.");

        GameUtils.dramaticPrint("\nTudo estava tranquilo, porém uma aura avassaladora começa a tomar conta do local em que você está.");
        GameUtils.dramaticPrint("Você sente o ar pesando, enquanto aquilo se aproxima de você.");
        GameUtils.dramaticPrint("Aquilo claramente não é humano, mas diferente de antes, você não sente medo.");
        GameUtils.dramaticPrint("E sim uma excitação, de ter alguém do seu nivel para enfrentar e ver o quanto melhorou.");
        GameUtils.dramaticPrint("Você parte para cima da aura, e vê aquele mesmo homem encapuzado, do dia do hospital.");

        GameUtils.dramaticPrint("\nO homem encapuzado sorri ao ver você se aproximando, ele diz: 'Parece que alguém não tem mais medo do desconhecido...'");
        GameUtils.dramaticPrint("'Você evoluiu garoto, tanto ao ponto de eu não conseguir reconhecer sua presença ao se aproximar de mim.'");
        GameUtils.dramaticPrint("'Venha com tudo...' Enquanto diz isso, ele começa a liberar uma aura ainda maior, rindo, como se aquilo não fosse nada.");

        GameUtils.dramaticPrint("\nVocê sente que tem alguém do seu nivel... Não, sente que ele talvez seja até mais forte que você.");
        GameUtils.dramaticPrint("Mas isso não importa, seu coração anseia por isso.");
        
        String className = player.getClassName();
        if (className.equals("Mago")) {
            player.addAttack("Explosão de Mana", 8, 6);
            player.addAttack("Circulo do Caos", 10, 8);
            player.addAttack("JUNÇÃO DE TODOS OS ELEMENTOS", 15, 12);
            GameUtils.dramaticPrint("Novos ataques desbloqueados: Explosão de Mana, Circulo do Caos, JUNÇÃO DE TODOS OS ELEMENTOS");
        } else if (className.equals("Espadachim")) {
            player.addAttack("Espada de luz", 7, 5);
            player.addAttack("1000 cortes por segundo", 9, 7);
            player.addAttack("CORTE DIMENSIONAL", 14, 11);
            GameUtils.dramaticPrint("Novos ataques desbloqueados: Espada de luz, 1000 cortes por segundo, CORTE DIMENSIONAL");
        } else if (className.equals("Berserker")) {
            player.addAttack("Quebrador de Planetas", 9, 7);
            player.addAttack("Destruidor de Deuses", 11, 9);
            player.addAttack("DEVASTADOR DE GALÁXIAS", 16, 13);
            GameUtils.dramaticPrint("Novos ataques desbloqueados: Quebrador de Planetas, Destruidor de Deuses, DEVASTADOR DE GALÁXIAS");
        }

        GameUtils.dramaticPrint("\n" + "-".repeat(60));
        GameUtils.dramaticPrint("Agora é a hora de provar seu verdadeiro poder!");
        GameUtils.dramaticPrint("Enfrente seu destino, fracassado!");
        GameUtils.dramaticPrint("-".repeat(60));

        boolean venceu = battleManager.batalhaEncapuzado(player);
        if (venceu) {
            gameTree.moveTo("continuar");
        } else {
            gameTree.moveTo("game_over");
        }
    }

    private void parte6() {
        GameUtils.dramaticPrint("\n" + "=".repeat(60));
        GameUtils.dramaticPrint("CAPÍTULO 6: O DESPERTAR DIVINO");
        GameUtils.dramaticPrint("=".repeat(60));
        
        GameUtils.dramaticPrint("Após enfrentar o homem encapuzado, e saber a real força dele, você fica em dúvida. Dúvida se irá realmente conseguir derrotar aquelas estátuas, afinal, nem ele conseguiu...");

        GameUtils.dramaticPrint("Antes que você conseguisse pensar, o chão a baixo de você se abre, como se fosse uma porta, você iria despencar, porém com seu tempo de reação aguçado, não é isso que acontece.");
        
        GameUtils.waitForEnter();

        GameUtils.dramaticPrint("Parece que o ser que queria que você caísse, não previu sua evolução");

        GameUtils.dramaticPrint("Você pensa quem iria querer colocar você em uma emboscada. Então uma aura começa a tomar conta do subsolo.");

        GameUtils.dramaticPrint("Uma aura que até então você tinha sentido. Uma aura poderosa, talvez até mais do que a do homem encapuzado. Porém você não se sente mais fraco ou inferior. Você SABE, que é mais forte do que aquela aura, do que aquele ser.");
        
        GameUtils.waitForEnter();

        GameUtils.dramaticPrint("E com um poder desses para abrir uma cratera no chão, como se não fosse nada. Só podia ser eles, aqueles Deuses de merda");

        GameUtils.dramaticPrint("Você sem pensar duas vezes pula de cabeça de onde a aura esta emanando");
        
        GameUtils.waitForEnter();

        GameUtils.dramaticPrint("\nAo cair na cratera, você se encontra em uma dimensão completamente diferente.");
        GameUtils.dramaticPrint("O ar é pesado, carregado com energia divina, e você sente que está no território deles.");
        GameUtils.dramaticPrint("Mas diferente de antes, você não teme. Você anseia por esse confronto.");
        
        GameUtils.waitForEnter();

        GameUtils.dramaticPrint("\nSeus atributos evoluíram além do que você imaginava ser possível:");
        GameUtils.dramaticPrint("Força: " + player.getStrength() + " | Agilidade: " + player.getAgility() + " | Percepção: " + player.getPerception() + " | Resistência: " + player.getResistance());
        GameUtils.dramaticPrint("Você não é mais o mesmo jogador que temia os Deuses. Você se tornou um Deus também.");
        
        GameUtils.waitForEnter();

        GameUtils.dramaticPrint("\nDas sombras, figuras divinas começam a emergir.");
        GameUtils.dramaticPrint("São eles - os Deuses que brincam com as vidas dos jogadores como se fossem marionetes.");
        GameUtils.dramaticPrint("Mas hoje, as cordas serão cortadas.");
        
        GameUtils.waitForEnter();

        GameUtils.dramaticPrint("\nO Deus principal olha para você com desdém e mostra o mesmo sorriso da última vez: 'Quem diria, você está vivo por um milagre e ainda tem coragem de voltar aqui. Apenas morra inseto'");
        GameUtils.dramaticPrint("'Você deveria ter caído na armadilha como os outros... Mas não importa, vamos acabar com isso aqui.'");
        GameUtils.dramaticPrint("Eles liberam uma aura divina poderosa.");
        
        GameUtils.waitForEnter();

        GameUtils.dramaticPrint("\nVocê não se acovarda diante a aura, e emana a sua de volta, mostrando para eles que quem deve temer, não é você.");
        GameUtils.dramaticPrint("Os Deuses arregalam os olhos assustados e se perguntam: 'Como um reles humano conseguiu tamanho poder...'.");

        GameUtils.dramaticPrint("\n" + "-".repeat(60));
        GameUtils.dramaticPrint("O confronto contra os Deuses está prestes a começar!");
        GameUtils.dramaticPrint("O destino de Aincrad está em suas mãos!");
        GameUtils.dramaticPrint("-".repeat(60));
        
        GameUtils.waitForEnter();

        GameUtils.dramaticPrint("Novos poderes desbloqueados.");

        // Adicionar ataques especiais para a batalha final
        String className = player.getClassName();
        if (className.equals("Mago")) {
            player.addAttack("EXPLOSÃO CÓSMICA", 20, 25);
            player.addAttack("DILÚVIO ARCANO", 18, 22);
            player.addAttack("FIM DOS TEMPOS", 25, 30);
            GameUtils.dramaticPrint("Novos ataques desbloqueados: EXPLOSÃO CÓSMICA, DILÚVIO ARCANO, FIM DOS TEMPOS");
        } else if (className.equals("Espadachim")) {
            player.addAttack("CORTE FINAL", 22, 28);
            player.addAttack("DANÇA DAS LÂMINAS", 19, 24);
            player.addAttack("JULGAMENTO DIVINO", 26, 32);
            GameUtils.dramaticPrint("Novos ataques desbloqueados: CORTE FINAL, DANÇA DAS LÂMINAS, JULGAMENTO DIVINO");
        } else if (className.equals("Berserker")) {
            player.addAttack("FÚRIA CELESTIAL", 24, 30);
            player.addAttack("METEORO DA PERDIÇÃO", 21, 26);
            player.addAttack("APOCALIPSE", 28, 35);
            GameUtils.dramaticPrint("Novos ataques desbloqueados: FÚRIA CELESTIAL, METEORO DA PERDIÇÃO, APOCALIPSE");
        }

        GameUtils.dramaticPrint("\n" + "★".repeat(60));
        GameUtils.dramaticPrint("PREPARE-SE PARA O CONFRONTO FINAL!");
        GameUtils.dramaticPrint("★".repeat(60));

        GameUtils.waitForEnter();
        
        // Move para a batalha contra os deuses
        gameTree.moveTo("continuar");
    }

    // NOVO MÉTODO: Batalha contra os Deuses
    private void batalhaDeuses() {
        GameUtils.dramaticPrint("\n" + "=".repeat(60));
        GameUtils.dramaticPrint("BATALHA FINAL: CONTRA OS DEUSES");
        GameUtils.dramaticPrint("=".repeat(60));
        
        boolean venceu = battleManager.batalhaDeuses(player);
        if (venceu) {
            gameTree.moveTo("vitoria");
        } else {
            gameTree.moveTo("game_over");
        }
    }

    private void vitoriaFinal() {
        GameUtils.dramaticPrint("\n" + "=".repeat(60));
        GameUtils.dramaticPrint("VITÓRIA FINAL!");
        GameUtils.dramaticPrint("=".repeat(60));
        
        GameUtils.dramaticPrint("PARABÉNS, " + player.getName() + "!");
        GameUtils.dramaticPrint("Você derrotou os Deuses de Aincrad!");
        GameUtils.dramaticPrint("As estátuas caíram diante do seu poder.");
        GameUtils.dramaticPrint("O ciclo de opressão foi quebrado!");
        
        GameUtils.dramaticPrint("\nSeus atributos finais:");
        GameUtils.dramaticPrint("Força: " + player.getStrength());
        GameUtils.dramaticPrint("Vida: " + player.getMaxHealth());
        GameUtils.dramaticPrint("Stamina: " + player.getMaxStamina());
        GameUtils.dramaticPrint("Nível: " + player.getLevel());
        GameUtils.dramaticPrint("Agilidade: " + player.getAgility());
        GameUtils.dramaticPrint("Resistência: " + player.getResistance());
        GameUtils.dramaticPrint("Percepção: " + player.getPerception());
        
        GameUtils.dramaticPrint("\nQUEM DIRIA QUE UM FRACASSADO IGUAL VOCÊ CHEGARIA TÃO LONGE...");
        GameUtils.dramaticPrint("Mas agora você é uma lenda - o mortal que desafiou e venceu os Deuses!");
    }
    
    private void showChoices(TreeNode node) {
        if (gameTree.isFinalNode()) {
            return;
        }
        
        if (node.getChoices().containsKey("")) {
            gameTree.moveTo("");
            return;
        }
        
        Map<String, TreeNode> escolhasValidas = new HashMap<>();
        for (Map.Entry<String, TreeNode> entry : node.getChoices().entrySet()) {
            String choice = entry.getKey();
            if (!choice.isEmpty() && !choice.equals("game_over")) {
                escolhasValidas.put(choice, entry.getValue());
            }
        }
        
        if (escolhasValidas.isEmpty()) {
            if (!node.getChoices().isEmpty()) {
                String autoChoice = node.getChoices().keySet().iterator().next();
                gameTree.moveTo(autoChoice);
            }
            return;
        }
        
        GameUtils.dramaticPrint("\nEscolhas disponíveis:");
        int i = 1;
        String[] choicesArray = escolhasValidas.keySet().toArray(new String[0]);
        for (String choice : choicesArray) {
            GameUtils.dramaticPrint(i + ". " + choice);
            i++;
        }
        
        boolean escolhaValida = false;
        while (!escolhaValida) {
            System.out.print("\nSua escolha (número): ");
            String input = scanner.nextLine().trim();
            
            try {
                int escolhaNum = Integer.parseInt(input);
                if (escolhaNum >= 1 && escolhaNum <= choicesArray.length) {
                    String escolhaKey = choicesArray[escolhaNum - 1];
                    gameTree.moveTo(escolhaKey);
                    escolhaValida = true;
                } else {
                    GameUtils.dramaticPrint("Escolha inválida! Digite um número entre 1 e " + choicesArray.length);
                }
            } catch (NumberFormatException e) {
                for (String key : escolhasValidas.keySet()) {
                    if (key.equalsIgnoreCase(input)) {
                        gameTree.moveTo(key);
                        escolhaValida = true;
                        break;
                    }
                }
                
                if (!escolhaValida) {
                    GameUtils.dramaticPrint("Escolha inválida! Tente novamente.");
                }
            }
        }
    }
    
    private void beginChapter() {
        GameUtils.dramaticPrint("Jogador: " + player.getName());
        GameUtils.dramaticPrint("Classe: " + player.getClassName());
        GameUtils.dramaticPrint("Nível: " + player.getLevel());
        GameUtils.dramaticPrint("Força: " + player.getStrength());
        GameUtils.dramaticPrint("Vida: " + player.getHealth());
        GameUtils.dramaticPrint("Stamina: " + player.getStamina());
        
        GameUtils.dramaticPrint("\nAVISOS NECESSÁRIOS PARA SUA SOBREVIVÊNCIA:");
        GameUtils.dramaticPrint("1- Você deve sempre estar atento aos seus arredores, inimigos podem surgir a qualquer momento.");
        GameUtils.dramaticPrint("2- Sempre gerencie bem sua stamina, ataques mais fortes consomem mais stamina, caso sua stamina chegue a zero, você morrerá instantaneamente.");
        GameUtils.dramaticPrint("3- Procure sempre evoluir seu nivel, com o nivel sendo aumentado, seus atributos também subirão, isso irá facilitar sua jornada.");
        GameUtils.dramaticPrint("4- Divirta-se ou morra!");
    }
    
    private void parte2() {
        GameUtils.dramaticPrint("Após alguns dias explorando o mundo de Aincrad, você se sente mais confiante.");
        GameUtils.dramaticPrint("Após vagar dias, você avista outra dungeon ao longe, decidindo se aproximar dela.");
        GameUtils.dramaticPrint("Ao chegar perto, você percebe que a entrada está cercada por goblins que parecem estar protegendo algo.");
        GameUtils.dramaticPrint("Você sabe que enfrentá-los será um desafio, mas parece ter uma voz vindo de dentro da dungeon, o chamando para entrar.");
        GameUtils.dramaticPrint("Você precisa entrar lá.");
        
        GameUtils.dramaticPrint("\nEntão fracassado, agora é seu momento!");
        GameUtils.dramaticPrint("Respire e vá a batalha contra esses goblins nojentos!");
        GameUtils.dramaticPrint("Descubra cada vez mais sobre esse mundo!");
        GameUtils.dramaticPrint("\nEssa é a primeira e última vez que irei encorajá-lo a enfrentar alguém,");
        GameUtils.dramaticPrint("então não me decepcione fracassado!");
        GameUtils.dramaticPrint("Essa dungeon tem algo de especial,");
        GameUtils.dramaticPrint("eu quero que você derrote-os e descubra o que está lá HAHAHAHA!");
    }
    
    private String obterEscolhaValida(String pergunta, String[] opcoesValidas) {
        while (true) {
            GameUtils.dramaticPrint(pergunta);
            System.out.print("> ");
            String input = scanner.nextLine().trim().toLowerCase();
            
            for (String opcao : opcoesValidas) {
                if (input.equals(opcao)) {
                    return input;
                }
            }
            
            GameUtils.dramaticPrint("Escolha inválida! Opções válidas: " + String.join("/", opcoesValidas));
        }
    }
    
    private void parte3() {
        GameUtils.dramaticPrint("\n" + "=".repeat(60));
        GameUtils.dramaticPrint("CAPÍTULO 3: O TEMPLO DOS DEUSES");
        GameUtils.dramaticPrint("=".repeat(60));
        
        GameUtils.dramaticPrint("Ao entrar na dungeon, você sente um clima estranho, como se a todo momento você estivesse sendo observado. Mesmo enfrentando os goblins, e vários outros inimigos dentro dessa dungeon, a sensação persiste.");
        GameUtils.dramaticPrint("Você encontra uma sala, com uma porta gigante, adornada com símbolos antigos e misteriosos. Ao se aproximar, uma voz ecoa em sua cabeça, 'Entre', diz a voz. Você sente calafrios, sente que algo grande irá acontecer caso você entre nessa sala... Porém, não sabe dizer se será algo bom ou ruim.");
        
        
        String decisao = obterEscolhaValida("\nO que você irá fazer? (entrar/abandonar)", new String[]{"entrar", "abandonar"});

        boolean sobreviveu = false;
        
        if (decisao.equals("entrar")) {
            sobreviveu = cenarioEntrar();
        } else {
            sobreviveu = cenarioAbandonar();
        }
        
        if (sobreviveu) {
            GameUtils.dramaticPrint("\nPressione ENTER para continuar sua jornada como jogador-sistema...");
            GameUtils.waitForEnter();
            gameTree.moveTo(""); 
        } else {
            gameTree.moveTo("game_over");
        }
    }

    private boolean cenarioEntrar() {
        GameUtils.dramaticPrint("\n" + "=".repeat(60));
        GameUtils.dramaticPrint("VOCÊ DECIDIU ENTRAR!");
        GameUtils.dramaticPrint("=".repeat(60));
        
        GameUtils.dramaticPrint("\nVocê toma coragem e atravessa a porta gigante. Ao entrar, uma luz intensa te envolve.");
        GameUtils.dramaticPrint("Você sente uma energia ancestral percorrer seu corpo.");
        GameUtils.dramaticPrint("As vozes na sua mente sussurram segredos antigos do mundo de Aincrad.");
        
        
        GameUtils.dramaticPrint("\nVocê encontrou o Santuário dos Deuses Antigos... Mas esses Deuses não são benevolentes.");
        GameUtils.dramaticPrint("Eles testam a coragem dos aventureiros, apenas para rirem de suas tentativas.");
        GameUtils.dramaticPrint("Você lê inscrições nas paredes que falam sobre sacrifícios e desafios.");
        GameUtils.dramaticPrint("Enquanto explora o templo, você sente que está sendo avaliado por essas entidades poderosas.");
        GameUtils.dramaticPrint("Entidades essas que são estátuas de aproximadamente 20m de altura, cada uma segurando uma arma diferente.");
        
        
        GameUtils.dramaticPrint("\nDe repente, as estátuas começam a se mover, revelando-se como seres vivos, gigantescos e poderosos.");
        GameUtils.dramaticPrint("Elas se aproximam de você, e você percebe que está em uma situação extremamente perigosa.");
        
        String decisao2 = obterEscolhaValida("\nO que você fará diante dessa situação? (lutar/fugir)", new String[]{"lutar", "fugir"});

        if (decisao2.equals("lutar")) {
            GameUtils.dramaticPrint("\nLutar é inútil, mesmo querendo, você sabe que não tem força o suficiente contra elas.");
            GameUtils.dramaticPrint("Você se sente pequeno diante essas criaturas gigantescas.");
        } else {
            GameUtils.dramaticPrint("\nVocê tenta fugir, mas a porta já se fechou atrás de você.");
            GameUtils.dramaticPrint("Você está preso ali e sente que as deixou furiosas.");
        }

        return desfechoFinal();
    }

    private boolean cenarioAbandonar() {
        GameUtils.dramaticPrint("\nVocê decide abandonar a dungeon, como um covarde. Você ainda tem medo de enfrentar o desconhecido.");
        GameUtils.dramaticPrint("E isso o torna incapaz de evoluir, você está sempre fugindo e fugindo do que desconhece.");
        GameUtils.dramaticPrint("Mas saiba, caro " + player.getName() + ", que essa dungeon, não é do tipo de fazer as pazes com covardes...");


        GameUtils.dramaticPrint("\nVocê sente algo te perseguindo enquanto tenta sair da dungeon.");
        GameUtils.dramaticPrint("De repente, uma sombra aparece atrás de você, e antes que possa reagir, tudo fica escuro...");
        GameUtils.dramaticPrint("Você foi derrotado, antes de poder fazer qualquer coisa, você foi derrotado, principalmente, por não ter coragem de enfrentar o desconhecido.");

        GameUtils.dramaticPrint("\n" + "-".repeat(60));
        GameUtils.dramaticPrint("O DESPERTAR NAS PROFUNDEZAS");
        GameUtils.dramaticPrint("-".repeat(60));

        GameUtils.dramaticPrint("\nVocê acorda em um lugar, cercado por estatuas gigantes, cada uma com uma arma, as estátuas deviam ter em cerca de 20m de altura.");
        GameUtils.dramaticPrint("Você finalmente entende o que estava te observando esse tempo todo, afinal, a sensação agora está mais forte do que nunca.");
        GameUtils.dramaticPrint("Você percebe algo estranho, como se fossem escritas pelas paredes, e mesmo estando em uma escrita que você não conhece, você consegue entender perfeitamente o que está escrito ali.");


        GameUtils.dramaticPrint("\nAlí diz... 'Adore-nos', 'Obedeça-nos', 'Sirva-nos'. Você sente um calafrio percorrer sua espinha.");
        GameUtils.dramaticPrint("De repente, as estátuas começam a se mover, uma a uma, revelando-se como seres vivos, gigantescos e poderosos.");
        GameUtils.dramaticPrint("Elas se aproximam de você, e você percebe que está em uma situação extremamente perigosa.");

        String decisao1 = obterEscolhaValida("\nO que você fará diante dessa situação? (lutar/fugir)", new String[]{"lutar", "fugir"});

        if (decisao1.equals("lutar")) {
            GameUtils.dramaticPrint("\nLutar é inútil, mesmo querendo, você sabe que não tem força o suficiente contra elas.");
        } else {
            GameUtils.dramaticPrint("\nVocê tenta fugir, mas a porta já se fechou atrás de você.");
        }

        return desfechoFinal();
    }

    private boolean desfechoFinal() {
        GameUtils.dramaticPrint("\nVocê percebe que está em uma situação extremamente perigosa, e que talvez, a única maneira de sobreviver seja obedecendo essas criaturas gigantescas...");


        GameUtils.dramaticPrint("\nVocê aceita o que está acontecendo alí, e decide se curvar diante dessas estatuas.");
        GameUtils.dramaticPrint("Essa é a única maneira de sobreviver, você pensa consigo mesmo.");
        GameUtils.dramaticPrint("As estátuas parecem se acalmar e param de se movimentar.");
        GameUtils.dramaticPrint("Você sente um alívio momentâneo, mas no fundo sabe que está apenas adiando o inevitável.");
        GameUtils.dramaticPrint("Com sua cabeça cheia de pensamentos, você tenta encontrar uma maneira de sair dali.");


        GameUtils.dramaticPrint("\nAo escutar vozes vindas de longe, você percebe que a saída da dungeon está próxima.");
        GameUtils.dramaticPrint("Você por instinto, grita por ajuda. Quando percebeu, já era tarde de mais, seu corpo foi partido ao meio quase que instantaneamente.");
        GameUtils.dramaticPrint("Tudo fica escuro, você sente seu corpo se afundando em seu próprio sangue.");
        GameUtils.dramaticPrint("Um calor vem te abraçando, você sabe que está morrendo.");
        GameUtils.dramaticPrint("Sua visão começa a escurecer, mas antes de tudo ficar completamente escuro, você vê as estátuas se aproximando de você, e a última coisa que você guarda em sua mente antes de sua morte, é a estátua abrindo um sorriso ao vê-lo morrer.");


        GameUtils.dramaticPrint("\nPensamento " + player.getName() + ": 'Droga, minha vida vai acabar, e a última coisa que tenho é a sensação de ser fraco, de que eu poderia mais, aquela estátua... Ela estava sorrindo para mim, desgraçada, se eu tiver uma chance de poder me vingar. Se eu conseguir voltar, eu juro, que irei destruir vocês, todas vocês, Deuses de merda...'");

        GameUtils.dramaticPrint("\n" + "=".repeat(60));
        GameUtils.dramaticPrint("SISTEMA DE EMERGÊNCIA");
        GameUtils.dramaticPrint("=".repeat(60));

        String sistema = obterEscolhaValida("\nVocê deseja se tornar um jogador-sistema? Caso responda não, seu coração irá parar de bater em 0,2 segundos. (sim/não)", new String[]{"sim", "não"});

        if (sistema.equals("sim")) {
            return true; 
        } else {
            GameUtils.dramaticPrint("\nVocê realmente nunca passou de um fracassado. Seu coração para de bater. Você morreu.");
            return false;
        }
    }
    
    private void desafioInicial() {
        boolean venceu = battleManager.desafioInicial(player);
        if (venceu) {
            gameTree.moveTo("");    
        } else {
            gameTree.moveTo("game_over");
        }
    }

    private void batalhaGoblins() {
        boolean venceu = battleManager.batalhaGoblins(player);
        if (venceu) {
            gameTree.moveTo("");       
        } else {
            gameTree.moveTo("game_over");
        }
    }
    
    private void gameOver() {
        GameUtils.dramaticPrint("GAME OVER - VOCÊ É UM FRACASSADO MESMO!");
    }
    
    private void sistema() {
        GameUtils.dramaticPrint("\nVocê acorda em um hospital, com o corpo inteiro. Você não está mais partido ao meio, e está alive, isso é impossivel, você pensa consigo mesmo.");
        GameUtils.dramaticPrint("Você começa a se levantar da cama, mas sente medo, medo daquilo se repetir, medo de continuar sendo fraco, medo do inevitável.");
        GameUtils.dramaticPrint("Ao olhar para o lado, você vê um homem, encapuzado, você não consegue ver seu rosto, mas sente uma presença poderosa vindo dele.");


        GameUtils.dramaticPrint("\nHomem: 'Então você é o novato? Hmm... Você ainda não passa de um fraco, mas vejo que mesmo com medo, você ainda tem vontade de viver. Obedeça ao sistema garoto, ele te deixara mais forte do que qualquer um.'");

        GameUtils.dramaticPrint("\nAo piscar de olhos o homem some, dúvidas vem a sua cabeça. O que é esse sistema?");
        GameUtils.dramaticPrint("Ao se perguntar, uma tela aparece em sua frente, como se fosse um jogo.");
        GameUtils.dramaticPrint("Você vê suas estatísticas, seus atributos, e uma série de missões para completar.");
        GameUtils.dramaticPrint("Você percebe que agora é um jogador-sistema, e que tem a chance de se tornar mais forte do que nunca.");
        
        GameUtils.dramaticPrint("\n" + "=".repeat(60));
        GameUtils.dramaticPrint("FIM DO CAPÍTULO 3 - AGORA VOCÊ É UM JOGADOR-SISTEMA!");
        GameUtils.dramaticPrint("=".repeat(60));

        GameUtils.dramaticPrint("\nPressione ENTER para iniciar o treinamento do sistema...");
        GameUtils.waitForEnter();
        gameTree.moveTo("continuar");
    }

    private void iniciarTreinamento() {
        TrainingManager trainingManager = new TrainingManager();
        boolean sucesso = trainingManager.iniciarTreinamento(player);
        if (sucesso) {
            gameTree.moveTo("");
        } else {
            gameTree.moveTo("game_over");
        }
    }
}