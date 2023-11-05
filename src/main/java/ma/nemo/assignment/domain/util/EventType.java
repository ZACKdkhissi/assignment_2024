package ma.nemo.assignment.domain.util;

public enum EventType {

  APPROVISIONNEMENT("Approvisionnement"),
  SALE("sale");

  private String type;

  EventType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
