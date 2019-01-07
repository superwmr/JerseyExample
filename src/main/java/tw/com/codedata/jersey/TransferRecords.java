package tw.com.codedata.jersey;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/transfer_records")
public class TransferRecords
{
    @POST  
//    @Path("/{param}")
//    public Response getName(@PathParam("param") String name)
    public Response getName()
    {
        String result = "{\"code\": \"200\",\"message\": \"statusOK\", \"success\": true}";
        return Response.status(200).entity(result).build();
    }
}