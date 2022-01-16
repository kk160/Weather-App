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
import de.hdmstuttgart.weatherapp.models.Day;
import de.hdmstuttgart.weatherapp.viewmodels.DayViewModel;
import de.hdmstuttgart.weatherapp.viewmodels.HourViewModel;

/**
 * Adapter for the RecyclerView in DayFragment
 * */
public class DayListAdapter extends RecyclerView.Adapter<DayListAdapter.ViewHolder> {

    private final List<Day> dayList;
    private Context context;

    public DayListAdapter(Context context, List<Day> dayList){
        this.dayList = dayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.days_list_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.mItem = dayList.get(position);
        try {
            holder.dayNameView.setText(dayList.get(position).getDayName());
            holder.dayNumberView.setText(dayList.get(position).getDayNumber());
            holder.monthView.setText(dayList.get(position).getMonth());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView dayNameView;
        public final TextView dayNumberView;
        public final TextView monthView;
        public Day mItem;
        DayViewModel dayViewModel;
        HourViewModel hourViewModel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            dayNameView = itemView.findViewById(R.id.dayNameTextView);
            dayNumberView = itemView.findViewById(R.id.dayNumberTextView);
            monthView = itemView.findViewById(R.id.monthTextView);
            dayViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(DayViewModel.class);
            hourViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(HourViewModel.class);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dayViewModel.select(mItem.getDateId());
                    hourViewModel.select(mItem.getDateId());
                }
            });
        }
    }
}
