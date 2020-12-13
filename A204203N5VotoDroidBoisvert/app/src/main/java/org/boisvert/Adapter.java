package org.boisvert;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.boisvert.Modele.VDQuestion;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    public List<VDQuestion> list;
    private isClick click;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView question;
        isClick isClick;
        public ViewHolder(LinearLayout v, isClick isClick){
            super(v);
            question = v.findViewById(R.id.question);
            itemView.setOnClickListener(this);
            this.isClick = isClick;
        }

        @Override
        public void onClick(View v) {
            isClick.onItemClick(getAdapterPosition());
        }
    }

    public Adapter(isClick aclick) {
        click = aclick;
        list  = new ArrayList<>();
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
