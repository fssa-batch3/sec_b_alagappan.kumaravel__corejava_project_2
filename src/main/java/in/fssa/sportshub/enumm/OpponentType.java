package in.fssa.sportshub.enumm;

public enum OpponentType {
	TO_TEAM(1),
    TO_AREA(2);

    private final int displayType;

    OpponentType(int displayName) {
        this.displayType = displayName;
    }

    public int getDisplayName() {
        return displayType;
    }
}
