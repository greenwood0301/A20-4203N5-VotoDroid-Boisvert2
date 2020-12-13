package org.boisvert;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.boisvert.DAO.BD;
import org.boisvert.Exceptions.MauvaiseQuestion;
import org.boisvert.Impl.ServiceImpl;
import org.boisvert.Interfaces.Service;
import org.boisvert.Modele.VDQuestion;
import org.boisvert.databinding.ActivityCreateBinding;
import org.boisvert.databinding.ActivityMainBinding;

public class CreateActivity extends AppCompatActivity {
    private ActivityCreateBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        BD local = BD.getInstance(this);
        
        binding.pose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateActivity.this,ListeActivity.class);
                try {
                    Service service = new ServiceImpl(getApplicationContext());
                    VDQuestion question = new VDQuestion();
                    question.texte = binding.textboxquestion.getText().toString();;
                    service.ajoutQuestion(question);
                    local.dao().creerQuestion(question);
                }
                catch (MauvaiseQuestion e)
                {
                    Toast.makeText(getApplicationContext(),"La question existe déjà!", Toast.LENGTH_SHORT).show();
                }
                startActivity(i);
            }
        });
    }
}
