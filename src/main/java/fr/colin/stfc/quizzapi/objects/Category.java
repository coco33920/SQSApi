package fr.colin.stfc.quizzapi.objects;

public class Category {

    private String uuid;
    private String name;

    public Category(String uuid, String name) {
        this.name = name;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "[name:" + name + ";uuid:" + uuid + "]";
    }

    public String getUuid() {
        return uuid;
    }
}
