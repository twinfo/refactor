package chapter01_1.Proto03;

import java.util.Objects;

//¾çÄ¿Êý¾Ý
public class Plays {
  private String type;
  private String name;

  public Plays(String type, String name) {
      this.type = type;
      this.name = name;
  }

  public String getType() {
      return type;
  }

  public void setType(String type) {
      this.type = type;
  }

  public String getName() {
      return name;
  }

  public void setName(String name) {
      this.name = name;
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Plays plays = (Plays) o;
      return Objects.equals(type, plays.type) &&
              Objects.equals(name, plays.name);
  }

  @Override
  public int hashCode() {
      return Objects.hash(type, name);
  }
}

