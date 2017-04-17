package leena.contactdbapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextName = null;
    EditText editTextPhoneNumber = null;
    EditText editTextEmailId = null;
    Button buttonAddContact = null;
    Button buttonShowAllContacts = null;

    ContactDB contactDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        editTextEmailId = (EditText) findViewById(R.id.editTextEmail);
        buttonAddContact = (Button) findViewById(R.id.btnAddContact);
        buttonShowAllContacts = (Button)findViewById(R.id.buttonShowAllContacts);

        contactDB = new ContactDB(getApplicationContext());

       buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //read the values from all three edit text
                //create pojo contact object
                //then call db.insert method

                String name = editTextName.getText().toString();
                String number = editTextPhoneNumber.getText().toString();
                String emailid = editTextEmailId.getText().toString();

                Contact contact = new Contact(name,number,emailid);

                contactDB.insert(contact);
            }
        });

        buttonShowAllContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* ArrayList<Contact> contactArrayList = contactDB.getAllContacts();
                Toast.makeText(MainActivity.this,"Count"+contactArrayList.size(),Toast.LENGTH_LONG).show();*/
                Intent intent = new Intent(getApplicationContext(),ContactListActivity.class);
                startActivity(intent);
            }
        });
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.menuItemAddContact:
                String name = editTextName.getText().toString();
                String number = editTextPhoneNumber.getText().toString();
                String emailid = editTextEmailId.getText().toString();

                Contact contact = new Contact(name,number,emailid);

                contactDB.insert(contact);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            Toast.makeText(MainActivity.this,"BACK",Toast.LENGTH_LONG).show();
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to exit?");

        //builder.setView(R.layout.activity_main);//to show entire layout
/*
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        View rootView = inflater.inflate(R.layout.activity_main,null,false);
       final EditText editTextName = (EditText) rootView.findViewById(R.id.editTextName);
        Button btAdd = (Button) rootView.findViewById(R.id.btnAddContact);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
                Toast.makeText(getApplicationContext(),"name",Toast.LENGTH_LONG).show();
            }
        });
        builder.setView(rootView);
*/
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                dialogInterface.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
        return super.onKeyDown(keyCode, event);
    }
}
