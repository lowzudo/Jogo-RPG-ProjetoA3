package src.game;

public class GameTree {
    private TreeNode root;
    private TreeNode currentNode;
    
    public GameTree() {
        buildTree();
    }
    
    private void buildTree() {
        this.root = new TreeNode("begin", "SWORD ART ONLINE - INÍCIO DA JORNADA");
        TreeNode desafio = new TreeNode("desafio", "DESAFIO INICIAL");
        TreeNode part2 = new TreeNode("part2", "CAPÍTULO 2: A DUNGEON DOS GOBLINS");
        TreeNode batalhaGoblins = new TreeNode("batalha_goblins", "BATALHA: GOBLINS");
        TreeNode part3 = new TreeNode("part3", "CAPÍTULO 3: O TEMPLO DOS DEUSES");
        TreeNode gameOver = new TreeNode("game_over", "GAME OVER");
        TreeNode sistema = new TreeNode("sistema", "JOGADOR-SISTEMA");
        TreeNode treinamento = new TreeNode("treinamento", "TREINAMENTO DO SISTEMA");
        TreeNode part5 = new TreeNode("part5", "CAPÍTULO 5: O CONFRONTO FINAL");
        TreeNode vitoria = new TreeNode("vitoria", "VITÓRIA FINAL");
        TreeNode part6 = new TreeNode("part6", "CAPÍTULO 6: O DESPERTAR DIVINO");
        TreeNode batalhaDeuses = new TreeNode("batalha_deuses", "BATALHA FINAL: CONTRA OS DEUSES");
        
        root.addChoice("", desafio);
        
        desafio.addChoice("", part2);        
        desafio.addChoice("game_over", gameOver);
        
        part2.addChoice("continuar", batalhaGoblins);
        
        batalhaGoblins.addChoice("continuar", part3);
        batalhaGoblins.addChoice("game_over", gameOver);
        
        part3.addChoice("continuar", sistema);
        part3.addChoice("game_over", gameOver);

        sistema.addChoice("continuar", treinamento);
        
        treinamento.addChoice("sucesso", part5);    
        treinamento.addChoice("falha", gameOver);   
        
        part5.addChoice("continuar", part6);
        
        part6.addChoice("continuar", batalhaDeuses);
        
        batalhaDeuses.addChoice("vitoria", vitoria);
        batalhaDeuses.addChoice("game_over", gameOver);

        this.currentNode = root;
    }
    
    public TreeNode getCurrentNode() { return currentNode; }
    public TreeNode getRoot() { return root; }
    
    public void moveTo(String choice) {
        TreeNode nextNode = currentNode.getChoices().get(choice);
        if (nextNode != null) {
            currentNode = nextNode;
        }
    }
    
    public boolean hasChoices() {
        return !currentNode.getChoices().isEmpty();
    }
    
    public boolean isFinalNode() {
        String nodeId = currentNode.getNodeId();
        return nodeId.equals("game_over") || nodeId.equals("vitoria");
    }
    
    public void reset() {
        this.currentNode = root;
    }
}