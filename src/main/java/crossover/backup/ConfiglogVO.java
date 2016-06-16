package crossover.backup;

import java.io.Serializable;

public class ConfiglogVO implements Serializable {
  
  public static enum Status {
	  STARTED,
	  PROGRESS,
	  DONE
  }
  
  public int id;
  public String log;
  public String srcip;
  public int configid;
  public int status;


  public String toString() {
    return String.format("id=%d log=%s srcip=%s configid=%d status=%d", id, log, srcip, configid, status);
  }
}
