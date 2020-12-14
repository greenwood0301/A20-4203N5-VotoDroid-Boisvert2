package org.boisvert;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.boisvert.DAO.BD;
import org.boisvert.Modele.VDQuestion;
import org.boisvert.Modele.VDVote;
import org.boisvert.databinding.ActivityListeBinding;

public class ListeActivity extends AppCompatActivity implements Adapter.isClick {
    Adapter adapter;
    BD local;
    private ActivityListeBinding binding;
    int fix = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        this.startRecyclerView();
        this.FillRecycler();


        binding.pose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListeActivity.this, CreateActivity.class);
                startActivity(i);
            }
        });
    }

    private void FillRecycler()
    {
       local = BD.getInstance(this);

        adapter.list.addAll(BD.getInstance(this).dao().ALLQ());
        adapter.notifyDataSetChanged();

    }

    private void startRecyclerView()
    {
        RecyclerView rec = binding.recyclerview;
        rec.setHasFixedSize(true);

        LinearLayoutManager LayoutManager = new LinearLayoutManager(this);
        rec.setLayoutManager(LayoutManager);

        adapter = new Adapter(this, this);
        rec.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Integer pos) {

        Intent i = new Intent(ListeActivity.this,VoteActivity.class);
        i.putExtra("id", pos);
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.SuppVotes) {
            local.dao().deleteV();
            return true;
        }
        if (id == R.id.SuppQues) {
            for (VDQuestion q:local.dao().ALLQ())
            {
                q.nbVote = 0;
                for (VDVote V:local.dao().ALLV())
                {
                    if (q.id == V.Qid)
                    {
                        q.nbVote++;
                    }
                }
                if (q.nbVote == 0)
                {
                    local.dao().deleteById(q.id);
                }
            }
            adapter.notifyDataSetChanged();
            Intent i = new Intent(ListeActivity.this,ListeActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
