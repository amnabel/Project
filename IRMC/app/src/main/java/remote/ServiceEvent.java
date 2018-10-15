package irmc.esprit.tn.irmc.remote;

import java.util.List;

import irmc.esprit.tn.irmc.model.Event;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Multipart;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceEvent {

    //@Headers("Accept: application/json")

    @GET(".")
    Call<List<Event> > getEvents();
    @GET("search/{term}")
    Call<List<Event> > getEventValide(@Path("term") boolean term);
    @GET("user/{id}")
    Call<List<Event> > getEventByUser(@Path("id") int id);
   // @Headers("Accept: application/json")

    @POST(".")
    Call<Event> addEvent(@Body Event user);
  //  @Headers("Accept: application/json")
    @PUT("update")
    Call<Event> updateEvent(@Body Event user);
   // @Headers("Accept: application/json")
    @DELETE
    Call<Event> deleteUser(@Query("id") int id);

}
