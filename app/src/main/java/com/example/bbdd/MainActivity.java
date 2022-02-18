package com.example.bbdd;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Button btnCreate;
    private Button btnView;
    private Button btnDelete;
    private EditText editName;
    private EditText editComment;
    private EditText txtName;
    private EditText txtComment;


    private Spinner spinComments;
    private ArrayAdapter spinnerAdapter;


    private ArrayList<Comment> lista;
    private Comment c;


    private MyOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editName=(EditText) findViewById(R.id.editName);
        editComment=(EditText)findViewById(R.id.editComment);
        txtName=(EditText) findViewById(R.id.txtName);
        txtComment=(EditText)findViewById(R.id.txtComment);


        txtName.setEnabled(false);
        txtComment.setEnabled(false);

        btnCreate=(Button)findViewById(R.id.btnCreate);
        btnView=(Button)findViewById(R.id.btnView);
        btnDelete=(Button)findViewById(R.id.btnDelete);

        btnCreate.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnDelete.setOnClickListener(this);


        db=new MyOpenHelper(this);


        spinComments=(Spinner) findViewById(R.id.spinComments);
        lista=db.getComments();


        spinnerAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,lista);
        spinComments.setAdapter(spinnerAdapter);
        spinComments.setOnItemSelectedListener(this);


    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btnCreate:

                db.insertar(editName.getText().toString(),editComment.getText().toString());

                lista=db.getComments();

                spinnerAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,lista);
                spinComments.setAdapter(spinnerAdapter);

                editName.setText("");
                editComment.setText("");

                break;
            case R.id.btnView:

                if(c!=null) {
                    txtName.setText(c.getName());
                    txtComment.setText(c.getComment());
                }
                break;
            case R.id.btnDelete:

                if(c!=null) {
                    db.borrar(c.getId());
                    lista = db.getComments();
                    spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lista);
                    spinComments.setAdapter(spinnerAdapter);

                    txtName.setText("");
                    txtComment.setText("");

                    c=null;
                }
                break;

        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinComments) {
            if(lista.size()>0) {
                c = lista.get(position);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}