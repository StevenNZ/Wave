package entities;

public class DiscographyForm {
    private String discographyFormID;
    private String discographyID;
    private String name;
    private String price;
    private Format hardMediaFormat;

    /*
     * Constructor for DiscographyForm class
     * @param discographyFormID
     * @param discographyID
     * @param name
     * @param price
     * @param hardMediaFormat
     */
    public DiscographyForm(String discographyFormID, String discographyID, String name, String price, Format hardMediaFormat) {
        this.discographyFormID = discographyFormID;
        this.discographyID = discographyID;
        this.name = name;
        this.price = price;
        this.hardMediaFormat = hardMediaFormat;
    }

    /*
     * Getter for discographyFormID
     * @return discographyFormID
     */
    public String getDiscographyFormID() {
        return discographyFormID;
    }

    /*
     * Getter for discographyID
     * @return discographyID
     */
    public String getDiscographyID() {
        return discographyID;
    }

    /*
     * Getter for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /*
     * Getter for price
     * @return price
     */
    public String getPrice() {
        return price;
    }

    /*
     * Getter for hardMediaFormat
     * @return hardMediaFormat
     */
    public Format getHardMediaFormat() {
        return hardMediaFormat;
    }
}
