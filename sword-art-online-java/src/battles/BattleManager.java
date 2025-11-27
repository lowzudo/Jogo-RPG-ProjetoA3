package src.battles;

import src.entities.Player;
import src.utils.GameUtils;
import java.util.Scanner;

public class BattleManager {
    private Scanner scanner = new Scanner(System.in);
    
    public boolean desafioInicial(Player player) {
        GameUtils.dramaticPrint("\n" + "-".repeat(60));
        System.out.print("Deseja ir enfrentar um desafio onde você provavelmente irá morrer, mas se sair vivo irá aumentar muito seus atributos por agora? (S/N)\n> ");
        String decisao1 = scanner.nextLine().trim().toUpperCase();

        while (!decisao1.equals("S") && !decisao1.equals("N")) {
            System.out.print("Resposta inválida. Por favor, responda com 'S' para sim ou 'N' para não: ");
            decisao1 = scanner.nextLine().trim().toUpperCase();
        }

        if (decisao1.equals("S")) {
            GameUtils.dramaticPrint("\nCORAJOSO! Avistamos uma dungeon e entramos nela...");
            return lutaInicial(player);
        } else {
            GameUtils.dramaticPrint("\nVocê decidiu não enfrentar o desafio por agora. É a primeira vez que vejo um " + player.getClassName() + " medroso!");
            GameUtils.dramaticPrint("Boa sorte ao encarar os desafios daqui para frente sem ter upado nada.");
            return true;
        }
    }
    
    private boolean lutaInicial(Player player) {
        GameUtils.dramaticPrint("\n" + "=".repeat(60));
        GameUtils.dramaticPrint("ENCONTRO COM A HORDA DE LOBOS!");
        GameUtils.dramaticPrint("=".repeat(60));
        
        GameUtils.dramaticPrint("Inimigo: Horda de Lobos Famintos");
        GameUtils.dramaticPrint("Vida dos Lobos: 20");
        GameUtils.dramaticPrint("Ataque dos Lobos: 2");
        GameUtils.dramaticPrint("-".repeat(60));

        int vidaLobos = 20;
        int atqLobos = 2;
        boolean venceu = false;

        while (vidaLobos > 0 && player.getHealth() > 0 && player.getStamina() > 0) {
            GameUtils.dramaticPrint("\nSEU STATUS:");
            GameUtils.dramaticPrint("Sua Vida: " + player.getHealth() + " | Stamina: " + player.getStamina());
            GameUtils.dramaticPrint("Vida dos Lobos: " + vidaLobos);
            GameUtils.dramaticPrint("-".repeat(40));
            
            player.showAttacks();
            
            System.out.print("\nQual ataque você deseja usar? > ");
            String ataque = scanner.nextLine().trim();

            if (!player.getAttackNames().contains(ataque)) {
                GameUtils.dramaticPrint("\nAtaque inválido! Você hesitou e os lobos aproveitaram para te atacar!");
                player.setHealth(player.getHealth() - atqLobos);
                GameUtils.dramaticPrint("Os lobos te atacam e causam " + atqLobos + " de dano.");
                GameUtils.dramaticPrint("Sua vida restante: " + player.getHealth());
                
                if (player.getHealth() <= 0) {
                    gameOver();
                    return false;
                }
                continue;
            }

            int custoStamina = player.getAttacks().get(ataque).getStaminaCost();
            int danoBase = player.getAttacks().get(ataque).getBaseDamage();
            int danoReal = danoBase + player.getStrength();
            
            if (player.getStamina() >= custoStamina) {
                vidaLobos -= danoReal;
                player.setStamina(player.getStamina() - custoStamina);
                GameUtils.dramaticPrint("\nVocê usou '" + ataque + "'!");
                GameUtils.dramaticPrint("Os lobos receberam " + danoReal + " de dano. Vida restante: " + vidaLobos + ". Sua stamina: " + player.getStamina());
            } else {
                GameUtils.dramaticPrint("\nStamina insuficiente para " + ataque + "!");
                GameUtils.dramaticPrint("Você perdeu a vez e os lobos te atacam!");
                player.setHealth(player.getHealth() - atqLobos);
                GameUtils.dramaticPrint("Os lobos te atacam e causam " + atqLobos + " de dano.");
                GameUtils.dramaticPrint("Sua vida restante: " + player.getHealth());
                
                if (player.getHealth() <= 0) {
                    gameOver();
                    return false;
                }
                continue;
            }
            
            if (vidaLobos <= 0) {
                GameUtils.dramaticPrint("\nSinceramente... Não achei que você sobreviveria, meus sinceros parabéns. Fracassado.");
                venceu = true;
                break;
            }
            
            GameUtils.dramaticPrint("\nAgora é a vez dos lobos amigão HAHAHA. Eles te atacam e causam " + atqLobos + " de dano.");
            player.setHealth(player.getHealth() - atqLobos);
            GameUtils.dramaticPrint("Sua vida restante: " + player.getHealth());
            
            if (player.getHealth() <= 0) {
                gameOver();
                return false;
            }
        }

        if (venceu) {
            player.rest();
            player.levelUp();
            return true;
        } else {
            player.rest();
            GameUtils.dramaticPrint("Pelo menos você pode descansar... fracassado!");
            return false;
        }
    }

    public boolean batalhaGoblins(Player player) {
        GameUtils.dramaticPrint("BATALHA CONTRA OS GOBLINS GUARDIÕES!");
        
        GameUtils.dramaticPrint("Inimigo: Goblin Guardião + 2 Goblin Peões");
        GameUtils.dramaticPrint("Vida do Goblin Guardião: 15");
        GameUtils.dramaticPrint("Vida dos Goblin Peões: 5 cada");
        GameUtils.dramaticPrint("Ataque do Guardião: 3 | Ataque dos Peões: 1");
        GameUtils.dramaticPrint("'GRRR! Ninguém passa!'");
        GameUtils.dramaticPrint("-".repeat(60));

        int vidaGuardiao = 15;
        int vidaPeao1 = 5;
        int vidaPeao2 = 5;
        boolean venceu = false;
        int turno = 1;

        while ((vidaGuardiao > 0 || vidaPeao1 > 0 || vidaPeao2 > 0) && player.getHealth() > 0 && player.getStamina() > 0) {
            GameUtils.dramaticPrint("\nTURNO " + turno);
            GameUtils.dramaticPrint("SEU STATUS: Vida " + player.getHealth() + " | Stamina " + player.getStamina());
            GameUtils.dramaticPrint("INIMIGOS: Guardião " + Math.max(0, vidaGuardiao) + " | Peão1 " + Math.max(0, vidaPeao1) + " | Peão2 " + Math.max(0, vidaPeao2));
            GameUtils.dramaticPrint("-".repeat(50));
            
            java.util.List<String> alvosDisponiveis = new java.util.ArrayList<>();
            GameUtils.dramaticPrint("ESCOLHA SEU ALVO:");
            
            if (vidaGuardiao > 0) {
                alvosDisponiveis.add("Guardião");
                GameUtils.dramaticPrint("1- Goblin Guardião (Vida: 15, Ataque: 3) - LÍDER");
            }
            if (vidaPeao1 > 0) {
                alvosDisponiveis.add("Peão1");
                GameUtils.dramaticPrint("2- Goblin Peão 1 (Vida: 5, Ataque: 1) - FRACO");
            }
            if (vidaPeao2 > 0) {
                alvosDisponiveis.add("Peão2");
                GameUtils.dramaticPrint("3- Goblin Peão 2 (Vida: 5, Ataque: 1) - FRACO");
            }
            
            System.out.print("\nEscolha o alvo (1/2/3) ou digite o nome do ataque: ");
            String escolha = scanner.nextLine().trim();

            String alvoEscolhido = null;
            String ataque = null;
            
            if (escolha.equals("1") || escolha.equals("2") || escolha.equals("3")) {
                int escolhaNum = Integer.parseInt(escolha);
                if (1 <= escolhaNum && escolhaNum <= alvosDisponiveis.size()) {
                    alvoEscolhido = alvosDisponiveis.get(escolhaNum-1);
                    System.out.print("Qual ataque usar em " + alvoEscolhido + "? " + String.join(", ", player.getAttackNames()) + ": ");
                    ataque = scanner.nextLine().trim();
                } else {
                    GameUtils.dramaticPrint("Alvo inválido! Você fica confuso e os goblins te atacam!");
                    int danoTotalGoblins = 0;
                    if (vidaGuardiao > 0) {
                        player.setHealth(player.getHealth() - 3);
                        danoTotalGoblins += 3;
                        GameUtils.dramaticPrint("Goblin Guardião te ataca com seu machado! (-3 Vida)");
                    }
                    if (vidaPeao1 > 0) {
                        player.setHealth(player.getHealth() - 1);
                        danoTotalGoblins += 1;
                        GameUtils.dramaticPrint("Goblin Peão 1 te joga uma pedra! (-1 Vida)");
                    }
                    if (vidaPeao2 > 0) {
                        player.setHealth(player.getHealth() - 1);
                        danoTotalGoblins += 1;
                        GameUtils.dramaticPrint("Goblin Peão 2 te ataca com uma faca! (-1 Vida)");
                    }
                    
                    GameUtils.dramaticPrint("Dano total recebido: " + danoTotalGoblins);
                    GameUtils.dramaticPrint("Sua vida restante: " + player.getHealth());
                    
                    if (player.getHealth() <= 0) {
                        gameOver();
                        return false;
                    }
                    continue;
                }
            } else {
                ataque = escolha;
                if (vidaPeao1 > 0) {
                    alvoEscolhido = "Peão1";
                } else if (vidaPeao2 > 0) {
                    alvoEscolhido = "Peão2";
                } else if (vidaGuardiao > 0) {
                    alvoEscolhido = "Guardião";
                } else {
                    GameUtils.dramaticPrint("Nenhum alvo disponível!");
                    continue;
                }
            }

            if (!player.getAttackNames().contains(ataque)) {
                GameUtils.dramaticPrint("Ataque inválido! Os goblins riem da sua incompetência e te atacam!");
                int danoTotalGoblins = 0;
                if (vidaGuardiao > 0) {
                    player.setHealth(player.getHealth() - 3);
                    danoTotalGoblins += 3;
                    GameUtils.dramaticPrint("Goblin Guardião te ataca com seu machado! (-3 Vida)");
                }
                if (vidaPeao1 > 0) {
                    player.setHealth(player.getHealth() - 1);
                    danoTotalGoblins += 1;
                    GameUtils.dramaticPrint("Goblin Peão 1 te joga uma pedra! (-1 Vida)");
                }
                if (vidaPeao2 > 0) {
                    player.setHealth(player.getHealth() - 1);
                    danoTotalGoblins += 1;
                    GameUtils.dramaticPrint("Goblin Peão 2 te ataca com uma faca! (-1 Vida)");
                }
                
                GameUtils.dramaticPrint("Dano total recebido: " + danoTotalGoblins);
                GameUtils.dramaticPrint("Sua vida restante: " + player.getHealth());
                
                if (player.getHealth() <= 0) {
                    gameOver();
                    return false;
                }
                continue;
            }

            int custoStamina = player.getAttacks().get(ataque).getStaminaCost();
            int danoBase = player.getAttacks().get(ataque).getBaseDamage();
            int dano = danoBase + player.getStrength();

            if (player.getStamina() < custoStamina) {
                GameUtils.dramaticPrint("Stamina insuficiente para " + ataque + "!");
                GameUtils.dramaticPrint("Você tropeça e fica vulnerável! Os goblins te atacam!");
                int danoTotalGoblins = 0;
                if (vidaGuardiao > 0) {
                    player.setHealth(player.getHealth() - 3);
                    danoTotalGoblins += 3;
                    GameUtils.dramaticPrint("Goblin Guardião te ataca com seu machado! (-3 Vida)");
                }
                if (vidaPeao1 > 0) {
                    player.setHealth(player.getHealth() - 1);
                    danoTotalGoblins += 1;
                    GameUtils.dramaticPrint("Goblin Peão 1 te joga uma pedra! (-1 Vida)");
                }
                if (vidaPeao2 > 0) {
                    player.setHealth(player.getHealth() - 1);
                    danoTotalGoblins += 1;
                    GameUtils.dramaticPrint("Goblin Peão 2 te ataca com uma faca! (-1 Vida)");
                }
                
                GameUtils.dramaticPrint("Dano total recebido: " + danoTotalGoblins);
                GameUtils.dramaticPrint("Sua vida restante: " + player.getHealth());
                
                if (player.getHealth() <= 0) {
                    gameOver();
                    return false;
                }
                continue;
            }

            player.setStamina(player.getStamina() - custoStamina);
            
            if (alvoEscolhido.equals("Guardião") && vidaGuardiao > 0) {
                vidaGuardiao -= dano;
                GameUtils.dramaticPrint("\nVocê usou '" + ataque + "' no Goblin Guardião!");
                GameUtils.dramaticPrint("Causou " + dano + " de dano! Vida Guardião: " + Math.max(0, vidaGuardiao));
            } else if (alvoEscolhido.equals("Peão1") && vidaPeao1 > 0) {
                vidaPeao1 -= dano;
                GameUtils.dramaticPrint("\nVocê usou '" + ataque + "' no Goblin Peão 1!");
                GameUtils.dramaticPrint("Causou " + dano + " de dano! Vida Peão 1: " + Math.max(0, vidaPeao1));
            } else if (alvoEscolhido.equals("Peão2") && vidaPeao2 > 0) {
                vidaPeao2 -= dano;
                GameUtils.dramaticPrint("\nVocê usou '" + ataque + "' no Goblin Peão 2!");
                GameUtils.dramaticPrint("Causou " + dano + " de dano! Vida Peão 2: " + Math.max(0, vidaPeao2));
            } else {
                GameUtils.dramaticPrint("Alvo já está derrotado! Ataque desperdiçado...");
            }
            
            if (vidaGuardiao <= 0 && vidaPeao1 <= 0 && vidaPeao2 <= 0) {
                GameUtils.dramaticPrint("\nVITÓRIA! Você derrotou todos os goblins!");
                GameUtils.dramaticPrint("A entrada da dungeon está livre!");
                GameUtils.dramaticPrint("Bom trabalho, aventureiro!");
                venceu = true;
                break;
            }
            
            GameUtils.dramaticPrint("\nVEZ DOS GOBLINS!");
            int danoTotalGoblins = 0;
            
            if (vidaGuardiao > 0) {
                player.setHealth(player.getHealth() - 3);
                danoTotalGoblins += 3;
                GameUtils.dramaticPrint("Goblin Guardião te ataca com seu machado! (-3 Vida)");
            }
            
            if (vidaPeao1 > 0) {
                player.setHealth(player.getHealth() - 1);
                danoTotalGoblins += 1;
                GameUtils.dramaticPrint("Goblin Peão 1 te joga uma pedra! (-1 Vida)");
            }
            
            if (vidaPeao2 > 0) {
                player.setHealth(player.getHealth() - 1);
                danoTotalGoblins += 1;
                GameUtils.dramaticPrint("Goblin Peão 2 te ataca com uma faca! (-1 Vida)");
            }
            
            GameUtils.dramaticPrint("Dano total recebido: " + danoTotalGoblins);
            GameUtils.dramaticPrint("Sua vida restante: " + player.getHealth());
            
            String[] frasesGoblins = {
                "'Morra, intruso!'",
                "'Ninguém passa por nós!'",
                "'Sua carne vai virar jantar!'",
                "'Hihihi, ele está sangrando!'"
            };
            java.util.Random random = new java.util.Random();
            GameUtils.dramaticPrint("Goblins: " + frasesGoblins[random.nextInt(frasesGoblins.length)]);
            
            if (player.getHealth() <= 0) {
                gameOver();
                return false;
            }
            
            turno++;
        }

        if (venceu) {
            player.rest();
            player.levelUp();
            GameUtils.dramaticPrint("\nAgora você pode entrar na dungeon...");
            return true;
        } else {
            player.rest();
            GameUtils.dramaticPrint("\nVocê recua enquanto os goblins zombam...");
            GameUtils.dramaticPrint("Você não é forte o suficiente, não passa de um fracassado. Vá treinar mais, fracote!");
            return false;
        }
    }
    
    private void gameOver() {
        GameUtils.dramaticPrint("\nDesde o inicio eu já sabia o quão fracassado você era, só não sabia que era tanto, você morreu");
        GameUtils.dramaticPrint("\n" + "=".repeat(60));
        GameUtils.dramaticPrint("FIM DE JOGO - VOCÊ MORREU");
        GameUtils.dramaticPrint("=".repeat(60));
    }
}