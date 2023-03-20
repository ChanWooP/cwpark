package change.company.cwpark.data.reinterface;

public interface MenuInterface {
    Long getId();
    Long getParentNum();
    Integer getDepth();
    String getName();
    String getPath();
    String getMenuName();
}
