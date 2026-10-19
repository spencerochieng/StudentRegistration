package com.example.studentregistration;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText edtFullName;
    private EditText edtDateOfBirth;
    private EditText edtPhone;
    private EditText edtEmail;

    private RadioGroup radioGroupGender;

    private Spinner spinnerCourse;

    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Connect Java variables to XML controls
        edtFullName = findViewById(R.id.edtFullName);
        edtDateOfBirth = findViewById(R.id.edtDateOfBirth);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);

        radioGroupGender = findViewById(R.id.radioGroupGender);

        spinnerCourse = findViewById(R.id.spinnerCourse);

        btnRegister = findViewById(R.id.btnRegister);

        setupCourseSpinner();
        setupDatePicker();

        btnRegister.setOnClickListener(v -> registerStudent());
    }


    private void setupCourseSpinner() {

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.courses,
                        android.R.layout.simple_spinner_item
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinnerCourse.setAdapter(adapter);
    }


    private void setupDatePicker() {

        edtDateOfBirth.setOnClickListener(v -> {

            Calendar calendar = Calendar.getInstance();

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog =
                    new DatePickerDialog(
                            MainActivity.this,
                            (view, selectedYear,
                             selectedMonth,
                             selectedDay) -> {

                                String date =
                                        selectedDay + "/" +
                                                (selectedMonth + 1) + "/" +
                                                selectedYear;

                                edtDateOfBirth.setText(date);
                            },
                            year,
                            month,
                            day
                    );

            datePickerDialog.show();
        });
    }


    private void registerStudent() {

        String fullName =
                edtFullName.getText().toString().trim();

        String dateOfBirth =
                edtDateOfBirth.getText().toString().trim();

        String phone =
                edtPhone.getText().toString().trim();

        String email =
                edtEmail.getText().toString().trim();

        String course =
                spinnerCourse.getSelectedItem().toString();


        // Validate full name
        if (fullName.isEmpty()) {

            edtFullName.setError("Full name is required");
            edtFullName.requestFocus();

            return;
        }


        // Validate date of birth
        if (dateOfBirth.isEmpty()) {

            Toast.makeText(
                    this,
                    "Select date of birth",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }


        // Validate gender
        int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {

            Toast.makeText(
                    this,
                    "Select gender",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        RadioButton selectedRadioButton = findViewById(selectedGenderId);
        String gender = selectedRadioButton != null ? selectedRadioButton.getText().toString() : "";


        // Validate course
        if (spinnerCourse.getSelectedItemPosition() == 0) {

            Toast.makeText(
                    this,
                    "Select a course",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }


        // Validate phone
        if (phone.isEmpty()) {

            edtPhone.setError("Phone number is required");
            edtPhone.requestFocus();

            return;
        }


        // Validate email
        if (email.isEmpty()) {

            edtEmail.setError("Email address is required");
            edtEmail.requestFocus();

            return;
        }


        Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
        intent.putExtra("EXTRA_FULL_NAME", fullName);
        intent.putExtra("EXTRA_DATE_OF_BIRTH", dateOfBirth);
        intent.putExtra("EXTRA_GENDER", gender);
        intent.putExtra("EXTRA_COURSE", course);
        intent.putExtra("EXTRA_PHONE", phone);
        intent.putExtra("EXTRA_EMAIL", email);

        startActivity(intent);
    }
}