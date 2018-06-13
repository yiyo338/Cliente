package odysseyLogic;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import com.neovisionaries.i18n.CountryCode;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class Spotify {
	private static String client = "cd1eab1c082d4cad9d492adcea7acd89";
	private static String secret_client = "ba6b673b51cd412aa5e4e03b42ed6788";
	private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
	          .setClientId(client)
	          .setClientSecret(secret_client)
	          .build();
	 private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
	          .build();
	 
	 public static void clientCredentials_Sync() {
		    try {
		      final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

		      // Set access token for further "spotifyApi" object usage
		      spotifyApi.setAccessToken(clientCredentials.getAccessToken());
		      System.out.println("Expires in: " + clientCredentials.getExpiresIn());
		    } catch (IOException | SpotifyWebApiException e) {
		      System.out.println("Error: " + e.getMessage());
		    }
		  }

	 public static void clientCredentials_Async() {
		 try {
			 final Future<ClientCredentials> clientCredentialsFuture = clientCredentialsRequest.executeAsync();

			 // ...

			 final ClientCredentials clientCredentials = clientCredentialsFuture.get();

			 // Set access token for further "spotifyApi" object usage
			 spotifyApi.setAccessToken(clientCredentials.getAccessToken());

			 System.out.println("Expires in: " + clientCredentials.getExpiresIn());
		 } catch (InterruptedException | ExecutionException e) {
			 System.out.println("Error: " + e.getCause().getMessage());
		 }
	 }
	 public static void searchTracks_Sync() {
		 SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks("Baby")
		          .market(CountryCode.SE)
		          .limit(10)
		          .offset(0)
		          .build();
		    try {
		      final Paging<Track> trackPaging = searchTracksRequest.execute();

		      System.out.println("Total: " + trackPaging.getTotal());
		      System.out.println(trackPaging.getItems()[2].getAlbum().getName());
		    } catch (IOException | SpotifyWebApiException e) {
		      System.out.println("Error: " + e.getMessage());
		    }
		  }
}
