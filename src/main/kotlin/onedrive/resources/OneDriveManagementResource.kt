package onedrive.resources

import com.codahale.metrics.annotation.Timed
import java.util.Optional
import java.util.concurrent.atomic.AtomicLong
import javax.ws.rs.core.MediaType
import onedrive.api.Ping
import onedrive.storage.client.OneDriveClient
import javax.ws.rs.*

@Path("/OneDriveManagement")
@Produces(MediaType.APPLICATION_JSON)
class OneDriveManagementResource(private val template: String, private val defaultName: String) {
    private val counter: AtomicLong = AtomicLong()

    @GET
    @Timed
    @Path("sayHello")
    fun sayHello(@QueryParam("name") name: Optional<String?>): Ping {
        var value = String.format(template, name.orElse(defaultName))
        val onedriveclient = OneDriveClient()
        value += onedriveclient.getAccessToken()
        return Ping(counter.incrementAndGet(), value)
    }

    @POST
    @Timed
    @Path("link")
    fun link(@QueryParam("name") name: Optional<String?>): Ping {
        val value = String.format(template, name.orElse(defaultName))
        val onedriveclient = OneDriveClient()
        onedriveclient.getAccessToken()
        return Ping(counter.incrementAndGet(), value)
    }
}