package com.example.evv.mtsfarm.ui.detail.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.evv.mtsfarm.data.Milking;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;

public class MilkingTableAdapter extends TableDataAdapter<Milking> {

    private final int TEXT_SIZE = 14;
    Context mContext;
    private final String DATE = "date";
    private final String LITERS = "LITERS";

    public MilkingTableAdapter(Context context, List<Milking> data) {
        super(context, data);
        mContext = context;
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        Milking milking = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderView(milking.date, DATE, milking);
                break;
            case 1:
                renderedView = renderView(String.valueOf(milking.litters), LITERS, milking);
                break;
        }
        return renderedView;
    }

    private EditText renderView(String text, String field, Milking milking) {
        final EditText editText = new EditText(getContext());
        editText.setText(text);
        editText.setPadding(20, 10, 20, 10);
        editText.setTextSize(TEXT_SIZE);
        final ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(30, 30); // Width , height
        editText.setLayoutParams(lparams);

        switch (field) {
            case DATE:
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case LITERS:
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
        }


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                switch (field) {
                    case DATE:
                        milking.date = s.toString();
                        break;
                    case LITERS:
                        milking.litters = s.toString().equals("") ? 0 : Integer.valueOf(s.toString());
                        break;
                }
            }
        });

        return editText;
    }
}
