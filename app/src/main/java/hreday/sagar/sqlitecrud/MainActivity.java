package hreday.sagar.sqlitecrud;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseHelper databaseHelper;
    private EditText name1, age1, gender1, id1;
    private TextView nameText, ageText, genderText;
    private Button button, button2, button3, button4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name1 = findViewById(R.id.nameEdit);
        age1 = findViewById(R.id.ageEdit);
        gender1 = findViewById(R.id.gendeEdit);
        id1 = findViewById(R.id.idEdit);

        nameText = findViewById(R.id.nameId);
        ageText = findViewById(R.id.ageId);
        genderText = findViewById(R.id.genderId);
        button = findViewById(R.id.insertId);
        button2 = findViewById(R.id.displayId);
        button3 = findViewById(R.id.updateId);
        button4 = findViewById(R.id.deleteId);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);


        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {

        String name = name1.getText().toString();
        String age = age1.getText().toString();
        String gender = gender1.getText().toString();
        String id = id1.getText().toString();

        if (v.getId() == R.id.insertId) {

            long rowId = databaseHelper.InsertData(name, age, gender);

            if (rowId > 0) {

                Toast.makeText(this, "Data soterd successfully", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, "unSuccessfull", Toast.LENGTH_SHORT).show();
            }


        } else if (v.getId() == R.id.updateId) {

            boolean isUpdated = databaseHelper.updateData(name, age, gender, id);

            if (isUpdated == true) {

                Toast.makeText(this, "Data has been updated", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, "Data is not updated succesfully", Toast.LENGTH_SHORT).show();
            }


        } else if (v.getId() == R.id.deleteId) {
            int value = databaseHelper.deleteData(name, age, gender, id);
            if (value > 0) {

                Toast.makeText(this, "Data is deleted", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, "Data is not deleted", Toast.LENGTH_SHORT).show();
            }


        } else if (v.getId() == R.id.displayId) {


            Cursor cursor = databaseHelper.displayData();

            if (cursor.getCount() == 0) {

                showData("Error", "No data has been founded");

                return;


            }


            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()) {

                stringBuffer.append("Id :" + cursor.getString(0) + "\n");
                stringBuffer.append("Name:" + cursor.getString(1) + "\n");
                stringBuffer.append("Age:" + cursor.getString(2) + "\n");
                stringBuffer.append("Gender:" + cursor.getString(3) + "\n\n\n");
            }
            showData("ResultSet", stringBuffer.toString());


        }
    }

    private void showData(String title, String message) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(true);
        alertDialog.show();
    }
}









