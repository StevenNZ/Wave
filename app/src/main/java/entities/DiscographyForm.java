package entities;

public class DiscographyForm {
    private String discographyFormID;
    private String discographyID;
    private String name;
    private String price;
    private Format hardMediaFormat;

    public DiscographyForm(String discographyFormID, String discographyID, String name, String price, Format hardMediaFormat) {
        this.discographyFormID = discographyFormID;
        this.discographyID = discographyID;
        this.name = name;
        this.price = price;
        this.hardMediaFormat = hardMediaFormat;
    }

    public String getDiscographyFormID() {
        return discographyFormID;
    }

    public String getDiscographyID() {
        return discographyID;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public Format getHardMediaFormat() {
        return hardMediaFormat;
    }
}
