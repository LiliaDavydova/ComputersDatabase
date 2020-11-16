package ua.lili.model;

public class Computer {
    private String computerName;
    private String introducedDate;
    private String discontinuedDate;
    private String companyName;

    public Computer(String computerName, String introducedDate, String discontinuedDate, String companyName) {
        this.computerName = computerName;
        this.introducedDate = introducedDate;
        this.discontinuedDate = discontinuedDate;
        this.companyName = companyName;
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public String getIntroducedDate() {
        return introducedDate;
    }

    public void setIntroducedDate(String introducedDate) {
        this.introducedDate = introducedDate;
    }

    public String getDiscontinuedDate() {
        return discontinuedDate;
    }

    public void setDiscontinuedDate(String discontinuedDate) {
        this.discontinuedDate = discontinuedDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
