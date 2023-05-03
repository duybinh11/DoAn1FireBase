package Model;

public class ItemCommon {
    private String id;
    private String name;
    private int cost;
    private String img;

    public ItemCommon() {
    }

    public ItemCommon(String id, String name, int cost, String img) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
