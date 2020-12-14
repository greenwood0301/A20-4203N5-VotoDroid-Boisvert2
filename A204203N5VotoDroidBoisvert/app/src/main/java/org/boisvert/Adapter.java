package org.boisvert;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.boisvert.DAO.BD;
import org.boisvert.Modele.VDQuestion;
import org.boisvert.Modele.VDVote;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    public List<VDQuestion> list;
    private isClick click;
    Context con;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView question;
        public Button stats;
        isClick isClick;
        public ViewHolder(LinearLayout v, isClick isClick){
            super(v);
            question = v.findViewById(R.id.question);
            itemView.setOnClickListener(this);
            stats = v.findViewById(R.id.tograph);
            this.isClick = isClick;

        }

        @Override
        public void onClick(View v) {
            isClick.onItemClick(getAdapterPosition());
        }
    }

    public Adapter(isClick aclick, Context c) {
        click = aclick;
        list  = new ArrayList<>();
        con = c;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        LinearLayout view = (LinearLayout) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.question_items, viewGroup, false);

        return new ViewHolder(view, click);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        VDQuestion q =list.get(position);
        viewHolder.question.setText(q.texte);
        viewHolder.stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tempnbvote = 0;
                for (VDVote V: BD.getInstance(con).dao().ALLV())
                {
                    if (V.Qid == position){tempnbvote++;}
                }

                if (tempnbvote != 0)
                {
                    Intent i = new Intent(con,ResultActivity.class);
                    i.putExtra("id", position);
                    con.startActivity(i);
                }else{Toast.makeText(con,"Aucun vote n'est disponible pour cette question. Veuiller réessayer plus tard...", Toast.LENGTH_SHORT).show();}
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface isClick
    {
        void onItemClick(Integer pos);
    }

}
