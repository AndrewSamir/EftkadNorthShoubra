package com.samir.andrew.eftkadnorthshoubra.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samir.andrew.eftkadnorthshoubra.R;
import com.samir.andrew.eftkadnorthshoubra.models.newMember.ModelMember;
import com.samir.andrew.eftkadnorthshoubra.singleton.SingletonSearchResult;
import com.samir.andrew.eftkadnorthshoubra.utlities.DataEnum;
import com.samir.andrew.eftkadnorthshoubra.views.activities.EnterNewMember;

import java.util.List;

/**
 * Created by lenovo on 5/3/2016.
 */
public class AdapterSearchResult extends RecyclerView.Adapter<AdapterSearchResult.ViewHolder> {


    public List<ModelMember> data;
    private Activity mContext;


    public AdapterSearchResult(List<ModelMember> data, Activity mContext) {
        this.data = data;
        this.mContext = mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_search_resault, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.tvRvItemSearchResultName.setText(data.get(position).getMember_name());
        holder.tvRvItemSearchResultBlockNumber.setText(data.get(position).getMember_block_no());
        holder.tvRvItemSearchResultStreet.setText(data.get(position).getMember_street());


    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(String string) {
        insert(string, data.size());
    }

    public void insert(String string, int position) {
        //data.add(position, string);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        int size = data.size();
        data.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addAll(List<ModelMember> items) {
        int startIndex = data.size();
        data.addAll(items);
        notifyItemRangeInserted(startIndex, items.size());
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvRvItemSearchResultName, tvRvItemSearchResultBlockNumber,tvRvItemSearchResultStreet;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvRvItemSearchResultName = (TextView) itemView.findViewById(R.id.tvRvItemSearchResultName);
            tvRvItemSearchResultBlockNumber = (TextView) itemView.findViewById(R.id.tvRvItemSearchResultBlockNumber);
            tvRvItemSearchResultStreet = (TextView) itemView.findViewById(R.id.tvRvItemSearchResultStreet);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Intent goToServiceDetails = new Intent(mContext, EnterNewMember.class);
            goToServiceDetails.putExtra("from", DataEnum.viewItem.name());

            SingletonSearchResult.getInstance().setFather(data.get(getAdapterPosition()).getFather());


            mContext.startActivity(goToServiceDetails);

        }


    }
}
