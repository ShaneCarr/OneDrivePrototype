package onedrive.storage.client

import okhttp3.MediaType
import okhttp3.OkHttpClient
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL;
import org.json.JSONObject;
import java.io.*
import java.util.*
import java.util.concurrent.TimeUnit
import javax.ws.rs.*
import okhttp3.FormBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
//import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
/* 'https://
curl -X POST -H "Content-Type: application/x-www-form-urlencoded"
-d 'client_id=88a3f5b1-e2c9-4721-b686-a92295c77b14&
scope=https%3A%2F%2Fbrandyleeloo-my.sharepoint.com%2F.default&
client_secret=aR-e.5G7uxSOx1O68-wEP6_2yRQo91h2xH&
grant_type=client_credentials'login.microsoftonline.com/8d2ae17d-e352-493c-acaa-41720ca2ea9b/oauth2/v2.0/token'


 curl -d "grant_type=client_credentials&client_id=88a3f5b1-e2c9-4721-b686-a92295c77b14&client_secret=aR-e.5G7uxSOx1O68-wEP6_2yRQo91h2xH&audience=api%3A%2F%2F06b2a484-141c-42d3-9d73-32bec5910b06&scope=https%3A%2F%2Fbrandyleeloo-my.sharepoint.com%2F.default" -X POST "https://login.microsoftonline.com/8d2ae17d-e352-493c-acaa-41720ca2ea9b/oauth2/v2.0/token"
curl -d "grant_type=client_credentials&client_id=88a3f5b1-e2c9-4721-b686-a92295c77b14&client_secret=aR-e.5G7uxSOx1O68-wEP6_2yRQo91h2xH&audience=api%3A%2F%2F06b2a484-141c-42d3-9d73-32bec5910b06&scope=https%3A%2F%2Fbrandyleeloo-my.sharepoint.com%2F.default" -X POST "https://login.microsoftonline.com/8d2ae17d-e352-493c-acaa-41720ca2ea9b/oauth2/v2.0/token"
curl -d "grant_type=client_credentials&client_id=88a3f5b1-e2c9-4721-b686-a92295c77b14&client_secret=aR-e.5G7uxSOx1O68-wEP6_2yRQo91h2xH&audience=api%3A%2F%2F06b2a484-141c-42d3-9d73-32bec5910b06&scope=https%3A%2F%2Fgraph.microsoft.com%2F.default" -X POST "https://login.microsoftonline.com/8d2ae17d-e352-493c-acaa-41720ca2ea9b/oauth2/v2.0/token"
 */
class OneDriveClient {
    private val httpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(3, TimeUnit.MINUTES)
        .readTimeout(3, TimeUnit.MINUTES) //.addInterceptor(new HttpLogger())
        .build()
    private val zipMediaType: okhttp3.MediaType = "application/zip; charset=utf-8".toMediaTypeOrNull()!!
    private val jsonMediaType: okhttp3.MediaType = "application/json; charset=utf-8".toMediaTypeOrNull()!!

    // todo stick in companion / static
    var clientid = "88a3f5b1-e2c9-4721-b686-a92295c77b14"
    var clientsecret = "aR-e.5G7uxSOx1O68-wEP6_2yRQo91h2xH"
    var baseUrl = "https://login.microsoftonline.com/8d2ae17d-e352-493c-acaa-41720ca2ea9b/oauth2/v2.0/token"
    var grantType = "client_credentials"
    var redirectUri = "http://localhost:8080/shcarr/login"
    var scope = "https%3A%2F%2Fbrandyleeloo-my.sharepoint.com%2F.default"
    var readLine: String? = null
    var accesstoken: String? = null
    var responseCode = 0
    val folderName = "onedrivetest"
     fun getAccessToken() : String? {
        return getAccessToken(baseUrl, clientid, redirectUri, clientsecret)
    }
    public fun getAccessToken(
        baseUrl: String,
        clientid: String,
        redirectUri: String,
        clientsecret: String
    ): String? {
        val urlForAccessToken = URL(baseUrl)
        var readLine: String? = null
        var responseCode = 0
        var accesstoken: String? = null
        val conection: HttpURLConnection = urlForAccessToken.openConnection() as HttpURLConnection
        System.out.println(conection)
        conection.setRequestMethod("POST")
        conection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        conection.setDoOutput(true)
        val queryString =
            "client_id=$clientid&redirect_uri=$redirectUri&client_secret=$clientsecret&grant_type=$grantType&scope=$scope"

        //send the above info in the body
        val outputInBytes = queryString.toByteArray(charset("UTF-8"))
        val os: OutputStream = conection.getOutputStream()
        os.write(outputInBytes)
        os.close()
        responseCode = conection.getResponseCode()
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val reader = BufferedReader(
                InputStreamReader(conection.getInputStream())
            )
            val response = StringBuffer()
            while (reader.readLine().also { readLine = it } != null) {
                response.append(readLine)
            }
            reader.close()
            val obj = JSONObject(response.toString())
            accesstoken = obj.get("access_token") as String?
        } else {
            println("Bad Request")
        }
        return accesstoken
    }


    /**
     * Uploads the specified file to the authenticated user's OneDrive inside a folder for the specified file type
     * @param file the file
     * @param type the type of file (ex. plugins, world)
     */
    fun uploadFile(file: File, type: String) {

    }

    /**
     * Returns the folder in the specified parent folder of the authenticated user's OneDrive with the specified name
     * @param name the name of the folder
     * @param parent the parent folder
     * @return the folder or `null`
     */
     fun getFolder() : String {
        try {

           // https://graph.microsoft.com/v1.0/me/drive/root:/

// https://graph.microsoft.com/v1.0/me/drive/root:/onedrivetest
            val request: Request = Request.Builder()
                .addHeader("Authorization", "Bearer " + this.getAccessToken())
              //  .url("https://brandyleeloo-my.sharepoint.com/personal/shanepcarr_brandyleeloo_onmicrosoft_com/_api/v2.0/drive/root:/$folderName")
                .url("https://graph.microsoft.com/v1.0/me/drive/root:/onedrivetest") // attempt the graph endpoint having issues getting scopes for onedrive direct
                .build()

            var parsedResponse: JSONObject
           httpClient.newCall(request).execute().use {
                 parsedResponse = JSONObject(it.body.toString())
            }


            //val jsonArray: JSONArray = parsedResponse.getJSONArray("value")
            //val folderName = jsonArray.getJSONObject(i).getString("name")
            return parsedResponse.get("id").toString()

        } catch (exception: Exception) {
        }
        return ""
    }


}






/*
create  folder

post: https://graph.microsoft.com/v1.0/me/drive/root/children
{
"name": "onedrivetsta",
"folder": {}
}

get folder

get: https://graph.microsoft.com/v1.0/me/drive/root:/onedrivetest
{
"@odata.context": "https://graph.microsoft.com/v1.0/$metadata#users('483fcfa8-4ec1-423c-b5e1-aeb998381099')/drive/root/$entity",
"createdDateTime": "2021-01-04T00:10:58Z",
"eTag": "\"{4B91D203-293A-44B2-B677-5925FA2B99D9},1\"",
"id": "01BYNJA3ID2KIUWORJWJCLM52ZEX5CXGOZ",
"lastModifiedDateTime": "2021-01-04T00:10:58Z",
"name": "onedrivetest",
"webUrl": "https://brandyleeloo-my.sharepoint.com/personal/shanepcarr_brandyleeloo_onmicrosoft_com/Documents/onedrivetest",
"cTag": "\"c:{4B91D203-293A-44B2-B677-5925FA2B99D9},0\"",
"size": 0,
"createdBy": {
    "application": {
        "id": "de8bc8b5-d9f9-48b1-a8ad-b748da725064",
        "displayName": "Graph explorer (official site)"
    },
    "user": {
        "email": "shanepcarr@brandyleeloo.onmicrosoft.com",
        "id": "483fcfa8-4ec1-423c-b5e1-aeb998381099",
        "displayName": "shane carr"
    }
},
"lastModifiedBy": {
    "application": {
        "id": "de8bc8b5-d9f9-48b1-a8ad-b748da725064",
        "displayName": "Graph explorer (official site)"
    },
    "user": {
        "email": "shanepcarr@brandyleeloo.onmicrosoft.com",
        "id": "483fcfa8-4ec1-423c-b5e1-aeb998381099",
        "displayName": "shane carr"
    }
},
"parentReference": {
    "driveId": "b!Yx-fSVBM-Ui01NZ9qkTWCo_jT0sk7MJItYZfs05yE3kYxJ8vlwcCQq3pQqquaaYr",
    "driveType": "business",
    "id": "01BYNJA3N6Y2GOVW7725BZO354PWSELRRZ",
    "path": "/drive/root:"
},


"fileSystemInfo": {
    "createdDateTime": "2021-01-04T00:10:58Z",
    "lastModifiedDateTime": "2021-01-04T00:10:58Z"
},
"folder": {
    "childCount": 0
}
}
 */