package androidscaner.Model;

import java.sql.Timestamp;
import java.util.Date;

public class LBG_Scanner {

    int id;
    String Barcode;
    String BarcodeType;
    boolean DMR;
    Timestamp PrintDate;
    String JobNumber;
    Timestamp DelivScanDate;
    String DelivScnaUser;
    boolean Deliver;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getBarcodeType() {
        return BarcodeType;
    }

    public void setBarcodeType(String barcodeType) {
        BarcodeType = barcodeType;
    }

    public boolean isDMR() {
        return DMR;
    }

    public void setDMR(boolean DMR) {
        this.DMR = DMR;
    }

    public Date getPrintDate() {
        return PrintDate;
    }

    public void setPrintDate(Timestamp printDate) {
        PrintDate = printDate;
    }

    public String getJobNumber() {
        return JobNumber;
    }

    public void setJobNumber(String jobNumber) {
        JobNumber = jobNumber;
    }

    public Date getDelivScanDate() {
        return DelivScanDate;
    }

    public void setDelivScanDate(Timestamp delivScanDate) {
        DelivScanDate = delivScanDate;
    }

    public String getDelivScnaUser() {
        return DelivScnaUser;
    }

    public void setDelivScnaUser(String delivScnaUser) {
        DelivScnaUser = delivScnaUser;
    }

    public boolean isDeliver() {
        return Deliver;
    }

    public void setDeliver(boolean deliver) {
        Deliver = deliver;
    }

    public Date getInspScanDate() {
        return InspScanDate;
    }

    public void setInspScanDate(Date inspScanDate) {
        InspScanDate = inspScanDate;
    }

    public String getInspScanUser() {
        return InspScanUser;
    }

    public void setInspScanUser(String inspScanUser) {
        InspScanUser = inspScanUser;
    }

    public String getPrintUser() {
        return PrintUser;
    }

    public void setPrintUser(String printUser) {
        PrintUser = printUser;
    }

    Date InspScanDate;
    String InspScanUser;
    String PrintUser;

}
