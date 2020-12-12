package org.boisvert;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.boisvert.Modele.VDQuestion;
import org.boisvert.databinding.ActivityListeBinding;

public class ListeActivity extends AppCompatActivity {
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
        
    }

    private void startRecyclerView()
    {
        RecyclerView rec = binding.recyclerview;
        rec.setHasFixedSize(true);

        LinearLayoutManager LayoutManager = new LinearLayoutManager(this);
        rec.setLayoutManager(LayoutManager);

        Adapter adp = new Adapter();
        rec.setAdapter(adp);
    }
}
