package de.hdmstuttgart.weatherapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.util.List;

import de.hdmstuttgart.weatherapp.R;
import de.hdmstuttgart.weatherapp.models.Hour;
import de.hdmstuttgart.weatherapp.viewmodels.HourViewModel;

/**
 * Adapter for the RecyclerView in HoursFragment
 * */
public class HourListAdapter extends RecyclerView.Adapter<HourListAdapter.VH> {

    private List<Hour> hours;
    private final Context context;
    public HourListAdapter(Context context, List<Hour> hours){
        this.context = context;
        this.hours = hours;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hours_list_item_view, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.mItem = hours.get(position);
        try {
            holder.hourView.setText(hours.get(position).getHour());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tempView.setText(hours.get(position).getTemp() + " °C");
    }

    @Override
    public int getItemCount() {
        return hours.size();
    }

    public class VH extends RecyclerView.ViewHolder {

        public final View mView;
        public TextView hourView;
        public TextView tempView;
        public Hour mItem;
        HourViewModel hourViewModel;

        public VH(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            hourView = itemView.findViewById(R.id.hourTextView);
            tempView = itemView.findViewById(R.id.temperatureTextView);
            hourViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(HourViewModel.class);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hourViewModel.select(mItem.getDateId());
                }
            });
        }
    }
}
