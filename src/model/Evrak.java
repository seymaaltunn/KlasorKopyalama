package model;

public class Evrak {

    private int id;
    private int evrakId;
    private String klasorAdi;

    public Evrak() {
    }

    public Evrak(int id, int evrakId, String klasorAdi) {
        this.id = id;
        this.evrakId = evrakId;
        this.klasorAdi = klasorAdi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvrakId() {
        return evrakId;
    }

    public void setEvrakId(int evrakId) {
        this.evrakId = evrakId;
    }

    public String getKlasorAdi() {
        return klasorAdi;
    }

    public void setKlasorAdi(String klasorAdi) {
        this.klasorAdi = klasorAdi;
    }

    @Override
    public String toString() {
        return "Evrak{" +
                "id=" + id +
                ", evrakId=" + evrakId +
                ", klasorAdi='" + klasorAdi + '\'' +
                '}';
    }
}