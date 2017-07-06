package com.example.prakharjaiswal.attendance;


import android.app.DatePickerDialog;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import static java.lang.Integer.valueOf;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopPaidFragment extends Fragment /*implements View.OnClickListener*/ {

    Button btn;
    String DILOG_DATE ="Date Picker";
    String dd = "01-Jan-2017";
String sclass = new String();
    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseStudent;
    Spinner spinnerClass;
    ListView lv;

    ArrayList<String> ar2;
    ArrayList<String> ar3;
    ArrayList<String> ar4;

    public TopPaidFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_paid, container, false);
     btn = (Button)view.findViewById(R.id.dateBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });


        // Inflate the layout for this fragment
        firebaseAuth = FirebaseAuth.getInstance();
        databaseStudent = FirebaseDatabase.getInstance().getReference("students");
        spinnerClass = (Spinner) view.findViewById(R.id.spinnerClass2);
        lv = (ListView) view.findViewById(R.id.student_list1);
        // Create an ArrayAdapter using the string array and a default spinner layout

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.studentClass, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerClass.setAdapter(adapter);

        spinnerClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               // System.out.println("lol");
                 sclass = spinnerClass.getSelectedItem().toString();
                /*
                ar2 = new ArrayList<String>();
                ar3 = new ArrayList<String>();
                ar4 = new ArrayList<String>();
                Query queryRef = databaseStudent.orderByChild("studentClass").equalTo(sclass);
                queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //   System.out.println("We're done loading the initial "+dataSnapshot.getChildrenCount()+" items");
                        StudentList2 lt = new StudentList2(getContext(), ar2, ar3, ar4, dd);
                        lv.setAdapter(lt);
                        //  loading.dismiss();;
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }


                });
                queryRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        //   for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {}

                        // ProgressDialog loading = ProgressDialog.show(getApplicationContext(), "Uploading...", "Please wait...", false, false);
                        Map<String, Object> value = (Map<String, Object>) dataSnapshot.getValue();
                        //  for(int i=0;i<value.size();i++)

                        {
                            String studentid = String.valueOf(value.get("studentId"));
                          /*  Calendar c = Calendar.getInstance();
                            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                            String formattedDate = df.format(c.getTime());
*/
/*
                            Map<String, Object> value1 = (Map<String, Object>) dataSnapshot.child("d").getValue();
                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                //  String l=child.child("d/"+formattedDate).getValue().toString();
                                // System.out.println(l);
                            }

                            String name = String.valueOf(value.get("studentName"));

                            String date = String.valueOf(value1.get(dd));
                            System.out.println("lol-" + dd);
                            ar2.add(studentid);
                            ar3.add(name);
                            ar4.add(dd);
                        }


                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {


                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });*/


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return view;
    }

//Date Activity
    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(onDateSetLister1);

       date.show(getActivity().getFragmentManager(),DILOG_DATE);

    }

    DatePickerDialog.OnDateSetListener onDateSetLister1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

            String m = new String();
            switch (valueOf(monthOfYear)){
                case 0:
                    m = "Jan";
                    break;
                case 1:
                    m ="Feb";
                    break;
                case 2:
                    m ="Mar";
                    break;
                case 3:
                    m ="Apr";
                    break;
                case 4:
                    m ="May";
                    break;
                case 5:
                    m ="Jun";
                    break;
                case 6:
                    m ="Jul";
                    break;
                case 7:
                    m ="Aug";
                    break;
                case 8:
                    m ="Sep";
                    break;
                case 9:
                    m ="Oct";
                    break;
                case 10:
                    m ="Nov";
                    break;
                case 11:
                    m ="Dec";
                    break;

            }
            String dt = new String();
            if(valueOf(dayOfMonth)<10)
                dt = "0"+String.valueOf(dayOfMonth);
            else
            dt = String.valueOf(dayOfMonth);
             dd = dt + "-" + m + "-" + String.valueOf(year);
            System.out.println("Date hai bc " + dd);


System.out.println("Sclass is " + sclass);

            ar2 = new ArrayList<String>();
            ar3 = new ArrayList<String>();
            ar4 = new ArrayList<String>();
            Query queryRef = databaseStudent.orderByChild("studentClass").equalTo(sclass);
            queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
                public void onDataChange(DataSnapshot dataSnapshot) {
                    StudentList2 lt = new StudentList2(getContext(), ar2, ar3, ar4, dd);
                    lv.setAdapter(lt);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }


            });
            queryRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Map<String, Object> value = (Map<String, Object>) dataSnapshot.getValue();

                    {
                        String studentid = String.valueOf(value.get("studentId"));


                            Map<String, Object> value1 = (Map<String, Object>) dataSnapshot.child("d").getValue();
                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                //  String l=child.child("d/"+formattedDate).getValue().toString();
                                // System.out.println(l);
                            }

                            String name = String.valueOf(value.get("studentName"));

                            String date = String.valueOf(value1.get(dd));
                            System.out.println("lol-" + dd);
                            ar2.add(studentid);
                            ar3.add(name);
                            ar4.add(date);
                        }


                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {


                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        }

    };


}