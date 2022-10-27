package com.example.todo_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class todoAdapter extends ArrayAdapter<todo> {
    public todoAdapter(Context context, List<todo> todos){
        super(context, 0, todos );

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        todo todo = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo_cell,parent, false);
        TextView title =  convertView.findViewById(R.id.cellTitle);
        TextView desc =  convertView.findViewById(R.id.cellDesc);

        title.setText(todo.getTitle());
        desc.setText(todo.getDescription());
        return convertView;
    }

}
