package gr.tsitoumis.geasemi.gea.schemas;

public class GeaRunRequestBody {
    String url;
    String language;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
