package irmc.esprit.tn.irmc.model;

import android.widget.DatePicker;
import java.util.Calendar;


import java.io.Serializable;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Event  {
    @SerializedName("id_Ev")
    @Expose
    private int idEv;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("valide")
    @Expose
    private Boolean valide=false;
    @SerializedName("canceled")
    @Expose
    private Boolean canceled=true;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("nbPart")
    @Expose
    private Integer nbPart;
    @SerializedName("etat")
    @Expose
    private Boolean etat=false;
    @SerializedName("cat")
    @Expose
    private Category cat;
    @SerializedName("creationDate")
    @Expose
    private String creationDate;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("capacity")
    @Expose
    private Integer capacity;
    @SerializedName("user")
    @Expose
    private User user;

    /**
     * No args constructor for use in serialization
     *
     */
    public Event() {
    }

    /**
     *
     * @param nbPart
     * @param canceled
     * @param startDate
     * @param cat
     * @param image
     * @param endDate
     * @param idEv
     * @param etat
     * @param creationDate
     * @param title
     * @param description
     * @param capacity
     * @param user
     * @param valide
     */
    public Event(Integer idEv, String title, String description, Boolean valide, Boolean canceled, String image, Integer nbPart, Boolean etat, Category cat, String creationDate, String startDate, String endDate, Integer capacity, User user) {
        super();
        this.idEv = idEv;
        this.title = title;
        this.description = description;
        this.valide = valide;
        this.canceled = canceled;
        this.image = image;
        this.nbPart = nbPart;
        this.etat = etat;
        this.cat = cat;
        this.creationDate = creationDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.capacity = capacity;
        this.user = user;
    }

    public Event(String title, String description, Integer capacity) {
        this.title = title;
        this.description = description;
        this.capacity = capacity;
    }

    public Integer getIdEv() {
        return idEv;
    }

    public void setIdEv(Integer idEv) {
        this.idEv = idEv;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getValide() {
        return valide;
    }

    public void setValide(Boolean valide) {
        this.valide = valide;
    }

    public Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getNbPart() {
        return nbPart;
    }

    public void setNbPart(Integer nbPart) {
        this.nbPart = nbPart;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public Category getCat() {
        return cat;
    }

    public void setCat(Category cat) {
        this.cat = cat;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
