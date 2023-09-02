package in.fssa.sportshub.model;

public enum OpponentType {
	TO_TEAM("1"),
    TO_AREA("2");

    private final String displayType;

    OpponentType(String displayName) {
        this.displayType = displayName;
    }

    public String getDisplayName() {
        return displayType;
    }
}
