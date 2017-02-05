package ua.dnigma.fragment2fragment_sendobject.model;

/**
 * Created by Даниил on 29.12.2016.
 */

public class MovieModel {
    private int id;
    private String url;
    private String titlle;
    private String dataStart;
    private String dataEnd;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDataStart() {
        return dataStart;
    }

    public void setDataStart(String dataStart) {
        this.dataStart = dataStart;
    }

    public String getDataEnd() {
        return dataEnd;
    }

    public void setDataEnd(String dataEnd) {
        this.dataEnd = dataEnd;
    }

    public String getTitlle() {
        return titlle;
    }

    public void setTitlle(String titlle) {
        this.titlle = titlle;
    }
}
