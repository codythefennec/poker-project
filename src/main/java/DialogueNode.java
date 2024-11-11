import java.lang.reflect.Array;
import java.util.ArrayList;

public class DialogueNode {
    // contains what the dealer will say / for branched nodes, what the choice will say
    private String text;

    // next node link
    private ArrayList<DialogueNode> nextDialogueNodes = new ArrayList<DialogueNode>();

    // prev node link for doubly linked list
    private DialogueNode prevDialogueNode;

    public DialogueNode(String text) {
        this.text = text;
    }

    public void addNextDialogueNode(DialogueNode node) {
        nextDialogueNodes.add(node);
    }

    public int nodeChoiceCount() {
        return nextDialogueNodes.size();
    }

    public DialogueNode getNextDialogueNode() {
        if (nextDialogueNodes.isEmpty()) {
            return null;
        } else {
            return nextDialogueNodes.get(0);
        }
    }

    public DialogueNode getNextDialogueNode(int index) {
        if (index > nextDialogueNodes.size() - 1) {
            return null;
        }

        return nextDialogueNodes.get(index);
    }

    public DialogueNode getPrevDialogueNode() {
        return prevDialogueNode;
    }

    public String getText() {
        return text;
    }

    public ArrayList<DialogueNode> getNextDialogueNodes() {
        return nextDialogueNodes;
    }
}