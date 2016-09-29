package com.example.omnissiah.minimanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 17/04/2016.
 */
public class AdapterListR extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<DayPageR> dayListR;


    public AdapterListR(Context context, ArrayList<DayPageR> dayListR){
        this.context = context;
        this.dayListR = dayListR;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<EmployeeListR> employeeListRR = dayListR.get(groupPosition).getEmployeeListRR();
        return employeeListRR.get(childPosition);
    }
    public void removeChild(int groupPosition, int childPosition){
        ArrayList<EmployeeListR> employeeListRR = dayListR.get(groupPosition).getEmployeeListRR();
        EmployeeListR temp =  employeeListRR.get(childPosition);
        employeeListRR.remove(temp);
        this.notifyDataSetChanged();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        EmployeeListR employee = (EmployeeListR) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.child_employee, null);
        }
        TextView sequence = (TextView) view.findViewById(R.id.sequence);
        sequence.setText(employee.getSequence().trim()+ ")");

        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(employee.getName().trim());

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<EmployeeListR> employeeListRR = dayListR.get(groupPosition).getEmployeeListRR();
        return employeeListRR.size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return dayListR.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return dayListR.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        DayPageR availableList = (DayPageR) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.parent_day, null);
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

    public void remove(String employee) {
        dayListR.remove(employee);
        notifyDataSetChanged();
    }
}












