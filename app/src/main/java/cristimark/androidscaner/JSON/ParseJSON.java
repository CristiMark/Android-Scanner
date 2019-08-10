package cristimark.androidscaner.JSON;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ParseJSON {

    // JSON Node names
    public static final String TAG_Barcode = "Barcode";
    public static final String TAG_ID = "id";
    public static final String TAG_BarcodeType = "BarcodeType";
    public static final String TAG_DMR = "DMR";
    public static final String TAG_Deliver = "Deliver";
    public static final String TAG_PrintDate = "PrintDate";
    public static final String TAG_PrintUser = "PrintUser";
    public static final String TAG_InspScanUser = "InspScanUser";
    public static final String TAG_InspScanDate = "InspScanDate";
    public static final String TAG_DelivScanDate = "DelivScanDate";
    public static final String TAG_DelivScnaUser = "DelivScnaUser";

    public ArrayList<HashMap<String, String>> ParseJSON(String json) {
        if (json != null) {
            try {
// Hashmap for ListView
                ArrayList<HashMap<String, String>> studentList = new ArrayList<HashMap<String, String>>();
                JSONArray jsonObj = new JSONArray(json);

// Getting JSON Array node
              //  JSONArray students = jsonObj.getJSONArray(TAG_STUDENT_INFO);

// looping through All Students
                for (int i = 0; i < json.length(); i++) {
                    JSONObject c = jsonObj.getJSONObject(i);

                    String id = c.getString(TAG_ID);
                    String Barcode = c.getString(TAG_Barcode);
                    String BarcodeType = c.getString(TAG_BarcodeType);
                    String Deliver = c.getString(TAG_Deliver);
                    String DelivScanDate = c.getString(TAG_DelivScanDate);
                    String DelivScnaUser = c.getString(TAG_DelivScnaUser);
                    String InspScanDate = c.getString(TAG_InspScanDate);
                    String InspScanUser = c.getString(TAG_InspScanUser);
                    String PrintUser = c.getString(TAG_PrintUser);
                    String DMR = c.getString(TAG_DMR);
                    String PrintDate = c.getString(TAG_PrintDate);

//// Phone node is JSON Object
//                    JSONObject phone = c.getJSONObject(TAG_STUDENT_PHONE);
//                    String mobile = phone.getString(TAG_STUDENT_PHONE_MOBILE);
//                    String home = phone.getString(TAG_STUDENT_PHONE_HOME);

// tmp hashmap for single student
                    HashMap<String, String> student = new HashMap<String, String>();

// adding every child node to HashMap key => value
                   // student.put(TAG_ID, id);
                    student.put(TAG_Barcode, Barcode);
                    student.put(TAG_BarcodeType, BarcodeType);
                    student.put(TAG_Deliver, Deliver);
                    student.put(TAG_DelivScanDate, DelivScanDate);
                    student.put(TAG_DelivScnaUser, DelivScnaUser);
                    student.put(TAG_InspScanDate, InspScanDate);
                    student.put(TAG_InspScanUser, InspScanUser);
                    student.put(TAG_PrintUser, PrintUser);
                    student.put(TAG_DMR, DMR);
                    student.put(TAG_PrintDate, PrintDate);

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
}
