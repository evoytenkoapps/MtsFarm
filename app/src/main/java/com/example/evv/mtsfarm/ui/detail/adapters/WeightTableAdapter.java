package com.example.evv.mtsfarm.ui.detail.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.evv.mtsfarm.data.Milking;
import com.example.evv.mtsfarm.data.Weight;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;

public class WeightTableAdapter extends TableDataAdapter<Weight> {


    private final int TEXT_SIZE = 14;
    Context mContext;
    private final String DATE = "date";
    private final String WEIGHT = "WEIGHT";

    public WeightTableAdapter(Context context, List<Weight> data) {
        super(context, data);
        mContext = context;
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        Weight weight = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderView(weight.date, DATE, weight);
                break;
            case 1:
                renderedView = renderView(String.valueOf(weight.weight), WEIGHT, weight);
                break;
        }
        return renderedView;
    }

    private EditText renderView(String text, String field, Weight weight) {
        final EditText editText = new EditText(getContext());
        editText.setText(text);
        editText.setPadding(20, 10, 20, 10);
        editText.setTextSize(TEXT_SIZE);
        switch (field) {
            case DATE:
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case WEIGHT:
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
                        weight.date = s.toString();
                        break;
                    case WEIGHT:
                        weight.weight = s.toString().equals("") ? 0 : Integer.valueOf(s.toString());
                        break;
                }
            }
        });

        return editText;
    }
}
