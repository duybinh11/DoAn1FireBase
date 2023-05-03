package Model;

import java.io.Serializable;

public class ItemCart implements Serializable {
    private String id;
    private int SLM;
    private String date;
    private String trangThai;
    private ItemNew itemNew;

    public ItemCart() {
    }

    public ItemCart(String id, int SLM, String date, String trangThai, ItemNew itemNew) {
        this.id = id;
        this.SLM = SLM;
        this.date = date;
        this.trangThai = trangThai;
        this.itemNew = itemNew;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSLM() {
        return SLM;
    }

    public void setSLM(int SLM) {
        this.SLM = SLM;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ItemNew getItemNew() {
        return itemNew;
    }

    public void setItemNew(ItemNew itemNew) {
        this.itemNew = itemNew;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
