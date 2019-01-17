package test;

public class RsSyncStatus {
	private String description;

	private String status_type;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus_type() {
		return status_type;
	}

	public void setStatus_type(String status_type) {
		this.status_type = status_type;
	}

	@Override
	public String toString() {
		return "ClassPojo [description = " + description + ", status_type = " + status_type + "]";
	}
}
