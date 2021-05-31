package com.peicheva.bmi_calculator_1098;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.peicheva.bmi_calculator_1098.helper.*;

public class BMIHistory extends Fragment {
    com.peicheva.bmi_calculator_1098.helper.BmiDatatable BmiDatatable;
    Cursor cursor;
    Button btnDelete;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history,container,false);
        btnDelete = v.findViewById(R.id.btnDelete);

    try {

        BmiDatatable = new BmiDatatable(getActivity());
        BmiDatatable.openDB();
        cursor = BmiDatatable.getAllRecords();

        ListView listHistory = v.findViewById(R.id.listview);
        CustomAdapter customAdapter = new CustomAdapter();
        listHistory.setAdapter(customAdapter);

        BmiDatatable.closeDB();

    }

    catch (Exception e)
    {
     Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();

    }

    btnDelete.setOnClickListener((view) -> {
        {
            try {
                BmiDatatable = new BmiDatatable(getActivity());
                BmiDatatable.openDB();
                cursor = BmiDatatable.clearAllRecords();
                cursor = BmiDatatable.getAllRecords();

                ListView listHistory = v.findViewById(R.id.listview);
                CustomAdapter customAdapter = new CustomAdapter();
                listHistory.setAdapter(customAdapter);

                BmiDatatable.closeDB();
                Toast.makeText(getActivity(),"Delete Successful",Toast.LENGTH_SHORT).show();
            }
            catch (Exception x)
            {
                Toast.makeText(getActivity(),"Delete Failed",Toast.LENGTH_SHORT).show();
            }

        }

    });

        return v;

    }


public class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return cursor.getCount() ;
        }

        @Override
        public Object getItem(int position) {
            return cursor;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Inflate the layout за всеки ред от списъка.
            if (convertView == null) {
                convertView = getLayoutInflater().
                        inflate(R.layout.custom_layout, parent, false);
            }

            // Gets the TextView за елементите.
            //TextView txt_id = convertView.findViewById(R.id.txtBmiId); // Това е Primary Key в база данни,
            // който не го показваме като резултат на екрана.
            TextView txt_date = convertView.findViewById(R.id.txtBmiDate);
            TextView txt_weight = convertView.findViewById(R.id.txtBmiWeight);
            TextView txt_height = convertView.findViewById(R.id.txtBmiHeight);
            TextView txt_value = convertView.findViewById(R.id.txtBmiValue);
            TextView txt_type = convertView.findViewById(R.id.txtBmiType);

            cursor.moveToPosition(position);

            // Sets текстът от елементите.
            //txt_id.setText(cursor.getString(0)); // Primary Key в база данни.
            txt_date.setText(cursor.getString(1));
            txt_weight.setText(cursor.getString(2));
            txt_height.setText(cursor.getString(3));
            txt_value.setText(cursor.getString(4));
            txt_type.setText(cursor.getString(5));

            return convertView;
        }
    }
}


