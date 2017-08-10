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
            SingletonSearchResult.getInstance().setMember_baptism_data(data.get(getAdapterPosition()).getMember_baptism_data());
            SingletonSearchResult.getInstance().setMember_birthdate(data.get(getAdapterPosition()).getMember_birthdate());
            SingletonSearchResult.getInstance().setMember_discription_in_family(data.get(getAdapterPosition()).getMember_discription_in_family());
            SingletonSearchResult.getInstance().setMember_facebook_link(data.get(getAdapterPosition()).getMember_facebook_link());
            SingletonSearchResult.getInstance().setMember_flat_no(data.get(getAdapterPosition()).getMember_flat_no());
            SingletonSearchResult.getInstance().setMember_floor_no(data.get(getAdapterPosition()).getMember_floor_no());
            SingletonSearchResult.getInstance().setMember_graduation_year(data.get(getAdapterPosition()).getMember_graduation_year());
            SingletonSearchResult.getInstance().setMember_job(data.get(getAdapterPosition()).getMember_job());
            SingletonSearchResult.getInstance().setMember_mobile_1(data.get(getAdapterPosition()).getMember_mobile_1());
            SingletonSearchResult.getInstance().setMember_mobile_2(data.get(getAdapterPosition()).getMember_mobile_2());
            SingletonSearchResult.getInstance().setMember_phone_1(data.get(getAdapterPosition()).getMember_phone_1());
            SingletonSearchResult.getInstance().setMember_phone_2(data.get(getAdapterPosition()).getMember_phone_2());
            SingletonSearchResult.getInstance().setMember_national_id(data.get(getAdapterPosition()).getMember_national_id());
            SingletonSearchResult.getInstance().setMember_name(data.get(getAdapterPosition()).getMember_name());
            SingletonSearchResult.getInstance().setMember_qualification(data.get(getAdapterPosition()).getMember_qualification());
            SingletonSearchResult.getInstance().setMember_social_status(data.get(getAdapterPosition()).getMember_social_status());
            SingletonSearchResult.getInstance().setMember_street(data.get(getAdapterPosition()).getMember_street());
            SingletonSearchResult.getInstance().setNotes(data.get(getAdapterPosition()).getNotes());
            SingletonSearchResult.getInstance().setMember_church(data.get(getAdapterPosition()).getMember_church());
            SingletonSearchResult.getInstance().setMember_area(data.get(getAdapterPosition()).getMember_area());
            SingletonSearchResult.getInstance().setKey(data.get(getAdapterPosition()).getKey());
            SingletonSearchResult.getInstance().setMember_block_no(data.get(getAdapterPosition()).getMember_block_no());
            SingletonSearchResult.getInstance().setMember_mail(data.get(getAdapterPosition()).getMember_mail());
            SingletonSearchResult.getInstance().setMember_marriage_date(data.get(getAdapterPosition()).getMember_marriage_date());


            mContext.startActivity(goToServiceDetails);

        }


    }
}
