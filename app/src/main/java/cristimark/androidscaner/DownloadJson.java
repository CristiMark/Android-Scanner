package cristimark.androidscaner;

import cristimark.androidscaner.JSON.*;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.ColorSpace;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;

public class DownloadJson extends AsyncTask<Void, Void, String> {

    // URL to get contacts JSON
    private String url = "http://127.0.0.1:90/LeibingerService/api/scanner";


    ArrayList<HashMap<String, String>> scannedList;
    ProgressBar proBar;
    Context context;


    public DownloadJson(ArrayList<HashMap<String, String>> scannedList, ProgressBar proBar, Context context) {
        this.scannedList = scannedList;
        this.proBar = proBar;
        this.context = context;
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
        protected String doInBackground(Void... arg0)
            {
                return download();
            }
//// Creating service handler class instance
//            WebRequest webreq = new WebRequest();
//
//// Making a request to url and getting response
//            String jsonStr = webreq.makeWebServiceCall(url, WebRequest.GETRequest);
//
//            Log.d("Response: ", "> " + jsonStr);
//            ParseJSON parseJson = new ParseJSON();
//            scannedList = parseJson.ParseJSON(jsonStr);
//
//            return null;


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
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }else
            {
                //PARSER
               // new JSONParser(c,jsonData,gv).execute();
            }
        }
/**
 * Updating received data from JSON into ListView
 * */

        public ArrayList<HashMap<String, String>> ReturnScannedList(){
        return scannedList;
    };


  //  public ArrayList<HashMap<String, String>> ReturnScannedListt() -> scannedList;


//            ListAdapter adapter = new SimpleAdapter(
//                    MainActivity.this, studentList,
//                    R.layout.list_item, new String[]{TAG_STUDENT_NAME, TAG_EMAIL,
//                    TAG_STUDENT_PHONE_MOBILE}, new int[]{R.id.name,
//                    R.id.email, R.id.mobile});
//
//            setListAdapter(adapter);
        //}

    //DOWNLOAD
    private String download()
    {
        Object connection= Connection.connect(url);
        if(connection.toString().startsWith("Error"))
        {
            return connection.toString();
        }

        try
        {
            HttpURLConnection con= (HttpURLConnection) connection;
            String var = con.getResponseMessage();
            if(con.getResponseCode()==con.HTTP_OK)
            {
                //GET INPUT FROM STREAM
                InputStream is=new BufferedInputStream(con.getInputStream());
                BufferedReader br=new BufferedReader(new InputStreamReader(is));

                String line;
                StringBuffer jsonData=new StringBuffer();

                //READ
                while ((line=br.readLine()) != null)
                {
                    jsonData.append(line+"\n");
                }

                //CLOSE RESOURCES
                br.close();
                is.close();

                //RETURN JSON
                return jsonData.toString();

            }else
            {
                return "Error "+con.getResponseMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error "+e.getMessage();

        }
    }

}
