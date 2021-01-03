package onedrive.storage.client

import java.io.InputStreamReader

import java.io.IOException
import java.io.OutputStream
import java.net.HttpURLConnection
import java.io.BufferedReader;
import java.io.File;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

class OneDriveClient {
    // todo stick in companion / static
    var clientid = "88a3f5b1-e2c9-4721-b686-a92295c77b14"
    var clientsecret = "aR-e.5G7uxSOx1O68-wEP6_2yRQo91h2xH"
    var baseUrl = "https://login.microsoft.com/common/oauth2/v2.0/"
    var authorizationCode = "client_credentials"
    var redirectUri = "http://localhost:8080/shcarr/login"
    var scope = "onedrive.readwrite"
    var readLine: String? = null
    var accesstoken: String? = null
    var responseCode = 0

    public fun getAccessToken() : String? {
        return getAccessToken(baseUrl, clientid, redirectUri, clientsecret);
    }
    public fun getAccessToken(
        baseUrl: String,
        clientid: String,
        redirectUri: String,
        clientsecret: String
    ): String? {
        val urlForAccessToken = URL(baseUrl + "token")
        var readLine: String? = null
        var responseCode = 0
        var accesstoken: String? = null
        val conection: HttpURLConnection = urlForAccessToken.openConnection() as HttpURLConnection
        System.out.println(conection)
        conection.setRequestMethod("POST")
        conection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        conection.setDoOutput(true)
        val str =
            "client_id=$clientid&redirect_uri=$redirectUri&client_secret=$clientsecret&grant_type=$authorizationCode"

        //send the above info in the body
        val outputInBytes = str.toByteArray(charset("UTF-8"))
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
            // print result
            val obj = JSONObject(response.toString())
            //System.out.println(obj)
            accesstoken = obj.get("access_token") as String?
//            println(accesstoken)
        } else {
            println("Bad Request")
        }
        return accesstoken
    }

}