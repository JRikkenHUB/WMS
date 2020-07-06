package nl.werkwent.service.dto;

import java.util.Date;
import java.util.List;

public class WorkorderDTO {
    private String workorderNummer;
    private String bestelNummer;
    private KlantDTO klant;
    private Date ontvangstDatum;
    private Date leverDatum;
    private Date verzendDatum;
    private Date productieStart;
    private Date productieEinde;
    private List<ProductDTO> producten;

    public WorkorderDTO(String workorderNummer, String bestelNummer, KlantDTO klant, Date ontvangstDatum, Date leverDatum, Date verzendDatum, Date productieStart, Date productieEinde, List<ProductDTO> producten) {
        this.workorderNummer = workorderNummer;
        this.bestelNummer = bestelNummer;
        this.klant = klant;
        this.ontvangstDatum = ontvangstDatum;
        this.leverDatum = leverDatum;
        this.verzendDatum = verzendDatum;
        this.productieStart = productieStart;
        this.productieEinde = productieEinde;
        this.producten = producten;
    }

    public String getWorkorderNummer() {
        return workorderNummer;
    }

    public void setWorkorderNummer(String workorderNummer) {
        this.workorderNummer = workorderNummer;
    }

    public String getBestelNummer() {
        return bestelNummer;
    }

    public void setBestelNummer(String bestelNummer) {
        this.bestelNummer = bestelNummer;
    }

    public KlantDTO getKlant() {
        return klant;
    }

    public void setKlant(KlantDTO klant) {
        this.klant = klant;
    }

    public Date getOntvangstDatum() {
        return ontvangstDatum;
    }

    public void setOntvangstDatum(Date ontvangstDatum) {
        this.ontvangstDatum = ontvangstDatum;
    }

    public Date getLeverDatum() {
        return leverDatum;
    }

    public void setLeverDatum(Date leverDatum) {
        this.leverDatum = leverDatum;
    }

    public Date getVerzendDatum() {
        return verzendDatum;
    }

    public void setVerzendDatum(Date verzendDatum) {
        this.verzendDatum = verzendDatum;
    }

    public Date getProductieStart() {
        return productieStart;
    }

    public void setProductieStart(Date productieStart) {
        this.productieStart = productieStart;
    }

    public Date getProductieEinde() {
        return productieEinde;
    }

    public void setProductieEinde(Date productieEinde) {
        this.productieEinde = productieEinde;
    }

    public List<ProductDTO> getProducten() {
        return producten;
    }

    public void setProducten(List<ProductDTO> producten) {
        this.producten = producten;
    }
}
