import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;

public class DialogueLoader {
    private BufferedReader dialogueTree;

    private ArrayList<String> Introductions = new ArrayList<String>();
    private ArrayList<String> LinearDialogue = new ArrayList<String>();
    private ArrayList<String> BranchingDialogue = new ArrayList<String>();

    public DialogueLoader(BufferedReader dialogueTree) {
        this.dialogueTree = dialogueTree;

        // handle reading line by line to fill values

    }
}
