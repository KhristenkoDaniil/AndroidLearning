package ua.dnigma.fragment2fragment_sendobject.model;


import com.google.gson.annotations.SerializedName;

/**
 * Created by Даниил on 05.02.2017.
 */

public class PhotoModel {
    @SerializedName("albumId")
    private int idAlbum;
    @SerializedName("id")
    private int idPicture;
    @SerializedName("title")
    private String discription;
    @SerializedName("url")
    private String urlFullPicture;
    @SerializedName("thumbnailUrl")
    private String urlPreviewPicture;

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public int getIdPicture() {
        return idPicture;
    }

    public void setIdPicture(int idPicture) {
        this.idPicture = idPicture;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getUrlFullPicture() {
        return urlFullPicture;
    }

    public void setUrlFullPicture(String urlFullPicture) {
        this.urlFullPicture = urlFullPicture;
    }

    public String getUrlPreviewPicture() {
        return urlPreviewPicture;
    }

    public void setUrlPreviewPicture(String urlPreviewPicture) {
        this.urlPreviewPicture = urlPreviewPicture;
    }
}
