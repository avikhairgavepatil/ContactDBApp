
package leena.contactdbapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ContactListActivity extends AppCompatActivity {

   /* EditText editTextName = null;
    EditText editTextPhoneNumber = null;
    EditText editTextEmailId = null;*/
    ListView listView = null;
    ContactAdapter contactAdapter;
    ContactDB contactDB =null;
    Contact contact = null;
    /*Contact contact=null;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        contactDB = new ContactDB(getApplicationContext());
        /*String name = editTextName.getText().toString();
        String number = editTextPhoneNumber.getText().toString();
        String emailid = editTextEmailId.getText().toString();*/

        /*contact = new Contact(name,number,emailid);*/
        listView = (ListView) findViewById(R.id.listView);
        contactAdapter = new ContactAdapter(getApplicationContext(),contactDB.getAllContacts());//ArrayList here is used to prepopulate the values
        listView.setAdapter(contactAdapter);
        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showPopupMenu();
            }
        });

     /*   listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    showPopupMenu();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/
       /* listView = (ListView) findViewById(R.id.listView);
        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //contactAdapter.removeItem();
                // contactAdapter.notifyDataSetChanged();
                showPopupMenu();
            }
        });*/

    }

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
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                break;

            case R.id.menuAbout: Intent i1 =new Intent(ContactListActivity.this,About.class);
                startActivity(i1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo  = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id = item.getItemId();
        switch(id)
        {
            case R.id.menuItemDelete:
                   /* AdapterView.AdapterContextMenuInfo  adapterContextMenuInfo = Contact.id.getMenuInfo();
                    int index = adapterContextMenuInfo.position;
                    contactDB.removeItem(index);*/
                   //contact = new Contact(name,number,email);
                    //contactDB = new ContactDB(getApplicationContext());
                    //contactDB.remove();
                   // contactDB.getWritableDatabase();
                   // contactDB.removeItem(contact);
                contactAdapter.removeItem(adapterContextMenuInfo.position);
                contactAdapter.notifyDataSetChanged();

                //contactDB.removeItem(listView.getId()
                int i = listView.getId();
                Toast.makeText(ContactListActivity.this,"id "+adapterContextMenuInfo.position,Toast.LENGTH_LONG).show();
                    break;
            case R.id.menuEdit:
                Toast.makeText(ContactListActivity.this,"Editing",Toast.LENGTH_LONG).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void showPopupMenu()
    {
        final PopupMenu popupMenu = new PopupMenu(ContactListActivity.this,listView);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.menuView:


                        Toast.makeText(ContactListActivity.this,"Viewing",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menuCall:
                        Toast.makeText(ContactListActivity.this,"Viewing",Toast.LENGTH_LONG).show();
                        break;
                }

                return true; //if we keep false then it means we are not handling it
            }
        });
        popupMenu.show();

    }
}
