package test;

public class RsTransferRecords {
	  private String fee;

	    private String transferName;

	    private String transferAccount;

	    private String account_id;

	    private String transferAmount;

	    private String errorCode;

	    private String transferCode;

	    private String backup_url;

	    private String transferBankCode;

	    private String transferTime;

	    private String account_balance;

	    private String account_name;

	    public String getFee ()
	    {
	        return fee;
	    }

	    public void setFee (String fee)
	    {
	        this.fee = fee;
	    }

	    public String getTransferName ()
	    {
	        return transferName;
	    }

	    public void setTransferName (String transferName)
	    {
	        this.transferName = transferName;
	    }

	    public String getTransferAccount ()
	    {
	        return transferAccount;
	    }

	    public void setTransferAccount (String transferAccount)
	    {
	        this.transferAccount = transferAccount;
	    }

	    public String getAccount_id ()
	    {
	        return account_id;
	    }

	    public void setAccount_id (String account_id)
	    {
	        this.account_id = account_id;
	    }

	    public String getTransferAmount ()
	    {
	        return transferAmount;
	    }

	    public void setTransferAmount (String transferAmount)
	    {
	        this.transferAmount = transferAmount;
	    }

	    public String getErrorCode ()
	    {
	        return errorCode;
	    }

	    public void setErrorCode (String errorCode)
	    {
	        this.errorCode = errorCode;
	    }

	    public String getTransferCode ()
	    {
	        return transferCode;
	    }

	    public void setTransferCode (String transferCode)
	    {
	        this.transferCode = transferCode;
	    }

	    public String getBackup_url ()
	    {
	        return backup_url;
	    }

	    public void setBackup_url (String backup_url)
	    {
	        this.backup_url = backup_url;
	    }

	    public String getTransferBankCode ()
	    {
	        return transferBankCode;
	    }

	    public void setTransferBankCode (String transferBankCode)
	    {
	        this.transferBankCode = transferBankCode;
	    }

	    public String getTransferTime ()
	    {
	        return transferTime;
	    }

	    public void setTransferTime (String transferTime)
	    {
	        this.transferTime = transferTime;
	    }

	    public String getAccount_balance ()
	    {
	        return account_balance;
	    }

	    public void setAccount_balance (String account_balance)
	    {
	        this.account_balance = account_balance;
	    }

	    public String getAccount_name ()
	    {
	        return account_name;
	    }

	    public void setAccount_name (String account_name)
	    {
	        this.account_name = account_name;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [fee = "+fee+", transferName = "+transferName+", transferAccount = "+transferAccount+", account_id = "+account_id+", transferAmount = "+transferAmount+", errorCode = "+errorCode+", transferCode = "+transferCode+", backup_url = "+backup_url+", transferBankCode = "+transferBankCode+", transferTime = "+transferTime+", account_balance = "+account_balance+", account_name = "+account_name+"]";
	    }
}
