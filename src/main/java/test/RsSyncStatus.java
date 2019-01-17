package test;

public class RsSyncStatus {
	 private String description;

	    private String status_type;

	    private String account;

	    private String bank;

	    public String getDescription ()
	    {
	        return description;
	    }

	    public void setDescription (String description)
	    {
	        this.description = description;
	    }

	    public String getStatus_type ()
	    {
	        return status_type;
	    }

	    public void setStatus_type (String status_type)
	    {
	        this.status_type = status_type;
	    }

	    public String getAccount ()
	    {
	        return account;
	    }

	    public void setAccount (String account)
	    {
	        this.account = account;
	    }

	    public String getBank ()
	    {
	        return bank;
	    }

	    public void setBank (String bank)
	    {
	        this.bank = bank;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [description = "+description+", status_type = "+status_type+", account = "+account+", bank = "+bank+"]";
	    }
}
