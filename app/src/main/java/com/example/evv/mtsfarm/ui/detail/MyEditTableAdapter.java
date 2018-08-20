package com.example.evv.mtsfarm.ui.detail;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.evv.mtsfarm.App;
import com.example.evv.mtsfarm.data.Cow;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;

public class MyEditTableAdapter extends TableDataAdapter<Cow> {
    private final int TEXT_SIZE = 14;
    Context mContext;

    public MyEditTableAdapter(Context context, List<Cow> data) {
        super(context, data);
        mContext = context;
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

    private EditText renderView(String text) {
        final EditText editText = new EditText(getContext());
        editText.setText(text);
        editText.setPadding(20, 10, 20, 10);
        editText.setTextSize(TEXT_SIZE);
        return editText;
    }
}
