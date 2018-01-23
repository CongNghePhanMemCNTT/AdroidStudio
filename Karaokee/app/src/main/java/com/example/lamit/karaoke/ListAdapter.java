package com.example.lamit.karaoke;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LamIT on 22/01/2018.
 */

//public class ListAdapter extends ArrayAdapter<KaraItem>{
//    private List<KaraItem> mangKaraItem;
//    private Context context;
//    public ListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<KaraItem> objects) {
//        super(context, resource, objects);
//    }
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View v=convertView;
//        if(v==null){
//            LayoutInflater vi;
//            vi=LayoutInflater.from(getContext());
//            v=vi.inflate(R.layout.item_kara ,null);
//        }
//        KaraItem p=getItem(position);
//        if (v!=null) {
//            TextView tvmabh=(TextView) v.findViewById(R.id.tvMaBaiHat);
//            tvmabh.setText(String.valueOf(p.MaBaiHat));
//            TextView tvtenbh=(TextView) v.findViewById(R.id.tvTenBaiHat);
//            tvtenbh.setText(p.TenBaiHat);
//            TextView tvloibh=(TextView) v.findViewById(R.id.tvLoiBaiHat);
//            tvloibh.setText(p.LoiBaiHat);
//        }
//        return v;
//    }
//    public void setfilter(List<KaraItem> listitem) {
//        mangKaraItem=new ArrayList<>();
//        mangKaraItem.addAll(listitem);
//        notifyDataSetChanged();
//    }


public class ListAdapter extends BaseAdapter implements Filterable {

    public Context context;
    public ArrayList<KaraItem> employeeArrayList;
    public ArrayList<KaraItem> orig;

    public ListAdapter(Context context, ArrayList<KaraItem> employeeArrayList) {
        super();
        this.context = context;
        this.employeeArrayList = employeeArrayList;
    }


    public class EmployeeHolder
    {
        TextView mabaihat;
        TextView tenbaihat;
        TextView loibaihat;
    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<KaraItem> results = new ArrayList<KaraItem>();
                if (orig == null)
                    orig = employeeArrayList;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final KaraItem g : orig) {
                            if (g.getTenBaiHat().toLowerCase()
                                    .contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                employeeArrayList = (ArrayList<KaraItem>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return employeeArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return employeeArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EmployeeHolder holder;
        if(convertView==null)
        {
            convertView=LayoutInflater.from(context).inflate(R.layout.item_kara, parent, false);
            holder=new EmployeeHolder();
            holder.mabaihat=(TextView) convertView.findViewById(R.id.tvMaBaiHat);
            holder.tenbaihat=(TextView) convertView.findViewById(R.id.tvTenBaiHat);
            holder.loibaihat=(TextView) convertView.findViewById(R.id.tvLoiBaiHat);
            convertView.setTag(holder);
        }
        else
        {
            holder=(EmployeeHolder) convertView.getTag();
        }

        holder.mabaihat.setText(employeeArrayList.get(position).getMaBaiHat());
        holder.tenbaihat.setText(employeeArrayList.get(position).getTenBaiHat());
        holder.loibaihat.setText(employeeArrayList.get(position).getTenBaiHat());

        return convertView;

    }
    public void setfilter(List<KaraItem> listitem) {
        employeeArrayList=new ArrayList<>();
        employeeArrayList.addAll(listitem);
        notifyDataSetChanged();
    }

}

//}
