import java.util.ArrayList;

public class BranchingDialogue extends Dialogue{
    private ArrayList<Dialogue> branchedOptions = new ArrayList<Dialogue>();

    public BranchingDialogue(String text, ArrayList<Dialogue> branchedOptions) {
        super(text);
        this.branchedOptions = branchedOptions;
    }

    public ArrayList<Dialogue> getBranchedOptions() {
        return branchedOptions;
    }

    public Dialogue getBranchedOption(int index) {
        if (index > branchedOptions.size() - 1) {
            return new Dialogue("N/A");
        }

        return branchedOptions.get(index);
    }
}
