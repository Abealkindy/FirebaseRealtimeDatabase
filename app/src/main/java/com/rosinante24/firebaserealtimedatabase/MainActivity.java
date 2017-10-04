package com.rosinante24.firebaserealtimedatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.txt_user)
    TextView txtUser;
    @BindView(R.id.edit_text_name)
    EditText editTextName;
    @BindView(R.id.edit_text_email)
    EditText editTextEmail;
    @BindView(R.id.btn_save)
    Button btnSave;

    FirebaseDatabase firebasedatabase;
    DatabaseReference databasereference;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        firebasedatabase = FirebaseDatabase.getInstance();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        databasereference = firebasedatabase.getReference("users");

        firebasedatabase.getReference("app_title").setValue("Realtime");


        firebasedatabase.getReference("app_title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String app_title = dataSnapshot.getValue(String.class);
                getSupportActionBar().setTitle(app_title);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(MainActivity.this, "Gagal bro", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @OnClick(R.id.btn_save)
    public void onClick() {
        user = new User(editTextName.getText().toString(), editTextEmail.getText().toString());

        String transfer = databasereference.push().getKey();
        databasereference.child(transfer).setValue(user);

        txtUser.setText(user.username + " , " + user.email);
    }
}
