package onedrive.resources

import com.codahale.metrics.annotation.Timed
import java.util.Optional
import java.util.concurrent.atomic.AtomicLong
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import onedrive.api.Ping

@Path("/OneDriveManagement")
@Produces(MediaType.APPLICATION_JSON)
class OneDriveManagementResource(private val template: String, private val defaultName: String) {
    private val counter: AtomicLong = AtomicLong()

    @GET
    @Timed
    @Path("sayHello")
    fun sayHello(@QueryParam("name") name: Optional<String?>): Ping {
        val value = String.format(template, name.orElse(defaultName))
        return Ping(counter.incrementAndGet(), value)
    }

}