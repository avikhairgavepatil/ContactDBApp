package leena.contactdbapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;

import java.util.ArrayList;

/**
 * Created by admin on 08-11-2016.
 */

public class ContactDB extends SQLiteOpenHelper{

    Contact contact;
    static String DatabaseName = "ContactDb";
    static int DatabaseVersion = 1;
    static String TableName = "Contact";
    static String createQuery = "create table Contact(id integer primary key autoincrement,name text,number text, email text);";

    public ContactDB(Context context) {
        super(context, DatabaseName, null, DatabaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //this will be the first method to be invoked hence create your tables here
        sqLiteDatabase.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(Contact contact)
    {

        SQLiteDatabase database =getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name",contact.getName()); // keep same name as given in create query
        contentValues.put("number",contact.getPhoneNumber());
        contentValues.put("email",contact.getEmailID());

        long id = database.insert(TableName,null,contentValues);
        System.out.println("Record inserted at id" + id);
    }

      public void removeItem(int id){

        SQLiteDatabase database = getWritableDatabase();
        database.delete(TableName,"id = ' "+id + "'",null);
    }

/*
    public void removeItem(Contact contact){
       // contact = new Contact();
        SQLiteDatabase database = getWritableDatabase();
       database.delete(TableName,"id = ' "+id+"' ",null);
    }*/

    public int update(Contact contact)
    {
        SQLiteDatabase database =getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name",contact.getName()); // keep same name as given in create query
        contentValues.put("number",contact.getPhoneNumber());
        contentValues.put("email",contact.getEmailID());

        long id = database.insert(TableName,null,contentValues);
        return database.update(TableName,contentValues,"id = "+id,null);

    }
/*
    public Contact getContact(int id)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TableName,)
        return contact;
    }*/

    public ArrayList<Contact> getAllContacts()
    {
        SQLiteDatabase database = getReadableDatabase();
        ArrayList<Contact> contactArrayList = new ArrayList<>();
        String query = "select * from " +TableName;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();

        //to move to next we need a loop
        while(cursor.isAfterLast()==false){

            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String number = cursor.getString(cursor.getColumnIndex("number")); //We can implement this if we are not sure about column number or else we can directly enter column number 1, 2 , 3 etc
            String emailid = cursor.getString(cursor.getColumnIndex("email"));

            Contact contact = new Contact(id,name,number,emailid); // if we have 10 or more objects use an ArrayList
            contactArrayList.add(contact);

            cursor.moveToNext();
        }
        cursor.close();
        return contactArrayList;
    }
}
