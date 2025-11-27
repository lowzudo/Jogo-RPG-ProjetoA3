package src.battles;

import src.entities.Player;
import src.utils.GameUtils;
import java.util.Scanner;
import java.util.Random;

public class BattleManager {
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random(); // Adicione esta linha
    
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

    public boolean batalhaEncapuzado(Player player) {
        int vidaEncapuzado = 150;
        int staminaEncapuzado = 120;
        int forcaEncapuzado = 25;
        
        int faseBatalha = 1;
        boolean golpesEspeciaisDesbloqueados = false;

        GameUtils.dramaticPrint("\n" + "=".repeat(60));
        GameUtils.dramaticPrint("BATALHA CONTRA O HOMEM QUE VENCEU O SISTEMA!");
        GameUtils.dramaticPrint("=".repeat(60));

        GameUtils.dramaticPrint("Inimigo: Homem Encapuzado");
        GameUtils.dramaticPrint("Vida do Encapuzado: " + vidaEncapuzado);
        GameUtils.dramaticPrint("Ataque do Encapuzado: " + forcaEncapuzado);

        GameUtils.dramaticPrint("Eu vejo que você evoluiu... Mas não o bastante para me derrotar. Venha, mostre-me do que é capaz!");

        GameUtils.dramaticPrint("Desafio secreto desbloqueado: 'Desafiante do Sistema - Derrote o Homem que venceu do Sistema'");

        int vidaOriginal = player.getMaxHealth();
        int staminaOriginal = player.getMaxStamina();
        int forcaOriginal = player.getStrength();
        
        player.setHealth(90);
        player.setMaxHealth(90);
        player.setStamina(90);
        player.setMaxStamina(90);
        player.setStrength(15);

        GameUtils.dramaticPrint("'Vamos ver o quanto eu progredi nesses 3 meses... Eu quero testar AQUILO...' - Você pensa consigo mesmo.");

        while (vidaEncapuzado > 0 && player.getHealth() > 0) {
            if (vidaEncapuzado <= 50 && faseBatalha == 1) {
                faseBatalha = 2;
                GameUtils.dramaticPrint("\nFASE 2 DESBLOQUEADA: O VÉU DA REALIDADE SE ABRE!");
                GameUtils.dramaticPrint("SEUS GOLPES ESPECIAIS MAIS PODEROSOS ESTÃO LIBERADOS!");
                golpesEspeciaisDesbloqueados = true;
            }

            GameUtils.dramaticPrint("\n" + "-".repeat(50));
            GameUtils.dramaticPrint("FASE " + faseBatalha + " | ENCAPUZADO: " + vidaEncapuzado + "/150 | " + player.getName() + ": " + player.getHealth() + "/90");
            GameUtils.dramaticPrint("SUA STAMINA: " + player.getStamina() + "/90 | AURA DO INIMIGO: " + staminaEncapuzado + "/120");
            GameUtils.dramaticPrint("-".repeat(50));
            
            GameUtils.dramaticPrint("\nESCOLHA SEU ATAQUE:");
            
            if (!golpesEspeciaisDesbloqueados) {
                GameUtils.dramaticPrint("1. Ataque Básico (5 stamina)");
                GameUtils.dramaticPrint("2. Defesa com Contra-Ataque (10 stamina)");
                GameUtils.dramaticPrint("3. Focar (0 stamina)");
                
                int i = 4;
                for (String ataque : player.getAttackNames()) {
                    if (i <= 7) {
                        int custo = player.getAttacks().get(ataque).getStaminaCost();
                        GameUtils.dramaticPrint(i + ". " + ataque + " (" + custo + " stamina)");
                        i++;
                    }
                }
            } else {
                int i = 1;
                for (String ataque : player.getAttackNames()) {
                    int custo = player.getAttacks().get(ataque).getStaminaCost();
                    GameUtils.dramaticPrint(i + ". " + ataque + " (" + custo + " stamina)");
                    i++;
                }
            }
            
            String escolha = scanner.nextLine().trim();
            int danoJogador = 0;
            int custoStamina = 0;
            
            if (!golpesEspeciaisDesbloqueados) {
                switch (escolha) {
                    case "1":
                        danoJogador = random.nextInt(8) + 8 + player.getStrength();
                        custoStamina = 5;
                        GameUtils.dramaticPrint("Ataque Básico! Dano: " + danoJogador);
                        break;
                    case "2":
                        danoJogador = random.nextInt(6) + 3 + player.getStrength();
                        custoStamina = 10;
                        player.setHealth(Math.min(player.getHealth() + 8, player.getMaxHealth()));
                        GameUtils.dramaticPrint("Defesa com Contra-Ataque! Dano: " + danoJogador + " + Cura: 8");
                        break;
                    case "3":
                        custoStamina = 0;
                        int bonusDano = random.nextInt(8) + 5;
                        player.setStrength(player.getStrength() + bonusDano);
                        GameUtils.dramaticPrint("Foco Intenso! Força +" + bonusDano + " neste turno!");
                        break;
                    default:
                        try {
                            int escolhaNum = Integer.parseInt(escolha);
                            if (escolhaNum >= 4 && escolhaNum <= 7) {
                                int indexAtaque = escolhaNum - 4;
                                if (indexAtaque < player.getAttackNames().size()) {
                                    String nomeAtaque = player.getAttackNames().get(indexAtaque);
                                    int danoBase = player.getAttacks().get(nomeAtaque).getBaseDamage();
                                    custoStamina = player.getAttacks().get(nomeAtaque).getStaminaCost();
                                    danoJogador = danoBase + player.getStrength() + random.nextInt(11) + 5;
                                    GameUtils.dramaticPrint(nomeAtaque + "! Dano: " + danoJogador);
                                }
                            } else {
                                GameUtils.dramaticPrint("Movimento inválido! Você perde o turno!");
                                custoStamina = 5;
                            }
                        } catch (NumberFormatException e) {
                            GameUtils.dramaticPrint("Movimento inválido! Você perde o turno!");
                            custoStamina = 5;
                        }
                }
            } else {
                try {
                    int escolhaNum = Integer.parseInt(escolha);
                    if (escolhaNum >= 1 && escolhaNum <= player.getAttackNames().size()) {
                        String nomeAtaque = player.getAttackNames().get(escolhaNum - 1);
                        int danoBase = player.getAttacks().get(nomeAtaque).getBaseDamage();
                        custoStamina = player.getAttacks().get(nomeAtaque).getStaminaCost();
                        danoJogador = danoBase + player.getStrength() + random.nextInt(11) + 5;
                        GameUtils.dramaticPrint(nomeAtaque + "! Dano: " + danoJogador);
                    } else {
                        GameUtils.dramaticPrint("Movimento inválido! Você perde o turno!");
                        custoStamina = 5;
                    }
                } catch (NumberFormatException e) {
                    GameUtils.dramaticPrint("Movimento inválido! Você perde o turno!");
                    custoStamina = 5;
                }
            }
            
            if (player.getStamina() >= custoStamina) {
                player.setStamina(player.getStamina() - custoStamina);
                vidaEncapuzado -= Math.max(0, danoJogador);
            } else {
                GameUtils.dramaticPrint("STAMINA INSUFICIENTE! Movimento falha!");
            }
            
            if (vidaEncapuzado <= 0) {
                GameUtils.dramaticPrint("\nVOCÊ DERROTOU O HOMEM ENCAPUZADO!");
                player.setMaxHealth(vidaOriginal);
                player.setMaxStamina(staminaOriginal);
                player.setStrength(forcaOriginal);
                vitoriaEncapuzado(player);
                return true;
            }
            
            GameUtils.dramaticPrint("\nTURNO DO HOMEM ENCAPUZADO...");
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
            
            int danoInimigo;
            String nomeAtaqueInimigo;
            
            if (faseBatalha == 1) {
                String[] ataques = {"Soco Sônico", "Campo de Força", "Investida Relâmpago"};
                nomeAtaqueInimigo = ataques[random.nextInt(ataques.length)];
                danoInimigo = random.nextInt(11) + (nomeAtaqueInimigo.equals("Investida Relâmpago") ? 15 : 10);
            } else {
                String[] ataques = {"DISRUPTOR DIMENSIONAL", "COLAPSO EXISTENCIAL", "PARADOXO TEMPORAL"};
                nomeAtaqueInimigo = ataques[random.nextInt(ataques.length)];
                danoInimigo = random.nextInt(16) + (nomeAtaqueInimigo.equals("COLAPSO EXISTENCIAL") ? 30 : 25);
            }
            
            int chanceEsquiva = Math.min(30, player.getStamina());
            if (random.nextInt(100) < chanceEsquiva) {
                GameUtils.dramaticPrint("Você esquiva habilmente do " + nomeAtaqueInimigo + "!");
                danoInimigo = 0;
            } else {
                GameUtils.dramaticPrint(nomeAtaqueInimigo + " acerta você! Dano: " + danoInimigo);
                player.setHealth(player.getHealth() - danoInimigo);
            }
            
            player.setStamina(Math.min(player.getStamina() + 8, player.getMaxStamina()));
            staminaEncapuzado = Math.min(staminaEncapuzado + 10, 120);
            
            if (player.getHealth() <= 0) {
                break;
            }
            
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
        }

        if (player.getHealth() <= 0) {
            derrotaEncapuzado(player, vidaOriginal, staminaOriginal, forcaOriginal);
            return false;
        }
        
        return true;
    }
    
    private void vitoriaEncapuzado(Player player) {
        GameUtils.dramaticPrint("\n" + "=".repeat(60));
        GameUtils.dramaticPrint("VITÓRIA CONTRA O IMPOSSÍVEL");
        GameUtils.dramaticPrint("=".repeat(60));

        GameUtils.dramaticPrint("Com um último golpe desferido com toda sua força... Você finalmente foi capaz de o derrotar, após uma árdua luta.");
        GameUtils.dramaticPrint("O homem encapuzado cai de joelhos... Sua aura avassaladora que tomava o ambiente, começa a se dissipar.");
        GameUtils.dramaticPrint("Ele remove lentamente o traje, junto de seu capuz, revelando um rosto marcado por cicatrizes e sabedoria.");

        GameUtils.dramaticPrint("Você pensa consigo mesmo: 'Ele era tão forte assim mesmo com essa idade. Talvez se ele fosse mais novo o resultado seria diferente...'");
        GameUtils.dramaticPrint("Você escuta ele o chamando para perto... E então ele diz");
        GameUtils.dramaticPrint("\n'Incrível, " + player.getName() + "...' ele sussurra com voz rouca.");
        GameUtils.dramaticPrint("'Você alcançou o que eu pensei ser impossível...'");
        GameUtils.dramaticPrint("'Durante séculos, eu busquei por alguém que pudesse me superar, que pudesse carregar o fardo...'");

        GameUtils.dramaticPrint("\n'O sistema não foi criado para nos controlar, mas para nos preparar.'");
        GameUtils.dramaticPrint("'Prepare-se para a verdade sobre Aincrad, sobre as estátuas, sobre tudo...'");
        GameUtils.dramaticPrint("Sua forma começa a brilhar com uma luz intensa.");

        GameUtils.dramaticPrint("\n'Você não derrotou apenas um homem - você derrotou o próprio conceito de limite!'");
        GameUtils.dramaticPrint("'A partir de hoje, você é o novo Guardião do Sistema.'");
        GameUtils.dramaticPrint("'Use esse poder com sabedoria, pois grandes desafios ainda estão por vir...'");

        GameUtils.dramaticPrint("\n" + "-".repeat(60));
        GameUtils.dramaticPrint("CONQUISTA DESBLOQUEADA: 'O Novo Guardião'");
        GameUtils.dramaticPrint("-".repeat(60));

        player.setStrength(player.getStrength() + 50);
        player.setMaxHealth(player.getMaxHealth() + 100);
        player.setMaxStamina(player.getMaxStamina() + 200);
        player.setHealth(player.getMaxHealth());
        player.setStamina(player.getMaxStamina());

        GameUtils.dramaticPrint("Força: " + player.getStrength() + " (+50)");
        GameUtils.dramaticPrint("Vida: " + player.getMaxHealth() + " (+100)");
        GameUtils.dramaticPrint("Stamina: " + player.getMaxStamina() + " (+200)");

        GameUtils.dramaticPrint("\nO homem sorri pela última vez, antes de se dissipar em partículas de luz.");
        GameUtils.dramaticPrint("'Enfrente as estátuas... Nem eu fui capaz de derrotá-las. Elas guardam a verdade final.'");
        GameUtils.dramaticPrint("Sua voz ecoa pela última vez: 'Mostre a elas o que é verdadeiro poder...'");
    }
    
    private void derrotaEncapuzado(Player player, int vidaOriginal, int staminaOriginal, int forcaOriginal) {
        player.setMaxHealth(vidaOriginal);
        player.setMaxStamina(staminaOriginal);
        player.setStrength(forcaOriginal);
        player.setHealth(player.getMaxHealth());
        player.setStamina(player.getMaxStamina());
        
        GameUtils.dramaticPrint("\nVOCÊ CHEGOU AO SEU LIMITE...");
        GameUtils.dramaticPrint("O homem encapuzado olha para você sorrindo...");
        GameUtils.dramaticPrint("Diferente do sorriso da estátua, aquele não era um sorriso de desprezo, e sim de admiração.");
        
        GameUtils.dramaticPrint("Homem: Você chegou longe, muito longe, meu jovem... Eu acredito no seu potencial.");
        GameUtils.dramaticPrint("Ele prepara um golpe final, ele ergue sua mão o clima ao redor começa a mudar...");
        GameUtils.dramaticPrint("Você pensa consigo mesmo: 'Eu não estou triste, eu consegui mudar o destino da minha vida, eu consegui me tornar mais forte...'");
        
        GameUtils.dramaticPrint("Você continua pensando: 'A única coisa em que me arrependo, é de não poder me vingar daquela estátua que me subestimou...'");
        GameUtils.dramaticPrint("Corta uma tela que mostra os homens mais fortes que já existiram, o primeiro tem seu nome como ???, o segundo é você.");
        
        GameUtils.dramaticPrint("O homem encapuzado diz antes de te atacar: 'Você foi um adversário digno, muitos já tiveram a oportunidade dada pelo sistema...'");
        GameUtils.dramaticPrint("Prestes a dar o golpe final, o homem encapuzado diz: 'A verdade garoto... É que minha era já acabou há muitos anos...'");
        
        GameUtils.dramaticPrint("Conquista Desbloqueada - 'O Homem mais forte do Mundo'. Melhorando seus atributos");

        player.setStrength(player.getStrength() + 30);
        player.setMaxHealth(player.getMaxHealth() + 50);
        player.setMaxStamina(player.getMaxStamina() + 100);
        player.setHealth(player.getMaxHealth());
        player.setStamina(player.getMaxStamina());

        GameUtils.dramaticPrint("\nSEUS NOVOS STATUS:");
        GameUtils.dramaticPrint("Força: " + player.getStrength() + " (+30)");
        GameUtils.dramaticPrint("Vida: " + player.getMaxHealth() + " (+50)");
        GameUtils.dramaticPrint("Stamina: " + player.getMaxStamina() + " (+100)");
    }
    
    private void gameOver() {
        GameUtils.dramaticPrint("\nDesde o inicio eu já sabia o quão fracassado você era, só não sabia que era tanto, você morreu");
        GameUtils.dramaticPrint("\n" + "=".repeat(60));
        GameUtils.dramaticPrint("FIM DE JOGO - VOCÊ MORREU");
        GameUtils.dramaticPrint("=".repeat(60));
    }
}