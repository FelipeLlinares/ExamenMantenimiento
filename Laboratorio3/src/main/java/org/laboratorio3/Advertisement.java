package org.laboratorio3;

import java.util.Objects;

/**
 @author Antonio J. Nebro
 */
public class Advertisement {
  public String title;
  public String text;
  public String advertiser;

  public Advertisement(String title, String text, String advertiser) {
    this.title = title ;
    this.text = text;
    this.advertiser = advertiser;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Advertisement that = (Advertisement) o;
    return Objects.equals(title, that.title) && Objects.equals(advertiser, that.advertiser);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, advertiser);
  }
}
