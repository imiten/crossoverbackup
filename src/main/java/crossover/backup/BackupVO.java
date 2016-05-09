package crossover.backup;

import java.io.Serializable;

public class BackupVO implements Serializable {
  public int id;
  public String name;
  public String srcip;


  public String toString() {
    return String.format("name=%s srcip=%s", name, srcip);
  }
}
