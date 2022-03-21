package ca.georgebrown.comp3074.assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText no_of_hours = findViewById(R.id.no_of_hours);
        EditText hourly_rate = findViewById(R.id.hourly_rate_text);
        TextView pay_text = findViewById(R.id.pay_text);
        TextView overtime_pay_text = findViewById(R.id.overtime_pay_text);
        TextView total_pay_text = findViewById(R.id.total_pay_text);
        TextView tax_text = findViewById(R.id.tax_text);

        Button btn = findViewById(R.id.btn_calculate);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    double pay = 0;
                    double overtime_pay = 0;
                    double total_pay = 0;
                    double tax = 0;
                    double hours = Double.parseDouble(no_of_hours.getText().toString());
                    double rate = Double.parseDouble(hourly_rate.getText().toString());
                    if(hours <= 40){
                        pay = rate * hours;
                        overtime_pay = 0;
                        total_pay = pay;
                    }else{
                        pay = 40 * rate;
                        overtime_pay = rate * (hours - 40) * 1.5;
                        total_pay = pay + overtime_pay;
                    }

                    tax = total_pay * 0.18;

                    pay_text.setText(String.format("%.2f", pay));
                    overtime_pay_text.setText(String.format("%.2f", overtime_pay));
                    total_pay_text.setText(String.format("%.2f", total_pay));
                    tax_text.setText(String.format("%.2f", tax));
                } catch (NumberFormatException nfe) {
                    Toast.makeText(MainActivity.this, "Empty field in not allowed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_about:
                Intent i = new Intent(this,AboutActivity.class);
                startActivity(i);
        }
        return true;
    }
}