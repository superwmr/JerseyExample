package tw.com.codedata.jersey;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/devices/1234567890/sync_status")
public class SyncStatus
{
    @PUT  
    public Response getName()
    {
        String result = "{\"code\": \"200\",\"message\": \"statusOK\", \"success\": true}";
        return Response.status(200).entity(result).build();
    }
}