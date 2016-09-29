package com.example.omnissiah.minimanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 07/04/2016.
 */
public class MyListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Day> dayList;

    /*//clear the adapter
    public void clear(){
        this.clear();
        this.notifyDataSetChanged();
    }

    //add the adapter back
    public void addAll(ArrayList<Day> dayList){
        this.addAll(dayList);
        this.notifyDataSetChanged();
    }*/



    public MyListAdapter(Context context, ArrayList<Day> dayList){
        this.context = context;
        this.dayList = dayList;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Employee> employeeList = dayList.get(groupPosition).getEmployeeList();
        return employeeList.get(childPosition);
    }

    public void removeChild(int groupPosition, int childPosition){
        ArrayList<Employee> employeeList = dayList.get(groupPosition).getEmployeeList();
        Employee temp =  employeeList.get(childPosition);
        employeeList.remove(temp);
        this.notifyDataSetChanged();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        Employee employee = (Employee) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.employee_row, null);
        }
        TextView sequence = (TextView) view.findViewById(R.id.sequence);
        sequence.setText(employee.getSequence().trim()+ ")");

        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(employee.getName().trim());

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<Employee> employeeList = dayList.get(groupPosition).getEmployeeList();
        return employeeList.size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return dayList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return dayList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {

        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        Day availableList = (Day) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.day_row, null);
        }

        TextView heading = (TextView) view.findViewById(R.id.heading);
        heading.setText(availableList.getName().trim());

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}












