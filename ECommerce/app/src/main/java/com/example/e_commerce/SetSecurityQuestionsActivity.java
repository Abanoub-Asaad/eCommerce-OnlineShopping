package com.example.e_commerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_commerce.Prevalent.Prevalent;
import com.example.e_commerce.ui.settings.SettingsActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetSecurityQuestionsActivity extends AppCompatActivity {

    private EditText q1Answer, q2Answer, q3Answer;
    private Button applyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_security_questions);

        q1Answer = findViewById(R.id.q1_editTxt);
        q2Answer = findViewById(R.id.q2_editTxt);
        q3Answer = findViewById(R.id.q3_editTxt);
        applyBtn = findViewById(R.id.setQuestions_btn);

        displayPreviousAnswers(q1Answer, q2Answer, q3Answer);

        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               setAnswers();
            }
        });
    }

        private void displayPreviousAnswers(final EditText q1Answer, final EditText q2Answer, final EditText q3Answer){

            DatabaseReference UserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.currentOnlineUser.getPhone()).child("Security Questions");

            UserRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                            String answer1 = snapshot.child("answer1").getValue().toString();
                            String answer2 = snapshot.child("answer2").getValue().toString();
                            String answer3 = snapshot.child("answer3").getValue().toString();

                        q1Answer.setText(answer1); q1Answer.setSelection(answer1.length());
                        q2Answer.setText(answer2);
                        q3Answer.setText(answer3);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    private void setAnswers() {
        String answer1 = q1Answer.getText().toString();
        String answer2 = q2Answer.getText().toString();
        String answer3 = q3Answer.getText().toString();

        if(answer1.equals("") || answer2.equals("") || answer3.equals(""))
            Toast.makeText(SetSecurityQuestionsActivity.this, "Please ansewr all questions", Toast.LENGTH_SHORT).show();
        else{
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                    .child("Users")
                    .child(Prevalent.currentOnlineUser.getPhone());

            HashMap <String , Object> userDataMap = new HashMap<>();
            userDataMap.put("answer1", answer1);
            userDataMap.put("answer2", answer2);
            userDataMap.put("answer3", answer3);

            ref.child("Security Questions").updateChildren(userDataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Intent intent = new Intent(SetSecurityQuestionsActivity.this, SettingsActivity.class);
                        startActivity(intent);
                        finish();

                        Toast.makeText(SetSecurityQuestionsActivity.this, "You have set security questions successfully", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
}
