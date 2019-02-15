package test;

public class RsTransferRecords {
	private String account_id;

	private String transfer_amount;

	private String error_code;

	private String transfer_code;

	private String order_id;

	private String transfer_time;

	private String error_msg;

	private String account_name;

	private String fee;

	private String transfer_bank_code;

	private String transfer_name;

	private String backup_url;

	private String account_balance;

	private String transfer_account;

	private String job_id;
	
	private String status;

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getTransfer_amount() {
		return transfer_amount;
	}

	public void setTransfer_amount(String transfer_amount) {
		this.transfer_amount = transfer_amount;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getTransfer_code() {
		return transfer_code;
	}

	public void setTransfer_code(String transfer_code) {
		this.transfer_code = transfer_code;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getTransfer_time() {
		return transfer_time;
	}

	public void setTransfer_time(String transfer_time) {
		this.transfer_time = transfer_time;
	}

	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getTransfer_bank_code() {
		return transfer_bank_code;
	}

	public void setTransfer_bank_code(String transfer_bank_code) {
		this.transfer_bank_code = transfer_bank_code;
	}

	public String getTransfer_name() {
		return transfer_name;
	}

	public void setTransfer_name(String transfer_name) {
		this.transfer_name = transfer_name;
	}

	public String getBackup_url() {
		return backup_url;
	}

	public void setBackup_url(String backup_url) {
		this.backup_url = backup_url;
	}

	public String getAccount_balance() {
		return account_balance;
	}

	public void setAccount_balance(String account_balance) {
		this.account_balance = account_balance;
	}

	public String getTransfer_account() {
		return transfer_account;
	}

	public void setTransfer_account(String transfer_account) {
		this.transfer_account = transfer_account;
	}

	public String getJob_id() {
		return job_id;
	}

	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	
	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "ClassPojo [account_id = " + account_id + ", transfer_amount = " + transfer_amount + ", error_code = "
				+ error_code + ", transfer_code = " + transfer_code + ", order_id = " + order_id + ", transfer_time = "
				+ transfer_time + ", error_msg = " + error_msg + ", account_name = " + account_name + ", fee = " + fee
				+ ", transfer_bank_code = " + transfer_bank_code + ", transfer_name = " + transfer_name
				+ ", backup_url = " + backup_url + ", account_balance = " + account_balance + ", transfer_account = "
				+ transfer_account + ", job_id = " + job_id + "]";
	}
}
