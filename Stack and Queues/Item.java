import java.util.Objects;

public class Item {
    private String type;
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item(String name, String type) {
        this.type = type;
        this.name = name;
        Objects.requireNonNull(Main.findstack(type)).push(this);
    }

    @Override
    public String toString() {
        return name;
    }
}
