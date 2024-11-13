import org.jetbrains.annotations.NotNull;

public record Edge(@NotNull String source, @NotNull String destination) {
    public @NotNull Edge flip() {
        return new Edge(destination, source);
    }
    public @NotNull String toString() {
        return source + "->" + destination;
    }

}
