package com.example.blitzerrechner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;


public class HistoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Vergehen> vergehensList = Collections.emptyList();

    private HistoryDao dao;
    private Context mContext;
    Intent intent;

    HistoryListAdapter(Context context){
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder
            (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_history_item, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder
            (@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView vergehensView = holder.itemView.findViewById(R.id.history_item);
        TextView bußgeldsView = holder.itemView.findViewById(R.id.gesamtBußgeld);
        int geschw = vergehensList.get(position).getVergehen();
        int geld = vergehensList.get(position).getBußgeld();
        int punkte = vergehensList.get(position).getPunkte();
        int fahrverbot = vergehensList.get(position).getFahrverbot();
        int id = vergehensList.get(position).getId();

        vergehensView.setText("Zu Schnell: " + geschw + " km/h\nBußgeld: " + geld + " €" +"\nPunkte: "+punkte+"\nFahrverbot: "+fahrverbot+" Monat(e)");

        dao = HistoryRoomDatabase.getDatabase(mContext).historyDao();

        holder.itemView.setOnClickListener((view) -> {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
            dialogBuilder.setMessage("Willst du diesen Eintrag wirklich löschen?");

            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch(which){
                        case DialogInterface.BUTTON_POSITIVE:
                            new DeleteTask().execute(vergehensList.get(position));
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            break;
                    }
                }
            };


            dialogBuilder.setPositiveButton("Ja", dialogClickListener);
            dialogBuilder.setNegativeButton("Nein", dialogClickListener);
            AlertDialog dialog = dialogBuilder.create();
            dialog.show();



        });
    }

    @Override
    public int getItemCount() {
        return vergehensList.size();
    }

    public void setVergehen(List<Vergehen> vergehen){
        this.vergehensList = vergehen;
        notifyDataSetChanged();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder{
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    class DeleteTask extends AsyncTask<Vergehen, Void, List<Vergehen>>{

        @Override
        protected List<Vergehen> doInBackground(Vergehen... vergehen) {
    /*        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder();
            dialogBuilder.setMessage("Wirklich löschen?");
            dialogBuilder.setPositiveButton("Ja klaro", null);

            AlertDialog dialog = dialogBuilder.create();
            dialog.show(); */

            dao.delete(vergehen[0]);
            return dao.getAll();

        }

        @Override
        protected void onPostExecute(List<Vergehen> vergehen) {
            super.onPostExecute(vergehen);
            setVergehen(vergehen);
        }
    }


}
