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

public class Availability extends Activity implements OnClickListener {

    private LinkedHashMap<String, Day> availList = new LinkedHashMap<String, Day>();
    private ArrayList<Day> availableList = new ArrayList<>();

    private MyListAdapter listAdapter;
    private ExpandableListView myList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.availability);

        Spinner spinner = (Spinner) findViewById(R.id.day);
        // Create an ArrayAdapter using the string array and a default spinner layout
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.daysOfWeek_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //Just add some data to start with
        loadData();

        //get reference to the ExpandableListView
        myList = (ExpandableListView) findViewById(R.id.myList);
        //create the adapter by passing your ArrayList data
        listAdapter = new MyListAdapter(Availability.this, availableList);
        //attach the adapter to the list
        myList.setAdapter(listAdapter);

        //expand all Groups
        expandAll();

        //add new item  button to the List
        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(this);

        //remove item button from the list
        //Button remove = (Button) findViewById(R.id.remove);
        //remove.setOnClickListener(this);

        //listener for child row click
        myList.setOnChildClickListener(myListItemClicked);
        //listener for group heading click
        myList.setOnGroupClickListener(myListGroupClicked);


        myList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                long packedPosition = myList.getExpandableListPosition(position);
                if (ExpandableListView.getPackedPositionType(packedPosition) ==
                        ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                    // get item ID's
                    int groupPosition = ExpandableListView.getPackedPositionGroup(packedPosition);
                    int childPosition = ExpandableListView.getPackedPositionChild(packedPosition);

                    // handle data
                    listAdapter.removeChild(groupPosition, childPosition);
                    //availableList.remove(childPosition);
                    listAdapter.notifyDataSetChanged();
                    //display message when employee name has been deleted
                    Toast.makeText(getBaseContext(),   childPosition +" has been deleted", Toast.LENGTH_LONG).show();
                    // return true as we are handling the event.
                    return true;
                }
                return false;
            }
        });

    }


    public void Roster(View view){
        Intent startNewActivity = new Intent(this,RosterMain.class);
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
                listAdapter.notifyDataSetChanged();

                //collapse all groups
                collapseAll();
                //expand the group where item was just added
                myList.expandGroup(groupPosition);
                //set the current group to be selected so that it becomes visible
                myList.setSelectedGroup(groupPosition);

                break;

            }
        }




    //method to expand all groups

    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            myList.expandGroup(i);
        }
    }

    //method to collapse all groups
    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            myList.collapseGroup(i);
        }
    }

    //load some initial data into out list
    private void loadData() {

        addEmployee("Monday", "John Cian");
        addEmployee("Monday", "Jason Shore");
        addEmployee("Monday", "Mary Docks");

        addEmployee("Tuesday", "Chloe Stairaway");
        addEmployee("Tuesday", "Julian Wertzkof");

    }




    private ExpandableListView.OnChildClickListener myListItemClicked = new ExpandableListView.OnChildClickListener() {

        public boolean onChildClick(ExpandableListView parent, View v,
                                    int groupPosition, int childPosition, long id) {
            //get the day header
            Day daysOfWeek = availableList.get(groupPosition);
            //get the employee info
            Employee employee = daysOfWeek.getEmployeeList().get(childPosition);
            //display it or do something with it
            Toast.makeText(getBaseContext(), employee.getName() + " is available", Toast.LENGTH_LONG).show();
            return false;
        }
    };


    //our group listener
    ExpandableListView.OnGroupClickListener myListGroupClicked =  new ExpandableListView.OnGroupClickListener() {

        public boolean onGroupClick(ExpandableListView parent, View v,
                                    int groupPosition, long id) {

            //get the day header
            Day daysOfWeek = availableList.get(groupPosition);
            //display it or do something with it
            Toast.makeText(getBaseContext(), "Available List for " + daysOfWeek.getName(),
                    Toast.LENGTH_LONG).show();

            return false;
        }

    };

    //here we maintain our employee names for the weekly days
    private int addEmployee(String day, String nameEmp) {

        int groupPosition;

        //check the hash map if the day already exists
        Day daysOfWeek = availList.get(day);
        //add the group if doesn't exists
        if (daysOfWeek == null) {
            daysOfWeek = new Day();
            daysOfWeek.setName(day);
            availList.put(day, daysOfWeek);
            availableList.add(daysOfWeek);
        }
        //get the employee for the group
        ArrayList<Employee> employeeList = daysOfWeek.getEmployeeList();
        //size of the employee list
        int listSize = employeeList.size();
        //add to the counter
        listSize++;


        //create a new employee and add that to the group
        Employee employee = new Employee();
        employee.setSequence(String.valueOf(listSize));
        employee.setName(nameEmp);
        employeeList.add(employee);
        daysOfWeek.setEmployeeList(employeeList);

        //find the group position inside the list
        groupPosition = availableList.indexOf(daysOfWeek);
        return groupPosition;
    }


}