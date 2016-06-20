package crossover.backup;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "configlog")
public class ConfiglogEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getSrcip() {
		return srcip;
	}
	public void setSrcip(String srcip) {
		this.srcip = srcip;
	}

	@Column(name="log", nullable = false)
	private String log;

	@Column(name="status", nullable = false)
	private int status;

	@ManyToOne(optional=false, cascade=CascadeType.PERSIST)
	@JoinColumn(name="configid",referencedColumnName="id")
	ConfigurationEntity configurationEntity;

	public ConfigurationEntity getConfigurationEntity() {
		return configurationEntity;
	}
	public void setConfigurationEntity(ConfigurationEntity configurationEntity) {
		this.configurationEntity = configurationEntity;
	}

	@Transient
	private String srcip;

	public ConfiglogEntity() {}
	public ConfiglogEntity(ConfiglogVO ConfiglogVO) {
		log = ConfiglogVO.log;
		srcip = ConfiglogVO.srcip;
		status = ConfiglogVO.status;
	}

	public String toString() {
		return String.format("id=%d log=%s srcip=%s configid=%d status=%d", id, log, srcip, configurationEntity.getId(), status);
	}

}

