package androidscaner.JSON;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import androidscaner.Model.LBG_Scanner;

public class ParseJSON {

    // JSON Node names
    public static final String TAG_Barcode = "Barcode";
    public static final String TAG_ID = "id";
    public static final String TAG_BarcodeType = "BarcodeType";
    public static final String TAG_JOBNUMBER = "JobNumber";
    public static final String TAG_DMR = "DMR";
    public static final String TAG_Deliver = "Deliver";
    public static final String TAG_PrintDate = "PrintDate";
    public static final String TAG_PrintUser = "PrintUser";
    public static final String TAG_InspScanUser = "InspScanUser";
    public static final String TAG_InspScanDate = "InspScanDate";
    public static final String TAG_DelivScanDate = "DelivScanDate";
    public static final String TAG_DelivScnaUser = "DelivScnaUser";

    public ArrayList<LBG_Scanner> ParseJSON(String json) {
        if (json != null) {
            try {
// Hashmap for ListView
                ArrayList<LBG_Scanner> studentList = new ArrayList<LBG_Scanner>();
                JSONArray jsonObj = new JSONArray(json);

// looping through All Students
                for (int i = 0; i < jsonObj.length(); i++) {
                    JSONObject c = jsonObj.getJSONObject(i);

                    int id = c.getInt(TAG_ID);
                    String Barcode = c.getString(TAG_Barcode);
                    String BarcodeType = c.getString(TAG_BarcodeType);
                    boolean Deliver = c.getBoolean(TAG_Deliver);
                    String DelivScanDate = c.getString(TAG_DelivScanDate);
                    String DelivScnaUser = c.getString(TAG_DelivScnaUser);
                    String InspScanDate = c.getString(TAG_InspScanDate);
                    String InspScanUser = c.getString(TAG_InspScanUser);
                    String PrintUser = c.getString(TAG_PrintUser);
                    boolean DMR = c.getBoolean(TAG_DMR);
                    String PrintDate = c.getString(TAG_PrintDate);
                    String JobNumber = c.getString(TAG_JOBNUMBER);


// tmp hashmap for single student

                    LBG_Scanner student = new LBG_Scanner();

//// adding every child node to HashMap key => value

                    student.setId(id);
                    student.setBarcode(Barcode);
                    student.setBarcodeType(BarcodeType);
                    student.setDeliver(Deliver);
                    student.setDelivScanDate(ConvertStringToTimeStamp(DelivScanDate));
                    student.setDelivScnaUser(DelivScnaUser);
                    student.setDMR(DMR);
                    student.setInspScanDate(ConvertStringToTimeStamp(InspScanDate));
                    student.setInspScanUser(InspScanUser);
                    student.setPrintUser(PrintUser);
                    student.setJobNumber(JobNumber);
                    student.setPrintDate(ConvertStringToTimeStamp(PrintDate));

// adding student to students list
                    studentList.add(student);
                }
                return studentList;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            Log.e("ServiceHandler", "No data received from HTTP request");
            return null;
        }
    }
    private Timestamp ConvertStringToTimeStamp(String date){

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd hh:mm:ss:SSS");
            Date date1=dateFormat.parse(date);
            Timestamp dateTimeStamp=new Timestamp(date1.getTime());
            return dateTimeStamp;
        }catch (ParseException e ){
            return new Timestamp(1);
        }

    }
}
