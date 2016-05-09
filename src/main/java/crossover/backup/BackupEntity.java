package crossover.backup;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "backups")
public class BackupEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) 
  int id;

  @Column(name="name", nullable = false)
  private String name;

  @Column(name="srcip", nullable = false)
  private String srcip;

  public BackupEntity() {}
  public BackupEntity(BackupVO backupVO) {
    name = backupVO.name;
    srcip = backupVO.srcip;
  }

  public int getId() { return id; }
  public void setId(int id) { this.id = id; }
 
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getSrcip() { return srcip; }
  public void setSrcip(String srcip) { this.srcip = srcip; }

  public String toString() {
    return String.format("id=%d name=%s srcip=%s", id, name, srcip);
  }

}

