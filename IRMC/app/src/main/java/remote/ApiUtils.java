package irmc.esprit.tn.irmc.remote;

/**
 * Created by MBM info on 23/11/2017.
 */

public class ApiUtils {
    public static final String BASE_URL ="https://d05a3cc7.ngrok.io/IRMCJEE-web/IRMC/event/";
    public static ServiceEvent getServiceEvent(){
        return RetrofitClient.getClient(BASE_URL).create(ServiceEvent.class);
    }
}
//pour specifier l'url