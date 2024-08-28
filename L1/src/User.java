import org.jetbrains.annotations.Nullable;

public class User {
    private String name;
    private Integer age;

    public User(String name, @Nullable Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    @Nullable
    public Integer getAge() {
        return age;
    }
}