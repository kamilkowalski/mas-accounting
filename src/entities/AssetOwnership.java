package entities;

import java.io.Serializable;
import java.util.Date;

public class AssetOwnership extends ObjectPlus implements Serializable {
    Date from;
    Date to;

    private Asset asset;
    private Company company;

    public AssetOwnership(Asset asset, Company company, Date from, Date to) {
        super();

        setAsset(asset);
        setCompany(company);
        setFrom(from);
        setTo(to);
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        if (this.asset == asset) return;

        if (this.asset != null) {
            this.asset.removeAssetOwnership(this);
        }

        this.asset = asset;
        this.asset.addAssetOwnership(this);
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        if (this.company == company) return;

        if (this.company != null) {
            this.company.removeAssetOwnership(this);
        }

        this.company = company;
        this.company.addAssetOwnership(this);
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Środek trwały: " + asset.getName() + "\n");
        sb.append("Firma: " + company.getFullName() + "\n");
        sb.append("Od: " + from + "\n");
        sb.append("Do: " + to + "\n");

        return sb.toString();
    }
}
