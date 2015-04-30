package edu.tamu.cs.success.smit.michelinchef;

/**
 * Created by Smit on 4/28/2015.
 */
public class RegionalCategory {

    private long region_id;
    private String region_name;

    public long getId() {
        return region_id;
    }

    public void setId(long id) {
        this.region_id = id;
    }

    public String getName() {
        return region_name;
    }

    public void setName(String name) {
        this.region_name = name;
    }
}
