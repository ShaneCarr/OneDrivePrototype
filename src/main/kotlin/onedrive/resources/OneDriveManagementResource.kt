package onedrive.resources

import com.codahale.metrics.annotation.Timed
import java.util.Optional
import java.util.concurrent.atomic.AtomicLong
import javax.ws.rs.core.MediaType
import onedrive.api.Ping
import onedrive.storage.client.OneDriveClient
import javax.ws.rs.*
import org.json.JSONObject
import okhttp3.FormBody
//import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response


@Path("/OneDriveManagement")
@Produces(MediaType.APPLICATION_JSON)
class OneDriveManagementResource(private val template: String, private val defaultName: String) {
    private val counter: AtomicLong = AtomicLong()

    @GET
    @Timed
    @Path("sayHello")
    fun sayHello(@QueryParam("name") name: Optional<String?>): Ping {
        var value = String.format(template, name.orElse(defaultName))
        try {
            val onedriveclient = OneDriveClient()
            value += onedriveclient.getAccessToken()
            value += onedriveclient.getFolder()
        }catch (exception: Exception)
        {
            value += exception.toString()
        }

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

//    @POST
//    @Timed
//    @Path("createUploadSession")
//    fun createUploadSession(){
//        val request: Request = Builder()
//            .addHeader("Authorization", "Bearer " + returnAccessToken())
//            .url(
//                "https://graph.microsoft.com/v1.0/me/drive/root:/" + folder.getPath().toString() + "/" + file.getName()
//                    .toString() + ":/createUploadSession"
//            )
//            .post(RequestBody.create("{}", jsonMediaType))
//            .build()
//
//        val response: Response = httpClient.newCall(request).execute()
//        val parsedResponse = JSONObject(response.body().string())
//        response.close()
//
//        val uploadURL: String = parsedResponse.getString("uploadUrl")
//    }
}