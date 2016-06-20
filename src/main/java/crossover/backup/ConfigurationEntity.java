package crossover.backup;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "configuration")
public class ConfigurationEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7522325865090329018L;

	public ConfigurationEntity() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	int id;

	@Column(name="name", nullable = false)
	@NotNull(message = "error.ce.name.notnull")
	private String name;

	@Column(name="source_ip", nullable = false)
	@NotNull(message = "error.ce.sourceIP.notnull")
	private String sourceIP;

	@Column(name="source_path", nullable = false)
	@NotNull(message = "error.ce.sourcePath.notnull")
	private String sourcePath;

	@Column(name="source_user", nullable = false)
	@NotNull(message = "error.ce.sourceUser.notnull")
	private String sourceUser;

	@Column(name="source_password", nullable = false)
	@NotNull(message = "error.ce.sourcePassword.notnull")
	private String sourcePassword;


	@Column(name="destination_ip", nullable = false)
	@NotNull(message = "error.ce.destinationIP.notnull")
	private String destinationIP;

	@Column(name="destination_path", nullable = false)
	@NotNull(message = "error.ce.destinationPath.notnull")
	private String destinationPath;

	@Column(name="destination_user", nullable = false)
	@NotNull(message = "error.ce.destinationUser.notnull")
	private String destinationUser;

	@Column(name="destination_password", nullable = false)
	@NotNull(message = "error.ce.destinationPassword.notnull")
	private String destinationPassword;

	@Column(name="enabled")
	private Boolean enabled;

	@Column(name = "create_timestamp", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP) 
	private Date createTimestamp;

	@Column(name="update_timestamp")
	@Temporal(TemporalType.TIMESTAMP) 
	private Date updateTimestamp;

	@Column(name="create_user")
	private String createUser;

	@Column(name="update_user")
	private String updateUser;

	@Column(name="run_start_timestamp", nullable = false)
	@Temporal(TemporalType.TIMESTAMP) 
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd HH:mm")
	@NotNull(message = "error.ce.runStartTimestamp.notnull")
	private Date runStartTimestamp;

	@Column(name="run_end_timestamp", nullable = true)
	@Temporal(TemporalType.TIMESTAMP) 
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd HH:mm")
	@NotNull(message = "error.ce.runStartTimestamp.notnull")
	private Date runEndTimestamp;

	

	public Date getRunEndTimestamp() {
		return runEndTimestamp;
	}


	public void setRunEndTimestamp(Date runEndTimestamp) {
		this.runEndTimestamp = runEndTimestamp;
	}


	public ConfigurationEntity(String name, String sourceIP, String sourcePath, String sourceUser, String sourcePassword,
			String destinationIP, String destinationPath, String destinationUser, String destinationPassword, Date runStartTimestamp) {
		this.name = name;
		this.sourceIP = sourceIP;
		this.sourcePath = sourcePath;
		this.sourceUser = sourceUser;
		this.sourcePassword = sourcePassword;
		this.destinationIP = destinationIP;
		this.destinationPath = destinationPath;
		this.destinationUser = destinationUser;
		this.destinationPassword = destinationPassword;
		this.runStartTimestamp = runStartTimestamp;
		// TODO Auto-generated constructor stub
	}


	@PreUpdate
	void preUpdate() {
		updateTimestamp = new Date();
	}

	@PrePersist
	void preInsert() {
		createTimestamp = new Date();
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSourceIP() {
		return sourceIP;
	}


	public void setSourceIP(String sourceIP) {
		this.sourceIP = sourceIP;
	}


	public String getSourcePath() {
		return sourcePath;
	}


	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}


	public String getSourceUser() {
		return sourceUser;
	}


	public void setSourceUser(String sourceUser) {
		this.sourceUser = sourceUser;
	}


	public String getSourcePassword() {
		return sourcePassword;
	}


	public void setSourcePassword(String sourcePassword) {
		this.sourcePassword = sourcePassword;
	}


	public String getDestinationIP() {
		return destinationIP;
	}


	public void setDestinationIP(String destinationIP) {
		this.destinationIP = destinationIP;
	}


	public String getDestinationPath() {
		return destinationPath;
	}


	public void setDestinationPath(String destinationPath) {
		this.destinationPath = destinationPath;
	}


	public String getDestinationUser() {
		return destinationUser;
	}


	public void setDestinationUser(String destinationUser) {
		this.destinationUser = destinationUser;
	}


	public String getDestinationPassword() {
		return destinationPassword;
	}


	public void setDestinationPassword(String destinationPassword) {
		this.destinationPassword = destinationPassword;
	}


	public Boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}





	public Date getCreateTimestamp() {
		return createTimestamp;
	}


	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}


	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}


	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}


	public String getCreateUser() {
		return createUser;
	}


	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}


	public String getUpdateUser() {
		return updateUser;
	}


	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}


	public Date getRunStartTimestamp() {
		return runStartTimestamp;
	}


	public void setRunStartTimestamp(Date runStartTimestamp) {
		this.runStartTimestamp = runStartTimestamp;
	}



	public String toString() {
		return String.format("id=%d, name=%s, sourceIP=%s, sourcePath=%s, sourceUser=%s, sourcePassword=%s, destinationIP=%s," +
				"destinationPath=%s, destinationUser=%s, destinationPassword=%s, createUser=%s, createTimestamp=%s," + 
				"updateUser=%s, updateTimestamp=%s, runStartTimestamp=%s", 
				id, name, sourceIP, sourcePath, sourceUser, sourcePassword, destinationIP, 
				destinationPath, destinationUser, destinationPassword, createUser, createTimestamp, 
				updateUser, updateTimestamp, runStartTimestamp);
		
	}

}

