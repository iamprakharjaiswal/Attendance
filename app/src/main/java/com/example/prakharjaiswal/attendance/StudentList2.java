package com.example.prakharjaiswal.attendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Prakhar Jaiswal on 02-07-2017.
 */

public class StudentList2 extends ArrayAdapter<String>{

        public StudentList2(Context context, int resource) {
            super(context, resource);
        }
        Context context;
        ArrayList<String> ar=new ArrayList<String>() ;
        View rowView;
        ArrayList<String> ar2=new ArrayList<String>();
        ArrayList<String> ar3=new ArrayList<String>();
    String d= new String();
        private FirebaseAuth firebaseAuth;
        DatabaseReference databaseStudent;

        public StudentList2(Context context,ArrayList<String> ar, ArrayList<String> ar1, ArrayList<String> ar4, String dd) {
            super(context,R.layout.studentlist2layout,ar);
            this.context = context;
            this.ar2=ar;
            this.ar3=ar1;
            this.ar=ar4;
            this.d = dd;

        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.studentlist2layout, parent, false);
            TextView name=(TextView)rowView.findViewById(R.id.name);
            name.setText(ar3.get(position));
            Switch sh=(Switch)rowView.findViewById(R.id.switch2);
            if(ar.get(position).equals("True"))
                sh.setChecked(true);
            else
                sh.setChecked(false);
            sh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){

                        firebaseAuth = FirebaseAuth.getInstance();
                        databaseStudent = FirebaseDatabase.getInstance().getReference("students");
                        Map<String, Object> childUpdates = new HashMap<>();
                        /*Calendar c = Calendar.getInstance();


                        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                        String formattedDate = df.format(c.getTime());*/
                        childUpdates.put("/"+ar2.get(position)+"/d/"+d, "True");
                        databaseStudent.updateChildren(childUpdates);


                    }else{
                        firebaseAuth = FirebaseAuth.getInstance();
                        databaseStudent = FirebaseDatabase.getInstance().getReference("students");
                        Map<String, Object> childUpdates = new HashMap<>();
                       /* Calendar c = Calendar.getInstance();


                        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                        String formattedDate = df.format(c.getTime());*/
                        childUpdates.put("/"+ar2.get(position)+"/d/"+d, "False");
                        databaseStudent.updateChildren(childUpdates);
                    }
                }
            });
            return rowView;

        }}
