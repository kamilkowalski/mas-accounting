package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Asset extends ObjectPlus implements Serializable {

    String name;
    double value;
    Date creationDate;
    int depreciation;

    private Set<AssetOwnership> assetOwnerships = new HashSet<>();

    public Asset(String name, double value, Date creationDate, int depreciation) {
        super();

        setName(name);
        setValue(value);
        setCreationDate(creationDate);
        setDepreciation(depreciation);
    }

    public Asset(String name, double value, int depreciation) {
        this(name, value, new Date(), depreciation);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(int depreciation) {
        this.depreciation = depreciation;
    }

    public void addAssetOwnership(AssetOwnership ownership) {
        if (!assetOwnerships.contains(ownership)) {
            assetOwnerships.add(ownership);
            ownership.setAsset(this);
        }
    }

    public void removeAssetOwnership(AssetOwnership ownership) {
        if (assetOwnerships.contains(ownership)) {
            assetOwnerships.remove(ownership);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Dane dla środka trwałego " + getName() + ": \n");

        for(AssetOwnership ao : assetOwnerships) {
            sb.append(ao.toString());
        }

        return sb.toString();
    }
}

