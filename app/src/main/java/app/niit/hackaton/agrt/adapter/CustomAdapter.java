package app.niit.hackaton.agrt.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import app.niit.hackaton.agrt.R;
import app.niit.hackaton.agrt.dto.AssetRegister;
import app.niit.hackaton.agrt.ui.LatestUpdatesFragment;

/**
 * Created by ChandraSekharPonugot on 29-09-2016.
 */

public class CustomAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    List<AssetRegister> result;
    Context context;
    LatestUpdatesFragment fragment;
    int[] imageId;

    public CustomAdapter(LatestUpdatesFragment fragment, List<AssetRegister> assets) {
        // TODO Auto-generated constructor stub
        result = assets;
        context = fragment.getContext();
        fragment = fragment;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.asset_list_updates, null);
        holder.tv = (TextView) rowView.findViewById(R.id.assetName);
        holder.tv.setText(result.get(position).getAsset().getAssetName());
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssetRegister item = result.get(position);
                AlertDialog alertDialog = new AlertDialog.Builder(fragment.getActivity()).create();
                // Setting Dialog Title
                alertDialog.setTitle("Asset Info");
                StringBuffer str = new StringBuffer();
                str.append("ASSET OWNER:" + item.getAsset().getAssetOwner());
                str.append("TAGGED LOCATION:" + item.getLocation());
                // Setting Dialog Message
                alertDialog.setMessage(str);
                // Setting Icon to Dialog
                alertDialog.setIcon(R.mipmap.asset_geo_tag);
                // Setting OK Button
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                // Showing Alert Message
                alertDialog.show();
            }
        });
        return rowView;
    }

    public class Holder {
        TextView tv;
    }

}