package studio.geek.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by think on 2017/2/1.
 */
public class Production {

    @JsonIgnore
    private int id;
    private String name;
    private String effectPicture;
    private String introduction;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEffectPicture() {
        return effectPicture;
    }

    public void setEffectPicture(String effectPicture) {
        this.effectPicture = effectPicture;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
