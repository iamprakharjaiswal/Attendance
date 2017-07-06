package com.example.prakharjaiswal.attendance;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */


public class HomeFragment extends Fragment implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    EditText editTextName;
    Spinner spinnerClass;
    Button buttonAdd;
    DatabaseReference databaseStudent;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseStudent = FirebaseDatabase.getInstance().getReference("students");
        editTextName = (EditText)view.findViewById(R.id.nameEditText);
        spinnerClass = (Spinner)view.findViewById(R.id.spinnerClass);

        // Create an ArrayAdapter using the string array and a default spinner layout

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.studentClass, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerClass.setAdapter(adapter);
        buttonAdd = (Button)view.findViewById(R.id.addButton);
        if (firebaseAuth.getCurrentUser()==null)
        {
            getActivity().finish();
            startActivity(new Intent(getActivity(),LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();
        textViewUserEmail = (TextView)view.findViewById(R.id.textViewUserEmail);
       // buttonLogout = (Button)getView().findViewById(R.id.buttonLogout);
        textViewUserEmail.setText("Welcome " + user.getEmail());
        buttonAdd.setOnClickListener(this);


        // Inflate the layout for this fragment
        return view;


    }

    @Override
    public void onClick(View view) {
         if(view==buttonAdd)
        {
            String name = editTextName.getText().toString().trim();
            String sclass = spinnerClass.getSelectedItem().toString();

            if(!TextUtils.isEmpty(name))
            {
                String id = databaseStudent.push().getKey();
                Student student = new Student(id,name,sclass,new Days());
                databaseStudent.child(id).setValue(student);
                Toast.makeText(getActivity(),"Student Added",Toast.LENGTH_SHORT).show();


            }
            else
            {
                Toast.makeText(getActivity(),"Please enter the name!!",Toast.LENGTH_LONG).show();
            }
        }

    }

}
