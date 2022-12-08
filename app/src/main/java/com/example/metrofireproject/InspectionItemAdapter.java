package com.example.metrofireproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InspectionItemAdapter extends RecyclerView.Adapter<InspectionItemAdapter.MyViewHolder> {
    private Context context;
    private List<InspectionItem> myData;

    public InspectionItemAdapter(Context context, List<InspectionItem> mData) {
        this.context = context;
        this.myData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//viewholder is design pattern of adapter
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context); //creates layout file for view object
        view = mInflater.inflate(R.layout.cardview,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        InspectionItem currentInspection = myData.get(position);
        holder.bindItem(currentInspection);

        //on click listener
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inspectionDetails = new Intent(context, InspectionDetailActivity.class);

                //passing data to detail Activity
                inspectionDetails.putExtra("Record Number",currentInspection.getRecordNum());
                inspectionDetails.putExtra("Site",currentInspection.getSite());
                inspectionDetails.putExtra("Date",currentInspection.getInspectionDate());
                inspectionDetails.putExtra("Inspector",currentInspection.getInspector());
                inspectionDetails.putExtra("Image",currentInspection.getImageResource());
                inspectionDetails.putExtra("Time",currentInspection.getInspectionTime());
                context.startActivity(inspectionDetails); //starting inspector detail activity
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {//creating dialog to confirm deletion of a food
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(R.string.deleteDialog_header);
                builder.setMessage(R.string.deleteDialog_body);
                builder.setNegativeButton(R.string.deleteDialog_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton(R.string.deleteDialog_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myData.remove(holder.getBindingAdapterPosition());
                        InspectionItemAdapter.this.notifyItemRemoved(holder.getBindingAdapterPosition());
                    }
                });
                AlertDialog warning = builder.create();
                warning.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return myData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView recordNumber;
        TextView siteName;
        ImageView siteImage;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView); //initializing the cardview that contains food name, description, and image
            cardView = (CardView) itemView.findViewById(R.id.inspection_cardview);
            recordNumber = (TextView) itemView.findViewById(R.id.record_Num);
            siteImage = (ImageView) itemView.findViewById(R.id.image);
            siteName = (TextView)itemView.findViewById(R.id.site);

        }
        void bindItem(InspectionItem currentInspection){
            recordNumber.setText(currentInspection.getRecordNum());
            siteImage.setImageResource(currentInspection.getImageResource());
            siteName.setText(currentInspection.getSite());
        }

    }
}
