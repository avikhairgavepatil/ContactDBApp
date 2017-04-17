package leena.contactdbapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.name;

/**
 * Created by admin on 09-11-2016.
 */

public class ContactAdapter extends BaseAdapter {

    ArrayList<Contact> contactArrayList = null;
    Context context = null; //we are using context to convert xml into java else we wouldn't have used it

    public ContactAdapter(Context context, ArrayList<Contact> list) {
        this.context = context;
        this.contactArrayList = list;
    }

    @Override
    public int getCount() {
        return contactArrayList.size();
    }

    @Override
    public Contact getItem(int i) {
        return contactArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override // this method will be invoked for every row
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.row_item, viewGroup, false);

        TextView textName = (TextView) v.findViewById(R.id.textViewName);
        TextView textNumber = (TextView) v.findViewById(R.id.textViewNumber);
        TextView textEmail = (TextView) v.findViewById(R.id.textViewEmail);
        TextView textId = (TextView) v.findViewById(R.id.textId);
        Contact contact = contactArrayList.get(i);

        textName.setText(contact.getName());
        textNumber.setText(contact.getPhoneNumber());
        textEmail.setText(contact.getEmailID());
        textId.setText(contact.getId().toString());
        return v;
    }

    public void removeItem(int i) {
        //Contact contact = new Contact()
        Contact contact = getItem(i);
        ContactDB contactDB = new ContactDB(context);
        contactDB.removeItem(contact.id.intValue());
        contactArrayList.remove(contact);
    }

}