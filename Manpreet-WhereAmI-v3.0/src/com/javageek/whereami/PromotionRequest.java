package com.javageek.whereami;

import java.io.IOException;
import java.io.InputStream;

import android.util.Log;

import com.google.api.client.googleapis.GoogleHeaders;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.json.JsonHttpParser;
import com.google.api.client.json.jackson.JacksonFactory;

public class PromotionRequest {

	// Create our transport.
	private static final HttpTransport transport = new ApacheHttpTransport();

	// Fill in the API key you want to use.
	private static final String API_KEY = "AIzaSyDAB0AMZ-34Bg27Pn3h0VptCa775zf9VFM";
	private static final String LOG_KEY = "GGPlace";

	// The different Places API endpoints.
	private static final String PLACES_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/search/xml?";
	private static final String PLACES_AUTOCOMPLETE_URL = "https://maps.googleapis.com/maps/api/place/autocomplete/json?";
	private static final String PLACES_DETAILS_URL = "https://maps.googleapis.com/maps/api/place/details/xml?";

	private static final boolean PRINT_AS_STRING = true;

	public String performSearch(double latitude, double longitude) throws Exception {
		try {
			GenericUrl reqUrl = new GenericUrl(PLACES_SEARCH_URL);
			reqUrl.put("key", API_KEY);
			reqUrl.put("location", latitude + "," + longitude);
			reqUrl.put("radius", 2000);
			reqUrl.put("sensor", "false");
			reqUrl.put("language", "en");
			//reqUrl.put("types", "store|shopping_mall");
			reqUrl.put("name", "Tesco");

			HttpRequestFactory httpRequestFactory = createRequestFactory(transport);
			HttpRequest request = httpRequestFactory.buildGetRequest(reqUrl);

			//PlacesList places = request.execute().parseAs(PlacesList.class);

			// test code
            System.out.println(request.execute().parseAsString());
    
			// InputStream is = request.execute().getContent();
			// StringBuilder sb = new StringBuilder();
			// String line;
			// BufferedReader reader = new BufferedReader(new
			// InputStreamReader(is, "UTF-8"));
			// while ((line = reader.readLine()) != null) {
			// sb.append(line).append("\n");
			// }
			// System.out.println(sb.toString());
			// Log.d(LOG_KEY, sb.toString());
//			String reference;
//			for (Place place : places.results) {
//				reference = place.reference;
//				performDetails(reference);
//			}
			

//			for (Place place : places.results) {
//				Log.v(LOG_KEY, place.name);
//
//			}
			return request.execute().parseAsString();

		} catch (HttpResponseException e) {
			Log.v(LOG_KEY, e.getResponse().parseAsString());
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}

	public void performDetails(String reference) throws Exception {
		try {
			System.out.println("Perform Place Detail....");
			System.out.println("-------------------");
			GenericUrl reqUrl = new GenericUrl(PLACES_DETAILS_URL);
			reqUrl.put("key", API_KEY);
			reqUrl.put("reference", reference);
			reqUrl.put("sensor", "false");

			HttpRequestFactory httpRequestFactory = createRequestFactory(transport);
			HttpRequest request = httpRequestFactory.buildGetRequest(reqUrl);

			if (PRINT_AS_STRING) {
				System.out.println(request.execute().parseAsString());
			} else {
				PlaceDetail place = request.execute().parseAs(PlaceDetail.class);
				System.out.println(place);
			}

		} catch (HttpResponseException e) {
			throw e;
		}
	}

	/*
	 * public void performAutoComplete() throws Exception { try {
	 * System.out.println("Perform Autocomplete ....");
	 * System.out.println("-------------------------");
	 * 
	 * HttpRequestFactory httpRequestFactory = createRequestFactory(transport);
	 * HttpRequest request = httpRequestFactory.buildGetRequest(new
	 * GenericUrl(PLACES_AUTOCOMPLETE_URL)); request.url.put("key", API_KEY);
	 * request.url.put("input", "mos"); request.url.put("location", latitude +
	 * "," + longitude); request.url.put("radius", 500);
	 * request.url.put("sensor", "false"); PlacesAutocompleteList places =
	 * request.execute().parseAs(PlacesAutocompleteList.class); if
	 * (PRINT_AS_STRING) {
	 * System.out.println(request.execute().parseAsString()); } else { for
	 * (PlaceAutoComplete place : places.predictions) {
	 * System.out.println(place); } }
	 * 
	 * } catch (HttpResponseException e) {
	 * System.err.println(e.response.parseAsString()); throw e; } }
	 */

	public static HttpRequestFactory createRequestFactory(final HttpTransport transport) {

		return transport.createRequestFactory(new HttpRequestInitializer() {
			public void initialize(HttpRequest request) {
				GoogleHeaders headers = new GoogleHeaders();
				headers.setApplicationName("Manpreet-WhereAmI");
				request.setHeaders(headers);
				JsonHttpParser parser = new JsonHttpParser(new JacksonFactory());
				request.addParser(parser);
			}
		});
	}

}
