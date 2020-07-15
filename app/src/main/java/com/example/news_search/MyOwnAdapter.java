package com.example.news_search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.BaseAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;



public class MyOwnAdapter extends RecyclerView.Adapter<MyOwnAdapter.MyOwnHolder> implements Filterable {


    Context ctx;
    ArrayList<NewsData> items1;
    ArrayList<NewsData> itemsAll;
    LayoutInflater inflater;


    public  MyOwnAdapter(Context ct, ArrayList<NewsData> items) {
        ctx = ct;
        inflater = LayoutInflater.from(ct);

           this.itemsAll.addAll(items);
    }


    @Override
    public MyOwnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInflator = LayoutInflater.from((Context) ctx);
        View myview = myInflator.inflate(R.layout.my_row, parent, false);
        return new MyOwnHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOwnAdapter.MyOwnHolder holder, int position) {
        final NewsData Item = items1.get(position);
        String title = Item.getString();
        String url = Item.getUrl();
        holder.t1.setText(title);
        ImageView image = holder.geti();
        Picasso.with(ctx).load(url).into(image);
    }

    @Override
    public int getItemCount() {
        return items1.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public class MyOwnHolder extends RecyclerView.ViewHolder {

        TextView t1;
        public final ImageView i1;

        public MyOwnHolder(@NonNull View itemView) {
            super(itemView);
            t1 = (TextView) itemView.findViewById(R.id.text1);
            i1 = (ImageView) itemView.findViewById(R.id.myimage);
            Object onBindViewHolder;

        }

        public ImageView geti() {
            return i1;
        }


    }


    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        items1.clear();
        if (charText.length() == 0) {
            items1.addAll(itemsAll);
        } else {
            for (NewsData wp : items1) {
                if (wp.getString().toLowerCase().contains(charText.toLowerCase())) {
                    items1.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
//    public Filter getFilter() {
//        return filter;
//    }
//
//    Filter filter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence charSequence) {
//
//            ArrayList<NewsData> filteredItems = new ArrayList<>();
//
//            if (charSequence.toString().isEmpty()) {
//                filteredItems.addAll(itemsAll);
//
//            } else {
//                for (NewsData news1 : itemsAll) {
//
//                    if (news1.getString().toLowerCase().contains(charSequence.toString().toLowerCase())) {
//                        filteredItems.add(news1);
//                    }
//                }
//            }
//            FilterResults filterResults = new FilterResults();
//            filterResults.values = filteredItems;
//            return filterResults;
//        }

//
//        @Override
//        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//            items1.clear();
//            items1.addAll((Collection<? extends NewsData>) filterResults.values);
//            notifyDataSetChanged();
//        }
//    };
}