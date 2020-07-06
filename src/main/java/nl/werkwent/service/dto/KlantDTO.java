package nl.werkwent.service.dto;

public class KlantDTO {
    private String naam;
    private String adres;
    private String postcode;
    private String plaatsnaam;
    private String ontvangstAdres;
    private String ontvangstPostcode;
    private String ontvangstPlaatsnaam;

    public KlantDTO(String naam, String adres, String postcode, String plaatsnaam, String ontvangstAdres, String ontvangstPostcode, String ontvangstPlaatsnaam) {
        this.naam = naam;
        this.adres = adres;
        this.postcode = postcode;
        this.plaatsnaam = plaatsnaam;
        this.ontvangstAdres = ontvangstAdres;
        this.ontvangstPostcode = ontvangstPostcode;
        this.ontvangstPlaatsnaam = ontvangstPlaatsnaam;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPlaatsnaam() {
        return plaatsnaam;
    }

    public void setPlaatsnaam(String plaatsnaam) {
        this.plaatsnaam = plaatsnaam;
    }

    public String getOntvangstAdres() {
        return ontvangstAdres;
    }

    public void setOntvangstAdres(String ontvangstAdres) {
        this.ontvangstAdres = ontvangstAdres;
    }

    public String getOntvangstPostcode() {
        return ontvangstPostcode;
    }

    public void setOntvangstPostcode(String ontvangstPostcode) {
        this.ontvangstPostcode = ontvangstPostcode;
    }

    public String getOntvangstPlaatsnaam() {
        return ontvangstPlaatsnaam;
    }

    public void setOntvangstPlaatsnaam(String ontvangstPlaatsnaam) {
        this.ontvangstPlaatsnaam = ontvangstPlaatsnaam;
    }
}
