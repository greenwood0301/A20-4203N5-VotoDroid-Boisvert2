package org.boisvert;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Dao;

import org.boisvert.DAO.BD;
import org.boisvert.Exceptions.MauvaiseQuestion;
import org.boisvert.Impl.ServiceImpl;
import org.boisvert.Interfaces.Service;
import org.boisvert.Modele.VDQuestion;
import org.boisvert.databinding.ActivityListeBinding;
import org.boisvert.databinding.QuestionItemsBinding;

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

        adapter = new Adapter(this);
        rec.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Integer pos) {

        Intent i = new Intent(ListeActivity.this,VoteActivity.class);
        i.putExtra("id", pos);
        startActivity(i);
    }
}
