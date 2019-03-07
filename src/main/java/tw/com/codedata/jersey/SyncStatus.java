package tw.com.codedata.jersey;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/devices/f6d0c2493b51d43aa07f5233ad2541af/sync_status")
public class SyncStatus
{
    @PUT  
    public Response getResponse()
    {
        String result = "{\"code\": \"200\",\"message\": \"statusOK\", \"success\": true}";
        return Response.status(200).entity(result).build();
    }
}