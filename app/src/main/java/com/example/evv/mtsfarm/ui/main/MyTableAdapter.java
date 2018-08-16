package com.example.evv.mtsfarm.ui.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evv.mtsfarm.data.Cow;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;

public class MyTableAdapter extends TableDataAdapter<Cow> {
    private final int TEXT_SIZE = 14;

    public MyTableAdapter(Context context, List<Cow> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        Cow cow = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderView(String.valueOf(cow.id));
                break;
            case 1:
                renderedView = renderView(cow.name);
                break;
            case 2:
                renderedView = renderView(String.valueOf(cow.herd));
                break;
        }
        return renderedView;
    }

    private View renderView(String text) {
        final TextView textView = new TextView(getContext());
        textView.setText(text);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }
}
