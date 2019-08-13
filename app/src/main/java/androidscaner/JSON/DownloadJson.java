package androidscaner.JSON;

import androidscaner.Connections.WebRequest;
import androidscaner.DScannerListActivity;
import androidscaner.Model.LBG_Scanner;
import androidscaner.RecyclerViewAdapter;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class DownloadJson extends AsyncTask<Void, Void, String> {

    // URL to get contacts JSON
    //private String url = "http://10.0.2.2:90/LeibingerService/api/scanner";
    private String url = "http://10.9.2.113:90/LeibingerService/api/scanner";


   // ArrayList<HashMap<String, String>> scannedList;
    ArrayList<LBG_Scanner> scannedList;
    ProgressBar proBar;
    DScannerListActivity context;
    RecyclerView recycleview;


//    public DownloadJson(ArrayList<HashMap<String, String>> scannedList, ProgressBar proBar, Context context) {
//        this.scannedList = scannedList;
//        this.proBar = proBar;
//        this.context = context;
//    }
    public DownloadJson(ArrayList<LBG_Scanner> scannedList, ProgressBar proBar, DScannerListActivity context,RecyclerView recycleview) {
        this.scannedList = scannedList;
        this.proBar = proBar;
        this.context = context;
        this.recycleview = recycleview;
    }

    // Hashmap for ListView


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
// Showing progress loading dialog
//            proDialog = new ProgressDialog(context);
//            proDialog.setMessage("Please wait...");
//            proDialog.setCancelable(false);
            proBar.setVisibility(View.VISIBLE);
            proBar.animate();
        }

        @Override
        protected String doInBackground(Void... arg0) {
            //  return download();
            // }
// Creating service handler class instance
            WebRequest webreq = new WebRequest();

// Making a request to url and getting response
            String jsonStr = webreq.makeWebServiceCall(url, WebRequest.GETRequest);

            Log.d("Response: ", "> " + jsonStr);

            return jsonStr;
        }

        @Override
        protected void onPostExecute(String jsonData) {
            super.onPostExecute(jsonData);
// Dismiss the progress dialog
            if (proBar.isShown())
                proBar.setVisibility(View.GONE);

            //  pd.dismiss();
            if(jsonData.startsWith("Error"))
            {
                String error=jsonData;
                Toast.makeText(context.getApplicationContext(), error, Toast.LENGTH_SHORT).show();
            }else
            {
                //PARSER
              //  new JSONParser(c,jsonData,gv).execute();
                scannedList =  new ParseJSON().ParseJSON(jsonData);
                  recycleview.setAdapter( new RecyclerViewAdapter(context,scannedList,false));

            }
        }
/**
 * Updating received data from JSON into ListView
 * */

  //  public ArrayList<HashMap<String, String>> ReturnScannedListt() -> scannedList;


//            ListAdapter adapter = new SimpleAdapter(
//        context, scannedList,
//                    R.layout.list_item, new String[]{TAG_STUDENT_NAME, TAG_EMAIL,
//                    TAG_STUDENT_PHONE_MOBILE}, new int[]{R.id.name,
//                    R.id.email, R.id.mobile});
//
//            setListAdapter(adapter);
        //}


}
