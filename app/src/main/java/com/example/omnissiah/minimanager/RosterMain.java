package com.example.omnissiah.minimanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class RosterMain extends Activity implements OnClickListener {

    private LinkedHashMap<String, DayPageR> workList = new LinkedHashMap<String, DayPageR>();
    private ArrayList<DayPageR> rosterList = new ArrayList<DayPageR>();

    private AdapterListR listAdapterR;
    private ExpandableListView myListR;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roster_main);

        Spinner spinner = (Spinner) findViewById(R.id.day);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.daysOfWeek_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //Just add some data to start with
        loadData();

        //get reference to the ExpandableListView
        myListR = (ExpandableListView) findViewById(R.id.myList);
        //create the adapter by passing your ArrayList data
        listAdapterR = new AdapterListR(RosterMain.this, rosterList);
        //attach the adapter to the list
        myListR.setAdapter(listAdapterR);

        //expand all Groups
        expandAll();

        //add new item to the List
        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(this);

        //listener for child row click
        myListR.setOnChildClickListener(myListItemClicked);
        //listener for group heading click
        myListR.setOnGroupClickListener(myListGroupClicked);
        //Listener for child row long click
        myListR.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                long packedPosition = myListR.getExpandableListPosition(position);
                if (ExpandableListView.getPackedPositionType(packedPosition) ==
                        ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                    // get item ID's
                    int groupPosition = ExpandableListView.getPackedPositionGroup(packedPosition);
                    int childPosition = ExpandableListView.getPackedPositionChild(packedPosition);

                    // remove the employee name
                    listAdapterR.removeChild(groupPosition, childPosition);
                    //notify the change
                    listAdapterR.notifyDataSetChanged();
                    //display message when employee name has been deleted
                    Toast.makeText(getBaseContext(),   childPosition +" has been deleted", Toast.LENGTH_LONG).show();
                    // return true as we are handling the event.
                    return true;
                }
                return false;
            }
        });


    }

    public void Availability(View view){
        Intent startNewActivity = new Intent(this,Availability.class);
        startActivity(startNewActivity);
    }

    public void onClick(View v) {

        switch (v.getId()) {

            //add entry to the List
            case R.id.add:

                Spinner spinner = (Spinner) findViewById(R.id.day);
                String daysOfWeek = spinner.getSelectedItem().toString();
                EditText editText = (EditText) findViewById(R.id.nameEmp);
                String employee = editText.getText().toString();
                editText.setText("");
                //add a new item to the list
                int groupPosition = addEmployee(daysOfWeek, employee);
                //notify the list so that changes can take effect
                listAdapterR.notifyDataSetChanged();

                //collapse all groups
                collapseAll();
                //expand the group where item was just added
                myListR.expandGroup(groupPosition);
                //set the current group to be selected so that it becomes visible
                myListR.setSelectedGroup(groupPosition);

                break;

        }
    }

    //method to expand all groups

    private void expandAll() {
        int count = listAdapterR.getGroupCount();
        for (int i = 0; i < count; i++) {
            myListR.expandGroup(i);
        }
    }

    //method to collapse all groups
    private void collapseAll() {
        int count = listAdapterR.getGroupCount();
        for (int i = 0; i < count; i++) {
            myListR.collapseGroup(i);
        }
    }

    //load some initial data into out list
    private void loadData() {

        addEmployee("Monday", "John Cian");
        addEmployee("Monday", "Jason Shore");
        addEmployee("Monday", "Mary Docks");

        addEmployee("Tuesday", "Chloe Stairaway");
        addEmployee("Tuesday", "Julian Wertzkof");
        addEmployee("Tuesday", "Chloe Cortez");

        addEmployee("Wednesday", "Julian Wertzkof");
        addEmployee("Wednesday", "Mary Docks");

    }

    //our child listener
    private ExpandableListView.OnChildClickListener myListItemClicked = new ExpandableListView.OnChildClickListener() {

        public boolean onChildClick(ExpandableListView parent, View v,
                                    int groupPosition, int childPosition, long id) {
            //get the day header
            DayPageR daysOfWeek = rosterList.get(groupPosition);
            //get the employee info
            EmployeeListR employee = daysOfWeek.getEmployeeListRR().get(childPosition);
            //display it or do something with it
            Toast.makeText(getBaseContext(), " Working " + daysOfWeek.getName()
                    + "/" + employee.getName(), Toast.LENGTH_LONG).show();


            return false;
        }
    };

    //our group listener
    private ExpandableListView.OnGroupClickListener myListGroupClicked = new ExpandableListView.OnGroupClickListener() {

        public boolean onGroupClick(ExpandableListView parent, View v,
                                    int groupPosition, long id) {

            //get the day header
            DayPageR daysOfWeek = rosterList.get(groupPosition);
            //display it or do something with it
            Toast.makeText(getBaseContext(), "Roster for the day " + daysOfWeek.getName(),
                    Toast.LENGTH_LONG).show();

            return false;
        }

    };

    //here we maintain our employee names for the weekly days
    private int addEmployee(String day, String empName) {

        int groupPosition;

        //check the hash map if the day already exists
        DayPageR daysOfWeek = workList.get(day);
        //add the group if doesn't exists
        if (daysOfWeek == null) {
            daysOfWeek = new DayPageR();
            daysOfWeek.setName(day);
            workList.put(day, daysOfWeek);
            rosterList.add(daysOfWeek);
        }
        //get the employee for the group
        ArrayList<EmployeeListR> employeeListRR = daysOfWeek.getEmployeeListRR();
        //size of the employee list
        int listSize = employeeListRR.size();
        //add to the counter
        listSize++;


        //create a new employee and add that to the group
        EmployeeListR employee = new EmployeeListR();
        employee.setSequence(String.valueOf(listSize));
        employee.setName(empName);
        employeeListRR.add(employee);
        daysOfWeek.setEmployeeListRR(employeeListRR);

        //find the group position inside the list
        groupPosition = rosterList.indexOf(daysOfWeek);
        return groupPosition;
    }
}

