package androidscaner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import androidscaner.Model.LBG_Scanner;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {

    private  DScannerListActivity mParentActivity;
    private  List<LBG_Scanner> mValues;
    private  boolean mTwoPane;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            LBG_Scanner item = (LBG_Scanner) view.getTag();
            if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putString(DScannerDetailFragment.ARG_ITEM_ID, String.valueOf(item.getId()));
                DScannerDetailFragment fragment = new DScannerDetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.dscanner_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, DScannerDetailActivity.class);
                intent.putExtra(DScannerDetailFragment.ARG_ITEM_ID, item.getId());

                context.startActivity(intent);
            }
        }
    };

    public RecyclerViewAdapter(DScannerListActivity parent,
                               List<LBG_Scanner> items,
                               boolean twoPane) {
        mValues = items;
        mParentActivity = parent;
        mTwoPane = twoPane;
    }
    //
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dscanner_list_content, parent, false);
        return new ViewHolder(view);
    }
    //
    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mlBarcode.setText(mValues.get(position).getBarcode());
        holder.mPrintDate.setText(mValues.get(position).getPrintDate().toString());

        holder.itemView.setTag(mValues.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }
    //
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
         TextView mlBarcode;
         EditText mPrintDate;

        ViewHolder(View view) {
            super(view);
            mlBarcode = (TextView) view.findViewById(R.id.txtBarcode);
            mPrintDate = (EditText) view.findViewById(R.id.txtPrintDate);
        }
    }
}
