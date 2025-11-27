package src.training;

import src.entities.Player;
import src.utils.GameUtils;
import java.util.Scanner;

public class TrainingManager {
    private Scanner scanner = new Scanner(System.in);
    
    public boolean iniciarTreinamento(Player player) {
        GameUtils.dramaticPrint("\n" + "=".repeat(60));
        GameUtils.dramaticPrint("CAPÍTULO 4: A ASCENSÃO DO JOGADOR-SISTEMA");
        GameUtils.dramaticPrint("=".repeat(60));
        
        GameUtils.dramaticPrint("\nApós toda essa loucura, você ainda está processando o que aconteceu.");
        GameUtils.dramaticPrint("Enquanto a tela do sistema flutua diante de seus olhos, você lê as missões que precisa completar para ganhar 'Recompensas' e 'Habilidades'.");
        GameUtils.dramaticPrint("Mas caso o contrário, tem algo bem grande e vermelho escrito 'Punição caso você não complete as missões. Tempo até missão acabar: 5 horas'.");

        GameUtils.waitForEnter();

        while (true) {  
            GameUtils.dramaticPrint("\nO que você fará? (aceitar/adiar)");
            String decisao = scanner.nextLine().trim().toLowerCase();

            if (decisao.equals("aceitar")) {
                GameUtils.dramaticPrint("\nVocê aceitou as missões do Sistema!");
                Recompensas recompensas = cenaTreinoSistema();
                if (recompensas != null) {
                    aplicarRecompensas(player, recompensas);
                }
                break;
                
            } else if (decisao.equals("adiar")) {
                while (true) {
                    GameUtils.dramaticPrint("\nTem certeza que deseja adiar as missões? Isso pode ter consequências graves. (sim/não)");
                    String confirmar = scanner.nextLine().trim().toLowerCase();
                    
                    if (confirmar.equals("sim")) {
                        break;
                    } else if (confirmar.equals("não")) {
                        decisao = "aceitar";
                        break;
                    } else {
                        GameUtils.dramaticPrint("\nResposta inválida. Digite 'sim' ou 'não'.");
                    }
                }
                
                if (decisao.equals("adiar")) {
                    GameUtils.dramaticPrint("\nVocê decide adiar as missões, mas o tempo está passando...");
                    
                    for (int x = 5; x > 0; x--) {
                        GameUtils.dramaticPrint("\nTempo restante para completar as missões: " + x + " horas");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        GameUtils.dramaticPrint("Você sente a pressão do Sistema...");
                    }
                    continue; 
                } else {
                    
                    GameUtils.dramaticPrint("\nVocê aceitou as missões do Sistema!");
                    Recompensas recompensas = cenaTreinoSistema();
                    if (recompensas != null) {
                        aplicarRecompensas(player, recompensas);
                    }
                    break;
                }
                
            } else {
                GameUtils.dramaticPrint("\nResposta inválida. Digite 'aceitar' ou 'adiar'");
            }
        }

        GameUtils.waitForEnter();
        return true;
    }
    
    private Recompensas cenaTreinoSistema() {
        GameUtils.dramaticPrint("\n" + "=".repeat(50));
        GameUtils.dramaticPrint("TREINAMENTO DO SISTEMA");
        GameUtils.dramaticPrint("=".repeat(50));
        
        GameUtils.dramaticPrint("\nSistema: 'Bem-vindo ao treinamento, fracote. Complete esses desafios para receber suas recompensas.'");
        
        GameUtils.dramaticPrint("\nDesafio 1: Corrida contra o tempo...");
        GameUtils.dramaticPrint("Você corre até quase desmaiar, mas completa o percurso!");
        
        GameUtils.dramaticPrint("\nDesafio 2: Levantamento de pesos extremos...");
        GameUtils.dramaticPrint("Seus músculos queimam, mas você levanta o impossível!");
        
        GameUtils.dramaticPrint("\nDesafio 3: Meditação sob pressão...");
        GameUtils.dramaticPrint("Sua mente expande, controlando a dor e o cansaço!");
        
        GameUtils.waitForEnter();
        
        GameUtils.dramaticPrint("\nSistema: 'Hmm... menos mal. Aqui estão suas recompensas, fracote.'");
        
        // Retorna as recompensas
        return new Recompensas(3, 15, 20);
    }
    
    private void aplicarRecompensas(Player player, Recompensas recompensas) {
        player.setStrength(player.getStrength() + recompensas.forca);
        player.setMaxHealth(player.getMaxHealth() + recompensas.vida);
        player.setHealth(player.getMaxHealth()); // Recupera vida completa
        player.setMaxStamina(player.getMaxStamina() + recompensas.stamina);
        player.setStamina(player.getMaxStamina()); // Recupera stamina completa
        
        GameUtils.dramaticPrint("\n" + "=".repeat(40));
        GameUtils.dramaticPrint("ATRIBUTOS ATUALIZADOS!");
        GameUtils.dramaticPrint("=".repeat(40));
        
        GameUtils.dramaticPrint("Força: +" + recompensas.forca);
        GameUtils.dramaticPrint("Vida Máxima: +" + recompensas.vida);
        GameUtils.dramaticPrint("Stamina Máxima: +" + recompensas.stamina);
        
        GameUtils.dramaticPrint("\nSTATUS ATUAL:");
        GameUtils.dramaticPrint("Força: " + player.getStrength());
        GameUtils.dramaticPrint("Vida: " + player.getMaxHealth());
        GameUtils.dramaticPrint("Stamina: " + player.getMaxStamina());
        
        GameUtils.waitForEnter();
    }
    
    private static class Recompensas {
        public final int forca;
        public final int vida;
        public final int stamina;
        
        public Recompensas(int forca, int vida, int stamina) {
            this.forca = forca;
            this.vida = vida;
            this.stamina = stamina;
        }
    }
}