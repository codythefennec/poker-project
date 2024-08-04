public class ConversationHandler {
    private int dealerFavor;
    private ConversationState currConversationState;
    public ConversationHandler(int dealerFavor) {
        // constructor
        this.dealerFavor = dealerFavor;
        currConversationState = ConversationState.Introductions;
    }

    public enum ConversationState {
        Introductions,
        LinearDialogue,
        BranchingDialogue
    }

    public void updateState(ConversationState newState) {
        currConversationState = newState;
    }

    public String getIntroductionText() {
        return "";
    }

    public String getLinearDialogue() {
        return "";
    }

    public String getBranchingDialogue() {
        return "";
    }
}
