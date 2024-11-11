import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;

public class ConversationHandler {
    private int dealerFavor;
    private int userChoice = 0;
    public ConversationHandler(int dealerFavor) {
        // constructor
        this.dealerFavor = dealerFavor;
    }

    public void explorePath(DialogueNode dialogueNode) {
        boolean end = false;
        boolean branch = false;
        DialogueNode currDialogueNode = dialogueNode;
        DialogueNode lastBranch = null;

        while (currDialogueNode.getNextDialogueNode() != null) {
            int paths = currDialogueNode.nodeChoiceCount();

            System.out.println(paths);

            if (paths == 0) {
                System.out.println("No next node");
                return;
            } else if (paths == 1) {
                // linear path
                // sayText();
                System.out.println(currDialogueNode.getText());
                currDialogueNode = currDialogueNode.getNextDialogueNode();
            } else {
                // > 1 or negative????
                // showDialogueOptions
                // for (each in dialoguenodes) { add to the ui }
                // wait here for a choice 0 being no choice yet, any number being a choice
                //while (userChoice == -1) {

                //}
                System.out.println(currDialogueNode.getText());
                for (DialogueNode each : currDialogueNode.getNextDialogueNodes()) {
                   System.out.println(each.getText());
                }

                currDialogueNode = currDialogueNode.getNextDialogueNode(0);
            }
        }
    }

    public void makeDialoguePath() {
        DialogueNode startNode = new DialogueNode("Hello!");
    }

    public static void main(String[] args) {
        ConversationHandler conversation = new ConversationHandler(0);

        ArrayList<String> dialogueLines = new ArrayList<String>();
        dialogueLines.add("How is it going?");
        dialogueLines.add("I'm doing great <3");

        // build dialogue
        DialogueNode startNode = new DialogueNode("Hello!");
        DialogueNode prevNode = startNode;

        for (String in : dialogueLines) {
            DialogueNode nextDialogueNode = new DialogueNode(in);
            prevNode.addNextDialogueNode(nextDialogueNode);
            prevNode = nextDialogueNode;
        }

        DialogueNode branchOption = new DialogueNode("option 1");
        branchOption.addNextDialogueNode(new DialogueNode("Option text"));
        DialogueNode branchNode = new DialogueNode("Options:");
        branchNode.addNextDialogueNode(branchOption);
        branchNode.addNextDialogueNode(new DialogueNode("option 2"));
        branchNode.addNextDialogueNode(new DialogueNode("option 3"));
        branchNode.addNextDialogueNode(new DialogueNode("option 4"));
        prevNode.addNextDialogueNode(branchNode);

        conversation.explorePath(startNode);
    }

    public void setUserChoice(int userChoice) {
        this.userChoice = userChoice;
    }
}
