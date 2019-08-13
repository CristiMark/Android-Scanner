package androidscaner;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidscaner.JSON.DownloadJson;
import androidscaner.Model.LBG_Scanner;
import androidscaner.dummy.DummyContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * An activity representing a list of 2DScanner. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link DScannerDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class DScannerListActivity extends AppCompatActivity {


    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    ArrayList<LBG_Scanner> scannedList;
    ProgressBar proBar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dscanner_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        proBar = (ProgressBar) findViewById(R.id.progressBar);
        proBar.setVisibility(View.INVISIBLE);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());


        scannedList = new ArrayList<LBG_Scanner>();
        if (findViewById(R.id.dscanner_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.dscanner_list);
        assert recyclerView != null;
        new DownloadJson(scannedList,proBar,DScannerListActivity.this,recyclerView).execute();

    }


}
