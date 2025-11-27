package src.game;

import java.util.*;

public class TreeNode {
    private String nodeId;
    private String title;
    private Map<String, TreeNode> choices;
    
    public TreeNode(String nodeId, String title) {
        this.nodeId = nodeId;
        this.title = title;
        this.choices = new HashMap<>();
    }
    
    public void addChoice(String option, TreeNode nextNode) {
        this.choices.put(option, nextNode);
    }
    
    public String getNodeId() { return nodeId; }
    public String getTitle() { return title; }
    public Map<String, TreeNode> getChoices() { return choices; }
}