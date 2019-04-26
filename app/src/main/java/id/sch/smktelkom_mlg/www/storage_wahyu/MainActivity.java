package id.sch.smktelkom_mlg.www.storage_wahyu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    static final int READ_BLOCK_SIZE = 100;
    EditText et;
    Button sv, load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.editInput);
        sv = findViewById(R.id.simpan);
        load = findViewById(R.id.lod);
        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fOut = openFileOutput("Textku.text", MODE_PRIVATE);
                    fOut.write(et.getText().toString().getBytes());
                    fOut.close();
                    Toast.makeText(getBaseContext(), "File Disimpan", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            try{
                FileInputStream fin = openFileInput("Textku.txt");
                InputStreamReader isr = new InputStreamReader(fin);
                char[] inputBuffer = new char[READ_BLOCK_SIZE];
                String s = "";
                int charRead;
                while ((charRead = isr.read(inputBuffer)) > 0){
                    s += String.copyValueOf(inputBuffer, 0, charRead);
                    inputBuffer = new char[READ_BLOCK_SIZE];
                }
                et.setText(s);
                Toast.makeText(getBaseContext(), "File Diload", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });
}
}
