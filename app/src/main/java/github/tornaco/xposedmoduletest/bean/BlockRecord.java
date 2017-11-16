package github.tornaco.xposedmoduletest.bean;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "BLOCK_RECORD".
 */
public class BlockRecord {

    private Integer id;
    private String pkgName;
    private String appName;
    private Long timeWhen;
    private Long howManyTimes;
    private Boolean allow;
    private String description;
    private String why;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public BlockRecord() {
    }

    public BlockRecord(Integer id) {
        this.id = id;
    }

    public BlockRecord(Integer id, String pkgName, String appName, Long timeWhen, Long howManyTimes, Boolean allow, String description, String why) {
        this.id = id;
        this.pkgName = pkgName;
        this.appName = appName;
        this.timeWhen = timeWhen;
        this.howManyTimes = howManyTimes;
        this.allow = allow;
        this.description = description;
        this.why = why;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Long getTimeWhen() {
        return timeWhen;
    }

    public void setTimeWhen(Long timeWhen) {
        this.timeWhen = timeWhen;
    }

    public Long getHowManyTimes() {
        return howManyTimes;
    }

    public void setHowManyTimes(Long howManyTimes) {
        this.howManyTimes = howManyTimes;
    }

    public Boolean getAllow() {
        return allow;
    }

    public void setAllow(Boolean allow) {
        this.allow = allow;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }

    // KEEP METHODS - put your custom methods here

    @Override
    public String toString() {
        return "BlockRecord2{" +
                ", appName='" + appName + '\'' +
                ", allow=" + allow +
                ", description='" + description + '\'' +
                ", why='" + why + '\'' +
                '}';
    }


    // KEEP METHODS END

}
