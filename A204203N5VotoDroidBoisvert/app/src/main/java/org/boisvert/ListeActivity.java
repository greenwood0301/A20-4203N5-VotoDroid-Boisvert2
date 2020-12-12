package org.boisvert;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.boisvert.Exceptions.MauvaiseQuestion;
import org.boisvert.Impl.ServiceImpl;
import org.boisvert.Interfaces.Service;
import org.boisvert.Modele.VDQuestion;
import org.boisvert.databinding.ActivityListeBinding;

public class ListeActivity extends AppCompatActivity {
    Adapter adapter;

    private ActivityListeBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        this.startRecyclerView();
        this.FillRecycler();
    }

    private void FillRecycler()
    {
       try {
           Service service = new ServiceImpl();
           VDQuestion question = new VDQuestion();
           question.texte = "Le quebec est-il un pays?";
           service.ajoutQuestion(question);
           adapter.list.add(question);
           adapter.list.add(question);
           adapter.list.add(question);
           adapter.list.add(question);
           adapter.notifyDataSetChanged();
       }
       catch (MauvaiseQuestion e)
       {

       }
    }

    private void startRecyclerView()
    {
        RecyclerView rec = binding.recyclerview;
        rec.setHasFixedSize(true);

        LinearLayoutManager LayoutManager = new LinearLayoutManager(this);
        rec.setLayoutManager(LayoutManager);

        adapter = new Adapter();
        rec.setAdapter(adapter);
    }
}
