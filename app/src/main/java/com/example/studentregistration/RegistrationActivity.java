package com.example.studentregistration;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistrationActivity extends AppCompatActivity {

    private TextView txtFullName;
    private TextView txtDateOfBirth;
    private TextView txtGender;
    private TextView txtCourse;
    private TextView txtPhone;
    private TextView txtEmail;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.registrationRoot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtFullName = findViewById(R.id.txtFullName);
        txtDateOfBirth = findViewById(R.id.txtDateOfBirth);
        txtGender = findViewById(R.id.txtGender);
        txtCourse = findViewById(R.id.txtCourse);
        txtPhone = findViewById(R.id.txtPhone);
        txtEmail = findViewById(R.id.txtEmail);
        btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        if (intent != null) {
            String fullName = intent.getStringExtra("EXTRA_FULL_NAME");
            String dateOfBirth = intent.getStringExtra("EXTRA_DATE_OF_BIRTH");
            String gender = intent.getStringExtra("EXTRA_GENDER");
            String course = intent.getStringExtra("EXTRA_COURSE");
            String phone = intent.getStringExtra("EXTRA_PHONE");
            String email = intent.getStringExtra("EXTRA_EMAIL");

            txtFullName.setText(getString(R.string.display_full_name, fullName != null ? fullName : ""));
            txtDateOfBirth.setText(getString(R.string.display_dob, dateOfBirth != null ? dateOfBirth : ""));
            txtGender.setText(getString(R.string.display_gender, gender != null ? gender : ""));
            txtCourse.setText(getString(R.string.display_course, course != null ? course : ""));
            txtPhone.setText(getString(R.string.display_phone, phone != null ? phone : ""));
            txtEmail.setText(getString(R.string.display_email, email != null ? email : ""));
        }

        btnBack.setOnClickListener(v -> finish());
    }
}