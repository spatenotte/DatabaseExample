package sampa.com.databaseexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DataAdapter extends ArrayAdapter<Data> {

    LayoutInflater inflater = LayoutInflater.from(getContext());

    public DataAdapter(Context context, List<Data> objects) {
        super(context, -1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem viewHolder;

        Data data = getItem(position);

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.item_view, parent, false);
            viewHolder = new ViewHolderItem();
            viewHolder.nameItemView = (TextView) convertView.findViewById(R.id.nameItemView);
            viewHolder.ageItemView = (TextView) convertView.findViewById(R.id.ageItemView);
            convertView.setTag(viewHolder);
        }

        else {
            viewHolder = (ViewHolderItem) convertView.getTag();
        }

        viewHolder.nameItemView.setText(data.getName());
        viewHolder.ageItemView.setText(String.valueOf(data.getAge()));

        return convertView;
    }

    static class ViewHolderItem {
        TextView nameItemView;
        TextView ageItemView;
    }
}
