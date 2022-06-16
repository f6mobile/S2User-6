package com.example.masterdemowear.screens.rooms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.masterdemowear.R;
import com.example.masterdemowear.common.Items;
import com.example.masterdemowear.databinding.RoomHolderBinding;

import java.util.List;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.RoomHolder> {

    List<Items> items;
    Context context;

    public RoomsAdapter(List<Items> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public RoomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.room_holder, parent, false);
        return new RoomHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomHolder holder, int position) {

        holder.bind(items.get(position));

        holder.binding.getRoot().setOnClickListener(view -> {
            Navigation.findNavController(holder.binding.getRoot()).navigate(R.id.action_roomsFeagment_to_devicesFragment);
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RoomHolder extends RecyclerView.ViewHolder {

        RoomHolderBinding binding;

        public RoomHolder(@NonNull View itemView) {
            super(itemView);
            binding = RoomHolderBinding.bind(itemView);
        }

        public void bind(Items items) {
            binding.roomName.setText(items.getName());
        }
    }
}
