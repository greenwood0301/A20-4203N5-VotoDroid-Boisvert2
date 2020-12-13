package org.boisvert;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.boisvert.DAO.BD;
import org.boisvert.Impl.ServiceImpl;
import org.boisvert.Interfaces.Service;
import org.boisvert.Modele.VDQuestion;
import org.boisvert.databinding.ActivityListeBinding;
import org.boisvert.databinding.ActivityResultBinding;

public class ResultActivity  extends AppCompatActivity
{
    private ActivityResultBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        int Id = getIntent().getIntExtra("id",-1);
        VDQuestion q = BD.getInstance(this).dao().ALLQ().get(Id);
        Service service = new ServiceImpl(getApplicationContext());
        double moy = service.moyennePour(q);
        String s=Double.toString(moy);
        binding.Moy.setText(s);
    }
}
