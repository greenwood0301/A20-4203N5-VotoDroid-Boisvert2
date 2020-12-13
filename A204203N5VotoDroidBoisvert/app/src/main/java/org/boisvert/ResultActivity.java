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

import java.util.Map;

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
        double ET = service.ecartTypePour(q);
        String sET=Double.toString(ET);
        binding.ET.setText(sET);
        binding.TitleR.setText(BD.getInstance(this).dao().ALLQ().get(Id).texte);
        Map<Integer, Integer> Map = service.distributionPour(q);
        binding.textView6.setText(Map.get(0).toString());
        binding.textView7.setText(Map.get(1).toString());
        binding.textView8.setText(Map.get(2).toString());
        binding.textView9.setText(Map.get(3).toString());
        binding.textView10.setText(Map.get(4).toString());
        binding.textView11.setText(Map.get(5).toString());
    }
}
