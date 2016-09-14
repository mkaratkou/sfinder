package job.stafffinder.validation;

public final class Patterns {

    private Patterns() {
        // prevent instantiation
    }

    public static final String NAME_PATTERN = "(?=.*[a-z])(?=.*[A-Z]).{2,5}";

}
