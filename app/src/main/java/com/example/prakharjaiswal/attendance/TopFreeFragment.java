package com.example.prakharjaiswal.attendance;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class TopFreeFragment extends Fragment {
    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseStudent;
    Spinner spinnerClass;
    ListView lv;

    ArrayList<String> ar2;
    ArrayList<String> ar3;
    ArrayList<String> ar4;


    public TopFreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_free, container, false);
        // Inflate the layout for this fragment
        firebaseAuth = FirebaseAuth.getInstance();
        databaseStudent = FirebaseDatabase.getInstance().getReference("students");
        spinnerClass = (Spinner) view.findViewById(R.id.spinnerClass1);
        lv = (ListView)view.findViewById(R.id.student_list);
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
                 System.out.println("lol");
                String sclass = spinnerClass.getSelectedItem().toString();

                ar2 = new ArrayList<String>();
                ar3 = new ArrayList<String>();
                ar4 = new ArrayList<String>();
                Query queryRef = databaseStudent.orderByChild("studentClass").equalTo(sclass);
                queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //   System.out.println("We're done loading the initial "+dataSnapshot.getChildrenCount()+" items");
                        Studentlist lt = new Studentlist(getContext(), ar2, ar3, ar4);
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
                            Calendar c = Calendar.getInstance();
                            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                            String formattedDate = df.format(c.getTime());


                            Map<String, Object> value1 = (Map<String, Object>) dataSnapshot.child("d").getValue();
                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                //  String l=child.child("d/"+formattedDate).getValue().toString();
                                // System.out.println(l);
                            }

                            String name = String.valueOf(value.get("studentName"));

                            String date = String.valueOf(value1.get(formattedDate));
                            System.out.println("date hai purani " + date);
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

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return view;
    }
}
