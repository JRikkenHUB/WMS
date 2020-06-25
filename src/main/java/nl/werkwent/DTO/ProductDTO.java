package nl.werkwent.DTO;

public class ProductDTO {
    private int artikelNummer;
    private String omschrijving;
    private String eenheid;
    private int aantal;

    public ProductDTO(int artikelNummer, String omschrijving, String eenheid, int aantal) {
        this.artikelNummer = artikelNummer;
        this.omschrijving = omschrijving;
        this.eenheid = eenheid;
        this.aantal = aantal;
    }

    public int getArtikelNummer() {
        return artikelNummer;
    }

    public void setArtikelNummer(int artikelNummer) {
        this.artikelNummer = artikelNummer;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getEenheid() {
        return eenheid;
    }

    public void setEenheid(String eenheid) {
        this.eenheid = eenheid;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }
}
