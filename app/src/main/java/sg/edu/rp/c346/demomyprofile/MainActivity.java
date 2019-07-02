package sg.edu.rp.c346.demomyprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;
    Button btSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etGPA = findViewById(R.id.etGPA);
        rgGender = findViewById(R.id.RadioGroupGender);
        btSave = findViewById(R.id.btSave);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor prefsedit = prefs.edit();
                prefsedit.putString("name", etName.getText().toString());
                prefsedit.putInt("rgChecked",rgGender.getCheckedRadioButtonId());
                prefsedit.putString("gpa",etGPA.getText().toString());
                prefsedit.commit();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        etName.setText(prefs.getString("name",""));
        rgGender.check(prefs.getInt("rgChecked",-1));
        etGPA.setText(prefs.getString("gpa",""));
    }
    @Override
    protected void onPause() {
        super.onPause();
        String strName = etName.getText().toString();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefsedit = prefs.edit();
        prefsedit.putString("name", strName);
        prefsedit.putInt("rgChecked",rgGender.getCheckedRadioButtonId());
        prefsedit.commit();

    }

}
