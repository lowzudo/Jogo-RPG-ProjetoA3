package src.training;

import src.entities.Player;
import src.utils.GameUtils;
import java.util.Scanner;
import java.util.Random;

public class TrainingManager {
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();
    
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
                Recompensas recompensas = cenaTreinoSistema(player);
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
                    Recompensas recompensas = cenaTreinoSistema(player);
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
    
    private Recompensas cenaTreinoSistema(Player player) {
        GameUtils.dramaticPrint("\n" + "=".repeat(50));
        GameUtils.dramaticPrint("TREINAMENTO DO SISTEMA");
        GameUtils.dramaticPrint("=".repeat(50));
        
        GameUtils.dramaticPrint("\nSistema: 'Bem-vindo ao treinamento, fracote. Complete esses desafios para receber suas recompensas.'");
        GameUtils.waitForEnter();
        
        int pontos = 0;
        int maxPontos = 0;
        
        // Desafio 1: Corrida contra o tempo
        GameUtils.dramaticPrint("\n" + "-".repeat(40));
        GameUtils.dramaticPrint("DESAFIO 1: CORRIDA CONTRA O TEMPO");
        GameUtils.dramaticPrint("-".repeat(40));
        pontos += minigameCorrida();
        maxPontos += 5;
        
        GameUtils.waitForEnter();
        
        // Desafio 2: Levantamento de pesos
        GameUtils.dramaticPrint("\n" + "-".repeat(40));
        GameUtils.dramaticPrint("DESAFIO 2: LEVANTAMENTO DE PESOS");
        GameUtils.dramaticPrint("-".repeat(40));
        pontos += minigameLevantamentoPesos();
        maxPontos += 5;
        
        GameUtils.waitForEnter();
        
        // Desafio 3: Meditação sob pressão
        GameUtils.dramaticPrint("\n" + "-".repeat(40));
        GameUtils.dramaticPrint("DESAFIO 3: MEDITAÇÃO SOB PRESSÃO");
        GameUtils.dramaticPrint("-".repeat(40));
        pontos += minigameMeditacao();
        maxPontos += 5;
        
        GameUtils.waitForEnter();
        
        // Desafio 4: Reação rápida
        GameUtils.dramaticPrint("\n" + "-".repeat(40));
        GameUtils.dramaticPrint("DESAFIO 4: TESTE DE REAÇÃO");
        GameUtils.dramaticPrint("-".repeat(40));
        pontos += minigameReacaoRapida();
        maxPontos += 5;
        
        GameUtils.waitForEnter();
        
        // Desafio 5: Memória do Sistema
        GameUtils.dramaticPrint("\n" + "-".repeat(40));
        GameUtils.dramaticPrint("DESAFIO 5: MEMÓRIA DO SISTEMA");
        GameUtils.dramaticPrint("-".repeat(40));
        pontos += minigameMemoria();
        maxPontos += 5;
        
        // Calcular recompensas baseadas no desempenho
        double percentual = (double) pontos / maxPontos;
        int bonusMultiplicador = 1;
        
        if (percentual >= 0.9) {
            GameUtils.dramaticPrint("\n\nSistema: 'IMPRESSIONANTE! Você dominou todos os desafios!'");
            bonusMultiplicador = 3;
        } else if (percentual >= 0.7) {
            GameUtils.dramaticPrint("\n\nSistema: 'Bom trabalho! Você mostrou potencial.'");
            bonusMultiplicador = 2;
        } else if (percentual >= 0.5) {
            GameUtils.dramaticPrint("\n\nSistema: 'Aceitável... pelo menos não foi completamente inútil.'");
            bonusMultiplicador = 1;
        } else {
            GameUtils.dramaticPrint("\n\nSistema: 'Patético... mas vou te dar outra chance.'");
            bonusMultiplicador = 1;
        }
        
        GameUtils.dramaticPrint("\nPontuação final: " + pontos + "/" + maxPontos);
        GameUtils.dramaticPrint("Multiplicador de recompensa: x" + bonusMultiplicador);
        
        GameUtils.waitForEnter();
        
        // Retorna as recompensas com bônus
        return new Recompensas(3 * bonusMultiplicador, 
                               15 * bonusMultiplicador, 
                               20 * bonusMultiplicador);
    }
    
    private int minigameCorrida() {
        GameUtils.dramaticPrint("\nSistema: 'Corra o mais rápido que puder! Digite 'correr' repetidamente!'");
        GameUtils.dramaticPrint("Você tem 5 segundos! PREPARE-SE...");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        GameUtils.dramaticPrint("\nAGORA! Digite 'correr' o mais rápido possível!");
        
        long startTime = System.currentTimeMillis();
        long endTime = startTime + 5000; // 5 segundos
        int contador = 0;
        
        while (System.currentTimeMillis() < endTime) {
            System.out.print("> ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("correr")) {
                contador++;
                GameUtils.dramaticPrint("Mais rápido! " + contador + " passos!");
            }
        }
        
        GameUtils.dramaticPrint("\nTEMPO ESGOTADO!");
        
        int pontos = Math.min(contador / 3, 5); // Máximo 5 pontos
        GameUtils.dramaticPrint("Você deu " + contador + " passos!");
        GameUtils.dramaticPrint("Pontos ganhos: " + pontos + "/5");
        
        return pontos;
    }
    
    private int minigameLevantamentoPesos() {
        GameUtils.dramaticPrint("\nSistema: 'Levante esses pesos digitando a sequência correta!'");
        GameUtils.dramaticPrint("Sequência: ↑ ↑ ↓ ↓ ← → ← →");
        GameUtils.dramaticPrint("Digite os comandos (use: cima, baixo, esquerda, direita)");
        
        String[] sequencia = {"cima", "cima", "baixo", "baixo", "esquerda", "direita", "esquerda", "direita"};
        int acertos = 0;
        
        for (int i = 0; i < sequencia.length; i++) {
            GameUtils.dramaticPrint("\nMovimento " + (i + 1) + "/8: ");
            System.out.print("> ");
            String input = scanner.nextLine().trim().toLowerCase();
            
            if (input.equals(sequencia[i])) {
                acertos++;
                GameUtils.dramaticPrint("Correto! Peso levantado!");
            } else {
                GameUtils.dramaticPrint("Errado! O peso caiu!");
            }
        }
        
        int pontos = (int) Math.ceil((acertos * 5) / 8.0);
        GameUtils.dramaticPrint("\nAcertos: " + acertos + "/8");
        GameUtils.dramaticPrint("Pontos ganhos: " + pontos + "/5");
        
        return pontos;
    }
    
    private int minigameMeditacao() {
        GameUtils.dramaticPrint("\nSistema: 'Medite e mantenha a calma. Não responda por 5 segundos.'");
        GameUtils.dramaticPrint("Pressione ENTER para começar a meditar...");
        scanner.nextLine();
        
        GameUtils.dramaticPrint("\nMeditando... (não digite nada por 5 segundos)");
        
        long startTime = System.currentTimeMillis();
        long endTime = startTime + 5000;
        
        Thread inputThread = new Thread(() -> {
            while (System.currentTimeMillis() < endTime) {
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                    GameUtils.dramaticPrint("Você quebrou a concentração!");
                }
            }
        });
        
        inputThread.start();
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        int pontos = 5; // Assume sucesso
        GameUtils.dramaticPrint("\nTempo acabou! Você manteve a concentração!");
        GameUtils.dramaticPrint("Pontos ganhos: 5/5");
        
        return pontos;
    }
    
    private int minigameReacaoRapida() {
        GameUtils.dramaticPrint("\nSistema: 'Quando aparecer a palavra AGORA, pressione ENTER o mais rápido possível!'");
        GameUtils.dramaticPrint("Preparado?");
        
        try {
            Thread.sleep(2000 + random.nextInt(3000)); // Espera aleatória
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        GameUtils.dramaticPrint("\nAGORA!");
        
        long startTime = System.currentTimeMillis();
        scanner.nextLine();
        long reactionTime = System.currentTimeMillis() - startTime;
        
        int pontos;
        if (reactionTime < 300) {
            pontos = 5;
            GameUtils.dramaticPrint("REAÇÃO SOBRE-HUMANA! " + reactionTime + "ms");
        } else if (reactionTime < 500) {
            pontos = 4;
            GameUtils.dramaticPrint("Ótima reação! " + reactionTime + "ms");
        } else if (reactionTime < 800) {
            pontos = 3;
            GameUtils.dramaticPrint("Boa reação! " + reactionTime + "ms");
        } else if (reactionTime < 1200) {
            pontos = 2;
            GameUtils.dramaticPrint("Reação lenta... " + reactionTime + "ms");
        } else {
            pontos = 1;
            GameUtils.dramaticPrint("Muito lento! " + reactionTime + "ms");
        }
        
        GameUtils.dramaticPrint("Pontos ganhos: " + pontos + "/5");
        return pontos;
    }
    
    private int minigameMemoria() {
        GameUtils.dramaticPrint("\nSistema: 'Memorize esta sequência de números!'");
        
        // Gerar sequência aleatória
        StringBuilder sequencia = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sequencia.append(random.nextInt(10));
        }
        
        GameUtils.dramaticPrint("Sequência: " + sequencia.toString());
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
        
        GameUtils.dramaticPrint("Agora digite a sequência que viu:");
        System.out.print("> ");
        String resposta = scanner.nextLine().trim();
        
        int acertos = 0;
        for (int i = 0; i < Math.min(sequencia.length(), resposta.length()); i++) {
            if (sequencia.charAt(i) == resposta.charAt(i)) {
                acertos++;
            }
        }
        
        int pontos = (int) Math.ceil((acertos * 5) / 6.0);
        GameUtils.dramaticPrint("\nSequência original: " + sequencia);
        GameUtils.dramaticPrint("Sua resposta: " + resposta);
        GameUtils.dramaticPrint("Acertos: " + acertos + "/6");
        GameUtils.dramaticPrint("Pontos ganhos: " + pontos + "/5");
        
        return pontos;
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